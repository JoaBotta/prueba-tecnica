package com.joa.springboot.Barra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarraService {

    @Autowired
    private BarraRepository barraRepository;

    public BarraResponseDTO createBarra(BarraRequestDTO requestDTO) {
        Barra barra = new Barra(requestDTO.getNombre());
        barra = barraRepository.save(barra);
        return new BarraResponseDTO(barra.getId(), barra.getNombre(), barra.getCantidadVentas(), barra.getGanancias());
    }

    public List<BarraResponseDTO> getAllBarras() {
        return barraRepository.findAll().stream()
                .map(barra -> new BarraResponseDTO(barra.getId(), barra.getNombre(), barra.getCantidadVentas(), barra.getGanancias()))
                .collect(Collectors.toList());
    }

    public BarraResponseDTO getBarraById(Long id) {
        Barra barra = barraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barra no encontrada con ID: " + id));
        return new BarraResponseDTO(barra.getId(), barra.getNombre(), barra.getCantidadVentas(), barra.getGanancias());
    }

    public BarraResponseDTO updateBarra(Long id, BarraRequestDTO requestDTO) {
        Barra barra = barraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barra no encontrada con ID: " + id));
        barra.setNombre(requestDTO.getNombre());
        barra = barraRepository.save(barra);
        return new BarraResponseDTO(barra.getId(), barra.getNombre(), barra.getCantidadVentas(), barra.getGanancias());
    }

    public void deleteBarra(Long id) {
        barraRepository.deleteById(id);
    }
}
