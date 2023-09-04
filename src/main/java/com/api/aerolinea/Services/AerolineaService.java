package com.api.aerolinea.Services;

import com.api.aerolinea.Entities.Aerolinea;
import com.api.aerolinea.Repositories.AerolineaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AerolineaService {
    private final AerolineaRepository aerolineaRepository;

    public AerolineaService(AerolineaRepository aerolineaRepository) {
        this.aerolineaRepository = aerolineaRepository;
    }

    public List<Aerolinea> getAerolineas() {
        return aerolineaRepository.findAll();
    }
}
