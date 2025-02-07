package com.joa.springboot.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public ProductoResponseDTO crearProducto(ProductoRequestDTO productoDTO) {
        Producto producto = new Producto(
            productoDTO.getNombre(),
            productoDTO.getPrecioUnitario(),
            productoDTO.getDescripcion()
        );
        producto = productoRepository.save(producto);
        return mapToDTO(producto);
    }

    public List<ProductoResponseDTO> listarProductos() {
        return productoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ProductoResponseDTO obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return mapToDTO(producto);
    }

    public ProductoResponseDTO actualizarProducto(Long id, ProductoRequestDTO productoDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setNombre(productoDTO.getNombre());
        producto.setPrecioUnitario(productoDTO.getPrecioUnitario());
        producto.setDescripcion(productoDTO.getDescripcion());

        producto = productoRepository.save(producto);
        return mapToDTO(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    private ProductoResponseDTO mapToDTO(Producto producto) {
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecioUnitario(),
                producto.getDescripcion()
        );
    }
}
