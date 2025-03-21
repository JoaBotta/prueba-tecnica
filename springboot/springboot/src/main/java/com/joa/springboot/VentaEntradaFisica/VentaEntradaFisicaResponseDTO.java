package com.joa.springboot.VentaEntradaFisica;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import com.joa.springboot.DetalleVentaFisica.DetalleVentaFisicaResponseDTO;

@Data
public class VentaEntradaFisicaResponseDTO {

    private Long id;
    private Long puntoDeVentaId;
    private Long formaDePagoId;
    private LocalDateTime fechaHora;
    private Double totalPrecio;
    private List<DetalleVentaFisicaResponseDTO> detalles;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    public List<DetalleVentaFisicaResponseDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaFisicaResponseDTO> detalles) {
        this.detalles = detalles;
    }
}