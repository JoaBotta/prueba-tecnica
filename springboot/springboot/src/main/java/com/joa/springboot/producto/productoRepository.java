package com.joa.springboot.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productoRepository extends JpaRepository<producto, Long> {
    // Métodos personalizados si es necesario
}
