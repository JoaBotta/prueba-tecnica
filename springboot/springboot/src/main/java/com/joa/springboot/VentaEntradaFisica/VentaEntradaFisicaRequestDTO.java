package com.joa.springboot.VentaEntradaFisica;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

import com.joa.springboot.DetalleVentaFisica.DetalleVentaFisicaRequestDTO;

@Data
public class VentaEntradaFisicaRequestDTO {

    private Long puntoDeVentaId;
    private Long formaDePagoId;
    private LocalDateTime fechaHora;
    private Double totalPrecio;
    private List<DetalleVentaFisicaRequestDTO> detalles;

    // Getters y Setters

    public Long getPuntoDeVentaId() {
        return puntoDeVentaId;
    }

    public void setPuntoDeVentaId(Long puntoDeVentaId) {
        this.puntoDeVentaId = puntoDeVentaId;
    }

    public Long getFormaDePagoId() {
        return formaDePagoId;
    }

    public void setFormaDePagoId(Long formaDePagoId) {
        this.formaDePagoId = formaDePagoId;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Double getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(Double totalPrecio) {
        this.totalPrecio = totalPrecio;
    }
    public List<DetalleVentaFisicaRequestDTO> getDetalles() {
        return detalles;
    }
    
    public void setDetalles(List<DetalleVentaFisicaRequestDTO> detalles) {
        this.detalles = detalles;
    }
    
}
