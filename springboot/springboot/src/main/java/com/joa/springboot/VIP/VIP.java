package com.joa.springboot.VIP;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "VIP")
public class VIP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private int telefono;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(unique = true, nullable = false)
    private String qrCode;

    // Constructor por defecto
    public VIP() {}

    // Constructor con parámetros
    public VIP(int telefono, String correo, BigDecimal precio) {
        this.telefono = telefono;
        this.correo = correo;
        this.precio = precio;
        this.qrCode = UUID.randomUUID().toString(); // Generar un código QR único
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
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }
}
