package com.joa.springboot.Lista;

import java.time.LocalDate;
import java.util.List;

import com.joa.springboot.Cliente.ClienteDTO;

public class ListaDTO {
    private Long id;
    private String nombre;
    private LocalDate fecha;
    private List<ClienteDTO> clientes;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public List<ClienteDTO> getClientes() { return clientes; }
    public void setClientes(List<ClienteDTO> clientes) { this.clientes = clientes; }
}
