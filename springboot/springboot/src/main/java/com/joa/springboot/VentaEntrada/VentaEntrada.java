package com.joa.springboot.VentaEntrada;

import jakarta.persistence.*;
import com.joa.springboot.PuntoDeVenta.PuntoDeVenta;
import com.joa.springboot.Usuario.Usuario;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntrada;
import com.joa.springboot.FormaDePago.FormaDePago;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas_entrada")
public class VentaEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "punto_venta_id", nullable = false)
    private PuntoDeVenta puntoDeVenta;

    @ManyToOne
    @JoinColumn(name = "empleado_ventas_id", nullable = false)
    private Usuario empleadoVentas;

    @ManyToOne
    @JoinColumn(name = "forma_pago_id", nullable = false)
    private FormaDePago formaDePago;

    @OneToMany(mappedBy = "ventaEntrada", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DetalleVentaEntrada> detalleVentaEntrada;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(nullable = false)
    private LocalDateTime fecha;

    // 🔹 Campos solo si hay una entrada VIP
    @Column(nullable = true)
    private String nombreComprador;

    @Column(nullable = true)
    private String correoElectronico;

    @Column(nullable = true)
    private String telefono;

    public VentaEntrada() {
        this.fecha = LocalDateTime.now();
        this.total = BigDecimal.ZERO;
    }

    public VentaEntrada(PuntoDeVenta puntoDeVenta, Usuario empleadoVentas, FormaDePago formaDePago) {
        this.puntoDeVenta = puntoDeVenta;
        this.empleadoVentas = empleadoVentas;
        this.formaDePago = formaDePago;
        this.fecha = LocalDateTime.now();
        this.total = BigDecimal.ZERO;
    }

    public void calcularTotal() {
        this.total = detalleVentaEntrada != null
                ? detalleVentaEntrada.stream()
                                     .map(DetalleVentaEntrada::getSubTotal)
                                     .reduce(BigDecimal.ZERO, BigDecimal::add)
                : BigDecimal.ZERO;
    }

    public boolean tieneEntradaVip() {
        return detalleVentaEntrada != null && detalleVentaEntrada.stream()
                .anyMatch(detalle -> detalle.getEntrada().getNombre().toLowerCase().contains("VIP"));
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public PuntoDeVenta getPuntoDeVenta() { return puntoDeVenta; }
    public void setPuntoDeVenta(PuntoDeVenta puntoDeVenta) { this.puntoDeVenta = puntoDeVenta; }

    public Usuario getEmpleadoVentas() { return empleadoVentas; }
    public void setEmpleadoVentas(Usuario empleadoVentas) { this.empleadoVentas = empleadoVentas; }

    public FormaDePago getFormaDePago() { return formaDePago; }
    public void setFormaDePago(FormaDePago formaDePago) { this.formaDePago = formaDePago; }

    public List<DetalleVentaEntrada> getDetalleVentaEntrada() { return detalleVentaEntrada; }
    public void setDetalleVentaEntrada(List<DetalleVentaEntrada> detalleVentaEntrada) { 
        this.detalleVentaEntrada = detalleVentaEntrada;
        calcularTotal();
    }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getNombreComprador() { return nombreComprador; }
    public void setNombreComprador(String nombreComprador) { this.nombreComprador = nombreComprador; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
