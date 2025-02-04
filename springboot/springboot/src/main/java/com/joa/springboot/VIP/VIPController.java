package com.joa.springboot.VIP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vip")
public class VIPController {

    @Autowired
    private VIPService VIPService;

    // Crear VIP
    @PostMapping
    public ResponseEntity<VIPResponseDTO> crearVIP(@RequestBody VIPRequestDTO requestDTO) {
        return ResponseEntity.ok(VIPService.crearVIP(requestDTO));
    }

    // Listar todas las VIPs
    @GetMapping
    public ResponseEntity<List<VIPResponseDTO>> listarVIP() {
        return ResponseEntity.ok(VIPService.listarVIP());
    }

    // Obtener una VIP por ID
    @GetMapping("/{id}")
    public ResponseEntity<VIPResponseDTO> obtenerVIPPorId(@PathVariable Long id) {
        return ResponseEntity.ok(VIPService.obtenerVIPPorId(id));
    }

    // Actualizar una VIP
    @PutMapping("/{id}")
    public ResponseEntity<VIPResponseDTO> actualizarVIP(
            @PathVariable Long id,
            @RequestBody VIPRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(VIPService.actualizarVIP(id, requestDTO));
    }

    // Eliminar una VIP
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVIP(@PathVariable Long id) {
        VIPService.eliminarVIP(id);
        return ResponseEntity.noContent().build();
    }
}
