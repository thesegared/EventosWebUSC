package com.example.eventoWebUsc.entity;

public class Usuario {
    private String id; // Identificador único del usuario
    private String nombre; // Nombre del usuario
    private String correo; // Correo electrónico del usuario
    private String rol; // Rol del usuario (por ejemplo, "Estudiante", "Profesor", etc.)

    // Constructor vacío (requerido por Firebase)
    public Usuario() {}

    // Constructor con parámetros
    public Usuario(String id, String nombre, String correo, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}