package com.api.aerolinea.Repositories;

import com.api.aerolinea.Entities.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {
    @Query(value = "SELECT c.ciudad FROM Ciudad c WHERE :id = c.pais")
    List<Ciudad> findAllCityInPais(@Param("id") Integer id);

    @Query("SELECT c.id, c.ciudad FROM Ciudad c ORDER BY c.id")
    List<Object[]> findAllCities();
}
