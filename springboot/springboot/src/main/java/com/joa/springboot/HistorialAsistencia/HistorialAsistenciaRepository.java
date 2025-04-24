package com.joa.springboot.HistorialAsistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialAsistenciaRepository extends JpaRepository<HistorialAsistencia, Long> {
}