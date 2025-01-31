package com.joa.springboot.VentaEntrada;

import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntradaResponseDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VentaEntradaResponseDTO {
    private Long id;
    private String puntoVentaNombre;
    private String empleadoVentasNombre;
    private String formaDePago;
    private BigDecimal total;
    private LocalDateTime fecha;
    private String qrCode;
    private List<DetalleVentaEntradaResponseDTO> detalleVentaEntrada;

    // Constructor
    public VentaEntradaResponseDTO(Long id, String puntoVentaNombre, String empleadoVentasNombre, 
                                   String formaDePago, BigDecimal total, LocalDateTime fecha, 
                                   String qrCode, List<DetalleVentaEntradaResponseDTO> detalleVentaEntrada) {
        this.id = id;
        this.puntoVentaNombre = puntoVentaNombre;
        this.empleadoVentasNombre = empleadoVentasNombre;
        this.formaDePago = formaDePago;
        this.total = total;
        this.fecha = fecha;
        this.qrCode = qrCode;
        this.detalleVentaEntrada = detalleVentaEntrada;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public String getPuntoVentaNombre() { return puntoVentaNombre; }
    public String getEmpleadoVentasNombre() { return empleadoVentasNombre; }
    public String getFormaDePago() { return formaDePago; }
    public BigDecimal getTotal() { return total; }
    public LocalDateTime getFecha() { return fecha; }
    public String getQrCode() { return qrCode; }
    public List<DetalleVentaEntradaResponseDTO> getDetalleVentaEntrada() { return detalleVentaEntrada; }
}
