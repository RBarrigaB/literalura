package com.alura.literalura.services;

import com.alura.literalura.model.DTOs.LibroDTO;
import com.alura.literalura.util.DataConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroService {

    private final DataConverter dataConverter;

    public void procesar(String searchIput) {
        List<LibroDTO> libros = dataConverter.obtenerLibros(searchIput);
        libros.stream().findFirst().ifPresent(System.out::println);
    }
}
