package com.api.aerolinea.Controllers;

import com.api.aerolinea.Services.TarjetaCreditoService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return tarjetaCreditoService.getTarjetaPorUsuario(id);
    }
}
