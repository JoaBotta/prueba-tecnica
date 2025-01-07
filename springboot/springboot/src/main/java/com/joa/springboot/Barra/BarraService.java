package com.joa.springboot.Barra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BarraService {
    @Autowired
    private BarraRepository barraRepository;

    public Barra registrarVenta(Barra barra) {
        // Registrar la fecha y hora actual si no se proporciona
        if (barra.getFechaHora() == null) {
            barra.setFechaHora(LocalDateTime.now());
        }
        return barraRepository.save(barra);
    }

    public List<Barra> listarVentas() {
        return barraRepository.findAll();
    }

    public BigDecimal calcularTotalVentas() {
        return barraRepository.findAll().stream()
                .map(barra -> barra.getPrecioUnitario().multiply(BigDecimal.valueOf(barra.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
