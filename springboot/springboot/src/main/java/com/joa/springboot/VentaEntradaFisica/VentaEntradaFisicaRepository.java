package com.joa.springboot.VentaEntradaFisica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaEntradaFisicaRepository extends JpaRepository<VentaEntradaFisica, Long> {
}
