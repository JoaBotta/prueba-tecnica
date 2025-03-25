package com.joa.springboot.DetalleVentaEntrada;

import java.util.List;

import com.joa.springboot.EntradaGenerada.EntradaGeneradaResponseDTO;

public class DetalleVentaEntradaResponseDTO {

    private Long id;
    private Long entradaId;
    private int cantidad;
    private Double subtotal;
    private List<EntradaGeneradaResponseDTO> entradasGeneradas;

    // 🔹 Constructor vacío (necesario para frameworks como Jackson)
    public DetalleVentaEntradaResponseDTO() {
    }

    // 🔹 Constructor que falta
    public DetalleVentaEntradaResponseDTO(Long id, Long entradaId, int cantidad, Double subtotal, List<EntradaGeneradaResponseDTO> entradasGeneradas) {
        this.id = id;
        this.entradaId = entradaId;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.entradasGeneradas = entradasGeneradas;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntradaId() {
        return entradaId;
    }

    public void setEntradaId(Long entradaId) {
        this.entradaId = entradaId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public List<EntradaGeneradaResponseDTO> getEntradasGeneradas() {
        return entradasGeneradas;
    }

    public void setEntradasGeneradas(List<EntradaGeneradaResponseDTO> entradasGeneradas) {
        this.entradasGeneradas = entradasGeneradas;
    }
}