package com.api.aerolinea.Services;

import com.api.aerolinea.DTOs.TarjetaRegistroDTO;
import com.api.aerolinea.Entities.TarjetaCredito;
import com.api.aerolinea.Entities.User;
import com.api.aerolinea.Repositories.TarjetaCreditoRepository;
import com.api.aerolinea.Repositories.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Servicio para gestionar operaciones relacionadas con tarjetas de crédito.
 */
@Service
public class TarjetaCreditoService {
    private final TarjetaCreditoRepository tarjetaCreditoRepository;
    private final UserRepository userRepository;

    /**
     * Constructor de TarjetaCreditoService.
     *
     * @param tarjetaCreditoRepository Repositorio de tarjetas de crédito utilizado para acceder a los datos de las tarjetas.
     * @param userRepository          Repositorio de usuarios utilizado para acceder a los datos de los usuarios.
     */
    public TarjetaCreditoService(TarjetaCreditoRepository tarjetaCreditoRepository, UserRepository userRepository) {
        this.tarjetaCreditoRepository = tarjetaCreditoRepository;
        this.userRepository = userRepository;
    }

    /**
     * Obtiene el nombre de la tarjeta de crédito asociada a un usuario por su ID.
     *
     * @param id ID del usuario.
     * @return ResponseEntity que contiene el nombre de la tarjeta si existe, o una respuesta de error si no existe.
     */
    public ResponseEntity<String> getNombreTarjetaPorUsuarioId(UUID id) {
        String numeroTarjeta = tarjetaCreditoRepository.getNombreTarjetaByUsuarioId(id);

        if (numeroTarjeta != null) {
            return ResponseEntity.ok(numeroTarjeta);
        }
        return ResponseEntity.badRequest().body("No posee tarjetas");
    }

    /**
     * Crea una nueva tarjeta de crédito asociada a un usuario.
     *
     * @param tarjetaRegistro Datos de la tarjeta de crédito a registrar.
     * @return ResponseEntity con un mensaje de éxito si se crea la tarjeta, o una respuesta de error si falla la creación.
     */
    public ResponseEntity<String> postTarjetaDeUsuario(TarjetaRegistroDTO tarjetaRegistro) {
        try {
            int[] fechaPartida = separarFecha(tarjetaRegistro.fechaExpiracion());

            TarjetaCredito tarjeta = TarjetaCredito.builder()
                    .id(UUID.randomUUID())
                    .nombreTarjeta(tarjetaRegistro.nombreTarjeta())
                    .numeroTarjeta(tarjetaRegistro.numeroTarjeta())
                    .usuario(buscarUsuario(tarjetaRegistro.idUsuario()))
                    .mesExpiracion(fechaPartida[0])
                    .anioExpiracion(fechaPartida[1])
                    .cvv(tarjetaRegistro.cvv())
                    .build();

            tarjetaCreditoRepository.save(tarjeta);

            return ResponseEntity.ok("Tarjeta creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error al crear la tarjeta: " + e.getMessage());
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
     * Separa una cadena de texto de fecha en un arreglo de enteros.
     *
     * @param fecha Cadena de texto con la fecha en formato "yyyy-MM-dd".
     * @return Arreglo de enteros con las partes de la fecha (año, mes, día).
     */
    private int[] separarFecha(String fecha) {
        String[] partes = fecha.split("-");
        int[] result = new int[partes.length];

        for (int i = 0; i < partes.length; i++) {
            result[i] = Integer.parseInt(partes[i]);
        }

        return result;
    }
}