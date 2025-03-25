package com.joa.springboot.EntradaOnline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntradaOnlineService {

    @Autowired
    private EntradaOnlineRepository EntradaOnlineRepository;

    // Crear una nueva EntradaOnline
    public EntradaOnlineResponseDTO crearEntradaOnline(EntradaOnlineRequestDTO requestDTO) {
        EntradaOnline EntradaOnline = new EntradaOnline(requestDTO.getNombre(), requestDTO.getPrecio());
        EntradaOnline = EntradaOnlineRepository.save(EntradaOnline);
        return mapToDTO(EntradaOnline);
    }

    // Listar todas las EntradaOnlines
    public List<EntradaOnlineResponseDTO> listarEntradaOnline() {
        return EntradaOnlineRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Obtener una EntradaOnline por ID
    public EntradaOnlineResponseDTO obtenerEntradaOnlinePorId(Long id) {
        EntradaOnline EntradaOnline = EntradaOnlineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EntradaOnline no encontrada con ID: " + id));
        return mapToDTO(EntradaOnline);
    }

    // Actualizar una EntradaOnline
    public EntradaOnlineResponseDTO actualizarEntradaOnline(Long id, EntradaOnlineRequestDTO requestDTO) {
        EntradaOnline EntradaOnline = EntradaOnlineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EntradaOnline no encontrada con ID: " + id));

        EntradaOnline.setNombre(requestDTO.getNombre());
        EntradaOnline.setPrecio(requestDTO.getPrecio());
        EntradaOnline = EntradaOnlineRepository.save(EntradaOnline);

        return mapToDTO(EntradaOnline);
    }

    // Eliminar una EntradaOnline
    public void eliminarEntradaOnline(Long id) {
        EntradaOnlineRepository.deleteById(id);
    }

    // Método para mapear `EntradaOnline` a `EntradaOnlineResponseDTO`
    private EntradaOnlineResponseDTO mapToDTO(EntradaOnline EntradaOnline) {
        return new EntradaOnlineResponseDTO(EntradaOnline.getId(), EntradaOnline.getNombre(), EntradaOnline.getPrecio());
    }
}
