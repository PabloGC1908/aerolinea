package com.api.aerolinea.Services;

import com.api.aerolinea.DTOs.AerolineaDTO;
import com.api.aerolinea.Repositories.AerolineaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para gestionar operaciones relacionadas con aerolíneas.
 */
@Service
public class AerolineaService {
    private final AerolineaRepository aerolineaRepository;

    /**
     * Constructor de AerolineaService.
     *
     * @param aerolineaRepository Repositorio de aerolíneas utilizado para acceder a los datos de las aerolíneas.
     */
    public AerolineaService(AerolineaRepository aerolineaRepository) {
        this.aerolineaRepository = aerolineaRepository;
    }

    /**
     * Obtiene la lista de aerolíneas y devuelve una respuesta HTTP.
     *
     * @return ResponseEntity que contiene una lista de objetos AerolineaDTO en caso de éxito,
     *         o ResponseEntity sin contenido si la lista está vacía.
     */
    public ResponseEntity<List<AerolineaDTO>> getAerolineas() {
        List<AerolineaDTO> aerolineas = listarAerolineas();

        if (aerolineas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(aerolineas);
        }
    }

    /**
     * Obtiene y lista las aerolíneas desde el repositorio.
     *
     * @return Lista de objetos AerolineaDTO que representan las aerolíneas.
     */
    public List<AerolineaDTO> listarAerolineas() {
        List<Object[]> resultSet = aerolineaRepository.findAllAerolineas();
        List<AerolineaDTO> aerolineas = new ArrayList<>();

        for (Object[] result: resultSet) {
            AerolineaDTO aerolinea = new AerolineaDTO(
                    (Integer) result[0],
                    (String) result[1]
            );

            aerolineas.add(aerolinea);
        }

        return aerolineas;
    }
}

