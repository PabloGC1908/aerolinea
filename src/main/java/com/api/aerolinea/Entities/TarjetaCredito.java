package com.api.aerolinea.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarjeta_credito")
public class TarjetaCredito {
    @Id
    @UuidGenerator
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;
    private String nombreTarjeta;
    private String numeroTarjeta;
    private Date fechaExpiracion;
    private Integer cvv;
}
