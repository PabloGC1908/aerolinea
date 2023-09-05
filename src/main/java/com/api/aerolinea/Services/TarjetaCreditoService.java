package com.api.aerolinea.Services;

import com.api.aerolinea.Repositories.TarjetaCreditoRepository;
import org.springframework.stereotype.Service;

@Service
public class TarjetaCreditoService {
    private final TarjetaCreditoRepository tarjetaCreditoRepository;

    public TarjetaCreditoService(TarjetaCreditoRepository tarjetaCreditoRepository) {
        this.tarjetaCreditoRepository = tarjetaCreditoRepository;
    }

    // TODO
}
