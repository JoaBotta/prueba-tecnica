package com.joa.springboot.DetalleVentaQrEntrada;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import com.joa.springboot.QrEntrada.QrEntrada;
import com.joa.springboot.VentaEntrada.VentaEntrada;

@Entity
@Table(name = "detalle_venta_qr_entrada")
public class DetalleVentaQrEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_entrada_id", nullable = false)
    private VentaEntrada ventaEntrada;

    @ManyToOne
    @JoinColumn(name = "qr_entrada_id", nullable = false)
    private QrEntrada qrEntrada;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotal;

    @ElementCollection
    @CollectionTable(name = "detalle_venta_qr", joinColumns = @JoinColumn(name = "detalle_venta_id"))
    @Column(name = "codigo_qr")
    private List<String> codigosQr;

    public DetalleVentaQrEntrada() {}

    public DetalleVentaQrEntrada(VentaEntrada ventaEntrada, QrEntrada qrEntrada, int cantidad, List<String> codigosQr) {
        this.ventaEntrada = ventaEntrada;
        this.qrEntrada = qrEntrada;
        this.cantidad = cantidad;
        this.codigosQr = codigosQr;
        this.subTotal = calcularSubTotal();
    }

    private BigDecimal calcularSubTotal() {
        return qrEntrada != null && qrEntrada.getPrecio() != null ? 
            qrEntrada.getPrecio().multiply(BigDecimal.valueOf(cantidad)) : 
            BigDecimal.ZERO;
    }

    public Long getId() { return id; }
    public VentaEntrada getVentaEntrada() { return ventaEntrada; }
    public QrEntrada getQrEntrada() { return qrEntrada; }
    public int getCantidad() { return cantidad; }
    public BigDecimal getSubTotal() { return subTotal; }
    public List<String> getCodigosQr() { return codigosQr; }
}
