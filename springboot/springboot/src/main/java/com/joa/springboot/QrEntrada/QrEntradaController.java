package com.joa.springboot.QrEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/qrEntrada")
public class QrEntradaController {

    @Autowired
    private QrEntradaService QrEntradaService;

    // Crear QrEntrada
    @PostMapping
    public ResponseEntity<QrEntradaResponseDTO> crearQrEntrada(@Valid @RequestBody QrEntradaRequestDTO requestDTO) {
        return ResponseEntity.ok(QrEntradaService.crearQrEntrada(requestDTO));
    }

    // Listar todas las QrEntradas
    @GetMapping
    public ResponseEntity<List<QrEntradaResponseDTO>> listarQrEntrada() {
        return ResponseEntity.ok(QrEntradaService.listarQrEntrada());
    }

    // Obtener una QrEntrada por ID
    @GetMapping("/{id}")
    public ResponseEntity<QrEntradaResponseDTO> obtenerQrEntradaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(QrEntradaService.obtenerQrEntradaPorId(id));
    }

    // Actualizar una QrEntrada
    @PutMapping("/{id}")
    public ResponseEntity<QrEntradaResponseDTO> actualizarQrEntrada(
            @PathVariable Long id,
            @Valid @RequestBody QrEntradaRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(QrEntradaService.actualizarQrEntrada(id, requestDTO));
    }

    // Eliminar una QrEntrada
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarQrEntrada(@PathVariable Long id) {
        QrEntradaService.eliminarQrEntrada(id);
        return ResponseEntity.noContent().build();
    }
}
