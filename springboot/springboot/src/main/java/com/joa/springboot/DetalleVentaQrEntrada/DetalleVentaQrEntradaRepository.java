package com.joa.springboot.DetalleVentaQrEntrada;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetalleVentaQrEntradaRepository extends JpaRepository<DetalleVentaQrEntrada, Long> {
    List<DetalleVentaQrEntrada> findByVentaEntradaId(Long ventaEntradaId);
}
