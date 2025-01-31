package com.joa.springboot.DetalleVentaBarra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joa.springboot.Producto.Producto;
import com.joa.springboot.Producto.ProductoRepository;
import com.joa.springboot.VentaBarra.VentaBarra;
import com.joa.springboot.VentaBarra.VentaBarraRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleVentaBarraService {

    @Autowired
    private DetalleVentaBarraRepository detalleVentaBarraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VentaBarraRepository ventaBarraRepository;

    public DetalleVentaBarraResponseDTO createDetalleVentaBarra(DetalleVentaBarraRequestDTO requestDTO) {
        VentaBarra ventaBarra = ventaBarraRepository.findById(requestDTO.getVentaBarraId())
                .orElseThrow(() -> new RuntimeException("VentaBarra no encontrada con ID: " + requestDTO.getVentaBarraId()));

        Producto producto = productoRepository.findById(requestDTO.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + requestDTO.getProductoId()));

        DetalleVentaBarra detalleVentaBarra = new DetalleVentaBarra(ventaBarra, producto, requestDTO.getCantidad());
        detalleVentaBarra = detalleVentaBarraRepository.save(detalleVentaBarra);

        return new DetalleVentaBarraResponseDTO(
                detalleVentaBarra.getId(),
                producto.getNombre(),
                detalleVentaBarra.getCantidad(),
                detalleVentaBarra.getSubTotal()
        );
    }

    public List<DetalleVentaBarraResponseDTO> getAllDetallesVentaBarra() {
        return detalleVentaBarraRepository.findAll().stream()
                .map(detalle -> new DetalleVentaBarraResponseDTO(
                        detalle.getId(),
                        detalle.getProducto().getNombre(),
                        detalle.getCantidad(),
                        detalle.getSubTotal()))
                .collect(Collectors.toList());
    }

    public DetalleVentaBarraResponseDTO getDetalleVentaBarraById(Long id) {
        DetalleVentaBarra detalle = detalleVentaBarraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de Venta no encontrado con ID: " + id));

        return new DetalleVentaBarraResponseDTO(
                detalle.getId(),
                detalle.getProducto().getNombre(),
                detalle.getCantidad(),
                detalle.getSubTotal()
        );
    }

    public void deleteDetalleVentaBarra(Long id) {
        detalleVentaBarraRepository.deleteById(id);
    }

    public List<DetalleVentaBarraResponseDTO> getDetallesByVentaBarra(Long ventaBarraId) {
        return detalleVentaBarraRepository.findDetallesByVentaBarra(ventaBarraId).stream()
                .map(detalle -> new DetalleVentaBarraResponseDTO(
                        detalle.getId(),
                        detalle.getProducto().getNombre(),
                        detalle.getCantidad(),
                        detalle.getSubTotal()))
                .collect(Collectors.toList());
    }
}
