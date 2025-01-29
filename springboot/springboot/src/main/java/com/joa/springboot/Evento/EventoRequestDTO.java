package com.joa.springboot.Evento;

import java.time.LocalDateTime;
import java.util.List;

public class EventoRequestDTO {
    private String nombre;
    private LocalDateTime fechaDesde;
    private LocalDateTime fechaHasta;
    private Long bolicheId;
    private List<Long> servicioIds;

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDateTime fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDateTime getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDateTime fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Long getBolicheId() {
        return bolicheId;
    }

    public void setBolicheId(Long bolicheId) {
        this.bolicheId = bolicheId;
    }

    public List<Long> getServicioIds() {
        return servicioIds;
    }

    public void setServicioIds(List<Long> servicioIds) {
        this.servicioIds = servicioIds;
    }
}
