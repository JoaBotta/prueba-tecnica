package com.joa.springboot.Servicios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    List<Servicio> findByBolicheId(Long bolicheId);

}
