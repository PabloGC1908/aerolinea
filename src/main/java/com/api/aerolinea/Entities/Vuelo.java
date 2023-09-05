package com.api.aerolinea.Entities;

import com.api.aerolinea.Entities.Aerolinea;
import com.api.aerolinea.Entities.Ciudad;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vuelo")
public class Vuelo {
    @Id
    @UuidGenerator
    private UUID uuid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aerolinea_id")
    private Aerolinea aerolinea;
    @ManyToOne
    @JoinColumn(name = "ciudad_origen_id")
    private Ciudad ciudadOrigen;
    @ManyToOne
    @JoinColumn(name = "ciudad_destino_id")
    private Ciudad ciudadDestino;
    private Float precio;
    @Column(name = "cantidad_pasajes")
    private Integer cantidadPasajes;
    @Column(name = "fecha_ida")
    private Date fechaIda;
    @Column(name = "fecha_vuelta")
    private Date fechaVuelta;
}
