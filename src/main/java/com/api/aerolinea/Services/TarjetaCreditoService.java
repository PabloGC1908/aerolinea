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

@Service
public class TarjetaCreditoService {
    private final TarjetaCreditoRepository tarjetaCreditoRepository;
    private final UserRepository userRepository;

    public TarjetaCreditoService(TarjetaCreditoRepository tarjetaCreditoRepository, UserRepository userRepository) {
        this.tarjetaCreditoRepository = tarjetaCreditoRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> getNombreTarjetaPorUsuarioId(UUID id) {
        String numeroTarjeta = tarjetaCreditoRepository.getNombreTarjetaByUsuarioId(id);

        if (numeroTarjeta != null) {
            return ResponseEntity.ok(numeroTarjeta);
        }
        return ResponseEntity.badRequest().body("No posee tarjetas");
    }

    public ResponseEntity<String> postTarjetaDeUsuario(TarjetaRegistroDTO tarjetaRegistro) {
        try {
            TarjetaCredito tarjeta = TarjetaCredito.builder()
                    .id(UUID.randomUUID())
                    .nombreTarjeta(tarjetaRegistro.nombreTarjeta())
                    .numeroTarjeta(tarjetaRegistro.numeroTarjeta())
                    .usuario(buscarUsuario(tarjetaRegistro.idUsuario()))
                    .mesExpiracion(tarjetaRegistro.mesExpiracion())
                    .anioExpiracion(tarjetaRegistro.anioExpiracion())
                    .cvv(tarjetaRegistro.cvv())
                    .build();

            tarjetaCreditoRepository.save(tarjeta);

            return ResponseEntity.ok("Tarjeta creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error al crear la tarjeta: " + e.getMessage());
        }
    }

    private User buscarUsuario(UUID id) throws ChangeSetPersister.NotFoundException {
        return userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
}
