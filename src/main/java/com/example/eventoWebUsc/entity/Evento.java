package com.example.eventoWebUsc.entity;

import java.time.LocalDate;

public class Evento {
    private String id; // Identificador único del evento
    private String nombre; // Nombre del evento
    private String descripcion; // Descripción del evento
    private LocalDate fecha; // Fecha del evento
    private String organizador; // Nombre del organizador del evento

    // Constructor vacío (requerido por Firebase)
    public Evento() {}

    // Constructor con parámetros
    public Evento(String id, String nombre, String descripcion, LocalDate fecha, String organizador) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.organizador = organizador;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }
}