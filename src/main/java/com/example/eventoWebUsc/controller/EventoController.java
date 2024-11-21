package com.example.eventoWebUsc.controller;

import com.example.eventoWebUsc.entity.Evento;
import com.example.eventoWebUsc.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // Rutas para gestión de eventos
    @GetMapping("/nuevo")
    public String formularioCrearEvento(Model model) {
        model.addAttribute("evento", new Evento());
        return "nuevo-evento";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarEvento(@PathVariable String id, Model model) {
        Evento evento = eventoService.obtenerEventoPorId(id);
        model.addAttribute("evento", evento);
        return "editar-evento";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarEvento(@PathVariable String id, @ModelAttribute Evento evento, @RequestParam("imagen") MultipartFile imagen) {
        eventoService.actualizarEvento(id, evento, imagen);
        return "redirect:/eventos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEvento(@PathVariable String id) {
        eventoService.eliminarEvento(id);
        return "redirect:/eventos";
    }

    @GetMapping("/{id}")
    public String detalleEvento(@PathVariable String id, Model model) {
        Evento evento = eventoService.obtenerEventoPorId(id);
        model.addAttribute("evento", evento);
        return "detalle-evento";
    }

    @PostMapping("/crear")
    public String crearEvento(@ModelAttribute Evento evento, RedirectAttributes redirectAttributes) {
        System.out.println("Intentando crear un evento con nombre: " + evento.getNombre());
        try {
            // Asegúrate de que el campo imagenUrl no sea nulo ni vacío
            if (evento.getImagenUrl() == null || evento.getImagenUrl().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "El campo de URL de la imagen es obligatorio");
                return "redirect:/eventos/nuevo";
            }
            eventoService.crearEvento(evento); // Guarda el evento con la URL de la imagen
            redirectAttributes.addFlashAttribute("mensaje", "Evento creado exitosamente");
            System.out.println("Evento creado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear el evento");
            System.err.println("Error al crear el evento: " + e.getMessage());
        }
        return "redirect:/eventos";
    }


    @GetMapping
    public String mostrarEventos(Model model) {
        try {
            List<Evento> eventos = eventoService.obtenerTodosLosEventos();
            model.addAttribute("eventos", eventos);
            return "eventos"; // Vista para gestión de eventos
        } catch (Exception e) {
            model.addAttribute("error", "No se pudieron cargar los eventos: " + e.getMessage());
            return "error";
        }
    }
}
