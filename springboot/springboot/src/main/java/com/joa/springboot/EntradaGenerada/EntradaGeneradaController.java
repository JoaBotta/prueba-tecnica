package com.joa.springboot.EntradaGenerada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entrada-generada")
@CrossOrigin("*")
public class EntradaGeneradaController {

    @Autowired
    private EntradaGeneradaService entradaGeneradaService;

    @PostMapping
    public ResponseEntity<EntradaGeneradaResponseDTO> crearEntrada(@RequestBody EntradaGeneradaRequestDTO dto) {
        EntradaGeneradaResponseDTO entrada = entradaGeneradaService.crearEntradaGenerada(dto);
        return ResponseEntity.ok(entrada);
    }

    @PostMapping("/validar/{qrCode}")
    public ResponseEntity<EntradaGeneradaResponseDTO> validarEntrada(@PathVariable String qrCode) {
        EntradaGeneradaResponseDTO entradaValidada = entradaGeneradaService.validarEntradaPorQR(qrCode);
        return ResponseEntity.ok(entradaValidada);
    }
}
