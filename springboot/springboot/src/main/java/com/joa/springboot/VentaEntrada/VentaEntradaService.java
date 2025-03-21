package com.joa.springboot.VentaEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joa.springboot.PuntoDeVenta.PuntoDeVenta;
import com.joa.springboot.PuntoDeVenta.PuntoDeVentaRepository;
import com.joa.springboot.Usuario.Usuario;
import com.joa.springboot.Usuario.UsuarioRepository;
import com.joa.springboot.FormaDePago.FormaDePago;
import com.joa.springboot.FormaDePago.FormaDePagoRepository;
import com.joa.springboot.Entrada.Entrada;
import com.joa.springboot.Entrada.EntradaRepository;
import com.joa.springboot.QrEntrada.QrEntrada;
import com.joa.springboot.QrEntrada.QrEntradaRepository;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntrada;
import com.joa.springboot.DetalleVentaQrEntrada.DetalleVentaQrEntrada;
import com.joa.springboot.DetalleVentaQrEntrada.DetalleVentaQrEntradaRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class VentaEntradaService {

    @Autowired
    private VentaEntradaRepository ventaEntradaRepository;

    @Autowired
    private PuntoDeVentaRepository puntoDeVentaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FormaDePagoRepository formaDePagoRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private QrEntradaRepository qrEntradaRepository;

    @Autowired
    private DetalleVentaQrEntradaRepository detalleVentaQrEntradaRepository;

    // ✅ Crear una nueva venta de entrada (Normal o QR)
    public VentaEntradaResponseDTO createVentaEntrada(VentaEntradaRequestDTO requestDTO) {
        PuntoDeVenta puntoDeVenta = puntoDeVentaRepository.findById(requestDTO.getPuntoVentaId())
                .orElseThrow(() -> new RuntimeException("Punto de Venta no encontrado"));

        Usuario empleadoVentas = usuarioRepository.findById(requestDTO.getEmpleadoVentasId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        FormaDePago formaDePago = formaDePagoRepository.findById(requestDTO.getFormaDePagoId())
                .orElseThrow(() -> new RuntimeException("Forma de Pago no encontrada"));

        VentaEntrada ventaEntrada = new VentaEntrada(puntoDeVenta, empleadoVentas, formaDePago);

        if (requestDTO.getEntradaId() != null) {
            Entrada entrada = entradaRepository.findById(requestDTO.getEntradaId())
                    .orElseThrow(() -> new RuntimeException("Entrada no encontrada"));

            DetalleVentaEntrada detalle = new DetalleVentaEntrada(ventaEntrada, entrada, requestDTO.getCantidad());
            ventaEntrada.setDetalleVentaEntrada(detalle);

        } else if (requestDTO.getQrEntradaId() != null) {
            QrEntrada qrEntrada = qrEntradaRepository.findById(requestDTO.getQrEntradaId())
                    .orElseThrow(() -> new RuntimeException("QrEntrada no encontrada"));

            // ✅ Generar códigos QR
            List<String> codigosQr = IntStream.range(0, requestDTO.getCantidad())
                    .mapToObj(i -> UUID.randomUUID().toString())
                    .collect(Collectors.toList());

            DetalleVentaQrEntrada detalle = new DetalleVentaQrEntrada(ventaEntrada, qrEntrada, requestDTO.getCantidad(), codigosQr);
            detalleVentaQrEntradaRepository.save(detalle);

            ventaEntrada.setDetalleVentaQrEntrada(detalle);
            ventaEntrada.actualizarDatosComprador(requestDTO.getNombreComprador(), requestDTO.getCorreoElectronico(), requestDTO.getTelefono());
        }

        ventaEntradaRepository.save(ventaEntrada);
        return mapToDTO(ventaEntrada);
    }

    // ✅ Obtener todas las ventas de entrada
    public List<VentaEntradaResponseDTO> getAllVentasEntrada() {
        return ventaEntradaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Obtener una venta de entrada por ID
    public VentaEntradaResponseDTO getVentaEntradaById(Long id) {
        VentaEntrada ventaEntrada = ventaEntradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta de entrada no encontrada con ID: " + id));

        return mapToDTO(ventaEntrada);
    }

    // ✅ Eliminar una venta de entrada por ID
    public void deleteVentaEntrada(Long id) {
        if (!ventaEntradaRepository.existsById(id)) {
            throw new RuntimeException("Venta de entrada con ID " + id + " no existe.");
        }
        ventaEntradaRepository.deleteById(id);
    }

    // ✅ Mapear VentaEntrada a VentaEntradaResponseDTO
    private VentaEntradaResponseDTO mapToDTO(VentaEntrada ventaEntrada) {
        boolean esQr = ventaEntrada.getDetalleVentaQrEntrada() != null;
        String tipoEntrada = esQr ? "QR" : "Normal";
        String entradaNombre = esQr ?
                ventaEntrada.getDetalleVentaQrEntrada().getQrEntrada().getNombre() :
                ventaEntrada.getDetalleVentaEntrada().getEntrada().getNombre();

        int cantidad = esQr ?
                ventaEntrada.getDetalleVentaQrEntrada().getCantidad() :
                ventaEntrada.getDetalleVentaEntrada().getCantidad();

        return new VentaEntradaResponseDTO(
                ventaEntrada.getId(),
                ventaEntrada.getPuntoDeVenta().getNombre(),
                ventaEntrada.getEmpleadoVentas().getUsername(),
                ventaEntrada.getFormaDePago().getNombre(),
                ventaEntrada.getTotal(),
                ventaEntrada.getFecha(),
                tipoEntrada,
                entradaNombre,
                cantidad
        );
    }
}
