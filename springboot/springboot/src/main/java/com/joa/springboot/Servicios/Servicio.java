package com.joa.springboot.Servicios;

import jakarta.persistence.*;
import com.joa.springboot.Boliche.Boliche;
import java.math.BigDecimal;

@Entity
@Table(name = "servicio")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private BigDecimal precio;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "boliche_id", nullable = false) // Relación con Boliche
    private Boliche boliche;

    // Constructor por defecto
    public Servicio() {}

    // Constructor con parámetros
    public Servicio(String nombre, BigDecimal precio, String descripcion, Boliche boliche) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.boliche = boliche;
    }

    // Getters y Setters
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boliche getBoliche() {
        return boliche;
    }

    public void setBoliche(Boliche boliche) {
        this.boliche = boliche;
    }
    // Métodos omitidos para brevedad...

    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", boliche=" + (boliche != null ? boliche.getNombre() : null) +
                '}';
    }
}
