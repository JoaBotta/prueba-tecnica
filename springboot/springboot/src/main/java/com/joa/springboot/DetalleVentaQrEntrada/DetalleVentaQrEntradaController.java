package com.joa.springboot.DetalleVentaQrEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-venta-qr-entrada")
public class DetalleVentaQrEntradaController {

    @Autowired
    private DetalleVentaQrEntradaService detalleVentaQrEntradaService;

    @PostMapping
    public ResponseEntity<DetalleVentaQrEntradaResponseDTO> createDetalleVentaQrEntrada(@RequestBody DetalleVentaQrEntradaRequestDTO requestDTO) {
        return ResponseEntity.ok(detalleVentaQrEntradaService.createDetalleVentaQrEntrada(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<DetalleVentaQrEntradaResponseDTO>> getAllDetallesVentaQrEntrada() {
        return ResponseEntity.ok(detalleVentaQrEntradaService.getAllDetallesVentaQrEntrada());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaQrEntradaResponseDTO> getDetalleVentaQrEntradaById(@PathVariable Long id) {
        return ResponseEntity.ok(detalleVentaQrEntradaService.getDetalleVentaQrEntradaById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleVentaQrEntrada(@PathVariable Long id) {
        detalleVentaQrEntradaService.deleteDetalleVentaQrEntrada(id);
        return ResponseEntity.noContent().build();
    }
}
