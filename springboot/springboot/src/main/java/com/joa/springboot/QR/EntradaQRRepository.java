package com.joa.springboot.QR;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EntradaQRRepository extends JpaRepository<EntradaQR, Long> {

    // 🔹 Nombre exacto del campo + "_Id" para acceder al id
    List<EntradaQR> findByVentaEntradaOnline_Id(Long ventaId);
}
