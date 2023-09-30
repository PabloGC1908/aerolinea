package com.api.aerolinea.Services;

import com.api.aerolinea.Entities.Pais;
import com.api.aerolinea.Repositories.PaisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para gestionar operaciones relacionadas con países.
 */
@Service
public class PaisService {
    private final PaisRepository paisRepository;

    /**
     * Constructor de PaisService.
     *
     * @param paisRepository Repositorio de países utilizado para acceder a los datos de los países.
     */
    public PaisService(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    /**
     * Obtiene todos los países disponibles.
     *
     * @return Lista de objetos Pais que representan los países disponibles.
     */
    public List<Pais> getPaises() {
        return paisRepository.findAll();
    }

    /**
     * Crea un nuevo país y lo guarda en la base de datos.
     *
     * @param pais Objeto Pais que representa el país a ser creado y guardado.
     * @return Cadena "ok" para indicar que el país se ha creado con éxito.
     */
    public String postPais(Pais pais) {
        paisRepository.save(pais);

        return "ok";
    }
}

