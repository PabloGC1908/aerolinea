package com.api.aerolinea.Entities.Pais;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisService {
    private final PaisRepository paisRepository;

    public PaisService(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    public List<Pais> getPaises() {
        return paisRepository.findAll();
    }

    public String postPais(Pais pais) {
        paisRepository.save(pais);

        return "ok";
    }
}
