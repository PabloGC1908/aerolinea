package com.api.aerolinea.Controllers;

import com.api.aerolinea.DTOs.VueloDTO;
import com.api.aerolinea.Services.VueloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vuelos")
public class VueloController {
    private final VueloService vueloService;

    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @GetMapping
    public ResponseEntity<List<VueloDTO>> getVuelos() {
        List<VueloDTO> vuelos = vueloService.getVuelos();

        if (!vuelos.isEmpty())
            return ResponseEntity.ok(vuelos);

        return ResponseEntity.noContent().build();
    }
}
