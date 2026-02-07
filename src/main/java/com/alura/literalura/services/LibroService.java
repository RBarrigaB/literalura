package com.alura.literalura.services;

import com.alura.literalura.model.DTOs.LibroDTO;
import com.alura.literalura.model.Entities.Autor;
import com.alura.literalura.model.Entities.Libro;
import com.alura.literalura.model.exception.ServiceException;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.util.DataConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroService {

    private final DataConverter dataConverter;
    private final LibroRepository libroRepository;

    public Libro buscarYRegistrarLibro(String searchIput) {

        boolean libroExiste = false;
        List<LibroDTO> libros = dataConverter.obtenerLibros(searchIput);
        LibroDTO libroDTO = libros.stream().findFirst().orElseThrow(() -> new ServiceException("No se encontraron libros"));
        try {
            if (!libroRepository.existsByTituloIgnoreCaseAndAutor(libroDTO.titulo(), new Autor().fromDTO(libroDTO.autores().get(0)))) {
                Libro libro = new Libro().fromDTO(libroDTO);
                libroRepository.save(libro);
                return libro;
            } else {
                throw new ServiceException("\nLibro ya existe. \nNo se puede registrar el mismo libro más de una vez\n");
            }
        } catch (DataIntegrityViolationException ex) {
            throw new ServiceException("\nError al registrar el libro. Por favor, intente nuevamente\n");
        }
    }
}
