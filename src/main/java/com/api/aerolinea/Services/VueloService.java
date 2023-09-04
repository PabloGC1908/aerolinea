package com.api.aerolinea.Services;

import com.api.aerolinea.DTOs.VueloDTO;
import com.api.aerolinea.Entities.Vuelo;
import com.api.aerolinea.Repositories.VueloRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VueloService {
    private final VueloRepository vueloRepository;

    public VueloService(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    public List<VueloDTO> getVuelos() {
        List<Object[]> results = vueloRepository.findAllVuelosConAerolineaYCiudades();
        List<VueloDTO> vuelos = new ArrayList<>();

        for (Object[] result : results) {
            VueloDTO vuelo = new VueloDTO(
                    (UUID) result[0],
                    (String) result[1],
                    (String) result[2],
                    (String) result[3],
                    (Date) result[4],
                    (Date) result[5],
                    (Float) result[6]
            );

            vuelos.add(vuelo);
        }

        return vuelos;
    }

    public Object getVuelo(UUID uuid) {

        return vueloRepository.findVueloPorId(uuid).get(0);
    }


}
