package com.joa.springboot.VentaEntradaFisica;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/venta-entrada/fisica")
@CrossOrigin("*")
public class VentaEntradaFisicaController {

    @Autowired
    private VentaEntradaFisicaService ventaEntradaFisicaService;

    @PostMapping
    public ResponseEntity<VentaEntradaFisicaResponseDTO> crearVentaFisica(@RequestBody VentaEntradaFisicaRequestDTO dto) {
        VentaEntradaFisicaResponseDTO nuevaVenta = ventaEntradaFisicaService.crearVentaEntradaFisica(dto);
        return ResponseEntity.ok(nuevaVenta);
    }
    // 🔹 Nuevo: Obtener todas las ventas físicas
    @GetMapping
    public ResponseEntity<List<VentaEntradaFisicaResponseDTO>> obtenerTodasLasVentasFisicas() {
        List<VentaEntradaFisicaResponseDTO> ventas = ventaEntradaFisicaService.obtenerTodas();
        return ResponseEntity.ok(ventas);
    }

    // 🔹 Nuevo: Obtener una venta física por ID
    @GetMapping("/{id}")
    public ResponseEntity<VentaEntradaFisicaResponseDTO> obtenerVentaFisicaPorId(@PathVariable Long id) {
        VentaEntradaFisicaResponseDTO venta = ventaEntradaFisicaService.obtenerPorId(id);
        return ResponseEntity.ok(venta);
    }
}
