package com.joa.springboot.Barra;

import jakarta.persistence.*;
import java.util.List;
import com.joa.springboot.VentaBarra.*;

@Entity
@Table(name = "barra")
public class Barra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "barra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaBarra> ventas;

    // Constructor por defecto
    public Barra() {}

    // Constructor con parámetros
    public Barra(String nombre) {
        this.nombre = nombre;
    }

    // Métodos
    public int getCantidadVentas() {
        return ventas != null ? ventas.size() : 0;
    }

    public double getGanancias() {
        return ventas != null ? ventas.stream().mapToDouble(VentaBarra::getTotal).sum() : 0.0;
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

    public List<VentaBarra> getVentas() {
        return ventas;
    }

    public void setVentas(List<VentaBarra> ventas) {
        this.ventas = ventas;
    }

    @Override
    public String toString() {
        return "Barra{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cantidadVentas=" + getCantidadVentas() +
                ", ganancias=" + getGanancias() +
                '}';
    }
}
