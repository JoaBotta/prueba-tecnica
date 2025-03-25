package com.joa.springboot.EntradaGenerada;


public class EntradaGeneradaResponseDTO {

    private Long id;
    private String qrCode;
    private Boolean usada;

    public EntradaGeneradaResponseDTO() {
    }

    public EntradaGeneradaResponseDTO(Long id, String qrCode, Boolean usada) {
        this.id = id;
        this.qrCode = qrCode;
        this.usada = usada;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Boolean getUsada() {
        return usada;
    }

    public void setUsada(Boolean usada) {
        this.usada = usada;
    }
}