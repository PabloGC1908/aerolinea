package com.api.aerolinea.Repositories;

import com.api.aerolinea.Entities.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AerolineaRepository extends JpaRepository<Aerolinea, Integer> {

    @Query("SELECT a.id, a.aerolinea FROM Aerolinea a")
    List<Object[]> findAllAerolineas();
}
