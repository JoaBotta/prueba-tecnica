package com.joa.springboot.VIP;

import java.math.BigDecimal;

public class VIPResponseDTO {
    private Long id;
    private int telefono;
    private String correo;
    private BigDecimal precio;
    private String qrCode;

    // Constructor
    public VIPResponseDTO(Long id, int telefono, String correo, BigDecimal precio, String qrCode) {
        this.id = id;
        this.telefono = telefono;
        this.correo = correo;
        this.precio = precio;
        this.qrCode = qrCode;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getQrCode() { return qrCode; }
}
