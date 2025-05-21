package com.joa.springboot.HistorialAsistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialAsistenciaRepository extends JpaRepository<HistorialAsistencia, Long> {

    long countByClienteIdAndEstadoNombre(Long clienteId, String estadoNombre);

    List<HistorialAsistencia> findByClienteId(Long clienteId);
}