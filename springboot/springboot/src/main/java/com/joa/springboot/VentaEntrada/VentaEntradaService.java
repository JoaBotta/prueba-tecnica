package com.joa.springboot.VentaEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import com.joa.springboot.PuntoDeVenta.PuntoDeVenta;
import com.joa.springboot.PuntoDeVenta.PuntoDeVentaRepository;
import com.joa.springboot.Usuario.Usuario;
import com.joa.springboot.Usuario.UsuarioRepository;
import com.joa.springboot.FormaDePago.FormaDePago;
import com.joa.springboot.FormaDePago.FormaDePagoRepository;
import com.joa.springboot.Entrada.Entrada;
import com.joa.springboot.Entrada.EntradaRepository;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntrada;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntradaRequestDTO;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntradaResponseDTO;

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

    public VentaEntradaResponseDTO createVentaEntrada(VentaEntradaRequestDTO requestDTO) {
        PuntoDeVenta puntoDeVenta = puntoDeVentaRepository.findById(requestDTO.getPuntoVentaId())
                .orElseThrow(() -> new RuntimeException("Punto de Venta no encontrado"));

        Usuario empleadoVentas = usuarioRepository.findById(requestDTO.getEmpleadoVentasId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        FormaDePago formaDePago = formaDePagoRepository.findById(requestDTO.getFormaDePagoId())
                .orElseThrow(() -> new RuntimeException("Forma de Pago no encontrada"));

        VentaEntrada ventaEntrada = new VentaEntrada(puntoDeVenta, empleadoVentas, formaDePago);
        ventaEntrada.setFecha(LocalDateTime.now());

        List<DetalleVentaEntrada> detalles = new ArrayList<>();
        boolean tieneEntradaVip = false;

        for (DetalleVentaEntradaRequestDTO detalleDTO : requestDTO.getDetalleVentaEntrada()) {
            Entrada entrada = entradaRepository.findById(detalleDTO.getEntradaId())
                    .orElseThrow(() -> new RuntimeException("Entrada no encontrada con ID: " + detalleDTO.getEntradaId()));

            if (entrada.getNombre().toLowerCase().contains("VIP")) {
                tieneEntradaVip = true;
            }

            DetalleVentaEntrada detalle = new DetalleVentaEntrada(
                    ventaEntrada, 
                    entrada, 
                    null,  // No es un QR, entonces dejamos esto en null
                    detalleDTO.getCantidad()
            );
            detalles.add(detalle);
        }

        ventaEntrada.setDetalleVentaEntrada(detalles);
        ventaEntrada.calcularTotal();

        if (tieneEntradaVip) {
            ventaEntrada.setNombreComprador(requestDTO.getNombreComprador());
            ventaEntrada.setCorreoElectronico(requestDTO.getCorreoElectronico());
            ventaEntrada.setTelefono(requestDTO.getTelefono());
        }

        ventaEntrada = ventaEntradaRepository.save(ventaEntrada);
        return mapToDTO(ventaEntrada);
    }

    public List<VentaEntradaResponseDTO> getAllVentasEntrada() {
        return ventaEntradaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public VentaEntradaResponseDTO getVentaEntradaById(Long id) {
        return ventaEntradaRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Venta de entrada no encontrada"));
    }

    public List<VentaEntradaResponseDTO> getVentasByPuntoDeVenta(Long puntoVentaId) {
        return ventaEntradaRepository.findByPuntoDeVentaId(puntoVentaId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void deleteVentaEntrada(Long id) {
        ventaEntradaRepository.deleteById(id);
    }

    private VentaEntradaResponseDTO mapToDTO(VentaEntrada ventaEntrada) {
        return new VentaEntradaResponseDTO(
                ventaEntrada.getId(),
                ventaEntrada.getPuntoDeVenta().getNombre(),
                ventaEntrada.getEmpleadoVentas().getUsername(),
                ventaEntrada.getFormaDePago().getNombre(),
                ventaEntrada.getTotal(),
                ventaEntrada.getFecha(),
                ventaEntrada.getDetalleVentaEntrada().stream()
                        .map(detalle -> new DetalleVentaEntradaResponseDTO(
                                detalle.getId(),
                                detalle.getEntrada().getNombre(),
                                detalle.getCantidad(),
                                detalle.getSubTotal()
                        ))
                        .collect(Collectors.toList()),
                ventaEntrada.getNombreComprador(),
                ventaEntrada.getCorreoElectronico(),
                ventaEntrada.getTelefono()
        );
    }
}
