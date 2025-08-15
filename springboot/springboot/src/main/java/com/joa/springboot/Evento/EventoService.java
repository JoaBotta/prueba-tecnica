package com.joa.springboot.Evento;

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
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private BarraRepository barraRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    // Crear un boliche con barras y servicios
    public EventoResponseDTO createEvento(EventoRequestDTO requestDTO) {
        Evento evento = new Evento(
                requestDTO.getNombre(),
                requestDTO.getProvincia(),
                requestDTO.getCiudad(),
                requestDTO.getCalle(),
                requestDTO.getCapacidadMaxima()
        );

        final Evento savedEvento = eventoRepository.save(evento);

        if (requestDTO.getBarraIds() != null && !requestDTO.getBarraIds().isEmpty()) {
            List<Barra> barras = barraRepository.findAllById(requestDTO.getBarraIds());
            barras.forEach(barra -> barra.setBoliche(savedEvento));
            barraRepository.saveAll(barras);
            savedEvento.setBarras(barras);
        }

        if (requestDTO.getServicioIds() != null && !requestDTO.getServicioIds().isEmpty()) {
            List<Servicio> servicios = servicioRepository.findAllById(requestDTO.getServicioIds());
            servicios.forEach(servicio -> servicio.setBoliche(savedEvento));
            servicioRepository.saveAll(servicios);
            savedEvento.setServicios(servicios);
        }

        return mapToDTO(savedEvento);
    }

    // Obtener todos los boliches
    public List<EventoResponseDTO> getAllEventos() {
        return eventoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Obtener un boliche por su ID
    public EventoResponseDTO getEventoById(Long id) {
        return eventoRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }

    // Eliminar un boliche
    public void deleteEvento(Long id) {
        eventoRepository.deleteById(id);
    }

    // Convertir a DTO
    private EventoResponseDTO mapToDTO(Evento evento) {
        return new EventoResponseDTO(
                evento.getId(),
                evento.getNombre(),
                evento.getProvincia(),
                evento.getCiudad(),
                evento.getCalle(),
                evento.getCapacidadMaxima(),
                evento.getServicios().stream().map(Servicio::getNombre).collect(Collectors.toList()),
                evento.getBarras().stream().map(Barra::getNombre).collect(Collectors.toList()),
                evento.getPuntoventa().stream().map(PuntoDeVenta::getNombre).collect(Collectors.toList())
        );
    }
}
