package com.joa.springboot.HistorialAsistencia;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/historial-asistencia")
public class HistorialAsistenciaController {

    private final HistorialAsistenciaService historialAsistenciaService;
    private final HistorialAsistenciaMapper mapper;

    @Autowired
    public HistorialAsistenciaController(HistorialAsistenciaService historialAsistenciaService, HistorialAsistenciaMapper mapper) {
        this.historialAsistenciaService = historialAsistenciaService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<HistorialAsistenciaDTO> getAllHistoriales() {
        return historialAsistenciaService.getAllHistoriales().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialAsistenciaDTO> getHistorialById(@PathVariable Long id) {
        Optional<HistorialAsistencia> historialAsistencia = historialAsistenciaService.getHistorialById(id);
        return historialAsistencia.map(h -> ResponseEntity.ok(mapper.toDTO(h)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HistorialAsistenciaDTO> createHistorial(@RequestBody HistorialAsistenciaDTO historialAsistenciaDTO) {
        HistorialAsistencia historialAsistencia = mapper.toEntity(historialAsistenciaDTO);
        HistorialAsistencia savedHistorial = historialAsistenciaService.saveHistorial(historialAsistencia);
        return new ResponseEntity<>(mapper.toDTO(savedHistorial), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistorial(@PathVariable Long id) {
        historialAsistenciaService.deleteHistorial(id);
        return ResponseEntity.noContent().build();
    }
}