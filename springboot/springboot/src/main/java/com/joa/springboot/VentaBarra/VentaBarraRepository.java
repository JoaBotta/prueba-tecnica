package com.joa.springboot.VentaBarra;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaBarraRepository extends JpaRepository<VentaBarra, Long> {
    List<VentaBarra> findByBarraId(Long barraId);

}
