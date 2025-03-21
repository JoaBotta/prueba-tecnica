package com.joa.springboot.DetalleVentaEntrada;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaEntradaRepository extends JpaRepository<DetalleVentaEntrada, Long> {
}