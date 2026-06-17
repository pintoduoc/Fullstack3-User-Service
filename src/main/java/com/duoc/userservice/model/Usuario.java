package com.duoc.userservice.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String rut;

    @NotNull
    private String nombreCompleto;

    @Nullable
    private String contacto;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public enum Rol {
        CIUDADANO,
        BRIGADISTA,
        ADMINISTRADOR
    }

    @Nullable
    @ManyToOne
    @JoinColumn(name = "brigada_id", referencedColumnName = "id")
    private Brigada brigada;
}
