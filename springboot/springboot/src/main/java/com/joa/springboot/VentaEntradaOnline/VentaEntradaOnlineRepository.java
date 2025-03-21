package com.joa.springboot.VentaEntradaOnline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaEntradaOnlineRepository extends JpaRepository<VentaEntradaOnline, Long> {
}
