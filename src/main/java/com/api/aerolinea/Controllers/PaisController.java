package com.api.aerolinea.Controllers;

import com.api.aerolinea.Entities.Pais;
import com.api.aerolinea.Services.PaisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paises")
public class PaisController {
    private final PaisService paisService;

    public PaisController(PaisService paisService) {
        this.paisService = paisService;
    }

    @GetMapping
    public ResponseEntity<List<Pais>> getPaises() {
        return ResponseEntity.ok(paisService.getPaises());
    }

    @PostMapping
    public ResponseEntity<String> postPais(@RequestBody Pais pais) {
        return ResponseEntity.ok(paisService.postPais(pais));
    }
}
