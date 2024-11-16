package com.example.eventoWebUsc.entity;

public class Asistencia {
    private String id; // Identificador único de la asistencia
    private String idEvento; // Identificador del evento
    private String idUsuario; // Identificador del usuario
    private boolean asistio; // Indicador de si el usuario asistió al evento

    // Constructor vacío (requerido por Firebase)
    public Asistencia() {}

    // Constructor con parámetros
    public Asistencia(String id, String idEvento, String idUsuario, boolean asistio) {
        this.id = id;
        this.idEvento = idEvento;
        this.idUsuario = idUsuario;
        this.asistio = asistio;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isAsistio() {
        return asistio;
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }
}