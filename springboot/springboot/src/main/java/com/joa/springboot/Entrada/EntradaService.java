package com.joa.springboot.Entrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;

    // Crear una nueva entrada
    public EntradaResponseDTO crearEntrada(EntradaRequestDTO requestDTO) {
        Entrada entrada = new Entrada(requestDTO.getNombre(), requestDTO.getPrecio());
        entrada = entradaRepository.save(entrada);
        return mapToDTO(entrada);
    }

    // Listar todas las entradas
    public List<EntradaResponseDTO> listarEntrada() {
        return entradaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Obtener una entrada por ID
    public EntradaResponseDTO obtenerEntradaPorId(Long id) {
        Entrada entrada = entradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrada no encontrada con ID: " + id));
        return mapToDTO(entrada);
    }

    // Actualizar una entrada
    public EntradaResponseDTO actualizarEntrada(Long id, EntradaRequestDTO requestDTO) {
        Entrada entrada = entradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrada no encontrada con ID: " + id));

        entrada.setNombre(requestDTO.getNombre());
        entrada.setPrecio(requestDTO.getPrecio());
        entrada = entradaRepository.save(entrada);

        return mapToDTO(entrada);
    }

    // Eliminar una entrada
    public void eliminarEntrada(Long id) {
        entradaRepository.deleteById(id);
    }

    // Método para mapear `Entrada` a `EntradaResponseDTO`
    private EntradaResponseDTO mapToDTO(Entrada entrada) {
        return new EntradaResponseDTO(entrada.getId(), entrada.getNombre(), entrada.getPrecio());
    }
}
