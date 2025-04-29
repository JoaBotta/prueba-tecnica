package com.joa.springboot.HistorialAsistencia;

import com.joa.springboot.Estado.Estado;
import jakarta.persistence.*;

@Entity
public class HistorialAsistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id")
    private Estado estado;

    // Opcionalmente podés tener otros campos como fecha de asistencia, etc.
    private String observaciones; // si querés guardar algún detalle extra

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
