package com.alura.literalura.services;

import com.alura.literalura.model.Entities.Autor;
import com.alura.literalura.model.exception.ServiceException;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.model.DTOs.AutorLibrosDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    @Transactional(readOnly = true)
    public List<AutorLibrosDTO> obtenerAutores() {
        try {
            return autorRepository.findAll().stream()
                    .map(AutorLibrosDTO::new)
                    .toList();
        } catch (Exception e) {
            throw new ServiceException("Error al obtener la lista de autores");
        }
    }
}
