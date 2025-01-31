package com.joa.springboot.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @PostMapping
    public ResponseEntity<ServicioResponseDTO> createServicio(@RequestBody ServicioRequestDTO requestDTO) {
        ServicioResponseDTO responseDTO = servicioService.createServicio(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ServicioResponseDTO>> getAllServicios() {
        List<ServicioResponseDTO> responseDTOs = servicioService.getAllServicios();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioResponseDTO> getServicioById(@PathVariable Long id) {
        ServicioResponseDTO responseDTO = servicioService.getServicioById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioResponseDTO> updateServicio(@PathVariable Long id, @RequestBody ServicioRequestDTO requestDTO) {
        ServicioResponseDTO responseDTO = servicioService.updateServicio(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable Long id) {
        servicioService.deleteServicio(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Obtener todos los servicios de un boliche específico
    @GetMapping("/boliche/{bolicheId}")
    public ResponseEntity<List<ServicioResponseDTO>> getServiciosByBoliche(@PathVariable Long bolicheId) {
        List<ServicioResponseDTO> servicios = servicioService.getServiciosByBoliche(bolicheId);
        return ResponseEntity.ok(servicios);
    }
}
