package com.joa.springboot.VentaEntrada;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VentaEntradaRepository extends JpaRepository<VentaEntrada, Long> {
    List<VentaEntrada> findByPuntoDeVentaId(Long puntoVentaId);
}
