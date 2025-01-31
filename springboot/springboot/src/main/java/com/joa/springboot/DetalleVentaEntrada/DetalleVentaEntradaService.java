package com.joa.springboot.DetalleVentaEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joa.springboot.VentaEntrada.VentaEntrada;
import com.joa.springboot.VentaEntrada.VentaEntradaRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleVentaEntradaService {

    @Autowired
    private DetalleVentaEntradaRepository detalleVentaEntradaRepository;

    @Autowired
    private VentaEntradaRepository ventaEntradaRepository;

    // ✅ Crear un detalle de venta de entrada
    public DetalleVentaEntradaResponseDTO createDetalleVentaEntrada(Long ventaEntradaId, DetalleVentaEntradaRequestDTO requestDTO) {
        VentaEntrada ventaEntrada = ventaEntradaRepository.findById(ventaEntradaId)
                .orElseThrow(() -> new RuntimeException("Venta de entrada no encontrada con ID: " + ventaEntradaId));

        DetalleVentaEntrada detalle = new DetalleVentaEntrada(
                ventaEntrada, 
                requestDTO.getCantidad(), 
                requestDTO.getPrecioUnitario()
        );

        detalle = detalleVentaEntradaRepository.save(detalle);

        return new DetalleVentaEntradaResponseDTO(
                detalle.getId(),
                detalle.getCantidad(),
                detalle.getSubTotal()
        );
    }

    // ✅ Obtener detalles de una venta de entrada específica
    public List<DetalleVentaEntradaResponseDTO> getDetallesByVentaEntrada(Long ventaEntradaId) {
        return detalleVentaEntradaRepository.findByVentaEntradaId(ventaEntradaId).stream()
                .map(detalle -> new DetalleVentaEntradaResponseDTO(
                        detalle.getId(),
                        detalle.getCantidad(),
                        detalle.getSubTotal()))
                .collect(Collectors.toList());
    }

    // ✅ Eliminar un detalle de venta de entrada
    public void deleteDetalleVentaEntrada(Long id) {
        detalleVentaEntradaRepository.deleteById(id);
    }
}
