package com.joa.springboot.Cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joa.springboot.HistorialAsistencia.HistorialAsistencia;
import com.joa.springboot.Lista.Lista;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    private String documento;

    private String telefono;

      // Campo presente con valor predeterminado
      private boolean presente = false; // Valor predeterminado para "presente"

    //@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<HistorialAsistencia> historialAsistencia;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading para evitar la carga de la lista completa
    @JoinColumn(name = "lista_id") // Nombre de la columna en la tabla cliente que referencia a la lista
    // Esto es para evitar la referencia circular en la serialización JSON
    @JsonIgnore
    private Lista lista;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistorialAsistencia> historialAsistencia = new ArrayList<>();


    // Getters y Setters

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<HistorialAsistencia> getHistorialAsistencia() {
        return historialAsistencia;
    }

    public void setHistorialAsistencia(List<HistorialAsistencia> historialAsistencia) {
        this.historialAsistencia = historialAsistencia;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }
}
