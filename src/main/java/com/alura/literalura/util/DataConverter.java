package com.alura.literalura.util;

import com.alura.literalura.model.DTOs.LibroDTO;

import java.util.List;


public interface DataConverter {
    <T> T getData(String json, Class<T> genClass);
    List<LibroDTO> obtenerLibros(String searchValue);
}
