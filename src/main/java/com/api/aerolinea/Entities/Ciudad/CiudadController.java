package com.api.aerolinea.Entities.Ciudad;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/ciudades")
public class CiudadController {
    private final CiudadService ciudadService;

    public CiudadController(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @GetMapping
    public List<Ciudad> getCiudades() {
        return ciudadService.getCiudades();
    }

    @GetMapping("/{id}")
    public List<Ciudad> getCiudadesPorPais(@PathVariable Integer id) {
        return ciudadService.findCiudadesPorPais(id);
    }
}
