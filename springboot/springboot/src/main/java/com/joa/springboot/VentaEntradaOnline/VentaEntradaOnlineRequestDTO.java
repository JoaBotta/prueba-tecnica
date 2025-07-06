package com.joa.springboot.VentaEntradaOnline;

import lombok.Data;
import java.time.LocalDateTime;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntradaRequestDTO;
import java.util.List;

@Data
public class VentaEntradaOnlineRequestDTO {
    private Long bolicheId;
    private Long formaDePagoId;
    private LocalDateTime fechaHora;
    private Double totalPrecio;
    
    private String nombreComprador;
    private String correoElectronico;
    private String telefono;
    private List<DetalleVentaEntradaRequestDTO> detalleVentaEntrada;

    // Getters y Setters
    public Long getBolicheId() {
        return bolicheId;
    }

    public void setBolicheId(Long bolicheId) {
        this.bolicheId = bolicheId;
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

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<DetalleVentaEntradaRequestDTO> getDetalleVentaEntrada() {
        return detalleVentaEntrada;
    }

    public void setDetalleVentaEntrada(List<DetalleVentaEntradaRequestDTO> detalleVentaEntrada) {
        this.detalleVentaEntrada = detalleVentaEntrada;
    }
}