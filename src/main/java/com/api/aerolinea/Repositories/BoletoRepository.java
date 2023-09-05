package com.api.aerolinea.Repositories;

import com.api.aerolinea.Entities.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, UUID> {
}
