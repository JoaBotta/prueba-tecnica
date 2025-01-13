package com.joa.springboot.DetalleVentaBarra;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.joa.springboot.Producto.Producto;
import com.joa.springboot.Producto.ProductoRepository;

@Service
public class DetalleVentaBarraService {

    private final DetalleVentaBarraRepository detalleVentaBarraRepository;
    private final ProductoRepository productoRepository;

    // Constructor injection
    public DetalleVentaBarraService(DetalleVentaBarraRepository detalleVentaBarraRepository, ProductoRepository productoRepository) {
        this.detalleVentaBarraRepository = detalleVentaBarraRepository;
        this.productoRepository = productoRepository;
    }

    public DetalleVentaBarraResponseDTO createDetalleVentaBarra(DetalleVentaBarraRequestDTO requestDTO) {
        Producto producto = productoRepository.findById(requestDTO.getproductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + requestDTO.getproductoId()));

        // Usar el constructor parametrizado para mayor claridad
        DetalleVentaBarra detalleVentaBarra = new DetalleVentaBarra(null, producto, requestDTO.getCantidad());

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

    public void deleteDetalleVentaBarra(Long id) {
        detalleVentaBarraRepository.deleteById(id); // Elimina directamente; JPA lanzará una excepción si no existe.
    }
}
