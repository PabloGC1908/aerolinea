package com.api.aerolinea.Controllers;

import com.api.aerolinea.DTOs.VueloDTO;
import com.api.aerolinea.DTOs.VueloRegistroDTO;
import com.api.aerolinea.Services.VueloService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vuelos")
public class VueloController {
    private final VueloService vueloService;
    private static final Logger logger = LoggerFactory.getLogger(VueloController.class);

    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @GetMapping("/ids/{id}")
    public ResponseEntity<VueloRegistroDTO> getVueloConIds(@Valid @PathVariable UUID id) {
        logger.info("Enviando ids de vuelo con id: {}", id);
        return vueloService.getVueloConIds(id);
    }

    @GetMapping
    public ResponseEntity<List<VueloDTO>> getVuelos() {
        logger.info("Enviando lista de vuelos");
        return vueloService.getVuelos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVuelo(@Valid @PathVariable UUID id) {
        logger.info("Enviando vuelos con id: {}", id);
        return vueloService.getVuelo(id);
    }

    @PostMapping
    @Transactional
    @Secured({"ADMIN"})
    public ResponseEntity<String> postVuelo(@Valid @RequestBody VueloRegistroDTO vuelo) {
        logger.info("Recibiendo vuelo: {}", vuelo);
        return vueloService.postVuelo(vuelo);
    }

    @PutMapping("/{id}")
    @Transactional
    @Secured({"ADMIN"})
    public ResponseEntity<String> putVuelo(@Valid @RequestBody VueloRegistroDTO vuelo, @PathVariable UUID id) {
        logger.info("Actualizando vuelo con id: {}", id);
        return vueloService.patchVuelo(vuelo, id);
    }

    @DeleteMapping("/{id}")
    @Secured({"ADMIN"})
    public ResponseEntity<String> deleteVuelo(@Valid @PathVariable UUID id) {
        logger.warn("Eliminado el vuelo con id: {}", id);
        return vueloService.deleteVuelo(id);
    }
}
