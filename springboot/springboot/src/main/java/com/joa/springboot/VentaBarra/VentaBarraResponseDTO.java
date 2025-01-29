package com.joa.springboot.VentaBarra;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.joa.springboot.DetalleVentaBarra.DetalleVentaBarraResponseDTO;

public class VentaBarraResponseDTO {
    private Long id;
    private String barraNombre;
    private String vendedoraNombre;
    private String formaDePago;
    private BigDecimal total;
    private LocalDateTime fecha;
    private List<DetalleVentaBarraResponseDTO> detalleVenta;

    // Constructor
    public VentaBarraResponseDTO(
            Long id, 
            String barraNombre, 
            String vendedoraNombre, 
            String formaDePago, 
            BigDecimal total, 
            LocalDateTime fecha, 
            List<DetalleVentaBarraResponseDTO> detalleVenta) {
        this.id = id;
        this.barraNombre = barraNombre;
        this.vendedoraNombre = vendedoraNombre;
        this.formaDePago = formaDePago;
        this.total = total;
        this.fecha = fecha;
        this.detalleVenta = detalleVenta;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarraNombre() {
        return barraNombre;
    }

    public void setBarraNombre(String barraNombre) {
        this.barraNombre = barraNombre;
    }

    public String getVendedoraNombre() {
        return vendedoraNombre;
    }

    public void setVendedoraNombre(String vendedoraNombre) {
        this.vendedoraNombre = vendedoraNombre;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
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

    public List<DetalleVentaBarraResponseDTO> getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(List<DetalleVentaBarraResponseDTO> detalleVenta) {
        this.detalleVenta = detalleVenta;
    }
}
