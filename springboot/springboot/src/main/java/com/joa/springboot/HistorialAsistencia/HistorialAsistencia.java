package com.joa.springboot.HistorialAsistencia;

import com.joa.springboot.Cliente.Cliente;
import com.joa.springboot.Estado.Estado;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class HistorialAsistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Estado estado;

    private LocalDateTime fechaHora; // o LocalDate

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}
