package com.joa.springboot.VentaEntrada;

import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntradaRequestDTO;
import java.util.List;

public class VentaEntradaRequestDTO {
    private Long puntoVentaId;
    private Long empleadoVentasId;
    private Long formaDePagoId;
    private List<DetalleVentaEntradaRequestDTO> detalleVentaEntrada;

    // Getters y Setters
    public Long getPuntoVentaId() { return puntoVentaId; }
    public void setPuntoVentaId(Long puntoVentaId) { this.puntoVentaId = puntoVentaId; }

    public Long getEmpleadoVentasId() { return empleadoVentasId; }
    public void setEmpleadoVentasId(Long empleadoVentasId) { this.empleadoVentasId = empleadoVentasId; }

    public Long getFormaDePagoId() { return formaDePagoId; }
    public void setFormaDePagoId(Long formaDePagoId) { this.formaDePagoId = formaDePagoId; }

    public List<DetalleVentaEntradaRequestDTO> getDetalleVentaEntrada() { return detalleVentaEntrada; }
    public void setDetalleVentaEntrada(List<DetalleVentaEntradaRequestDTO> detalleVentaEntrada) { 
        this.detalleVentaEntrada = detalleVentaEntrada;
    }
}
