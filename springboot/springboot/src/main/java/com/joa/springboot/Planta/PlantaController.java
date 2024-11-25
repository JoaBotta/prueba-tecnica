package com.joa.springboot.Planta;

import com.joa.springboot.Planta.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planta")
@CrossOrigin(origins = "*") // Permitir peticiones desde el frontend
public class PlantaController {
    private final PlantaService plantaService;

    public PlantaController(PlantaService plantaService) {
        this.plantaService = plantaService;
    }

    // Obtener todas las plantas y sus datos
    @GetMapping
    public ResponseEntity<List<Planta>> obtenerPlantas() {
        return ResponseEntity.ok(plantaService.obtenerPlantas());
    }

    // Crear una nueva planta
    @PostMapping
    public ResponseEntity<Planta> crearPlanta(@RequestBody Planta planta) {
        return ResponseEntity.ok(plantaService.crearPlanta(planta));
    }

    // Obtener lecturas OK
    @GetMapping("/lecturas-ok")
    public ResponseEntity<Integer> obtenerLecturasOk() {
        int totalLecturas = plantaService.obtenerPlantas()
                                         .stream()
                                         .mapToInt(Planta::getLecturasOk)
                                         .sum();
        return ResponseEntity.ok(totalLecturas);
    }

    // Obtener alertas medias
    @GetMapping("/alertas-medias")
    public ResponseEntity<Integer> obtenerAlertasMedias() {
        int totalAlertas = plantaService.obtenerPlantas()
                                        .stream()
                                        .mapToInt(Planta::getAlertasMedias)
                                        .sum();
        return ResponseEntity.ok(totalAlertas);
    }

    // Obtener alertas rojas
    @GetMapping("/alertas-rojas")
    public ResponseEntity<Integer> obtenerAlertasRojas() {
        int totalAlertas = plantaService.obtenerPlantas()
                                        .stream()
                                        .mapToInt(Planta::getAlertasRojas)
                                        .sum();
        return ResponseEntity.ok(totalAlertas);
    }

    // Obtener sensores deshabilitados
    @GetMapping("/sensores-deshabilitados")
    public ResponseEntity<Integer> obtenerSensoresDeshabilitados() {
        int totalSensores = plantaService.obtenerPlantas()
                                          .stream()
                                          .mapToInt(Planta::getSensoresDeshabilitados)
                                          .sum();
        return ResponseEntity.ok(totalSensores);
    }

    // Editar una planta
    @PutMapping("/{id}")
    public ResponseEntity<Planta> editarPlanta(@PathVariable Long id, @RequestBody Planta plantaActualizada) {
        return ResponseEntity.ok(plantaService.editarPlanta(id, plantaActualizada));
    }


    // Eliminar una planta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlanta(@PathVariable Long id) {
        plantaService.eliminarPlanta(id);
        return ResponseEntity.noContent().build();
    }
}