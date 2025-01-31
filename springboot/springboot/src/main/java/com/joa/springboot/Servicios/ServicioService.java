package com.joa.springboot.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joa.springboot.Boliche.Boliche;
import com.joa.springboot.Boliche.BolicheRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private BolicheRepository bolicheRepository;

    // ✅ Crear un servicio asociado a un boliche
    public ServicioResponseDTO createServicio(ServicioRequestDTO requestDTO) {
        Boliche boliche = bolicheRepository.findById(requestDTO.getBolicheId())
                .orElseThrow(() -> new RuntimeException("Boliche no encontrado con ID: " + requestDTO.getBolicheId()));

        Servicio servicio = new Servicio(
                requestDTO.getNombre(),
                requestDTO.getPrecio(),
                requestDTO.getDescripcion(),
                boliche
        );
        servicio = servicioRepository.save(servicio);

        return mapToDTO(servicio);
    }

    // ✅ Obtener todos los servicios
    public List<ServicioResponseDTO> getAllServicios() {
        return servicioRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Obtener un servicio por su ID
    public ServicioResponseDTO getServicioById(Long id) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + id));

        return mapToDTO(servicio);
    }

    // ✅ Actualizar un servicio
    public ServicioResponseDTO updateServicio(Long id, ServicioRequestDTO requestDTO) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + id));

        Boliche boliche = bolicheRepository.findById(requestDTO.getBolicheId())
                .orElseThrow(() -> new RuntimeException("Boliche no encontrado con ID: " + requestDTO.getBolicheId()));

        servicio.setNombre(requestDTO.getNombre());
        servicio.setPrecio(requestDTO.getPrecio());
        servicio.setDescripcion(requestDTO.getDescripcion());
        servicio.setBoliche(boliche);

        servicio = servicioRepository.save(servicio);

        return mapToDTO(servicio);
    }

    // ✅ Eliminar un servicio
    public void deleteServicio(Long id) {
        servicioRepository.deleteById(id);
    }

    // ✅ Obtener todos los servicios de un boliche
    public List<ServicioResponseDTO> getServiciosByBoliche(Long bolicheId) {
        return servicioRepository.findByBolicheId(bolicheId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Método privado para mapear a DTO
    private ServicioResponseDTO mapToDTO(Servicio servicio) {
        return new ServicioResponseDTO(
                servicio.getId(),
                servicio.getNombre(),
                servicio.getPrecio(),
                servicio.getDescripcion(),
                servicio.getBoliche() != null ? servicio.getBoliche().getNombre() : null
        );
    }
}
