package com.alura.literalura.model.Entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "libro")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "idioma", nullable = false)
    private String idioma;

    @Column(name = "num_descargas", nullable = false)
    private Integer numDescargas;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;
}
