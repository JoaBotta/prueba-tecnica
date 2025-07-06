package com.joa.springboot.Boliche;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joa.springboot.Servicios.Servicio;
import com.joa.springboot.Servicios.ServicioRepository;
import com.joa.springboot.Barra.Barra;
import com.joa.springboot.Barra.BarraRepository;
import com.joa.springboot.PuntoDeVenta.PuntoDeVenta;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BolicheService {

    @Autowired
    private BolicheRepository bolicheRepository;

    @Autowired
    private BarraRepository barraRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    // Crear un boliche con barras y servicios
    public BolicheResponseDTO createBoliche(BolicheRequestDTO requestDTO) {
        Boliche boliche = new Boliche(
                requestDTO.getNombre(),
                requestDTO.getProvincia(),
                requestDTO.getCiudad(),
                requestDTO.getCalle(),
                requestDTO.getCapacidadMaxima()
        );

        final Boliche savedBoliche = bolicheRepository.save(boliche);

        if (requestDTO.getBarraIds() != null && !requestDTO.getBarraIds().isEmpty()) {
            List<Barra> barras = barraRepository.findAllById(requestDTO.getBarraIds());
            barras.forEach(barra -> barra.setBoliche(savedBoliche));
            barraRepository.saveAll(barras);
            savedBoliche.setBarras(barras);
        }

        if (requestDTO.getServicioIds() != null && !requestDTO.getServicioIds().isEmpty()) {
            List<Servicio> servicios = servicioRepository.findAllById(requestDTO.getServicioIds());
            servicios.forEach(servicio -> servicio.setBoliche(savedBoliche));
            servicioRepository.saveAll(servicios);
            savedBoliche.setServicios(servicios);
        }

        return mapToDTO(savedBoliche);
    }

    // Obtener todos los boliches
    public List<BolicheResponseDTO> getAllBoliches() {
        return bolicheRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Obtener un boliche por su ID
    public BolicheResponseDTO getBolicheById(Long id) {
        return bolicheRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Boliche no encontrado"));
    }

    // Eliminar un boliche
    public void deleteBoliche(Long id) {
        bolicheRepository.deleteById(id);
    }

    // Convertir a DTO
    private BolicheResponseDTO mapToDTO(Boliche boliche) {
        return new BolicheResponseDTO(
                boliche.getId(),
                boliche.getNombre(),
                boliche.getProvincia(),
                boliche.getCiudad(),
                boliche.getCalle(),
                boliche.getCapacidadMaxima(),
                boliche.getServicios().stream().map(Servicio::getNombre).collect(Collectors.toList()),
                boliche.getBarras().stream().map(Barra::getNombre).collect(Collectors.toList()),
                boliche.getPuntoventa().stream().map(PuntoDeVenta::getNombre).collect(Collectors.toList())
        );
    }
}
