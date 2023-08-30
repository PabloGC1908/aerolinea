package com.api.aerolinea.Entities.Aerolinea;

import com.api.aerolinea.Entities.Vuelo.Vuelo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aerolinea")
public class Aerolinea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String aerolinea;
    @OneToMany(mappedBy = "aerolinea")
    private List<Vuelo> vuelos;
}
