package com.joa.springboot.Evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // ✅ Crear un evento
    @PostMapping
    public ResponseEntity<EventoResponseDTO> createEvento(@RequestBody EventoRequestDTO requestDTO) {
        EventoResponseDTO evento = eventoService.createEvento(requestDTO);
        return ResponseEntity.ok(evento);
    }

    // ✅ Obtener todos los eventos con el formato correcto
    @GetMapping
    public ResponseEntity<List<EventoResponseDTO>> getAllEventos() {
        List<EventoResponseDTO> eventos = eventoService.getAllEventos();
        return ResponseEntity.ok(eventos);
    }

    // ✅ Obtener un evento por ID con el formato correcto
    @GetMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> getEventoById(@PathVariable Long id) {
        EventoResponseDTO evento = eventoService.getEventoById(id);
        return ResponseEntity.ok(evento);
    }

    // ✅ Actualizar un evento
    @PutMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> updateEvento(@PathVariable Long id, @RequestBody EventoRequestDTO requestDTO) {
        EventoResponseDTO eventoActualizado = eventoService.updateEvento(id, requestDTO);
        return ResponseEntity.ok(eventoActualizado);
    }

    // ✅ Eliminar un evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }
}
