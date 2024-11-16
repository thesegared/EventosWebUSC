package com.example.eventoWebUsc.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eventoWebUsc.entity.Evento;
import com.example.eventoWebUsc.repository.EventoRepository;

@Service
public class EventoService {

    // Inyección del repositorio de eventos
    @Autowired
    private EventoRepository eventoRepository;

    // Método para crear un nuevo evento
    public CompletableFuture<Void> crearEvento(Evento evento) {
        // Puedes agregar aquí cualquier regla de negocio necesaria antes de guardar el evento
        return eventoRepository.guardarEvento(evento);
    }

    // Método para obtener un evento por su ID
    public CompletableFuture<Evento> obtenerEvento(String id) {
        return eventoRepository.obtenerEvento(id);
    }

    // Método para obtener una lista de eventos (implementación personalizada si es necesaria)
    public CompletableFuture<List<Evento>> obtenerTodosLosEventos() {
        // Aquí puedes definir la lógica para obtener y procesar una lista de eventos si es necesario
        // Firebase no tiene un método directo para obtener todos los eventos, así que necesitarías una implementación personalizada
        return CompletableFuture.completedFuture(null); // Placeholder (cambiar según la implementación)
    }

    // Método para actualizar un evento existente
    public CompletableFuture<Void> actualizarEvento(Evento evento) {
        // Puedes agregar validaciones o reglas de negocio antes de actualizar
        return eventoRepository.actualizarEvento(evento);
    }

    // Método para eliminar un evento por su ID
    public CompletableFuture<Void> eliminarEvento(String id) {
        return eventoRepository.eliminarEvento(id);
    }
}