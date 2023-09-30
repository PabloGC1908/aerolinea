package com.api.aerolinea.Controllers;

import com.api.aerolinea.DTOs.TarjetaRegistroDTO;
import com.api.aerolinea.Services.TarjetaCreditoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaCreditoController {
    private final TarjetaCreditoService tarjetaCreditoService;
    private static final Logger logger = LoggerFactory.getLogger(TarjetaCreditoController.class);

    public TarjetaCreditoController(TarjetaCreditoService tarjetaCreditoService) {
        this.tarjetaCreditoService = tarjetaCreditoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getTarjetaPorUsuario(@PathVariable UUID id){
        logger.info("Enviando tarjeta con id: {}", id);
        return tarjetaCreditoService.getNombreTarjetaPorUsuarioId(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> postTarjetaDeUsuario(@Valid @RequestBody TarjetaRegistroDTO tarjeta) {
        logger.info("Ingresando nueva tarjeta: {}", tarjeta);
        return tarjetaCreditoService.postTarjetaDeUsuario(tarjeta);
    }
}
