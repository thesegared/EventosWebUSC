package com.example.eventoWebUsc.service;

import com.example.eventoWebUsc.entity.Evento;
import com.example.eventoWebUsc.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    // Crear evento con imagen
    public void crearEventoConImagen(Evento evento, MultipartFile imagen) {
        try {
            String imageUrl = guardarImagen(imagen); // Simula la subida de la imagen
            evento.setImagenUrl(imageUrl);
            guardarEvento(evento);
            System.out.println("Evento guardado con nombre: " + evento.getNombre());
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen", e);
        }
    }

    
    public void crearEvento(Evento evento) {
        // Puedes incluir validaciones o lógica adicional aquí
        eventoRepository.guardarEvento(evento);
    }

    public List<Evento> obtenerTodosLosEventos() {
        try {
            return eventoRepository.findAllAsync().get();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los eventos", e);
        }
    }
    // Simula datos de eventos para pruebas
    private List<Evento> cargarDatosSimulados() {
        List<Evento> eventosSimulados = new ArrayList<>();
        eventosSimulados.add(new Evento("1", "Evento Simulado 1", "Descripción del evento simulado 1", "2024-11-25", "Facultad 1", "10000", "Lugar 1", "URL_SIMULADA_1"));
        eventosSimulados.add(new Evento("2", "Evento Simulado 2", "Descripción del evento simulado 2", "2024-11-30", "Facultad 2", "20000", "Lugar 2", "URL_SIMULADA_2"));
        eventosSimulados.add(new Evento("3", "Evento Simulado 3", "Descripción del evento simulado 3", "2024-12-10", "Facultad 3", "30000", "Lugar 3", "URL_SIMULADA_3"));
        return eventosSimulados;
    }

    // Guardar evento
    public void guardarEvento(Evento evento) {
        try {
            eventoRepository.guardarEvento(evento).get();
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el evento", e);
        }
    }

    // Guardar imagen (simulada)
    private String guardarImagen(MultipartFile imagen) throws IOException {
        String fileName = imagen.getOriginalFilename();
        System.out.println("Guardando imagen: " + fileName);
        return "ruta/simulada/" + fileName; // Cambiar por lógica real
    }

    // Obtener evento por ID
    public Evento obtenerEventoPorId(String id) {
        try {
            return eventoRepository.obtenerEvento(id).get();
        } catch (Exception e) {
            throw new RuntimeException("Evento no encontrado con ID: " + id, e);
        }
    }

    // Actualizar evento
    public void actualizarEvento(String id, Evento evento, MultipartFile imagen) {
        Evento eventoExistente = obtenerEventoPorId(id);
        eventoExistente.setNombre(evento.getNombre());
        if (imagen != null && !imagen.isEmpty()) {
            try {
                String imagenUrl = guardarImagen(imagen);
                eventoExistente.setImagenUrl(imagenUrl);
            } catch (IOException e) {
                throw new RuntimeException("Error al actualizar la imagen", e);
            }
        }
        guardarEvento(eventoExistente);
    }

    // Eliminar evento
    public void eliminarEvento(String id) {
        try {
            eventoRepository.eliminarEvento(id).get();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el evento con ID: " + id, e);
        }
    }
}
