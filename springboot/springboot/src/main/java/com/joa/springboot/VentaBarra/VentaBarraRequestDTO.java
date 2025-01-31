package com.joa.springboot.VentaBarra;

import java.util.List;
import com.joa.springboot.DetalleVentaBarra.DetalleVentaBarraRequestDTO;

public class VentaBarraRequestDTO {
    private Long barraId;
    private Long vendedoraId;
    private Long formaDePagoId;
    private List<DetalleVentaBarraRequestDTO> detalleVenta;

    // Getters y Setters
    public Long getBarraId() {
        return barraId;
    }

    public void setBarraId(Long barraId) {
        this.barraId = barraId;
    }

    public Long getVendedoraId() {
        return vendedoraId;
    }

    public void setVendedoraId(Long vendedoraId) {
        this.vendedoraId = vendedoraId;
    }

    public Long getFormaDePagoId() {
        return formaDePagoId;
    }

    public void setFormaDePagoId(Long formaDePagoId) {
        this.formaDePagoId = formaDePagoId;
    }

    public List<DetalleVentaBarraRequestDTO> getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(List<DetalleVentaBarraRequestDTO> detalleVenta) {
        this.detalleVenta = detalleVenta;
    }
}
