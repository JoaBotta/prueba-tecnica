package com.joa.springboot.Cliente;

public class ClienteResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String documento;
    private String telefono;
    private Boolean asistencia;

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
    public Boolean getAsistencia() {
        return asistencia;
    }
    public void setAsistencia(Boolean asistencia) {
        this.asistencia = asistencia;
    }
}