package com.alura.literalura.model.Entities;

import com.alura.literalura.model.DTOs.AutorDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
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
    private Integer anioNacimiento;

    @Column(name = "anio_fallecimiento")
    private Integer anioFallecimiento;

    public Autor fromDTO(AutorDTO autorDTO) {
        this.nombre = autorDTO.name();
        this.anioNacimiento = autorDTO.birth_year() != null
                ? Integer.parseInt(autorDTO.birth_year())
                : null;
        this.anioFallecimiento = autorDTO.death_year() != null
                ? Integer.parseInt(autorDTO.death_year())
                : null;
        return this;
    }
}
