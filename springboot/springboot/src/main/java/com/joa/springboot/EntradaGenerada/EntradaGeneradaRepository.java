package com.joa.springboot.EntradaGenerada;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaGeneradaRepository extends JpaRepository<EntradaGenerada, Long> {

    // Buscar por QR Code
    EntradaGenerada findByQrCode(String qrCode);
}
