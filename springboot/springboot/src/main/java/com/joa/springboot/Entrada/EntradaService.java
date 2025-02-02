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

        return new EntradaResponseDTO(entrada.getId(), entrada.getNombre(), entrada.getPrecio());
    }

    // Listar todas las entradas
    public List<EntradaResponseDTO> listarEntrada() {
        return entradaRepository.findAll().stream()
                .map(entrada -> new EntradaResponseDTO(entrada.getId(), entrada.getNombre(), entrada.getPrecio()))
                .collect(Collectors.toList());
    }

    // Obtener una entrada por ID
    public EntradaResponseDTO obtenerEntradaPorId(Long id) {
        Entrada entrada = entradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrada no encontrada con ID: " + id));

        return new EntradaResponseDTO(entrada.getId(), entrada.getNombre(), entrada.getPrecio());
    }

    // Actualizar una entrada
    public EntradaResponseDTO actualizarEntrada(Long id, EntradaRequestDTO requestDTO) {
        Entrada entrada = entradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrada no encontrada con ID: " + id));

        entrada.setNombre(requestDTO.getNombre());
        entrada.setPrecio(requestDTO.getPrecio());

        entrada = entradaRepository.save(entrada);

        return new EntradaResponseDTO(entrada.getId(), entrada.getNombre(), entrada.getPrecio());
    }

    // Eliminar una entrada
    public void eliminarEntrada(Long id) {
        entradaRepository.deleteById(id);
    }
}
