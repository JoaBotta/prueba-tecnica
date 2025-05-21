package com.joa.springboot.VentaEntradaOnline;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntrada;
import com.joa.springboot.FormaDePago.FormaDePago;
import com.joa.springboot.Boliche.Boliche;
import com.joa.springboot.Cliente.Cliente;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@DiscriminatorValue("ONLINE")
public class VentaEntradaOnline {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private FormaDePago formaDePago;

    private LocalDateTime fechaHora;

    private Double totalPrecio;

    @ManyToOne(optional = false)
    @JoinColumn(name = "boliche_id", nullable = false)
    private Boliche boliche;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "ventaEntradaOnline", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVentaEntrada> detalleVentaEntrada;

    // Getters y Setters
    public Boliche getBoliche() {
        return boliche;
    }

    public void setBoliche(Boliche boliche) {
        this.boliche = boliche;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FormaDePago getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(FormaDePago formaDePago) {
        this.formaDePago = formaDePago;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Double getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(Double totalPrecio) {
        this.totalPrecio = totalPrecio;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public List<DetalleVentaEntrada> getDetalleVentaEntrada() {
        return detalleVentaEntrada;
    }

    public void setDetalleVentaEntrada(List<DetalleVentaEntrada> detalleVentaEntrada) {
        this.detalleVentaEntrada = detalleVentaEntrada;
    }
}