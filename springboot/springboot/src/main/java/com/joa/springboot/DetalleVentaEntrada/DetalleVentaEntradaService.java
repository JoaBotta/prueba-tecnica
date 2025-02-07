package com.joa.springboot.DetalleVentaEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joa.springboot.Entrada.Entrada;
import com.joa.springboot.Entrada.EntradaRepository;
import com.joa.springboot.VentaEntrada.VentaEntrada;
import com.joa.springboot.VentaEntrada.VentaEntradaRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DetalleVentaEntradaService {

    @Autowired
    private DetalleVentaEntradaRepository detalleVentaEntradaRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private VentaEntradaRepository ventaEntradaRepository;

    // ✅ Crear un nuevo detalle de venta de entrada
    public DetalleVentaEntradaResponseDTO createDetalleVentaEntrada(DetalleVentaEntradaRequestDTO requestDTO) {
        VentaEntrada ventaEntrada = ventaEntradaRepository.findById(requestDTO.getEntradaId())
                .orElseThrow(() -> new RuntimeException("VentaEntrada no encontrada con ID: " + requestDTO.getEntradaId()));

        Entrada entrada = entradaRepository.findById(requestDTO.getEntradaId())
                .orElseThrow(() -> new RuntimeException("Entrada no encontrada con ID: " + requestDTO.getEntradaId()));

        List<String> codigosQr = null;

        // 🔹 Si la entrada es VIP, generamos códigos QR
        if (entrada.getNombre().toLowerCase().contains("VIP")) {
            codigosQr = IntStream.range(0, requestDTO.getCantidad())
                    .mapToObj(i -> UUID.randomUUID().toString())
                    .collect(Collectors.toList());
        }

        // Crear el DetalleVentaEntrada y guardarlo en la base de datos
        DetalleVentaEntrada detalleVentaEntrada = new DetalleVentaEntrada(ventaEntrada, entrada, requestDTO.getCantidad());
        detalleVentaEntrada = detalleVentaEntradaRepository.save(detalleVentaEntrada);

        return new DetalleVentaEntradaResponseDTO(
                detalleVentaEntrada.getId(),
                entrada.getNombre(),
                detalleVentaEntrada.getCantidad(),
                detalleVentaEntrada.getSubTotal(),
                codigosQr
        );
    }

    // ✅ Obtener todos los detalles de venta de entrada
    public List<DetalleVentaEntradaResponseDTO> getAllDetallesVentaEntrada() {
        return detalleVentaEntradaRepository.findAll().stream()
                .map(detalle -> new DetalleVentaEntradaResponseDTO(
                        detalle.getId(),
                        detalle.getEntrada().getNombre(),
                        detalle.getCantidad(),
                        detalle.getSubTotal(),
                        detalle.getCodigosQr()))
                .collect(Collectors.toList());
    }

    // ✅ Obtener un detalle de venta por ID
    public DetalleVentaEntradaResponseDTO getDetalleVentaEntradaById(Long id) {
        DetalleVentaEntrada detalle = detalleVentaEntradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de Venta no encontrado con ID: " + id));

        return new DetalleVentaEntradaResponseDTO(
                detalle.getId(),
                detalle.getEntrada().getNombre(),
                detalle.getCantidad(),
                detalle.getSubTotal(),
                detalle.getCodigosQr()
        );
    }

    // ✅ Eliminar un detalle de venta por ID
    public void deleteDetalleVentaEntrada(Long id) {
        if (!detalleVentaEntradaRepository.existsById(id)) {
            throw new RuntimeException("El detalle de venta con ID " + id + " no existe.");
        }
        detalleVentaEntradaRepository.deleteById(id);
    }
}
