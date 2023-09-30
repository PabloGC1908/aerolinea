package com.api.aerolinea.Services;

import com.api.aerolinea.DTOs.CiudadDTO;
import com.api.aerolinea.Entities.Ciudad;
import com.api.aerolinea.Repositories.CiudadRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para gestionar operaciones relacionadas con ciudades.
 */
@Service
public class CiudadService {
    private final CiudadRepository ciudadRepository;

    /**
     * Constructor de CiudadService.
     *
     * @param ciudadRepository Repositorio de ciudades utilizado para acceder a los datos de las ciudades.
     */
    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    /**
     * Obtiene todas las ciudades disponibles.
     *
     * @return ResponseEntity que contiene la lista de ciudades si existen, o una respuesta de contenido vacío si no existen.
     */
    public ResponseEntity<List<CiudadDTO>> getCiudades() {
        List<CiudadDTO> ciudades = listarCiudades();

        if (ciudades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ciudades);
    }

    /**
     * Lista todas las ciudades disponibles.
     *
     * @return Lista de objetos CiudadDTO que representan las ciudades.
     */
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

    /**
     * Busca y devuelve todas las ciudades de un país específico.
     *
     * @param id ID del país del que se desean obtener las ciudades.
     * @return Lista de objetos Ciudad que representan las ciudades del país.
     */
    public List<Ciudad> findCiudadesPorPais(Integer id) {
        return ciudadRepository.findAllCityInPais(id);
    }
}

