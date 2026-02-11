package com.alura.literalura.model.DTOs;

import com.alura.literalura.model.Entities.Autor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AutorLibrosDTO {

    private AutorDTO autor;
    private List<LibroDTO> libros;

    public AutorLibrosDTO(Autor autor) {
        this.autor = new AutorDTO(
                autor.getNombreFormatoApi(),
                String.valueOf(autor.getAnioNacimiento()),
                String.valueOf(autor.getAnioFallecimiento())
        );

        this.libros = autor.getLibros()
                .stream()
                .map(libro -> new LibroDTO(
                        libro.getTitulo(),
                        List.of(libro.getIdioma()),
                        libro.getNumDescargas(),
                        List.of(new AutorDTO(
                                libro.getAutor().getNombre(),
                                String.valueOf(libro.getAutor().getAnioNacimiento()),
                                String.valueOf(libro.getAutor().getAnioFallecimiento())
                        ))
                ))
                .toList();
    }
}
