package com.joa.springboot.Evento;

import com.joa.springboot.VentaBarra.VentaBarraResponseDTO;
import java.time.LocalDateTime;
import java.util.List;

public class EventoResponseDTO {
    private Long id;
    private String nombre;
    private LocalDateTime fechaDesde;
    private LocalDateTime fechaHasta;
    private String bolicheNombre;
    private List<VentaBarraResponseDTO> ventasEnEvento;

    public EventoResponseDTO(Long id, String nombre, LocalDateTime fechaDesde, LocalDateTime fechaHasta, 
                             String bolicheNombre, List<VentaBarraResponseDTO> ventasEnEvento) {
        this.id = id;
        this.nombre = nombre;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.bolicheNombre = bolicheNombre;
        this.ventasEnEvento = ventasEnEvento;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public LocalDateTime getFechaDesde() { return fechaDesde; }
    public void setFechaDesde(LocalDateTime fechaDesde) { this.fechaDesde = fechaDesde; }

    public LocalDateTime getFechaHasta() { return fechaHasta; }
    public void setFechaHasta(LocalDateTime fechaHasta) { this.fechaHasta = fechaHasta; }

    public String getBolicheNombre() { return bolicheNombre; }
    public void setBolicheNombre(String bolicheNombre) { this.bolicheNombre = bolicheNombre; }

    public List<VentaBarraResponseDTO> getVentasEnEvento() { return ventasEnEvento; }
    public void setVentasEnEvento(List<VentaBarraResponseDTO> ventasEnEvento) { this.ventasEnEvento = ventasEnEvento; }
}
