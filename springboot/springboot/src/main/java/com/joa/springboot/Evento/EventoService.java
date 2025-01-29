package com.joa.springboot.Evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joa.springboot.Boliche.Boliche;
import com.joa.springboot.Boliche.BolicheRepository;
import com.joa.springboot.Servicios.Servicio;
import com.joa.springboot.Servicios.ServicioRepository;
import com.joa.springboot.VentaBarra.VentaBarraRepository;
import com.joa.springboot.VentaBarra.VentaBarraResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private BolicheRepository bolicheRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private VentaBarraRepository ventaBarraRepository;

    // ✅ Crear un evento
    public EventoResponseDTO createEvento(EventoRequestDTO requestDTO) {
        Boliche boliche = bolicheRepository.findById(requestDTO.getBolicheId())
                .orElseThrow(() -> new RuntimeException("Boliche no encontrado con ID: " + requestDTO.getBolicheId()));

        Evento evento = new Evento(requestDTO.getNombre(), requestDTO.getFechaDesde(), requestDTO.getFechaHasta(), boliche);

        if (requestDTO.getServicioIds() != null && !requestDTO.getServicioIds().isEmpty()) {
            List<Servicio> servicios = servicioRepository.findAllById(requestDTO.getServicioIds());
            evento.setServicios(servicios);
        }

        evento = eventoRepository.save(evento);

        return convertirAEventoResponseDTO(evento);
    }

    // ✅ Obtener todos los eventos con ventas asociadas
    public List<EventoResponseDTO> getAllEventos() {
        return eventoRepository.findAll().stream()
                .map(this::convertirAEventoResponseDTO)
                .collect(Collectors.toList());
    }

    // ✅ Obtener un evento por ID con ventas asociadas
    public EventoResponseDTO getEventoById(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));

        return convertirAEventoResponseDTO(evento);
    }

    // ✅ Método para convertir un Evento a EventoResponseDTO con ventas de la barra
    private EventoResponseDTO convertirAEventoResponseDTO(Evento evento) {
        List<VentaBarraResponseDTO> ventasEnEvento = ventaBarraRepository.findVentasByBarraAndFecha(
                evento.getBoliche().getId(), evento.getFechaDesde(), evento.getFechaHasta()
        ).stream().map(venta -> new VentaBarraResponseDTO(
                venta.getId(),
                venta.getBarra().getNombre(),
                venta.getVendedora().getUsername(),
                venta.getFormaDePago().getNombre(),
                venta.getTotal(),
                venta.getFecha(),
                null // Se omiten detalles de productos en la respuesta
        )).collect(Collectors.toList());

        return new EventoResponseDTO(
                evento.getId(),
                evento.getNombre(),
                evento.getFechaDesde(),
                evento.getFechaHasta(),
                evento.getBoliche().getNombre(),
                ventasEnEvento
        );
    }

    // ✅ Actualizar un evento
    public EventoResponseDTO updateEvento(Long id, EventoRequestDTO requestDTO) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));

        evento.setNombre(requestDTO.getNombre());
        evento.setFechaDesde(requestDTO.getFechaDesde());
        evento.setFechaHasta(requestDTO.getFechaHasta());

        Boliche boliche = bolicheRepository.findById(requestDTO.getBolicheId())
                .orElseThrow(() -> new RuntimeException("Boliche no encontrado con ID: " + requestDTO.getBolicheId()));
        evento.setBoliche(boliche);

        if (requestDTO.getServicioIds() != null && !requestDTO.getServicioIds().isEmpty()) {
            List<Servicio> servicios = servicioRepository.findAllById(requestDTO.getServicioIds());
            evento.setServicios(servicios);
        }

        evento = eventoRepository.save(evento);

        return convertirAEventoResponseDTO(evento);
    }

    // ✅ Eliminar un evento
    public void deleteEvento(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));
        eventoRepository.delete(evento);
    }
}
