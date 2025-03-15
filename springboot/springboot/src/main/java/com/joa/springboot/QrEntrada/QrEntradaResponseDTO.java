package com.joa.springboot.QrEntrada;

import java.math.BigDecimal;

public class QrEntradaResponseDTO {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private String nombreComprador;
    private String correoElectronico;
    private String telefono;
    private String codigoQr;

    // Constructor
    public QrEntradaResponseDTO(Long id, String nombre, BigDecimal precio, String nombreComprador, String correoElectronico, String telefono, String codigoQr) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.nombreComprador = nombreComprador;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.codigoQr = codigoQr;
    }

    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public BigDecimal getPrecio() { return precio; }
    public String getNombreComprador() { return nombreComprador; }
    public String getCorreoElectronico() { return correoElectronico; }
    public String getTelefono() { return telefono; }
    public String getCodigoQr() { return codigoQr; }
}
