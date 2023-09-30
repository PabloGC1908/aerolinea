package com.api.aerolinea.Controllers;

import com.api.aerolinea.DTOs.BoletoDTO;
import com.api.aerolinea.DTOs.BoletoRegistroDTO;
import com.api.aerolinea.Services.BoletoService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/boletos")
public class BoletoController {
    private final BoletoService boletoService;
    private static final Logger logger = LoggerFactory.getLogger(BoletoController.class);

    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BoletoDTO>> getBoletosPorUsuario(@PathVariable UUID id) {
        logger.info("Enviando boleto con id: {}", id);
        return boletoService.getBoletoUsuario(id);
    }

    @GetMapping
    public ResponseEntity<List<BoletoDTO>> getBoletos() {
        logger.info("Enviando lista de boletos");
        return boletoService.getBoletos();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> postBoleto(@RequestBody BoletoRegistroDTO boleto) {
        logger.info("Ingresando boleto: {}", boleto);
        return boletoService.postBoleto(boleto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoleto(@PathVariable UUID id) {
        logger.warn("Eliminando boleto con id: {}", id);
        return boletoService.deleteBoleto(id);
    }
}
