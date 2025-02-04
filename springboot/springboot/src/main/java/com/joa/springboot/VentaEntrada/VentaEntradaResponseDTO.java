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
    private List<DetalleVentaEntradaResponseDTO> detalleVentaEntrada;

    // Constructor
    public VentaEntradaResponseDTO(Long id, String puntoVentaNombre, String empleadoVentasNombre, 
                                   String formaDePago, BigDecimal total, LocalDateTime fecha, List<DetalleVentaEntradaResponseDTO> detalleVentaEntrada) {
        this.id = id;
        this.puntoVentaNombre = puntoVentaNombre;
        this.empleadoVentasNombre = empleadoVentasNombre;
        this.formaDePago = formaDePago;
        this.total = total;
        this.fecha = fecha;
        this.detalleVentaEntrada = detalleVentaEntrada;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public String getPuntoVentaNombre() { return puntoVentaNombre; }
    public String getEmpleadoVentasNombre() { return empleadoVentasNombre; }
    public String getFormaDePago() { return formaDePago; }
    public BigDecimal getTotal() { return total; }
    public LocalDateTime getFecha() { return fecha; }
    public List<DetalleVentaEntradaResponseDTO> getDetalleVentaEntrada() { return detalleVentaEntrada; }
}
