package com.joa.springboot.DetalleVentaBarra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleVentaBarraRepository extends JpaRepository<DetalleVentaBarra, Long> {
    
    List<DetalleVentaBarra> findByVentaBarraId(Long ventaBarraId);
    
    @Query("SELECT d FROM DetalleVentaBarra d WHERE d.ventaBarra.id = :ventaBarraId")
    List<DetalleVentaBarra> findDetallesByVentaBarra(@Param("ventaBarraId") Long ventaBarraId);
}
