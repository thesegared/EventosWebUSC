package com.example.eventoWebUsc.entity;

public class Evento {
    private String id;
    private String nombre;
    private String descripcion;
    private String fecha;
    private String facultad;
    private String valor;
    private String lugar;
    private String imagenUrl;

    // Constructor vacío (obligatorio para Firebase y frameworks)
    public Evento() {
    }

    // Constructor con los campos necesarios
    public Evento(String id, String nombre, String descripcion, String fecha, String facultad, String valor, String lugar, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.facultad = facultad;
        this.valor = valor;
        this.lugar = lugar;
        this.imagenUrl = imagenUrl;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
