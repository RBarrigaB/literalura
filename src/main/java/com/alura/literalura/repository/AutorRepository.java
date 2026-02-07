package com.alura.literalura.repository;

import com.alura.literalura.model.Entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findByNombreAndAnioNacimiento(String nombre, Integer anioNacimiento);
}
