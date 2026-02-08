package com.alura.literalura.util;

import com.alura.literalura.model.DTOs.ApiResponseDTO;
import com.alura.literalura.model.DTOs.LibroDTO;
import com.alura.literalura.model.exception.ServiceException;
import com.alura.literalura.services.Gutendex_API;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class DataConverterImpl implements DataConverter {

    private final ObjectMapper objectMapper;
    private final Gutendex_API gutendexApi;

    @Override
    public <T> T getData(String json, Class<T> genClass) {
        try {
            return objectMapper.readValue(json,genClass);
        } catch (JsonProcessingException e) {
            throw new ServiceException("Error al parsear la respuesta de la API");
        }
    }

    public List<LibroDTO> obtenerLibros(String searchInput) {
        searchInput = searchInput.isBlank() ? "" : searchInput;
            ApiResponseDTO.GutendexResponseDTO response =
                    getData(gutendexApi.getLibros(searchInput), ApiResponseDTO.GutendexResponseDTO.class);
        return response.results();
    }
}
