package com.joa.springboot.DetalleVentaQrEntrada;

import java.math.BigDecimal;
import java.util.List;

public class DetalleVentaQrEntradaResponseDTO {
    private Long id;
    private String qrEntradaNombre;
    private int cantidad;
    private BigDecimal subTotal;
    private List<String> codigosQr;

    public DetalleVentaQrEntradaResponseDTO(Long id, String qrEntradaNombre, int cantidad, BigDecimal subTotal, List<String> codigosQr) {
        this.id = id;
        this.qrEntradaNombre = qrEntradaNombre;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.codigosQr = codigosQr;
    }

    public Long getId() { return id; }
    public String getQrEntradaNombre() { return qrEntradaNombre; }
    public int getCantidad() { return cantidad; }
    public BigDecimal getSubTotal() { return subTotal; }
    public List<String> getCodigosQr() { return codigosQr; }
}
