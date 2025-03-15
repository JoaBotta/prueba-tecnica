package com.joa.springboot.QrEntrada;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "qr_entrada")
public class QrEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(nullable = false)
    private String nombreComprador;

    @Column(nullable = false)
    private String correoElectronico;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false, unique = true)
    private String codigoQr; // Se generará automáticamente

    // Constructor por defecto
    public QrEntrada() {}

    // Constructor con generación automática del código QR
    public QrEntrada(String nombre, BigDecimal precio, String nombreComprador, String correoElectronico, String telefono) {
        this.nombre = nombre;
        this.precio = precio;
        this.nombreComprador = nombreComprador;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.codigoQr = generarCodigoQr();
    }

    private String generarCodigoQr() {
        return UUID.randomUUID().toString();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public String getNombreComprador() { return nombreComprador; }
    public void setNombreComprador(String nombreComprador) { this.nombreComprador = nombreComprador; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCodigoQr() { return codigoQr; }
}
