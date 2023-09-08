package com.api.aerolinea.Controllers;

import com.api.aerolinea.DTOs.BoletoDTO;
import com.api.aerolinea.DTOs.BoletoRegistroDTO;
import com.api.aerolinea.Services.BoletoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/boletos")
public class BoletoController {
    private final BoletoService boletoService;

    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BoletoDTO>> getBoletosPorUsuario(@PathVariable UUID id) {
        return boletoService.getBoletoUsuario(id);
    }

    @GetMapping
    public ResponseEntity<List<BoletoDTO>> getBoletos() {
        return boletoService.getBoletos();
    }

    @PostMapping
    public ResponseEntity<String> postBoleto(@RequestBody BoletoRegistroDTO boleto) {
        return boletoService.postBoleto(boleto);
    }
}
