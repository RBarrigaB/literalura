package com.alura.literalura.model.Entities;

import com.alura.literalura.model.DTOs.AutorDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "anio_nacimiento", nullable = false)
    private Integer anioNacimiento;

    @Column(name = "anio_fallecimiento")
    private Integer anioFallecimiento;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<Libro> libros = new ArrayList<>();

    public Autor fromDTO(AutorDTO autorDTO) {
        this.nombre = normalizarNombre(autorDTO.name());
        this.anioNacimiento = autorDTO.birth_year() != null
                ? Integer.parseInt(autorDTO.birth_year())
                : null;
        this.anioFallecimiento = autorDTO.death_year() != null
                ? Integer.parseInt(autorDTO.death_year())
                : null;
        return this;
    }

    private String normalizarNombre(String nombreApi) {
        if (nombreApi.contains(",")) {
            String[] partes = nombreApi.split(",");
            return partes[1].trim() + " " + partes[0].trim();
        }
        return nombreApi.trim();
    }

    public String getNombreFormatoApi() {
        if (nombre == null || !nombre.contains(" ")) {
            return nombre;
        }

        String[] partes = nombre.split(" ", 2);
        return partes[1] + ", " + partes[0];
    }
}


