package com.joa.springboot.FormaDePago;

import jakarta.persistence.*;
import java.util.List;
import com.joa.springboot.VentaBarra.VentaBarra;
import com.joa.springboot.VentaEntrada.VentaEntrada;

@Entity
@Table(name = "formas_de_pago")
public class FormaDePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "formaDePago", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaBarra> ventasBarra;

    @OneToMany(mappedBy = "formaDePago", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaEntrada> ventasEntrada; // Relación con VentaEntrada

    // Constructor por defecto
    public FormaDePago() {}

    // Constructor con parámetros
    public FormaDePago(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<VentaBarra> getVentasBarra() { return ventasBarra; }
    public void setVentasBarra(List<VentaBarra> ventasBarra) { this.ventasBarra = ventasBarra; }
    public List<VentaEntrada> getVentasEntrada() { return ventasEntrada; }
    public void setVentasEntrada(List<VentaEntrada> ventasEntrada) { this.ventasEntrada = ventasEntrada; }

    @Override
    public String toString() {
        return "FormaDePago{" + "id=" + id + ", nombre='" + nombre + '\'' + '}';
    }
}
