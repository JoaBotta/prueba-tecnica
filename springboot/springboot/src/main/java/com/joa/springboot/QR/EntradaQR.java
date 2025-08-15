package com.joa.springboot.QR;

import com.joa.springboot.VentaEntradaOnline.VentaEntradaOnline;

import jakarta.persistence.*;

@Entity
@Table(name = "entrada_qr")
public class EntradaQR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "venta_entrada_online_id", nullable = false, unique = true)
    private VentaEntradaOnline ventaEntradaOnline;

    private String textoQR; // Contenido codificado del QR (puede ser UUID o token)

    // Getters y Setters
    // ✅ Nombres correctos (JavaBean)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public VentaEntradaOnline getVentaEntradaOnline() { return ventaEntradaOnline; }
    public void setVentaEntradaOnline(VentaEntradaOnline v) { this.ventaEntradaOnline = v; }

    public String getTextoQR() { return textoQR; }
    public void setTextoQR(String textoQR) { this.textoQR = textoQR; }
}
