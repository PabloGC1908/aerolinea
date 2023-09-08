package com.api.aerolinea.Services;

import com.api.aerolinea.DTOs.BoletoDTO;
import com.api.aerolinea.DTOs.BoletoRegistroDTO;
import com.api.aerolinea.Entities.Boleto;
import com.api.aerolinea.Entities.User;
import com.api.aerolinea.Entities.Vuelo;
import com.api.aerolinea.Repositories.BoletoRepository;
import com.api.aerolinea.Repositories.UserRepository;
import com.api.aerolinea.Repositories.VueloRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoletoService {
    private final BoletoRepository boletoRepository;
    private final UserRepository userRepository;
    private final VueloRepository vueloRepository;

    public BoletoService(BoletoRepository boletoRepository,
                         UserRepository userRepository,
                         VueloRepository vueloRepository) {
        this.boletoRepository = boletoRepository;
        this.userRepository = userRepository;
        this.vueloRepository = vueloRepository;
    }


    // TODO

    public ResponseEntity<List<BoletoDTO>> getBoletoUsuario(UUID id) {
        try {
            List<Object[]> resultSet = boletoRepository.findBoletoByUsuarioId(id);
            return getListaBoletos(resultSet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<List<BoletoDTO>> getBoletos() {
        try {
            List<Object[]> resultSet = boletoRepository.findAllBoletos();
            return getListaBoletos(resultSet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    private ResponseEntity<List<BoletoDTO>> getListaBoletos(List<Object[]> resultSet) {
        List<BoletoDTO> boletos = new ArrayList<>();

        for (Object[] result: resultSet) {
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

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(boletos);
    }

    public ResponseEntity<String> postBoleto(BoletoRegistroDTO boletoRegistro) {
        try {
            Boleto boleto = Boleto.builder()
                    .id(UUID.randomUUID())
                    .usuario(buscarUsuario(boletoRegistro.usuarioId()))
                    .vuelo(buscarVuelo(boletoRegistro.vueloId()))
                    .build();

            boletoRepository.save(boleto);

            return ResponseEntity.ok("Boleto creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private User buscarUsuario(UUID id) throws ChangeSetPersister.NotFoundException {
        return userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    private Vuelo buscarVuelo(UUID id) throws ChangeSetPersister.NotFoundException {
        return vueloRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public ResponseEntity<String> deleteBoleto(UUID id) {
        Optional<Boleto> boleto = boletoRepository.findById(id);

        if (boleto.isPresent()) {
            boletoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Boleto eliminado");
        } else {
            return ResponseEntity.badRequest().body("Boleto no encontrado");
        }
    }
}
