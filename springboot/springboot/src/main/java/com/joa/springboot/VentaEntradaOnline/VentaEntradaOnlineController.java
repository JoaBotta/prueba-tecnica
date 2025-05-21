package com.joa.springboot.VentaEntradaOnline;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/venta-entrada/online")
@CrossOrigin("*")
public class VentaEntradaOnlineController {

    @Autowired
    private VentaEntradaOnlineService ventaEntradaOnlineService;

    @PostMapping
    public ResponseEntity<VentaEntradaOnlineResponseDTO> crearVentaOnline(@RequestBody VentaEntradaOnlineRequestDTO dto) {
        VentaEntradaOnlineResponseDTO nuevaVenta = ventaEntradaOnlineService.crearVentaEntradaOnline(dto);
        return ResponseEntity.ok(nuevaVenta);
    }
    // 🔹 Nuevo: Obtener todas las ventas online
    @GetMapping
    public ResponseEntity<List<VentaEntradaOnlineResponseDTO>> obtenerTodasLasVentasOnline() {
        List<VentaEntradaOnlineResponseDTO> ventas = ventaEntradaOnlineService.obtenerTodas();
        return ResponseEntity.ok(ventas);
    }

    // 🔹 Nuevo: Obtener una venta online por ID
    @GetMapping("/{id}")
    public ResponseEntity<VentaEntradaOnlineResponseDTO> obtenerVentaOnlinePorId(@PathVariable Long id) {
        VentaEntradaOnlineResponseDTO venta = ventaEntradaOnlineService.obtenerPorId(id);
        return ResponseEntity.ok(venta);
    }

    // eliminar una venta online por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVentaOnline(@PathVariable Long id) {
        ventaEntradaOnlineService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
    
}
