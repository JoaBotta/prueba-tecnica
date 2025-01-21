package com.joa.springboot.VentaBarra;

import java.time.LocalDateTime;
import java.util.List;

public class VentaBarraTicketDTO {
    private Long id;
    private String barraNombre;
    private String vendedoraNombre;
    private String formaDePago;
    private double total;
    private LocalDateTime fecha;
    private List<TicketDetalleDTO> detalleVenta;

    // Constructor
    public VentaBarraTicketDTO(Long id, String barraNombre, String vendedoraNombre, String formaDePago, double total, LocalDateTime fecha, List<TicketDetalleDTO> detalleVenta) {
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<TicketDetalleDTO> getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(List<TicketDetalleDTO> detalleVenta) {
        this.detalleVenta = detalleVenta;
    }




}