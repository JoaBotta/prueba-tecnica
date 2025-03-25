package com.joa.springboot.DetalleVentaEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/detalle-venta-entrada")
@CrossOrigin("*")
public class DetalleVentaEntradaController {

    @Autowired
    private DetalleVentaEntradaService detalleVentaEntradaService;

    @PostMapping
    public ResponseEntity<DetalleVentaEntradaResponseDTO> crearDetalle(@RequestBody DetalleVentaEntradaRequestDTO dto) {
        DetalleVentaEntradaResponseDTO detalle = detalleVentaEntradaService.crearDetalleVentaEntrada(null, dto);
        return ResponseEntity.ok(detalle);
    }
}