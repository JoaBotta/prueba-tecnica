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

    public BarraResponseDTO createBarra(BarraRequestDTO requestDTO) {
        // Buscar el boliche asociado
        Boliche boliche = bolicheRepository.findById(requestDTO.getBolicheId())
                .orElseThrow(() -> new RuntimeException("Boliche no encontrado con ID: " + requestDTO.getBolicheId()));

        // Crear una nueva barra asociada al boliche
        Barra barra = new Barra(requestDTO.getNombre(), boliche);
        barra = barraRepository.save(barra);

        return new BarraResponseDTO(barra.getId(), barra.getNombre(),
                barra.getCantidadVentas(), barra.getGanancias(), boliche.getNombre());
    }

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

    public BarraResponseDTO updateBarra(Long id, BarraRequestDTO requestDTO) {
        // Buscar la barra existente
        Barra barra = barraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barra no encontrada con ID: " + id));

        // Buscar el boliche asociado (opcional)
        Boliche boliche = bolicheRepository.findById(requestDTO.getBolicheId())
                .orElseThrow(() -> new RuntimeException("Boliche no encontrado con ID: " + requestDTO.getBolicheId()));

        // Actualizar los valores
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

    public void deleteBarra(Long id) {
        barraRepository.deleteById(id);
    }
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
