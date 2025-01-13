package com.joa.springboot.VentaBarra;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import com.joa.springboot.Barra.*;
import com.joa.springboot.DetalleVentaBarra.*;
import com.joa.springboot.Usuario.*;
import com.joa.springboot.FormaDePago.*;

@Entity
@Table(name = "ventas_barra")
public class VentaBarra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "barra_id", nullable = false)
    private Barra barra;

    @OneToMany(mappedBy = "ventaBarra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVentaBarra> detalleVenta;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario vendedora;

    @ManyToOne
    @JoinColumn(name = "forma_pago_id", nullable = false)
    private FormaDePago formaDePago;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false)
    private LocalDateTime fecha;

    // Constructor por defecto
    public VentaBarra() {
        this.fecha = LocalDateTime.now();
    }

    // Constructor con parámetros
    public VentaBarra(Barra barra, Usuario vendedora, FormaDePago formaDePago) {
        this.barra = barra;
        this.vendedora = vendedora;
        this.formaDePago = formaDePago;
        this.fecha = LocalDateTime.now();
        this.total = 0.0; // Se calcula a partir de los detalles
    }

    // Métodos
    public void calcularTotal() {
        this.total = detalleVenta != null ? detalleVenta.stream().mapToDouble(DetalleVentaBarra::getSubTotal).sum() : 0.0;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Barra getBarra() {
        return barra;
    }

    public void setBarra(Barra barra) {
        this.barra = barra;
    }

    public List<DetalleVentaBarra> getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(List<DetalleVentaBarra> detalleVenta) {
        this.detalleVenta = detalleVenta;
        calcularTotal();
    }

    public Usuario getVendedora() {
        return vendedora;
    }

    public void setVendedora(Usuario vendedora) {
        this.vendedora = vendedora;
    }

    public FormaDePago getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(FormaDePago formaDePago) {
        this.formaDePago = formaDePago;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "VentaBarra{" +
                "id=" + id +
                ", barra=" + barra.getNombre() +
                ", vendedora=" + vendedora.getUsername() +
                ", formaDePago=" + formaDePago.getNombre() +
                ", total=" + total +
                ", fecha=" + fecha +
                '}';
    }
}