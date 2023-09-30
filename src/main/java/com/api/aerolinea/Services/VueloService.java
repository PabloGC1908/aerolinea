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
import java.util.*;

/**
 * Servicio para gestionar operaciones relacionadas con vuelos.
 */
@Service
public class VueloService {
    private final VueloRepository vueloRepository;
    private final AerolineaRepository aerolineaRepository;
    private final CiudadRepository ciudadRepository;


    /**
     * Constructor de VueloService.
     *
     * @param vueloRepository    Repositorio de vuelos utilizado para acceder a los datos de los vuelos.
     * @param aerolineaRepository Repositorio de aerolíneas utilizado para acceder a los datos de las aerolíneas.
     * @param ciudadRepository    Repositorio de ciudades utilizado para acceder a los datos de las ciudades.
     */
    public VueloService(VueloRepository vueloRepository,
                        AerolineaRepository aerolineaRepository,
                        CiudadRepository ciudadRepository) {
        this.vueloRepository = vueloRepository;
        this.aerolineaRepository = aerolineaRepository;
        this.ciudadRepository = ciudadRepository;
    }

    public ResponseEntity<VueloRegistroDTO> getVueloConIds(UUID id) {
        Object[] vuelo = vueloRepository.findVueloIdsById(id).get(0);

        VueloRegistroDTO vueloRegistroDTO = new VueloRegistroDTO(
                (Integer) vuelo[0],
                (Integer) vuelo[1],
                (Integer) vuelo[2],
                (Integer) vuelo[3],
                (Float) vuelo[4],
                fechaToString((Date) vuelo[5]),
                fechaToString((Date) vuelo[6])
        );

        return ResponseEntity.ok(vueloRegistroDTO);
    }

    public ResponseEntity<List<VueloDTO>> getVuelos() {
        List<VueloDTO> vuelos = listarVuelos();

        if (!vuelos.isEmpty())
            return ResponseEntity.ok(vuelos);

        return ResponseEntity.noContent().build();
    }

    private List<VueloDTO> listarVuelos() {
        List<Object[]> results = vueloRepository.findAllVuelosConAerolineaYCiudades();
        List<VueloDTO> vuelos = new ArrayList<>();

        for (Object[] result : results) {
            VueloDTO vuelo = new VueloDTO(
                    (UUID) result[0],
                    (String) result[1],
                    (String) result[2],
                    (String) result[3],
                    (Integer) result[4],
                    (Date) result[5],
                    (Date) result[6],
                    (Float) result[7]

            );

            vuelos.add(vuelo);
        }

        return vuelos;
    }

    public ResponseEntity<Object> getVuelo(UUID uuid) {
        try {
            Object[] result = vueloRepository.findVueloConDetallesPorId(uuid).get(0);
            VueloDTO vuelo = new VueloDTO(
                    (UUID) result[0],
                    (String) result[1],
                    (String) result[2],
                    (String) result[3],
                    (Integer) result[4],
                    (Date) result[5],
                    (Date) result[6],
                    (Float) result[7]
            );


            return ResponseEntity.status(HttpStatus.FOUND).body(vuelo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Id no valido");
        }
    }

    public ResponseEntity<String> postVuelo(VueloRegistroDTO vueloRegistro) {
        try {
            Vuelo vuelo = Vuelo.builder()
                    .uuid(UUID.randomUUID())
                    .precio(vueloRegistro.precio())
                    .ciudadOrigen(buscarCiudad(vueloRegistro.ciudadOrigenId()))
                    .ciudadDestino(buscarCiudad(vueloRegistro.ciudadDestinoId()))
                    .aerolinea(buscarAerolinea(vueloRegistro.aerolineaId()))
                    .cantidadPasajes(vueloRegistro.cantidadPasajeros())
                    .fechaIda(parseFecha(vueloRegistro.fechaIda()))
                    .fechaVuelta(parseFecha(vueloRegistro.fechaVuelta()))
                    .build();

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

    /**
     * Convierte una fecha en formato Date a una cadena de texto en el formato "yyyy-MM-dd'T'HH:mm".
     *
     * @param fecha Fecha en formato Date.
     * @return Cadena de texto con la fecha formateada.
     */
    public String fechaToString(Date fecha) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return simpleDateFormat.format(fecha);
    }

    /**
     * Convierte una cadena de texto en formato "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" a un objeto Date.
     *
     * @param fecha Cadena de texto con la fecha en formato especificado.
     * @return Objeto Date representando la fecha.
     * @throws RuntimeException Si se produce un error durante la conversión.
     */
    private Date parseFecha(String fecha) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        try {
            return simpleDateFormat.parse(fecha);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Busca una ciudad por su ID en el repositorio de ciudades.
     *
     * @param id ID de la ciudad a buscar.
     * @return Objeto Ciudad si se encuentra, o lanza una excepción si no se encuentra.
     * @throws ChangeSetPersister.NotFoundException Si la ciudad no se encuentra en el repositorio.
     */
    private Ciudad buscarCiudad(Integer id) throws ChangeSetPersister.NotFoundException {
        return ciudadRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    /**
     * Busca una aerolínea por su ID en el repositorio de aerolíneas.
     *
     * @param id ID de la aerolínea a buscar.
     * @return Objeto Aerolinea si se encuentra, o lanza una excepción si no se encuentra.
     * @throws ChangeSetPersister.NotFoundException Si la aerolínea no se encuentra en el repositorio.
     */
    private Aerolinea buscarAerolinea(Integer id) throws ChangeSetPersister.NotFoundException {
        return aerolineaRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public ResponseEntity<String> patchVuelo(VueloRegistroDTO vueloRegistro, UUID uuid) {
        Optional<Vuelo> vuelo = vueloRepository.findById(uuid);

        if (vuelo.isPresent()) {
            try {
                Vuelo vueloModificado = vuelo.get();
                vueloModificado.setAerolinea(buscarAerolinea(vueloRegistro.aerolineaId()));
                vueloModificado.setCiudadOrigen(buscarCiudad(vueloRegistro.ciudadOrigenId()));
                vueloModificado.setCiudadDestino(buscarCiudad(vueloRegistro.ciudadDestinoId()));
                vueloModificado.setPrecio(vueloRegistro.precio());
                vueloModificado.setCantidadPasajes(vueloRegistro.cantidadPasajeros());
                vueloModificado.setFechaIda(parseFecha(vueloRegistro.fechaIda()));
                vueloModificado.setFechaVuelta(parseFecha(vueloRegistro.fechaVuelta()));

                vueloRepository.save(vueloModificado);

                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Vuelo modificado exitosamente");
            } catch (ChangeSetPersister.NotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
            }
        } else
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Vuelo no encontrado");
    }

    public ResponseEntity<String> deleteVuelo(UUID uuid) {
        vueloRepository.deleteById(uuid);
        return ResponseEntity.ok("Vuelo eliminado exitosamente");
    }

}
