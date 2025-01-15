package com.joa.springboot.Boliche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joa.springboot.Servicios.Servicio;
import com.joa.springboot.Servicios.ServicioRepository;
import com.joa.springboot.Barra.Barra;
import com.joa.springboot.Barra.BarraRepository;

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
    public BolicheResponseDTO createBoliche(BolicheRequestDTO requestDTO) {
        // Crea el objeto boliche sin asociar aún barras y servicios
        Boliche boliche = new Boliche(
                requestDTO.getNombre(),
                requestDTO.getProvincia(),
                requestDTO.getCiudad(),
                requestDTO.getCalle(),
                requestDTO.getCapacidadMaxima()
        );
    
        // Guarda el boliche para obtener su ID
        final Boliche savedBoliche = bolicheRepository.save(boliche);
    
        // Asocia barras si hay IDs proporcionados
        if (requestDTO.getBarraIds() != null && !requestDTO.getBarraIds().isEmpty()) {
            List<Barra> barras = barraRepository.findAllById(requestDTO.getBarraIds());
            barras.forEach(barra -> barra.setBoliche(savedBoliche));
            barraRepository.saveAll(barras); // Actualiza las barras con el boliche asociado
            savedBoliche.setBarras(barras);
        }
    
        // Asocia servicios si hay IDs proporcionados
        if (requestDTO.getServicioIds() != null && !requestDTO.getServicioIds().isEmpty()) {
            List<Servicio> servicios = servicioRepository.findAllById(requestDTO.getServicioIds());
            servicios.forEach(servicio -> servicio.setBoliche(savedBoliche));
            servicioRepository.saveAll(servicios); // Actualiza los servicios con el boliche asociado
            savedBoliche.setServicios(servicios);
        }
    
        // Retorna el DTO de respuesta
        return new BolicheResponseDTO(
                savedBoliche.getId(),
                savedBoliche.getNombre(),
                savedBoliche.getProvincia(),
                savedBoliche.getCiudad(),
                savedBoliche.getCalle(),
                savedBoliche.getCapacidadMaxima(),
                null // No es necesario devolver servicios/barras aquí
        );
    }
    
    public List<BolicheResponseDTO> getAllBoliches() {
        return bolicheRepository.findAll().stream()
                .map(boliche -> new BolicheResponseDTO(
                        boliche.getId(),
                        boliche.getNombre(),
                        boliche.getProvincia(),
                        boliche.getCiudad(),
                        boliche.getCalle(),
                        boliche.getCapacidadMaxima(),
                        null // Puede incluir servicios o barras si es necesario
                ))
                .collect(Collectors.toList());
    }
}
