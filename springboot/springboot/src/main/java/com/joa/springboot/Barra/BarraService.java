package com.joa.springboot.Barra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joa.springboot.Boliche.Boliche;
import com.joa.springboot.Boliche.BolicheRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarraService {

    @Autowired
    private BarraRepository barraRepository;

    @Autowired
    private BolicheRepository bolicheRepository;

    // ✅ Crear una barra
    public BarraResponseDTO createBarra(BarraRequestDTO requestDTO) {
        Boliche boliche = bolicheRepository.findById(requestDTO.getBolicheId())
                .orElseThrow(() -> new RuntimeException("Boliche no encontrado con ID: " + requestDTO.getBolicheId()));

        Barra barra = new Barra(requestDTO.getNombre(), boliche);
        barra = barraRepository.save(barra);

        return new BarraResponseDTO(
                barra.getId(), 
                barra.getNombre(),
                barra.getCantidadVentas(), 
                barra.getGanancias(), 
                boliche.getNombre());
    }

    // ✅ Obtener todas las barras
    public List<BarraResponseDTO> getAllBarras() {
        return barraRepository.findAll().stream()
                .map(barra -> new BarraResponseDTO(
                        barra.getId(),
                        barra.getNombre(),
                        barra.getCantidadVentas(),
                        barra.getGanancias(),
                        barra.getBoliche() != null ? barra.getBoliche().getNombre() : null))
                .collect(Collectors.toList());
    }

    // ✅ Obtener una barra por ID
    public BarraResponseDTO getBarraById(Long id) {
        Barra barra = barraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barra no encontrada con ID: " + id));

        return new BarraResponseDTO(
                barra.getId(),
                barra.getNombre(),
                barra.getCantidadVentas(),
                barra.getGanancias(),
                barra.getBoliche() != null ? barra.getBoliche().getNombre() : null);
    }

    // ✅ Modificar una barra
    public BarraResponseDTO updateBarra(Long id, BarraRequestDTO requestDTO) {
        Barra barra = barraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barra no encontrada con ID: " + id));

        Boliche boliche = bolicheRepository.findById(requestDTO.getBolicheId())
                .orElseThrow(() -> new RuntimeException("Boliche no encontrado con ID: " + requestDTO.getBolicheId()));

        // Actualizar valores
        barra.setNombre(requestDTO.getNombre());
        barra.setBoliche(boliche);
        barra = barraRepository.save(barra);

        return new BarraResponseDTO(
                barra.getId(),
                barra.getNombre(),
                barra.getCantidadVentas(),
                barra.getGanancias(),
                boliche.getNombre());
    }

    // ✅ Eliminar una barra
    public void deleteBarra(Long id) {
        Barra barra = barraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barra no encontrada con ID: " + id));
        
        barraRepository.delete(barra);
    }

    // ✅ Obtener todas las barras de un boliche específico
    public List<BarraResponseDTO> getBarrasByBolicheId(Long bolicheId) {
        return barraRepository.findByBolicheId(bolicheId).stream()
                .map(barra -> new BarraResponseDTO(
                        barra.getId(),
                        barra.getNombre(),
                        barra.getCantidadVentas(),
                        barra.getGanancias(),
                        barra.getBoliche() != null ? barra.getBoliche().getNombre() : null))
                .collect(Collectors.toList());
    }
}
