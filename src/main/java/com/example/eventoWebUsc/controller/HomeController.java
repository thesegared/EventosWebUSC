package com.example.eventoWebUsc.controller;

import com.example.eventoWebUsc.entity.Evento;
import com.example.eventoWebUsc.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/")
    public String index(Model model) {
        try {
            System.out.println("Cargando eventos para la página principal...");
            List<Evento> eventos = eventoService.obtenerTodosLosEventos();
            model.addAttribute("eventos", eventos);
            return "index"; // Vista para mostrar eventos al público
        } catch (Exception e) {
            System.err.println("Error al cargar eventos: " + e.getMessage());
            model.addAttribute("error", "No se pudieron cargar los eventos: " + e.getMessage());
            return "error";
        }
    }
}
