package com.alura.literalura.model.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class ApiResponseDTO {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record GutendexResponseDTO(
            List<LibroDTO> results
    ) {}
}
