package com.api.aerolinea.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "boleto")
public class Boleto {
    @Id
    @UuidGenerator
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties("boletos")
    private User usuario;
    @ManyToOne
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;
    private Date fechaCompra;
}
