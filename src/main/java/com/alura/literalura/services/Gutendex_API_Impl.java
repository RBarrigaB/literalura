package com.alura.literalura.services;

import com.alura.literalura.model.Entities.Libro;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class Gutendex_API_Impl implements Gutendex_API {

    private final String baseUrl = "https://gutendex.com/books/?search=";

    @Override
    public String getLibros(String searchInput) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl+searchInput.replaceAll(" ","%20")))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }
}
