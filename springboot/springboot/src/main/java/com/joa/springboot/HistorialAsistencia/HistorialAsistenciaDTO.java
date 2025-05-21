package com.joa.springboot.HistorialAsistencia;

import com.joa.springboot.Estado.EstadoDTO;
import java.time.LocalDateTime;

public class HistorialAsistenciaDTO {
    private Long id;
    private Long clienteId;
    private EstadoDTO estado;
    private LocalDateTime fechaHora;

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public EstadoDTO getEstado() {
        return estado;
    }
    public void setEstado(EstadoDTO estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
