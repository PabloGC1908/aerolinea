package com.api.aerolinea.Controllers;

import com.api.aerolinea.Entities.Aerolinea;
import com.api.aerolinea.Services.AerolineaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/aerolineas")
public class AerolineaController {
    private final AerolineaService aerolineaService;

    public AerolineaController(AerolineaService aerolineaService) {
        this.aerolineaService = aerolineaService;
    }

    @GetMapping
    public List<Aerolinea> getAerolineas() {
        return aerolineaService.getAerolineas();
    }
}
