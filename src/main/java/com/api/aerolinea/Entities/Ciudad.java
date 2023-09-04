package com.api.aerolinea.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ciudad")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ciudad_name", length = 50, nullable = false, unique = true)
    private String ciudad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pais_id")
    private Pais pais;
    @OneToMany(mappedBy = "ciudadOrigen")
    private List<Vuelo> vuelosOrigen;
    @OneToMany(mappedBy = "ciudadDestino")
    private List<Vuelo> vuelosDestino;
}
