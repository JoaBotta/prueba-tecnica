package com.joa.springboot.Estado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
@CrossOrigin(origins = "*")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    // Obtener todos los estados
    @GetMapping
    public List<Estado> obtenerTodos() {
        return estadoService.obtenerTodos();
    }

    // Obtener un estado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estado> obtenerPorId(@PathVariable Long id) {
        return estadoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo estado
    @PostMapping
    public Estado crearEstado(@RequestBody Estado estado) {
        return estadoService.crearEstado(estado);
    }

    // Actualizar un estado existente
    @PutMapping("/{id}")
    public Estado actualizarEstado(@PathVariable Long id, @RequestBody Estado estado) {
        return estadoService.actualizarEstado(id, estado);
    }

    // Eliminar un estado
    @DeleteMapping("/{id}")
    public void eliminarEstado(@PathVariable Long id) {
        estadoService.eliminarEstado(id);
    }
}
