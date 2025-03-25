package com.joa.springboot.EntradaGenerada;

public class EntradaGeneradaRequestDTO {

    private Long detalleVentaEntradaId;
    private String qrCode;

    // Getters y Setters

    public Long getDetalleVentaEntradaId() {
        return detalleVentaEntradaId;
    }

    public void setDetalleVentaEntradaId(Long detalleVentaEntradaId) {
        this.detalleVentaEntradaId = detalleVentaEntradaId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}