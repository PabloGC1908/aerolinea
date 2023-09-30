package com.api.aerolinea.Controllers;

import com.api.aerolinea.Entities.Pais;
import com.api.aerolinea.Services.PaisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paises")
public class PaisController {
    private final PaisService paisService;
    private static final Logger logger = LoggerFactory.getLogger(PaisController.class);

    public PaisController(PaisService paisService) {
        this.paisService = paisService;
    }

    @GetMapping
    public ResponseEntity<List<Pais>> getPaises() {
        logger.info("Enviando lista de paises");
        return ResponseEntity.ok(paisService.getPaises());
    }

    @PostMapping
    public ResponseEntity<String> postPais(@RequestBody Pais pais) {
        logger.info("Recibiendo nuevo pais: {}", pais);
        return ResponseEntity.ok(paisService.postPais(pais));
    }
}
