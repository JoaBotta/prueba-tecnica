package com.joa.springboot.VentaBarra;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.joa.springboot.Barra.Barra;
import com.joa.springboot.DetalleVentaBarra.DetalleVentaBarra;
import com.joa.springboot.Usuario.Usuario;
import com.joa.springboot.FormaDePago.FormaDePago;

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

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(nullable = false)
    private LocalDateTime fecha;

    // Constructor por defecto
    public VentaBarra() {
        this.fecha = LocalDateTime.now();
        this.total = BigDecimal.ZERO;
    }

    // Constructor con parámetros
    public VentaBarra(Barra barra, Usuario vendedora, FormaDePago formaDePago) {
        this.barra = barra;
        this.vendedora = vendedora;
        this.formaDePago = formaDePago;
        this.fecha = LocalDateTime.now();
        this.total = BigDecimal.ZERO;
    }

    // Métodos
    public void calcularTotal() {
        this.total = detalleVenta != null
                ? detalleVenta.stream()
                              .map(DetalleVentaBarra::getSubTotal)
                              .reduce(BigDecimal.ZERO, BigDecimal::add)
                : BigDecimal.ZERO;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
