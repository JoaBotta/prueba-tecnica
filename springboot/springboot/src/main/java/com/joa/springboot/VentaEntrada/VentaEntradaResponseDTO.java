package com.joa.springboot.VentaEntrada;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class VentaEntradaResponseDTO {
    private Long id;
    private String puntoVentaNombre;
    private String empleadoVentasNombre;
    private String formaDePago;
    private BigDecimal total;
    private LocalDateTime fecha;
    private String tipoEntrada;
    private String entradaNombre;
    private int cantidad;

    // 🔹 Datos del comprador (solo si es una venta QR)
    private String nombreComprador;
    private String correoElectronico;
    private String telefono;
    
    // 🔹 Lista de códigos QR (solo si es una venta QR)
    private List<String> codigosQr;

    // ✅ Constructor para venta normal (sin QR)
    public VentaEntradaResponseDTO(Long id, String puntoVentaNombre, String empleadoVentasNombre, 
                                   String formaDePago, BigDecimal total, LocalDateTime fecha, 
                                   String tipoEntrada, String entradaNombre, int cantidad) {
        this.id = id;
        this.puntoVentaNombre = puntoVentaNombre;
        this.empleadoVentasNombre = empleadoVentasNombre;
        this.formaDePago = formaDePago;
        this.total = total;
        this.fecha = fecha;
        this.tipoEntrada = tipoEntrada;
        this.entradaNombre = entradaNombre;
        this.cantidad = cantidad;

        // Datos de comprador y códigos QR vacíos para ventas normales
        this.nombreComprador = null;
        this.correoElectronico = null;
        this.telefono = null;
        this.codigosQr = null;
    }

    // ✅ Constructor para venta QR (con códigos QR y datos del comprador)
    public VentaEntradaResponseDTO(Long id, String puntoVentaNombre, String empleadoVentasNombre, 
                                   String formaDePago, BigDecimal total, LocalDateTime fecha, 
                                   String tipoEntrada, String entradaNombre, int cantidad,
                                   String nombreComprador, String correoElectronico, 
                                   String telefono, List<String> codigosQr) {
        this.id = id;
        this.puntoVentaNombre = puntoVentaNombre;
        this.empleadoVentasNombre = empleadoVentasNombre;
        this.formaDePago = formaDePago;
        this.total = total;
        this.fecha = fecha;
        this.tipoEntrada = tipoEntrada;
        this.entradaNombre = entradaNombre;
        this.cantidad = cantidad;
        this.nombreComprador = nombreComprador;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.codigosQr = codigosQr;
    }
}
