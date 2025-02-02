package com.joa.springboot.DetalleVentaEntrada;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleVentaEntradaRepository extends JpaRepository<DetalleVentaEntrada, Long> {
    List<DetalleVentaEntrada> findByVentaEntradaId(Long ventaEntradaId);
    
    @Query("SELECT d FROM DetalleVentaEntrada d WHERE d.ventaEntrada.id = :ventaEntradaId")
    List<DetalleVentaEntrada> findDetallesByVentaEntrada(@Param("ventaBarraId") Long ventaEntradaId);
}
