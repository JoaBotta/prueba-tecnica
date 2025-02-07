package com.joa.springboot.DetalleVentaEntrada;

import java.math.BigDecimal;
import java.util.List;

public class DetalleVentaEntradaResponseDTO {
    private Long id;
    private String entradaNombre;
    private int cantidad;
    private BigDecimal subTotal;
    private List<String> codigosQr;

    // 🔹 Constructor para entradas normales (sin QR)
    public DetalleVentaEntradaResponseDTO(Long id, String entradaNombre, int cantidad, BigDecimal subTotal) {
        this.id = id;
        this.entradaNombre = entradaNombre;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.codigosQr = null; // Se deja en null porque no tiene QR
    }

    // 🔹 Constructor para entradas VIP (con QR)
    public DetalleVentaEntradaResponseDTO(Long id, String entradaNombre, int cantidad, BigDecimal subTotal, List<String> codigosQr) {
        this.id = id;
        this.entradaNombre = entradaNombre;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.codigosQr = codigosQr;
    }

    // Getters
    public Long getId() { return id; }
    public String getEntradaNombre() { return entradaNombre; }
    public int getCantidad() { return cantidad; }
    public BigDecimal getSubTotal() { return subTotal; }
    public List<String> getCodigosQr() { return codigosQr; }
}
