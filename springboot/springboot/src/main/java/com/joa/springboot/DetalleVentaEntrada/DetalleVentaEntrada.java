package com.joa.springboot.DetalleVentaEntrada;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
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
    @JoinColumn(name = "entrada_id", nullable = false)
    private Entrada entrada;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotal;

    @ElementCollection
    @CollectionTable(name = "detalle_venta_qr", joinColumns = @JoinColumn(name = "detalle_venta_id"))
    @Column(name = "codigo_qr")
    private List<String> codigosQr; 

    public DetalleVentaEntrada() {}

    public DetalleVentaEntrada(VentaEntrada ventaEntrada, Entrada entrada, int cantidad) {
        this.ventaEntrada = ventaEntrada;
        this.entrada = entrada;
        this.cantidad = cantidad;
        this.subTotal = calcularSubTotal();
    }

    private BigDecimal calcularSubTotal() {
        return entrada != null && entrada.getPrecio() != null ? 
               entrada.getPrecio().multiply(BigDecimal.valueOf(cantidad)) : 
               BigDecimal.ZERO;
    }

    public Long getId() { return id; }
    public VentaEntrada getVentaEntrada() { return ventaEntrada; }
    public Entrada getEntrada() { return entrada; }
    public int getCantidad() { return cantidad; }
    public BigDecimal getSubTotal() { return subTotal; }
    public List<String> getCodigosQr() { return codigosQr; }
}
