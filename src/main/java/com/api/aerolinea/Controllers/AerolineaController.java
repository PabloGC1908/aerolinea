package com.api.aerolinea.Controllers;

import com.api.aerolinea.DTOs.AerolineaDTO;
import com.api.aerolinea.Services.AerolineaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/aerolineas")
public class AerolineaController {
    private final AerolineaService aerolineaService;
    private static final Logger logger = LoggerFactory.getLogger(AerolineaController.class);

    public AerolineaController(AerolineaService aerolineaService) {
        this.aerolineaService = aerolineaService;
    }

    @GetMapping
    public ResponseEntity<List<AerolineaDTO>> getAerolineas() {
        logger.info("Enviando lista de aerolineas");
        return aerolineaService.getAerolineas();
    }
}
