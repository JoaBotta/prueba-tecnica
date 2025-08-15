package com.joa.springboot.DetalleVentaEntradaFisica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joa.springboot.EntradaFisica.EntradaFisica;
import com.joa.springboot.EntradaFisica.EntradaFisicaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleVentaEntradaFisicaService {

    @Autowired
    private DetalleVentaEntradaFisicaRepository detalleVentaFisicaRepository;

    @Autowired
    private EntradaFisicaRepository entradaRepository;

    // Crear un nuevo detalle de venta física
    public DetalleVentaEntradaFisicaResponseDTO crearDetalle(DetalleVentaEntradaFisicaRequestDTO dto) {
        EntradaFisica entrada = entradaRepository.findById(dto.getEntradaFisicaId())
                .orElseThrow(() -> new IllegalArgumentException("Entrada no encontrada"));

        DetalleVentaEntradaFisica detalle = new DetalleVentaEntradaFisica();
        detalle.setEntradaFisicaId(entrada);
        detalle.setCantidad(dto.getCantidad());
        detalle.setSubtotal(dto.getCantidad() * entrada.getPrecio().doubleValue());

        detalle = detalleVentaFisicaRepository.save(detalle);

        return new DetalleVentaEntradaFisicaResponseDTO(
                detalle.getId(),
                detalle.getEntradaFisicaId().getId(),
                detalle.getCantidad(),
                detalle.getSubtotal()
        );
    }

    // Obtener detalles de una venta física específica
    public List<DetalleVentaEntradaFisicaResponseDTO> obtenerDetallesPorVenta(Long ventaId) {
        List<DetalleVentaEntradaFisica> detalles = detalleVentaFisicaRepository.findByVentaEntradaId(ventaId);
        return detalles.stream()
                .map(detalle -> new DetalleVentaEntradaFisicaResponseDTO(
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
