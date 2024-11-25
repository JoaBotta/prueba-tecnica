package com.joa.springboot.Planta;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Planta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String pais;
    private int lecturasOk;
    private int alertasMedias;
    private int alertasRojas;
    private int sensoresDeshabilitados;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getLecturasOk() {
        return lecturasOk;
    }

    public void setLecturasOk(int lecturasOk) {
        this.lecturasOk = lecturasOk;
    }

    public int getAlertasMedias() {
        return alertasMedias;
    }

    public void setAlertasMedias(int alertasMedias) {
        this.alertasMedias = alertasMedias;
    }

    public int getAlertasRojas() {
        return alertasRojas;
    }

    public void setAlertasRojas(int alertasRojas) {
        this.alertasRojas = alertasRojas;
    }

    public int getSensoresDeshabilitados() {
        return sensoresDeshabilitados;
    }

    public void setSensoresDeshabilitados(int sensoresDeshabilitados) {
        this.sensoresDeshabilitados = sensoresDeshabilitados;
    }
}
