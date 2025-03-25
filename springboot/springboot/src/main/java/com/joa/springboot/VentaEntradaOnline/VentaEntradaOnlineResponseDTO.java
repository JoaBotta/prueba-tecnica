package com.joa.springboot.VentaEntradaOnline;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntradaResponseDTO;

@Data
public class VentaEntradaOnlineResponseDTO {

    private Long id;
    private Long puntoDeVentaId;
    private Long formaDePagoId;
    private LocalDateTime fechaHora;
    private Double totalPrecio;
    private String nombreComprador;
    private String correoElectronico;
    private String telefono;
    private List<DetalleVentaEntradaResponseDTO> detalleVentaEntrada;

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

    public List<DetalleVentaEntradaResponseDTO> getDetalleVentaEntrada() {
        return detalleVentaEntrada;
    }

    public void setDetalleVentaEntrada(List<DetalleVentaEntradaResponseDTO> detalleVentaEntrada) {
        this.detalleVentaEntrada = detalleVentaEntrada;
    }
}