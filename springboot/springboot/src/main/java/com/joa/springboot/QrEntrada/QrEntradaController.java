package com.joa.springboot.QrEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/qrentrada")
public class QrEntradaController {

    @Autowired
    private QrEntradaService qrEntradaService;

    @PostMapping
    public ResponseEntity<QrEntradaResponseDTO> crearQrEntrada(@Valid @RequestBody QrEntradaRequestDTO requestDTO) {
        return ResponseEntity.ok(qrEntradaService.crearQrEntrada(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<QrEntradaResponseDTO>> listarQrEntrada() {
        return ResponseEntity.ok(qrEntradaService.listarQrEntrada());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QrEntradaResponseDTO> obtenerQrEntradaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(qrEntradaService.obtenerQrEntradaPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarQrEntrada(@PathVariable Long id) {
        qrEntradaService.eliminarQrEntrada(id);
        return ResponseEntity.noContent().build();
    }
}
