package com.joa.springboot.EntradaFisica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntradaFisicaService {

    @Autowired
    private EntradaFisicaRepository entradaRepository;

    // Crear una nueva entrada
    public EntradaFisicaResponseDTO crearEntrada(EntradaFisicaRequestDTO requestDTO) {
        EntradaFisica entrada = new EntradaFisica(requestDTO.getNombre(), requestDTO.getPrecio());
        entrada = entradaRepository.save(entrada);
        return mapToDTO(entrada);
    }

    // Listar todas las entradas
    public List<EntradaFisicaResponseDTO> listarEntrada() {
        return entradaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Obtener una entrada por ID
    public EntradaFisicaResponseDTO obtenerEntradaPorId(Long id) {
        EntradaFisica entrada = entradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrada no encontrada con ID: " + id));
        return mapToDTO(entrada);
    }

    // Actualizar una entrada
    public EntradaFisicaResponseDTO actualizarEntrada(Long id, EntradaFisicaRequestDTO requestDTO) {
        EntradaFisica entrada = entradaRepository.findById(id)
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
    private EntradaFisicaResponseDTO mapToDTO(EntradaFisica entrada) {
        return new EntradaFisicaResponseDTO(entrada.getId(), entrada.getNombre(), entrada.getPrecio());
    }
}
