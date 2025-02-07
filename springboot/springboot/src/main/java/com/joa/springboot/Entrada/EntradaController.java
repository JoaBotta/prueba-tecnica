package com.joa.springboot.Entrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/entrada")
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    // Crear entrada
    @PostMapping
    public ResponseEntity<EntradaResponseDTO> crearEntrada(@Valid @RequestBody EntradaRequestDTO requestDTO) {
        return ResponseEntity.ok(entradaService.crearEntrada(requestDTO));
    }

    // Listar todas las entradas
    @GetMapping
    public ResponseEntity<List<EntradaResponseDTO>> listarEntrada() {
        return ResponseEntity.ok(entradaService.listarEntrada());
    }

    // Obtener una entrada por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntradaResponseDTO> obtenerEntradaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(entradaService.obtenerEntradaPorId(id));
    }

    // Actualizar una entrada
    @PutMapping("/{id}")
    public ResponseEntity<EntradaResponseDTO> actualizarEntrada(
            @PathVariable Long id,
            @Valid @RequestBody EntradaRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(entradaService.actualizarEntrada(id, requestDTO));
    }

    // Eliminar una entrada
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntrada(@PathVariable Long id) {
        entradaService.eliminarEntrada(id);
        return ResponseEntity.noContent().build();
    }
}
