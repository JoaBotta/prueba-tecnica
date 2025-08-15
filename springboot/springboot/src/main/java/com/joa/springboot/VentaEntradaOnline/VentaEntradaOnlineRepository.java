package com.joa.springboot.VentaEntradaOnline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaEntradaOnlineRepository extends JpaRepository<VentaEntradaOnline, Long> {
    @Query("SELECT COUNT(v) FROM VentaEntradaOnline v WHERE v.entradaOnline.id = :entradaId")
    int contarVentasPorEntrada(@Param("entradaId") Long entradaId);
    
    boolean existsByEntradaOnline_Id(Long entradaOnlineId);
}

