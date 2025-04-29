package com.joa.springboot.DetalleVentaFisica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaFisicaRepository extends JpaRepository<DetalleVentaFisica, Long> {
    List<DetalleVentaFisica> findByVentaEntradaId(Long VentaEntradaId);
}
