package com.api.aerolinea.Services;

import com.api.aerolinea.Entities.Ciudad;
import com.api.aerolinea.Repositories.CiudadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadService {
    private final CiudadRepository ciudadRepository;

    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public List<Ciudad> getCiudades() {
        return ciudadRepository.findAll();
    }

    public List<Ciudad> findCiudadesPorPais(Integer id) {
        return ciudadRepository.findAllCityInPais(id);
    }
}
