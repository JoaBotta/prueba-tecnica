package com.joa.springboot.VentaBarra;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VentaBarraRepository extends JpaRepository<VentaBarra, Long> {
    List<VentaBarra> findByBarraId(Long barraId);
    @Query("SELECT v FROM VentaBarra v WHERE v.barra.id = :barraId AND v.fecha BETWEEN :fechaDesde AND :fechaHasta")
    List<VentaBarra> findVentasByBarraAndFecha(@Param("barraId") Long barraId, 
                                               @Param("fechaDesde") LocalDateTime fechaDesde, 
                                               @Param("fechaHasta") LocalDateTime fechaHasta);

}
