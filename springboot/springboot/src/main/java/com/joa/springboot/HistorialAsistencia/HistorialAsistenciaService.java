package com.joa.springboot.HistorialAsistencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialAsistenciaService {

    private final HistorialAsistenciaRepository historialAsistenciaRepository;

    @Autowired
    public HistorialAsistenciaService(HistorialAsistenciaRepository historialAsistenciaRepository) {
        this.historialAsistenciaRepository = historialAsistenciaRepository;
    }

    // Obtener todos los historiales de asistencia
    public List<HistorialAsistencia> getAllHistoriales() {
        return historialAsistenciaRepository.findAll();
    }

    // Obtener un historial de asistencia por ID
    public Optional<HistorialAsistencia> getHistorialById(Long id) {
        return historialAsistenciaRepository.findById(id);
    }

    // Guardar un historial de asistencia
    public HistorialAsistencia saveHistorial(HistorialAsistencia historialAsistencia) {
        return historialAsistenciaRepository.save(historialAsistencia);
    }

    // Eliminar un historial de asistencia
    public void deleteHistorial(Long id) {
        historialAsistenciaRepository.deleteById(id);
    }
}