package com.joa.springboot.VentaEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Venta_Entrada_Fisica")
@CrossOrigin("*")
public class VentaEntradaController {

    @Autowired
    private VentaEntradaService VentaEntradaService;

    @PostMapping
    public ResponseEntity<VentaEntradaResponseDTO> crearVentaFisica(@RequestBody VentaEntradaRequestDTO dto) {
        VentaEntradaResponseDTO nuevaVenta = VentaEntradaService.crearVentaEntrada(dto);
        return ResponseEntity.ok(nuevaVenta);
    }
    // 🔹 Nuevo: Obtener todas las ventas físicas
    @GetMapping
    public ResponseEntity<List<VentaEntradaResponseDTO>> obtenerTodasLasVentasFisicas() {
        List<VentaEntradaResponseDTO> ventas = VentaEntradaService.obtenerTodas();
        return ResponseEntity.ok(ventas);
    }

    // 🔹 Nuevo: Obtener una venta física por ID
    @GetMapping("/{id}")
    public ResponseEntity<VentaEntradaResponseDTO> obtenerVentaFisicaPorId(@PathVariable Long id) {
        VentaEntradaResponseDTO venta = VentaEntradaService.obtenerPorId(id);
        return ResponseEntity.ok(venta);
    }
}
