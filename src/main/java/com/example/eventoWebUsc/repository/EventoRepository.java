package com.example.eventoWebUsc.repository;

import com.example.eventoWebUsc.entity.Evento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public class EventoRepository {

    // Referencia a la base de datos de Firebase
    private final DatabaseReference databaseReference;

    // Constructor para inicializar la referencia
    public EventoRepository() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference("eventos");
    }

    // Método para crear o guardar un evento
    public CompletableFuture<Void> guardarEvento(Evento evento) {
        return CompletableFuture.runAsync(() -> {
            if (evento.getId() == null || evento.getId().isEmpty()) {
                evento.setId(databaseReference.push().getKey());
            }
            databaseReference.child(evento.getId()).setValueAsync(evento);
        });
    }
    

    // Método para leer un evento por su ID
    public CompletableFuture<Evento> obtenerEvento(String id) {
        CompletableFuture<Evento> future = new CompletableFuture<>();
        databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Evento evento = dataSnapshot.getValue(Evento.class);
                    future.complete(evento);
                } else {
                    future.complete(null); // El evento no existe
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });
        return future;
    }

    // Método para actualizar un evento
    public CompletableFuture<Void> actualizarEvento(Evento evento) {
        return guardarEvento(evento); // La actualización es similar al guardado en Firebase
    }

    // Método para eliminar un evento por su ID
    public CompletableFuture<Void> eliminarEvento(String id) {
        return CompletableFuture.runAsync(() -> {
            databaseReference.child(id).removeValueAsync();
        });
    }

    public void save(Evento evento) {
        databaseReference.child(evento.getId()).setValueAsync(evento);
    }

    // Método para obtener todos los eventos
    public CompletableFuture<List<Evento>> findAllAsync() {
        CompletableFuture<List<Evento>> future = new CompletableFuture<>();
        List<Evento> eventos = new ArrayList<>();
    
        System.out.println("Iniciando carga de eventos desde Firebase...");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("Datos obtenidos de Firebase, procesando...");
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Evento evento = snapshot.getValue(Evento.class);
                    if (evento != null) {
                        eventos.add(evento);
                        System.out.println("Evento encontrado: " + evento.getNombre());
                    }
                }
                System.out.println("Carga de eventos completada.");
                future.complete(eventos);
            }
    
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error al cargar eventos desde Firebase: " + databaseError.getMessage());
                future.completeExceptionally(new RuntimeException("Error al cargar eventos", databaseError.toException()));
            }
        });
    
        return future;
    }
    
}
