package com.joa.springboot.PuntoDeVenta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PuntoDeVentaRepository extends JpaRepository<PuntoDeVenta, Long> {
    List<PuntoDeVenta> findByBolicheId(Long bolicheId);
}
