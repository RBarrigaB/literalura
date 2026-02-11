package com.alura.literalura.repository;

import com.alura.literalura.model.Entities.Autor;
import com.alura.literalura.model.Entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    boolean existsByTituloIgnoreCaseAndAutor(String titulo, Autor autor);
    List<Libro> findByIdioma(String idioma);
}
