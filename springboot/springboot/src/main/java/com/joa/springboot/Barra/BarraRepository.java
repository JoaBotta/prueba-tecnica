package com.joa.springboot.Barra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:4200/")
public interface BarraRepository extends JpaRepository<Barra, Long> {
    List<Barra> findByBolicheId(Long bolicheId);
}
