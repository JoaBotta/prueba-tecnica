package com.joa.springboot.VentaEntrada;

import jakarta.persistence.*;
import com.joa.springboot.PuntoDeVenta.PuntoDeVenta;
import com.joa.springboot.Usuario.Usuario;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntrada;
import com.joa.springboot.FormaDePago.FormaDePago;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    @OneToMany(mappedBy = "ventaEntrada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVentaEntrada> detalleVentaEntrada;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(unique = true, nullable = false)
    private String qrCode;

    // Constructor por defecto
    public VentaEntrada() {
        this.fecha = LocalDateTime.now();
        this.total = BigDecimal.ZERO;
        this.qrCode = UUID.randomUUID().toString(); // Generar un código QR único
    }

    // Constructor con parámetros
    public VentaEntrada(PuntoDeVenta puntoDeVenta, Usuario empleadoVentas, FormaDePago formaDePago) {
        this.puntoDeVenta = puntoDeVenta;
        this.empleadoVentas = empleadoVentas;
        this.formaDePago = formaDePago;
        this.fecha = LocalDateTime.now();
        this.total = BigDecimal.ZERO;
        this.qrCode = UUID.randomUUID().toString(); // Generar QR único
    }

    // Métodos
    public void calcularTotal() {
        this.total = detalleVentaEntrada != null
                ? detalleVentaEntrada.stream()
                                     .map(DetalleVentaEntrada::getSubTotal)
                                     .reduce(BigDecimal.ZERO, BigDecimal::add)
                : BigDecimal.ZERO;
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

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }
}
