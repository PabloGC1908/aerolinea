package com.api.aerolinea.Repositories;

import com.api.aerolinea.Entities.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, UUID> {

    @Query("SELECT vuelo.uuid as id," +
            " a.aerolinea AS aerolinea," +
            " c_o.ciudad AS ciudadOrigen," +
            " c_d.ciudad AS ciudadDestino," +
            " vuelo.fechaIda AS fechaIda," +
            " vuelo.fechaVuelta AS fechaVuelta," +
            " vuelo.precio as precio" +
            " FROM Vuelo vuelo INNER JOIN Aerolinea a ON vuelo.aerolinea.id = a.id" +
            " INNER JOIN Ciudad c_o ON vuelo.ciudadOrigen.id = c_o.id" +
            " INNER JOIN Ciudad c_d ON vuelo.ciudadDestino.id = c_d.id" +
            " ORDER BY vuelo.fechaIda")
    List<Object[]> findAllVuelosConAerolineaYCiudades();

    @Query("SELECT vuelo.uuid as id," +
            " a.aerolinea AS aerolinea," +
            " c_o.ciudad AS ciudadOrigen," +
            " c_d.ciudad AS ciudadDestino," +
            " vuelo.fechaIda AS fechaIda," +
            " vuelo.fechaVuelta AS fechaVuelta," +
            " vuelo.precio as precio" +
            " FROM Vuelo vuelo INNER JOIN Aerolinea a ON vuelo.aerolinea.id = a.id" +
            " INNER JOIN Ciudad c_o ON vuelo.ciudadOrigen.id = c_o.id" +
            " INNER JOIN Ciudad c_d ON vuelo.ciudadDestino.id = c_d.id" +
            " WHERE vuelo.uuid = :id")
    List<Object[]> findVueloConDetallesPorId(@Param("id") UUID id);
}
