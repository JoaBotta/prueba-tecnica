package com.joa.springboot.EntradaOnline;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "entrada_online")
public class EntradaOnline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    // 🔹 Constructor vacío requerido por Hibernate
    public EntradaOnline() {
    }

    // Constructor con generación automática del código QR
    public EntradaOnline(String nombre, BigDecimal precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
}
