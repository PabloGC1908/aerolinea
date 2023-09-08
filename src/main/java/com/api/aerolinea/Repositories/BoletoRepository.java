package com.api.aerolinea.Repositories;

import com.api.aerolinea.Entities.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, UUID> {

    @Query("SELECT boleto.id," +
            " vuelo.ciudadOrigen.ciudad," +
            " vuelo.ciudadDestino.ciudad," +
            " vuelo.fechaIda," +
            " boleto.fechaCompra," +
            " boleto.vuelo.precio" +
            " FROM Boleto boleto" +
            " INNER JOIN Vuelo vuelo ON vuelo.uuid = boleto.id" +
            " WHERE boleto.usuario.uuid = :id")
    List<Object[]> findBoletoByUsuarioId(@Param("id") UUID id);

    @Query("SELECT boleto.id," +
            " vuelo.ciudadOrigen.ciudad," +
            " vuelo.ciudadDestino.ciudad," +
            " vuelo.fechaIda," +
            " boleto.fechaCompra," +
            " boleto.vuelo.precio" +
            " FROM Boleto boleto" +
            " INNER JOIN Vuelo vuelo ON vuelo.uuid = boleto.id")
    List<Object[]> findAllBoletos();
}
