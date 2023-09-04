package com.api.aerolinea.Services;

import com.api.aerolinea.DTOs.CiudadDTO;
import com.api.aerolinea.Entities.Ciudad;
import com.api.aerolinea.Repositories.CiudadRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiudadService {
    private final CiudadRepository ciudadRepository;

    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public ResponseEntity<List<CiudadDTO>> getCiudades() {
        List<CiudadDTO> ciudades = listarCiudades();

        if (ciudades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ciudades);
    }

    private List<CiudadDTO> listarCiudades() {
        List<Object[]> results = ciudadRepository.findAllCities();
        List<CiudadDTO> ciudades = new ArrayList<>();

        for (Object[] result : results) {
            CiudadDTO ciudad = new CiudadDTO(
                    (Integer) result[0],
                    (String) result[1]
            );

            ciudades.add(ciudad);
        }

        return ciudades;
    }

    public List<Ciudad> findCiudadesPorPais(Integer id) {
        return ciudadRepository.findAllCityInPais(id);
    }
}
