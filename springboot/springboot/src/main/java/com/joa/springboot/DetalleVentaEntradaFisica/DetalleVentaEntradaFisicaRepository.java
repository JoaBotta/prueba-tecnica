package com.joa.springboot.DetalleVentaEntradaFisica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaEntradaFisicaRepository extends JpaRepository<DetalleVentaEntradaFisica, Long> {
    List<DetalleVentaEntradaFisica> findByVentaEntradaId(Long VentaEntradaId);
}
