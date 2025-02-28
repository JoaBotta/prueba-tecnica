package com.joa.springboot.Barra;

import jakarta.persistence.*;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.joa.springboot.Boliche.Boliche;
import com.joa.springboot.VentaBarra.VentaBarra;

@Entity
@Table(name = "barra")
@CrossOrigin(origins = "http://localhost:4200/")
public class Barra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "barra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaBarra> ventas;

    @ManyToOne
    @JoinColumn(name = "boliche_id", nullable = false)
    @JsonBackReference
    private Boliche boliche;

    // Constructor por defecto
    public Barra() {}

    // Constructor con parámetros
    public Barra(String nombre, Boliche boliche) {
        this.nombre = nombre;
        this.boliche = boliche;
    }

    // Métodos
    public int getCantidadVentas() {
        return ventas != null ? ventas.size() : 0;
    }

    public double getGanancias() {
        return ventas != null ? ventas.stream()
                .map(VentaBarra::getTotal)
                .mapToDouble(total -> total != null ? total.doubleValue() : 0.0)
                .sum()
                : 0.0;
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

    public Boliche getBoliche() {
        return boliche;
    }

    public void setBoliche(Boliche boliche) {
        this.boliche = boliche;
    }
}
