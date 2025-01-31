package com.joa.springboot.VentaBarra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaBarraRepository extends JpaRepository<VentaBarra, Long> {
    List<VentaBarra> findByBarraId(Long barraId);
}
