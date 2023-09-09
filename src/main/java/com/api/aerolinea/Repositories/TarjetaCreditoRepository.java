package com.api.aerolinea.Repositories;

import com.api.aerolinea.Entities.TarjetaCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TarjetaCreditoRepository extends JpaRepository<TarjetaCredito, UUID> {

    @Query("SELECT tarjeta.numeroTarjeta FROM TarjetaCredito tarjeta WHERE tarjeta.usuario.uuid = :id")
    String getNombreTarjetaByUsuarioId(@Param("id") UUID id);
}
