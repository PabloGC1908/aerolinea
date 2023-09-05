package com.api.aerolinea.Services;

import com.api.aerolinea.Repositories.BoletoRepository;
import org.springframework.stereotype.Service;

@Service
public class BoletoService {
    private final BoletoRepository boletoRepository;

    public BoletoService(BoletoRepository boletoRepository) {
        this.boletoRepository = boletoRepository;
    }

    // TODO
}
