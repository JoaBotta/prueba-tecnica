package com.joa.springboot.PuntoDeVenta;

public class PuntoDeVentaResponseDTO {
    private Long id;
    private String nombre;
    private String bolicheNombre;
    private String seguridadNombre;

    // Constructor
    public PuntoDeVentaResponseDTO(Long id, String nombre, String bolicheNombre, String seguridadNombre) {
        this.id = id;
        this.nombre = nombre;
        this.bolicheNombre = bolicheNombre;
        this.seguridadNombre = seguridadNombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBolicheNombre() {
        return bolicheNombre;
    }

    public void setBolicheNombre(String bolicheNombre) {
        this.bolicheNombre = bolicheNombre;
    }

    public String getSeguridadNombre() {
        return seguridadNombre;
    }

    public void setSeguridadNombre(String seguridadNombre) {
        this.seguridadNombre = seguridadNombre;
    }
}
