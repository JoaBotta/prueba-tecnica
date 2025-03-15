package com.joa.springboot.DetalleVentaEntrada;

import jakarta.persistence.*;
import java.math.BigDecimal;
import com.joa.springboot.Entrada.Entrada;
import com.joa.springboot.QrEntrada.QrEntrada;
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
    @JoinColumn(name = "entrada_id")
    private Entrada entrada; // Puede ser null

    @ManyToOne
    @JoinColumn(name = "qr_entrada_id")
    private QrEntrada qr_entrada; // Puede ser null

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotal;

    public DetalleVentaEntrada() {}

    public DetalleVentaEntrada(VentaEntrada ventaEntrada, Entrada entrada, QrEntrada qr_entrada, int cantidad) {
        this.ventaEntrada = ventaEntrada;
        this.entrada = entrada;
        this.qr_entrada = qr_entrada;
        this.cantidad = cantidad;

        // Validación para asegurar que solo una de las dos entradas sea no nula
        if ((entrada != null && qr_entrada != null) || (entrada == null && qr_entrada == null)) {
            throw new IllegalArgumentException("Debe haber exactamente una entrada: Entrada normal o QR.");
        }

        this.subTotal = calcularSubTotal();
    }

    private BigDecimal calcularSubTotal() {
        if (entrada != null && entrada.getPrecio() != null) {
            return entrada.getPrecio().multiply(BigDecimal.valueOf(cantidad));
        } else if (qr_entrada != null && qr_entrada.getPrecio() != null) {
            return qr_entrada.getPrecio().multiply(BigDecimal.valueOf(cantidad));
        } else {
            return BigDecimal.ZERO;
        }
    }

    public Long getId() { return id; }
    public VentaEntrada getVentaEntrada() { return ventaEntrada; }
    public Entrada getEntrada() { return entrada; }
    public QrEntrada getQr_entrada() { return qr_entrada; }
    public int getCantidad() { return cantidad; }
    public BigDecimal getSubTotal() { return subTotal; }
}
