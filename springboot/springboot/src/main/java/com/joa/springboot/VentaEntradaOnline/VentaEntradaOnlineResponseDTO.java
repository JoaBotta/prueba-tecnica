package com.joa.springboot.VentaEntradaOnline;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

@Data
public class VentaEntradaOnlineResponseDTO {

    private Long id;
    private Long formaDePagoId;
    private LocalDateTime fechaHora;
    private BigDecimal Precio;
    private String nombreComprador;
    private String correoElectronico;
    private String telefono;
    private String bolicheNombre;
    private Long entradaId;
    private List<String> codigosQR;

    public VentaEntradaOnlineResponseDTO(Long id, Long formaDePagoId, LocalDateTime fechaHora, BigDecimal Precio,
            String nombreComprador, String correoElectronico, String telefono, String bolicheNombre,
            Long entradaId) {
        this.id = id;
        this.formaDePagoId = formaDePagoId;
        this.fechaHora = fechaHora;
        this.Precio = Precio;
        this.nombreComprador = nombreComprador;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.bolicheNombre = bolicheNombre;
        this.entradaId = entradaId;
    }

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

    public BigDecimal getPrecio() {
        return Precio;
    }
    public void setPrecio(BigDecimal Precio) {
        this.Precio = Precio;
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

    public List<String> getCodigosQR() {
        return codigosQR;
    }
    public void setCodigosQR(List<String> codigosQR) {
        this.codigosQR = codigosQR;
    }

    public Long getEntradaId() {
        return entradaId;
    }
    public void setEntradaId(Long entradaId) {
        this.entradaId = entradaId;
    }
}