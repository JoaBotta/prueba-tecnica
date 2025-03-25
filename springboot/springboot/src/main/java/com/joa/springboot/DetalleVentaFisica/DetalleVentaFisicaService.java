package com.joa.springboot.DetalleVentaFisica;

import com.joa.springboot.Entrada.Entrada;
import com.joa.springboot.Entrada.EntradaRepository;
import com.joa.springboot.VentaEntradaFisica.VentaEntradaFisica;
import com.joa.springboot.VentaEntradaFisica.VentaEntradaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleVentaFisicaService {

    @Autowired
    private DetalleVentaFisicaRepository detalleVentaFisicaRepository;

    @Autowired
    private VentaEntradaFisicaRepository ventaEntradaFisicaRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    // Crear un nuevo detalle de venta física
    public DetalleVentaFisicaResponseDTO crearDetalle(DetalleVentaFisicaRequestDTO dto) {
        VentaEntradaFisica venta = ventaEntradaFisicaRepository.findById(dto.getVentaEntradaFisicaId())
                .orElseThrow(() -> new IllegalArgumentException("Venta Física no encontrada"));

        Entrada entrada = entradaRepository.findById(dto.getEntradaFisicaId())
                .orElseThrow(() -> new IllegalArgumentException("Entrada no encontrada"));

        DetalleVentaFisica detalle = new DetalleVentaFisica();
        detalle.setVentaEntradaFisica(venta);
        detalle.setEntradaFisicaId(entrada);
        detalle.setCantidad(dto.getCantidad());
        detalle.setSubtotal(dto.getCantidad() * entrada.getPrecio().doubleValue());

        detalle = detalleVentaFisicaRepository.save(detalle);

        return new DetalleVentaFisicaResponseDTO(
                detalle.getId(),
                detalle.getEntradaFisicaId().getId(),
                detalle.getCantidad(),
                detalle.getSubtotal()
        );
    }

    // Obtener detalles de una venta física específica
    public List<DetalleVentaFisicaResponseDTO> obtenerDetallesPorVenta(Long ventaId) {
        List<DetalleVentaFisica> detalles = detalleVentaFisicaRepository.findByVentaEntradaFisicaId(ventaId);
        return detalles.stream()
                .map(detalle -> new DetalleVentaFisicaResponseDTO(
                        detalle.getId(),
                        detalle.getEntradaFisicaId().getId(),
                        detalle.getCantidad(),
                        detalle.getSubtotal()
                ))
                .collect(Collectors.toList());
    }

    // Eliminar un detalle de venta física
    public void eliminarDetalle(Long id) {
        detalleVentaFisicaRepository.deleteById(id);
    }
}
