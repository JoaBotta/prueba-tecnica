package com.joa.springboot.HistorialAsistencia;

import com.joa.springboot.Estado.Estado;
import com.joa.springboot.Estado.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial-asistencia")
@CrossOrigin(origins = "*")
public class HistorialAsistenciaController {

    @Autowired
    private HistorialAsistenciaRepository historialAsistenciaRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    // Crear un nuevo registro de asistencia relacionado a un estado
    @PostMapping
    public HistorialAsistencia crearHistorialAsistencia(@RequestParam Long estadoId) {
        Estado estado = estadoRepository.findById(estadoId)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        HistorialAsistencia historial = new HistorialAsistencia();
        historial.setEstado(estado);

        return historialAsistenciaRepository.save(historial);
    }

    // Obtener todos los registros de historial
    @GetMapping
    public List<HistorialAsistencia> obtenerTodos() {
        return historialAsistenciaRepository.findAll();
    }
}
