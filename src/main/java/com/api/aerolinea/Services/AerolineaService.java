package com.api.aerolinea.Services;

import com.api.aerolinea.DTOs.AerolineaDTO;
import com.api.aerolinea.Repositories.AerolineaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AerolineaService {
    private final AerolineaRepository aerolineaRepository;

    public AerolineaService(AerolineaRepository aerolineaRepository) {
        this.aerolineaRepository = aerolineaRepository;
    }

    public ResponseEntity<List<AerolineaDTO>> getAerolineas() {
        List<AerolineaDTO> aerolineas = listarAerolineas();

        if (aerolineas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(aerolineas);
        }
    }


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
