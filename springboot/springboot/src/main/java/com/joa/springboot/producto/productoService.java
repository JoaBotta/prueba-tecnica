package com.joa.springboot.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productoService {
    @Autowired
    private productoRepository productoRepository;

    public producto crearProducto(producto producto) {
        return productoRepository.save(producto);
    }

    public List<producto> listarProductos() {
        return productoRepository.findAll();
    }

    public producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public producto actualizarProducto(Long id, producto productoActualizado) {
        producto producto = obtenerProductoPorId(id);
        producto.setNombre(productoActualizado.getNombre());
        producto.setPrecioUnitario(productoActualizado.getPrecioUnitario());
        producto.setDescripcion(productoActualizado.getDescripcion());
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
