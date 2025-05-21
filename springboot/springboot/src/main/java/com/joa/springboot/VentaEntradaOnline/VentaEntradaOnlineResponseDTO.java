package com.joa.springboot.VentaEntradaOnline;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntradaResponseDTO;

@Data
public class VentaEntradaOnlineResponseDTO {

    private Long id;
    private Long formaDePagoId;
    private LocalDateTime fechaHora;
    private Double totalPrecio;
    private long clienteId;
    private String bolicheNombre;
    private List<DetalleVentaEntradaResponseDTO> detalleVentaEntrada;

    // Getters y Setters
    public String getBolicheNombre() {
        return bolicheNombre;
    }

    public void setBolicheNombre(String bolicheNombre) {
        this.bolicheNombre = bolicheNombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getClienteId() {
        return clienteId;
    }
    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    public List<DetalleVentaEntradaResponseDTO> getDetalleVentaEntrada() {
        return detalleVentaEntrada;
    }

    public void setDetalleVentaEntrada(List<DetalleVentaEntradaResponseDTO> detalleVentaEntrada) {
        this.detalleVentaEntrada = detalleVentaEntrada;
    }
}