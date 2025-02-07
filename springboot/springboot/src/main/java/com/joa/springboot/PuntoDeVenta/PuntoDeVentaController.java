package com.joa.springboot.PuntoDeVenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/puntos-de-venta")
public class PuntoDeVentaController {

    @Autowired
    private PuntoDeVentaService puntoDeVentaService;

    @PostMapping
    public ResponseEntity<PuntoDeVentaResponseDTO> createPuntoDeVenta(@RequestBody PuntoDeVentaRequestDTO requestDTO) {
        PuntoDeVentaResponseDTO responseDTO = puntoDeVentaService.createPuntoDeVenta(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PuntoDeVentaResponseDTO>> getAllPuntosDeVenta() {
        return ResponseEntity.ok(puntoDeVentaService.getAllPuntosDeVenta());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuntoDeVentaResponseDTO> getPuntoDeVentaById(@PathVariable Long id) {
        return ResponseEntity.ok(puntoDeVentaService.getPuntoDeVentaById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePuntoDeVenta(@PathVariable Long id) {
        puntoDeVentaService.deletePuntoDeVenta(id);
        return ResponseEntity.noContent().build();
    }

    // un controlador para que me muestre todos los puntos de venta de ese boliche
    @GetMapping("/boliche/{id}")
    public ResponseEntity<List<PuntoDeVentaResponseDTO>> getPuntosDeVentaByBolicheId(@PathVariable Long id) {
        return ResponseEntity.ok(puntoDeVentaService.getPuntosDeVentaByBoliche(id));
    }
}
