package com.joa.springboot.Barra;

public class BarraResponseDTO {
    private Long id;
    private String nombre;
    private int cantidadVentas;
    private double ganancias;

    // Constructor
    public BarraResponseDTO(Long id, String nombre, int cantidadVentas, double ganancias) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadVentas = cantidadVentas;
        this.ganancias = ganancias;
    }

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

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }

    public double getGanancias() {
        return ganancias;
    }

    public void setGanancias(double ganancias) {
        this.ganancias = ganancias;
    }
}
