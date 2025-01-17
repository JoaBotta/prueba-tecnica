package com.joa.springboot.Barra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BarraRepository extends JpaRepository<Barra, Long> {
    List<Barra> findByBolicheId(Long bolicheId);
}
