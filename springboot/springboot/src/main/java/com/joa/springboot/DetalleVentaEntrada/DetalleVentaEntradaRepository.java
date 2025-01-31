package com.joa.springboot.DetalleVentaEntrada;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetalleVentaEntradaRepository extends JpaRepository<DetalleVentaEntrada, Long> {
    List<DetalleVentaEntrada> findByVentaEntradaId(Long ventaEntradaId);
}
