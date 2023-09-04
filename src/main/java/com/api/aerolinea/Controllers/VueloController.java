package com.api.aerolinea.Controllers;

import com.api.aerolinea.DTOs.VueloDTO;
import com.api.aerolinea.Entities.Vuelo;
import com.api.aerolinea.Services.VueloService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVuelo(@Valid @PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Object vuelo = vueloService.getVuelo(uuid);
            return ResponseEntity.ok(vuelo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Id no valido");
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> postVuelo() {


        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<String> patchVuelo() {
        return ResponseEntity.noContent().build();
    }


}
