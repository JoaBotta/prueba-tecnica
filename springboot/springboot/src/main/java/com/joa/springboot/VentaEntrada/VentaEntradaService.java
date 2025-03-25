package com.joa.springboot.VentaEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class VentaEntradaService {

    @Autowired
    private VentaEntradaRepository ventaEntradaRepository;

    public VentaEntrada guardar(VentaEntrada ventaEntrada) {
        return ventaEntradaRepository.save(ventaEntrada);
    }

    public List<VentaEntrada> obtenerTodas() {
        return ventaEntradaRepository.findAll();
    }

    public VentaEntrada buscarPorId(Long id) {
        return ventaEntradaRepository.findById(id).orElse(null);
    }
}