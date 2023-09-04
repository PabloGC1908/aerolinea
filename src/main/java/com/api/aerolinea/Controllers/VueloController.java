package com.api.aerolinea.Controllers;

import com.api.aerolinea.DTOs.VueloDTO;
import com.api.aerolinea.DTOs.VueloRegistroDTO;
import com.api.aerolinea.Services.VueloService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return vueloService.getVuelos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVuelo(@Valid @PathVariable String id) {
        return vueloService.getVuelo(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> postVuelo(@Valid @RequestBody VueloRegistroDTO vuelo) {

        return vueloService.postVuelo(vuelo);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<String> patchVuelo(@Valid @RequestBody VueloRegistroDTO vuelo, @PathVariable String id) {
        return vueloService.patchVuelo(vuelo, id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteVuelo(@Valid @PathVariable String id) {
        return vueloService.deleteVuelo(id);
    }


}
