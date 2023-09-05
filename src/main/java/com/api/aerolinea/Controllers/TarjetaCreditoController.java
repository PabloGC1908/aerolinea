package com.api.aerolinea.Controllers;

import com.api.aerolinea.Services.TarjetaCreditoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaCreditoController {
    private final TarjetaCreditoService tarjetaCreditoService;

    public TarjetaCreditoController(TarjetaCreditoService tarjetaCreditoService) {
        this.tarjetaCreditoService = tarjetaCreditoService;
    }


}
