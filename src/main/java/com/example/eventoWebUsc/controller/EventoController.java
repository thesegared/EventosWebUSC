package com.example.eventoWebUsc.controller;

import com.example.eventoWebUsc.entity.Evento;
import com.example.eventoWebUsc.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    // Inyección del servicio de eventos
    @Autowired
    private EventoService eventoService;

    // Endpoint para crear un nuevo evento (HTTP POST)
    @PostMapping
    public CompletableFuture<ResponseEntity<String>> crearEvento(@RequestBody Evento evento) {
        return eventoService.crearEvento(evento)
                .thenApply(v -> ResponseEntity.ok("Evento creado exitosamente"))
                .exceptionally(ex -> ResponseEntity.status(500).body("Error al crear el evento: " + ex.getMessage()));
    }

    // Endpoint para obtener un evento por su ID (HTTP GET)
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<? extends Object>> obtenerEvento(@PathVariable String id) {
        return eventoService.obtenerEvento(id)
                .thenApply(evento -> {
                    if (evento != null) {
                        return ResponseEntity.ok(evento);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                })
                .exceptionally(ex -> {
                    // Devuelve una respuesta con un objeto Evento nulo o vacío y un estado 500
                    return ResponseEntity.status(500).body(null);
                });
    }

    // Endpoint para actualizar un evento (HTTP PUT)
    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<String>> actualizarEvento(@PathVariable String id, @RequestBody Evento evento) {
        evento.setId(id); // Asegúrate de que el ID del evento esté configurado correctamente
        return eventoService.actualizarEvento(evento)
                .thenApply(v -> ResponseEntity.ok("Evento actualizado exitosamente"))
                .exceptionally(ex -> ResponseEntity.status(500).body("Error al actualizar el evento: " + ex.getMessage()));
    }

    // Endpoint para eliminar un evento por su ID (HTTP DELETE)
    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<String>> eliminarEvento(@PathVariable String id) {
        return eventoService.eliminarEvento(id)
                .thenApply(v -> ResponseEntity.ok("Evento eliminado exitosamente"))
                .exceptionally(ex -> ResponseEntity.status(500).body("Error al eliminar el evento: " + ex.getMessage()));
    }
}