package com.api.aerolinea.Controllers;

import com.api.aerolinea.DTOs.TarjetaRegistroDTO;
import com.api.aerolinea.Services.TarjetaCreditoService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaCreditoController {
    private final TarjetaCreditoService tarjetaCreditoService;

    public TarjetaCreditoController(TarjetaCreditoService tarjetaCreditoService) {
        this.tarjetaCreditoService = tarjetaCreditoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getTarjetaPorUsuario(@PathVariable UUID id){
        return tarjetaCreditoService.getNombreTarjetaPorUsuarioId(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> postTarjetaDeUsuario(@RequestBody TarjetaRegistroDTO tarjeta) {
        return tarjetaCreditoService.postTarjetaDeUsuario(tarjeta);
    }
}
