package com.api.aerolinea.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarjeta_credito", indexes = @Index(columnList = "numeroTarjeta"))
public class TarjetaCredito {
    @Id
    @UuidGenerator
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;
    private String nombreTarjeta;
    private String numeroTarjeta;
    private Integer mesExpiracion;
    private Integer anioExpiracion;
    private Integer cvv;
}
