package com.alura.literalura.model.Entities;

import com.alura.literalura.model.DTOs.LibroDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "libro", uniqueConstraints = {
       @UniqueConstraint(columnNames =  {"titulo", "id_autor"})
})
@Getter
@NoArgsConstructor
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

    public Libro fromDTO(LibroDTO libroDTO) {
        this.titulo = libroDTO.titulo();
        this.idioma = libroDTO.idiomas().get(0);
        this.numDescargas = libroDTO.numeroDescargas();
        this.autor = new Autor().fromDTO(libroDTO.autores().get(0));
        return this;
    }
}


