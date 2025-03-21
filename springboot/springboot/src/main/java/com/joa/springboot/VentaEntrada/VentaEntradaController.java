package com.joa.springboot.VentaEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venta-entrada")
@CrossOrigin("*")
public class VentaEntradaController {

    @Autowired
    private VentaEntradaService ventaEntradaService;

    @GetMapping
    public List<VentaEntrada> listarTodas() {
        return ventaEntradaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public VentaEntrada obtenerPorId(@PathVariable Long id) {
        return ventaEntradaService.buscarPorId(id);
    }
}
