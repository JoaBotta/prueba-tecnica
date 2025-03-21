package com.joa.springboot.VentaEntrada;

import jakarta.persistence.*;
import com.joa.springboot.PuntoDeVenta.PuntoDeVenta;
import com.joa.springboot.Usuario.Usuario;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntrada;
import com.joa.springboot.DetalleVentaQrEntrada.DetalleVentaQrEntrada;
import com.joa.springboot.FormaDePago.FormaDePago;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @OneToOne(mappedBy = "ventaEntrada", cascade = CascadeType.ALL, orphanRemoval = true)
    private DetalleVentaEntrada detalleVentaEntrada;

    @OneToOne(mappedBy = "ventaEntrada", cascade = CascadeType.ALL, orphanRemoval = true)
    private DetalleVentaQrEntrada detalleVentaQrEntrada;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(nullable = false)
    private LocalDateTime fecha;

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
        if (detalleVentaEntrada != null) {
            this.total = detalleVentaEntrada.getSubTotal();
        } else if (detalleVentaQrEntrada != null) {
            this.total = detalleVentaQrEntrada.getSubTotal();
        } else {
            this.total = BigDecimal.ZERO;
        }
    }

    public void actualizarDatosComprador(String nombre, String correo, String telefono) {
        this.nombreComprador = nombre;
        this.correoElectronico = correo;
        this.telefono = telefono;
    }

    public void setDetalleVentaEntrada(DetalleVentaEntrada detalle) {
        if (this.detalleVentaQrEntrada != null) {
            throw new IllegalStateException("No se puede asignar DetalleVentaEntrada si ya hay un DetalleVentaQrEntrada.");
        }
        this.detalleVentaEntrada = detalle;
        calcularTotal();
    }

    public void setDetalleVentaQrEntrada(DetalleVentaQrEntrada detalle) {
        if (this.detalleVentaEntrada != null) {
            throw new IllegalStateException("No se puede asignar DetalleVentaQrEntrada si ya hay un DetalleVentaEntrada.");
        }
        this.detalleVentaQrEntrada = detalle;
        calcularTotal();
    }

    public Long getId() { return id; }
    public PuntoDeVenta getPuntoDeVenta() { return puntoDeVenta; }
    public Usuario getEmpleadoVentas() { return empleadoVentas; }
    public FormaDePago getFormaDePago() { return formaDePago; }
    public DetalleVentaEntrada getDetalleVentaEntrada() { return detalleVentaEntrada; }
    public DetalleVentaQrEntrada getDetalleVentaQrEntrada() { return detalleVentaQrEntrada; }
    public BigDecimal getTotal() { return total; }
    public LocalDateTime getFecha() { return fecha; }
    public String getNombreComprador() { return nombreComprador; }
    public String getCorreoElectronico() { return correoElectronico; }
    public String getTelefono() { return telefono; }
}
