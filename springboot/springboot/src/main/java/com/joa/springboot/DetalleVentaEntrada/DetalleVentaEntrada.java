package com.joa.springboot.DetalleVentaEntrada;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.joa.springboot.VIP.VIP;
import com.joa.springboot.Entrada.Entrada;
import com.joa.springboot.VentaEntrada.VentaEntrada;

@Entity
@Table(name = "detalle_venta_entrada")
public class DetalleVentaEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_entrada_id", nullable = false)
    private VentaEntrada ventaEntrada;

    @ManyToOne
    @JoinColumn(name = "entrada_id", nullable = true)
    private Entrada entrada;

    @ManyToOne
    @JoinColumn(name = "VIP_id", nullable = true)
    private VIP VIP;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotal;

    // Constructor por defecto
    public DetalleVentaEntrada() {}

    // Constructor con parámetros
    public DetalleVentaEntrada(VentaEntrada ventaEntrada, Entrada entrada, VIP VIP, int cantidad) {
        this.ventaEntrada = ventaEntrada;
        this.entrada = entrada;
        this.VIP = VIP;
        this.cantidad = cantidad;
        this.subTotal = calcularSubTotal();
    }

    // Método para calcular el subtotal
    private BigDecimal calcularSubTotal() {
        if (entrada != null && entrada.getPrecio() != null) {
            return entrada.getPrecio().multiply(BigDecimal.valueOf(cantidad));
        }
        else if (VIP != null && VIP.getPrecio() != null){
            return VIP.getPrecio().multiply(BigDecimal.valueOf(cantidad));
        }
        return BigDecimal.ZERO;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public VentaEntrada getVentaEntrada() { return ventaEntrada; }
    public void setVentaEntrada(VentaEntrada ventaEntrada) { this.ventaEntrada = ventaEntrada; }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subTotal = calcularSubTotal();
    }

    public BigDecimal getSubTotal() { return subTotal; }
    public void setSubTotal(BigDecimal subTotal) { this.subTotal = subTotal; }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
        this.subTotal = calcularSubTotal();
    }

    public VIP getVIP() {
        return VIP;
    }

    public void setVIP(VIP VIP) {
        this.VIP = VIP;
        this.subTotal = calcularSubTotal();
    }
}
