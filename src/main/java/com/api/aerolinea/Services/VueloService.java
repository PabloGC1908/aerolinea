package com.api.aerolinea.Services;

import com.api.aerolinea.DTOs.VueloDTO;
import com.api.aerolinea.DTOs.VueloRegistroDTO;
import com.api.aerolinea.Entities.Aerolinea;
import com.api.aerolinea.Entities.Ciudad;
import com.api.aerolinea.Entities.Vuelo;
import com.api.aerolinea.Repositories.AerolineaRepository;
import com.api.aerolinea.Repositories.CiudadRepository;
import com.api.aerolinea.Repositories.VueloRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VueloService {
    private final VueloRepository vueloRepository;
    private final AerolineaRepository aerolineaRepository;
    private final CiudadRepository ciudadRepository;


    public VueloService(VueloRepository vueloRepository,
                        AerolineaRepository aerolineaRepository,
                        CiudadRepository ciudadRepository) {
        this.vueloRepository = vueloRepository;
        this.aerolineaRepository = aerolineaRepository;
        this.ciudadRepository = ciudadRepository;
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

    public ResponseEntity<String> postVuelo(VueloRegistroDTO vueloRegistro) {
        try {
            Vuelo vuelo = new Vuelo();
            vuelo.setUuid(UUID.randomUUID());
            vuelo.setPrecio(vueloRegistro.precio());
            vuelo.setFechaIda(parseDate(vueloRegistro.fechaIda()));
            vuelo.setFechaVuelta(parseDate(vueloRegistro.fechaVuelta()));

            Aerolinea aerolinea = aerolineaRepository.findById(vueloRegistro.aerolineaId())
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
            vuelo.setAerolinea(aerolinea);

            Ciudad ciudadOrigen = ciudadRepository.findById(vueloRegistro.ciudadOrigenId())
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
            vuelo.setCiudadOrigen(ciudadOrigen);

            Ciudad ciudadDestino = ciudadRepository.findById(vueloRegistro.ciudadDestinoId())
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
            vuelo.setCiudadDestino(ciudadDestino);

            vueloRepository.save(vuelo);

            return ResponseEntity.ok("Vuelo creado exitosamente");
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el vuelo: " + e.getMessage());
        }
    }

    private Date parseDate(String fecha) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        try {
            return simpleDateFormat.parse(fecha);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
