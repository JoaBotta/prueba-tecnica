package com.joa.springboot.VentaEntrada;

import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntradaResponseDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VentaEntradaResponseDTO {
    private Long id;
    private String puntoVentaNombre;
    private String empleadoVentasNombre;
    private String formaDePago;
    private BigDecimal total;
    private LocalDateTime fecha;
    private List<DetalleVentaEntradaResponseDTO> detallesVentaEntrada; // Renombrado para mejor claridad

    private String nombreComprador;
    private String correoElectronico;
    private String telefono;

    // 🔹 Constructor principal
    public VentaEntradaResponseDTO(Long id, String puntoVentaNombre, String empleadoVentasNombre, 
                                   String formaDePago, BigDecimal total, LocalDateTime fecha, 
                                   List<DetalleVentaEntradaResponseDTO> detallesVentaEntrada,
                                   String nombreComprador, String correoElectronico, String telefono) {
        this.id = id;
        this.puntoVentaNombre = puntoVentaNombre;
        this.empleadoVentasNombre = empleadoVentasNombre;
        this.formaDePago = formaDePago;
        this.total = total;
        this.fecha = fecha;
        this.detallesVentaEntrada = detallesVentaEntrada;
        this.nombreComprador = nombreComprador;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    // 🔹 Constructor para ventas SIN entrada VIP (nombreComprador, correo y teléfono serán null)
    public VentaEntradaResponseDTO(Long id, String puntoVentaNombre, String empleadoVentasNombre, 
                                   String formaDePago, BigDecimal total, LocalDateTime fecha, 
                                   List<DetalleVentaEntradaResponseDTO> detallesVentaEntrada) {
        this(id, puntoVentaNombre, empleadoVentasNombre, formaDePago, total, fecha, detallesVentaEntrada, null, null, null);
    }

    // ✅ Getters y Setters necesarios
    public Long getId() { return id; }
    public String getPuntoVentaNombre() { return puntoVentaNombre; }
    public String getEmpleadoVentasNombre() { return empleadoVentasNombre; }
    public String getFormaDePago() { return formaDePago; }
    public BigDecimal getTotal() { return total; }
    public LocalDateTime getFecha() { return fecha; }
    public List<DetalleVentaEntradaResponseDTO> getDetallesVentaEntrada() { return detallesVentaEntrada; }
    public String getNombreComprador() { return nombreComprador; }
    public String getCorreoElectronico() { return correoElectronico; }
    public String getTelefono() { return telefono; }

    public void setId(Long id) { this.id = id; }
    public void setPuntoVentaNombre(String puntoVentaNombre) { this.puntoVentaNombre = puntoVentaNombre; }
    public void setEmpleadoVentasNombre(String empleadoVentasNombre) { this.empleadoVentasNombre = empleadoVentasNombre; }
    public void setFormaDePago(String formaDePago) { this.formaDePago = formaDePago; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public void setDetallesVentaEntrada(List<DetalleVentaEntradaResponseDTO> detallesVentaEntrada) { this.detallesVentaEntrada = detallesVentaEntrada; }
    public void setNombreComprador(String nombreComprador) { this.nombreComprador = nombreComprador; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
