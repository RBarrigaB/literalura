package com.alura.literalura.model.Entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "anio_nacimiento", nullable = false)
    private LocalDate anioNacimiento;

    @Column(name = "anio_fallecimiento")
    private LocalDate anioFallecimiento;
}
