package com.joa.springboot.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class productoController {
    @Autowired
    private productoService productoService;

    @PostMapping
    public ResponseEntity<producto> crearproducto(@RequestBody producto producto) {
        return ResponseEntity.ok(productoService.crearProducto(producto));
    }

    @GetMapping
    public ResponseEntity<List<producto>> listarproductos() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<producto> obtenerproductoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerProductoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<producto> actualizarproducto(
            @PathVariable Long id,
            @RequestBody producto productoActualizado
    ) {
        return ResponseEntity.ok(productoService.actualizarProducto(id, productoActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
