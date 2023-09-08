package com.api.aerolinea.Repositories;

import com.api.aerolinea.Entities.TarjetaCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.UUID;

@Repository
public interface TarjetaCreditoRepository extends JpaRepository<TarjetaCredito, UUID> {
}
