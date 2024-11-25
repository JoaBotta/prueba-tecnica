package com.joa.springboot.Planta;

import com.joa.springboot.Planta.Planta;
import com.joa.springboot.Planta.PlantaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantaService {
    private final PlantaRepository plantaRepository;

    public PlantaService(PlantaRepository plantaRepository) {
        this.plantaRepository = plantaRepository;
    }

    // Obtener todas las plantas y sus datos
    public List<Planta> obtenerPlantas() {
        return plantaRepository.findAll();
    }

    // Crear una nueva planta
    public Planta crearPlanta(Planta planta) {
        return plantaRepository.save(planta);
    }

    // Obtener una planta por ID
    public Optional<Planta> obtenerPlantaPorId(Long id) {
        return plantaRepository.findById(id);
    }

    // Editar una planta
    public Planta editarPlanta(Long id, Planta plantaActualizada) {
        return plantaRepository.findById(id).map(planta -> {
            planta.setNombre(plantaActualizada.getNombre());
            planta.setPais(plantaActualizada.getPais());
            planta.setLecturasOk(plantaActualizada.getLecturasOk());
            planta.setAlertasMedias(plantaActualizada.getAlertasMedias());
            planta.setAlertasRojas(plantaActualizada.getAlertasRojas());
            planta.setSensoresDeshabilitados(plantaActualizada.getSensoresDeshabilitados());
            return plantaRepository.save(planta);
        }).orElseThrow(() -> new RuntimeException("Planta no encontrada"));
    }

    // Eliminar una planta
    public void eliminarPlanta(Long id) {
        plantaRepository.deleteById(id);
    }
}