package com.joa.springboot.HistorialAsistencia;

public class HistorialAsistenciaDTO {
    private Long id;
    private Long clienteId; // Solo el ID del cliente
    private Long listaId;   // Solo el ID de la lista
    private boolean presente;

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

    public Long getListaId() {
        return listaId;
    }

    public void setListaId(Long listaId) {
        this.listaId = listaId;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }
}
