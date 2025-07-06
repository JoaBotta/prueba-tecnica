package com.joa.springboot.QR;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EntradaQRRepository extends JpaRepository<EntradaQR, Long> {
    List<EntradaQR> findByDetalleVentaEntradaId(Long detalleId);
}
