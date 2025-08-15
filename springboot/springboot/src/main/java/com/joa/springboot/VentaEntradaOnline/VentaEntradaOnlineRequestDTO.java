package com.joa.springboot.VentaEntradaOnline;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class VentaEntradaOnlineRequestDTO {
    private Long bolicheId;
    private Long formaDePagoId;
    private Long entradaOnlineId;
    private LocalDateTime fechaHora;
    
    private String nombreComprador;
    private String correoElectronico;
    private String telefono;

    // Getters y Setters
    public Long getEntradaOnlineId() {
        return entradaOnlineId;
    }
    public void setEntradaOnlineId(Long entradaOnlineId) {
        this.entradaOnlineId = entradaOnlineId;
    }

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
}