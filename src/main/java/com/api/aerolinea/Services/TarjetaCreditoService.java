package com.api.aerolinea.Services;

import com.api.aerolinea.Repositories.TarjetaCreditoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TarjetaCreditoService {
    private final TarjetaCreditoRepository tarjetaCreditoRepository;

    public TarjetaCreditoService(TarjetaCreditoRepository tarjetaCreditoRepository) {
        this.tarjetaCreditoRepository = tarjetaCreditoRepository;
    }

    public ResponseEntity<String> getTarjetaPorUsuario(UUID id) {
        return ResponseEntity.noContent().build();
    }
}
