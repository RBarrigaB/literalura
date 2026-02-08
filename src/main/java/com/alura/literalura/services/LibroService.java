package com.alura.literalura.services;

import com.alura.literalura.model.DTOs.AutorDTO;
import com.alura.literalura.model.DTOs.LibroDTO;
import com.alura.literalura.model.Entities.Autor;
import com.alura.literalura.model.Entities.Libro;
import com.alura.literalura.model.exception.ServiceException;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.util.DataConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroService {

    private final DataConverter dataConverter;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    @Transactional
    public Libro buscarYRegistrarLibro(String searchIput) {

        List<LibroDTO> libros = dataConverter.obtenerLibros(searchIput);
        LibroDTO libroDTO = libros.stream().findFirst().orElseThrow(() -> new ServiceException("No se encontraron libros"));
        try {
            AutorDTO autorDTO = libroDTO.autores().get(0);

            Autor autor = autorRepository
                    .findByNombreIgnoreCaseAndAnioNacimiento(
                            normalizarNombreAutor(autorDTO.name()),
                            Integer.valueOf(autorDTO.birth_year())
                    )
                    .orElseGet(() -> autorRepository.save(new Autor().fromDTO(autorDTO)));

            if (!libroRepository.existsByTituloIgnoreCaseAndAutor(libroDTO.titulo(), autor)) {
                Libro libro = new Libro().fromDTO(libroDTO);
                libro.setAutor(autor);
                libroRepository.save(libro);
                return libro;
            } else {
                throw new ServiceException("\nLibro ya existe. \nNo se puede registrar el mismo libro más de una vez\n");
            }
        } catch (DataIntegrityViolationException ex) {
            throw new ServiceException("Error al registrar el libro. Por favor, intente nuevamente");
        } catch (Exception e) {
            throw new ServiceException("\n"+e.getMessage());
        }
    }

    public List<Libro> obtenerLibros() {
        try {
            return libroRepository.findAll();
        } catch (Exception ex) {
            throw new ServiceException("Error al obtener la lista de libros");
        }
    }

    public static String normalizarNombreAutor(String nombreApi) {
        if (nombreApi.contains(",")) {
            String[] partes = nombreApi.split(",");
            return (partes[1].trim() + " " + partes[0].trim());
        }
        return nombreApi.trim();
    }
}
