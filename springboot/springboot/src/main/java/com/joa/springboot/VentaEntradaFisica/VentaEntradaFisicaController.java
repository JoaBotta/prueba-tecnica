package com.joa.springboot.VentaEntradaFisica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Venta_Entrada_Fisica")
@CrossOrigin("*")
public class VentaEntradaFisicaController {

    @Autowired
    private VentaEntradaFisicaService VentaEntradaService;

    @PostMapping
    public ResponseEntity<VentaEntradaFisicaResponseDTO> crearVentaFisica(@RequestBody VentaEntradaFisicaRequestDTO dto) {
        VentaEntradaFisicaResponseDTO nuevaVenta = VentaEntradaService.crearVentaEntrada(dto);
        return ResponseEntity.ok(nuevaVenta);
    }
    // 🔹 Nuevo: Obtener todas las ventas físicas
    @GetMapping
    public ResponseEntity<List<VentaEntradaFisicaResponseDTO>> obtenerTodasLasVentasFisicas() {
        List<VentaEntradaFisicaResponseDTO> ventas = VentaEntradaService.obtenerTodas();
        return ResponseEntity.ok(ventas);
    }

    // 🔹 Nuevo: Obtener una venta física por ID
    @GetMapping("/{id}")
    public ResponseEntity<VentaEntradaFisicaResponseDTO> obtenerVentaFisicaPorId(@PathVariable Long id) {
        VentaEntradaFisicaResponseDTO venta = VentaEntradaService.obtenerPorId(id);
        return ResponseEntity.ok(venta);
    }
}
