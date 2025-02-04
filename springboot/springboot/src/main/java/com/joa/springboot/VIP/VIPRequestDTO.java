package com.joa.springboot.VIP;

import java.math.BigDecimal;

public class VIPRequestDTO {
    private int telefono;
    private String correo;
    private BigDecimal precio;

    // Getters y Setters

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
}
