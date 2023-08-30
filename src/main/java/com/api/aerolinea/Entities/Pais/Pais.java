package com.api.aerolinea.Entities.Pais;

import com.api.aerolinea.Entities.Ciudad.Ciudad;
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
@Table(name = "pais")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "pais_name", nullable = false, length = 50)
    private String pais;
    @OneToMany(mappedBy = "pais")
    private List<Ciudad> ciudad;
}
