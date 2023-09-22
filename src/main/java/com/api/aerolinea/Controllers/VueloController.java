package com.api.aerolinea.Controllers;

import com.api.aerolinea.DTOs.VueloDTO;
import com.api.aerolinea.DTOs.VueloRegistroDTO;
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

    @GetMapping("/ids/{id}")
    public ResponseEntity<VueloRegistroDTO> getVueloConIds(@Valid @PathVariable UUID id) {
        return vueloService.getVueloConIds(id);
    }

    @GetMapping
    public ResponseEntity<List<VueloDTO>> getVuelos() {
        return vueloService.getVuelos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVuelo(@Valid @PathVariable UUID id) {
        return vueloService.getVuelo(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> postVuelo(@Valid @RequestBody VueloRegistroDTO vuelo) {
        return vueloService.postVuelo(vuelo);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> putVuelo(@Valid @RequestBody VueloRegistroDTO vuelo, @PathVariable UUID id) {
        System.out.println(vuelo);
        return vueloService.patchVuelo(vuelo, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVuelo(@Valid @PathVariable UUID id) {
        return vueloService.deleteVuelo(id);
    }


}
