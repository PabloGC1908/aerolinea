package com.api.aerolinea.Services;

import com.api.aerolinea.DTOs.BoletoDTO;
import com.api.aerolinea.DTOs.BoletoRegistroDTO;
import com.api.aerolinea.DTOs.ReservasDTO;
import com.api.aerolinea.Entities.Boleto;
import com.api.aerolinea.Entities.User;
import com.api.aerolinea.Entities.Vuelo;
import com.api.aerolinea.Repositories.BoletoRepository;
import com.api.aerolinea.Repositories.UserRepository;
import com.api.aerolinea.Repositories.VueloRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Servicio para gestionar operaciones relacionadas con boletos.
 */
@Service
public class BoletoService {
    private final BoletoRepository boletoRepository;
    private final UserRepository userRepository;
    private final VueloRepository vueloRepository;

    /**
     * Constructor de BoletoService.
     *
     * @param boletoRepository Repositorio de boletos utilizado para acceder a los datos de los boletos.
     * @param userRepository   Repositorio de usuarios utilizado para acceder a los datos de los usuarios.
     * @param vueloRepository  Repositorio de vuelos utilizado para acceder a los datos de los vuelos.
     */
    public BoletoService(BoletoRepository boletoRepository,
                         UserRepository userRepository,
                         VueloRepository vueloRepository) {
        this.boletoRepository = boletoRepository;
        this.userRepository = userRepository;
        this.vueloRepository = vueloRepository;
    }

    /**
     * Obtiene los boletos de un usuario por su ID.
     *
     * @param id ID del usuario.
     * @return ResponseEntity que contiene la lista de boletos si existen, o una respuesta de error si no existen.
     */
    public ResponseEntity<List<BoletoDTO>> getBoletoUsuario(UUID id) {
        try {
            List<Object[]> resultSet = boletoRepository.findBoletoByUsuarioId(id);
            return getListaBoletos(resultSet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Obtiene todos los boletos disponibles.
     *
     * @return ResponseEntity que contiene la lista de boletos si existen, o una respuesta de error si no existen.
     */
    public ResponseEntity<List<BoletoDTO>> getBoletos() {
        try {
            List<Object[]> resultSet = boletoRepository.findAllBoletos();
            return getListaBoletos(resultSet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Crea un nuevo boleto y lo guarda en el repositorio.
     *
     * @param boletoRegistro Datos del boleto a crear.
     * @return ResponseEntity con un mensaje de éxito si se crea el boleto, o una respuesta de error si falla la creación.
     */
    public ResponseEntity<String> postBoleto(BoletoRegistroDTO boletoRegistro) {
        try {
            Boleto boleto = Boleto.builder()
                    .id(UUID.randomUUID())
                    .fechaCompra(new Date())
                    .usuario(buscarUsuario(boletoRegistro.usuarioId()))
                    .vuelo(buscarVuelo(boletoRegistro.vueloId()))
                    .build();

            boletoRepository.save(boleto);

            return ResponseEntity.ok("Boleto creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Busca un usuario por su ID en el repositorio de usuarios.
     *
     * @param id ID del usuario a buscar.
     * @return Objeto User si se encuentra, o lanza una excepción si no se encuentra.
     * @throws ChangeSetPersister.NotFoundException Si el usuario no se encuentra en el repositorio.
     */
    private User buscarUsuario(UUID id) throws ChangeSetPersister.NotFoundException {
        return userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    /**
     * Busca un vuelo por su ID en el repositorio de vuelos.
     *
     * @param id ID del vuelo a buscar.
     * @return Objeto Vuelo si se encuentra, o lanza una excepción si no se encuentra.
     * @throws ChangeSetPersister.NotFoundException Si el vuelo no se encuentra en el repositorio.
     */
    private Vuelo buscarVuelo(UUID id) throws ChangeSetPersister.NotFoundException {
        return vueloRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    /**
     * Elimina un boleto por su ID.
     *
     * @param id ID del boleto a eliminar.
     * @return ResponseEntity con un mensaje de éxito si se elimina el boleto, o una respuesta de error si no se encuentra.
     */
    public ResponseEntity<String> deleteBoleto(UUID id) {
        Optional<Boleto> boleto = boletoRepository.findById(id);

        if (boleto.isPresent()) {
            boletoRepository.deleteById(id);
            return ResponseEntity.ok("Boleto eliminado correctamente");
        } else {
            return ResponseEntity.badRequest().body("Boleto no encontrado");
        }
    }

    /**
     * Convierte un resultado de consulta en una lista de objetos BoletoDTO.
     *
     * @param resultSet Resultado de la consulta a la base de datos.
     * @return ResponseEntity que contiene la lista de boletos si existen, o una respuesta de error si no existen.
     */
    private ResponseEntity<List<BoletoDTO>> getListaBoletos(List<Object[]> resultSet) {
        List<BoletoDTO> boletos = new ArrayList<>();

        for (Object[] result : resultSet) {
            BoletoDTO boleto = new BoletoDTO(
                    (UUID) result[0],
                    (String) result[1],
                    (String) result[2],
                    (Date) result[3],
                    (Date) result[4],
                    (Float) result[5]
            );

            boletos.add(boleto);
        }

        return ResponseEntity.ok(boletos);
    }

    public ResponseEntity<List<ReservasDTO>> getReservaVuelos() {
        List<Object[]> resultSet = boletoRepository.findAllReservaVuelos();
        List<ReservasDTO> boletos = new ArrayList<>();

        for (Object[] result : resultSet) {
            ReservasDTO boleto = new ReservasDTO(
                    (UUID) result[0],
                    (String) result[1],
                    (String) result[2],
                    (Date) result[3],
                    (Date) result[4],
                    (Float) result[5]
            );

            boletos.add(boleto);
        }

        return ResponseEntity.ok(boletos);
    }
}