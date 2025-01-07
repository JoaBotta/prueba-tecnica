package com.joa.springboot.Barra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarraRepository extends JpaRepository<Barra, Long> {
    // Métodos personalizados si es necesario
}
