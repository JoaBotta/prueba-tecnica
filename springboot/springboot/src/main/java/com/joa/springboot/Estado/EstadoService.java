package com.joa.springboot.Estado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    // Listar todos los estados
    public List<Estado> obtenerTodos() {
        return estadoRepository.findAll();
    }

    // Obtener estado por ID
    public Optional<Estado> obtenerPorId(Long id) {
        return estadoRepository.findById(id);
    }

    // Crear un nuevo estado
    public Estado crearEstado(Estado estado) {
        return estadoRepository.save(estado);
    }

    // Actualizar un estado
    public Estado actualizarEstado(Long id, Estado estadoActualizado) {
        Estado estadoExistente = estadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        estadoExistente.setNombre(estadoActualizado.getNombre());
        return estadoRepository.save(estadoExistente);
    }

    // Eliminar un estado
    public void eliminarEstado(Long id) {
        estadoRepository.deleteById(id);
    }
}
