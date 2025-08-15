package com.joa.springboot.EntradaFisica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/entrada")
public class EntradaFisicaController {

    @Autowired
    private EntradaFisicaService entradaService;

    // Crear entrada
    @PostMapping
    public ResponseEntity<EntradaFisicaResponseDTO> crearEntrada(@Valid @RequestBody EntradaFisicaRequestDTO requestDTO) {
        return ResponseEntity.ok(entradaService.crearEntrada(requestDTO));
    }

    // Listar todas las entradas
    @GetMapping
    public ResponseEntity<List<EntradaFisicaResponseDTO>> listarEntrada() {
        return ResponseEntity.ok(entradaService.listarEntrada());
    }

    // Obtener una entrada por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntradaFisicaResponseDTO> obtenerEntradaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(entradaService.obtenerEntradaPorId(id));
    }

    // Actualizar una entrada
    @PutMapping("/{id}")
    public ResponseEntity<EntradaFisicaResponseDTO> actualizarEntrada(
            @PathVariable Long id,
            @Valid @RequestBody EntradaFisicaRequestDTO requestDTO
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
