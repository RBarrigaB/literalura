package com.alura.literalura.util;

import com.alura.literalura.model.DTOs.AutorLibrosDTO;
import com.alura.literalura.model.DTOs.LibroDTO;
import com.alura.literalura.model.Entities.Libro;
import com.alura.literalura.model.exception.ServiceException;
import com.alura.literalura.services.AutorService;
import com.alura.literalura.services.LibroService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Menu {

    private final Scanner inputUser = new Scanner(System.in);
    private final LibroService libroService;
    private final AutorService autorService;

    public Menu(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }


    public void startApp() {

        int opcionElegida = 0;

        System.out.println("\n------- Bienvenido a Literalura -------\n");
        while (true) {
            try {
                System.out.println("Elige la alternativa de lo que deseas realizar: ");
                System.out.println("1 - Buscar libro por título");
                System.out.println("2 - Listar libros registrados");
                System.out.println("3 - Listar autores registrados");
                System.out.println("4 - Listar autores vivos en un determinado año");
                System.out.println("5 - Listar libros por idioma");
                System.out.println("0 - Salir");
                System.out.print("Opcion: ");

                String input = inputUser.nextLine().trim();
                if (!input.matches("\\d+")) {
                    throw new ServiceException("Entrada inválida. Debe ser un número.");
                }
                opcionElegida = Integer.parseInt(input);

                if (opcionElegida < 0 || opcionElegida > 5) {
                    throw new ServiceException("Opción inválida. Intente nuevamente.");
                }

                switch (opcionElegida) {
                    case (1):
                        opc1();
                        break;
                    case (2):
                        opc2();
                        break;
                    case (3):
                        opc3();
                        break;
                    case (4):
                        opc4();
                        break;
                    case (5):
                        opc5();
                        break;
                    case (0):
                        System.out.println("\nGracias por usar Literalura. \nHasta pronto.\n");
                        return;
                }
            } catch (ServiceException ex) {
                System.out.println("\n⚠ " + ex.getMessage() + "\n");
            } catch (Exception ex) {
                System.out.println("❌ Error inesperado: " + ex.getMessage());
            }

        }
    }

    private void opc1() {
        System.out.println("\nIngrese el nombre del libro que desea buscar\n");
        System.out.print("Nombre del libro: ");
        String titulo = inputUser.nextLine().trim();
        if (titulo.isBlank()) {
            throw new ServiceException("El nombre del libro no puede estar vacío");
        }
        Libro libro = libroService.buscarYRegistrarLibro(titulo);
        formatoImpresionLibro(libro);
    }

    private void opc2() {
        List<Libro> librosRegistrados = libroService.obtenerLibros();
        if (librosRegistrados.isEmpty()) {
            System.out.println("\n----------------------------------\n");
            System.out.println("AÚN NO EXISTE NINGÚN LIBRO REGISTRADO ");
            System.out.println("\n----------------------------------");
        } else {
            System.out.println("\n----------------------------------\n");
            System.out.println("CANTIDAD DE LIBROS REGISTRADOS: " + librosRegistrados.size());
            System.out.println("\n----------------------------------");
            librosRegistrados.forEach(this::formatoImpresionLibro);
        }
    }

    private void opc3() {
        List<AutorLibrosDTO> librosRegistrados = autorService.obtenerAutores();
        if (librosRegistrados.isEmpty()) {
            System.out.println("\n----------------------------------\n");
            System.out.println("AÚN NO EXISTE NINGÚN AUTOR REGISTRADO ");
            System.out.println("\n----------------------------------");
        } else {
            System.out.println("\n----------------------------------\n");
            System.out.println("CANTIDAD DE AUTORES REGISTRADOS: " + librosRegistrados.size());
            System.out.println("\n----------------------------------");
            librosRegistrados.forEach(this::formatoImpresionAutores);
        }
    }

    private void opc4() {
        int opcionElegida;
        System.out.println("\nIngrese el año en el cual desea ver los autores vivos\n");
        System.out.print("Año: ");
        String input = inputUser.nextLine().trim();

        if (!input.matches("\\d{1,4}")) {
            throw new ServiceException("Entrada inválida. El año de búsqueda debe ser un número válido.");
        }

        int anioElegido = Integer.parseInt(input);
        List<AutorLibrosDTO> librosRegistrados = autorService.obtenerAutoresPorAnio(anioElegido);
        if (librosRegistrados.isEmpty()) {
            System.out.println("\n----------------------------------\n");
            System.out.println("AÚN NO EXISTE NINGÚN AUTOR REGISTRADO QUE ESTUVIERA VIVO ESE AÑO");
            System.out.println("----------------------------------\n");
        } else {
            System.out.println("\n----------------------------------\n");
            System.out.println("CANTIDAD DE AUTORES REGISTRADOS VIVOS: " + librosRegistrados.size());
            System.out.println("\n----------------------------------");
            librosRegistrados.forEach(this::formatoImpresionAutores);
        }
    }

    private void opc5() {
        System.out.println("\nIngrese el idioma que desea en que desea consultar los libros registrados\n");
        System.out.println("es - español\nen - inglés\nfr - francés\npt - portugués");
        System.out.print("Idioma del libro: ");
        String idioma = inputUser.nextLine().trim();
        if (idioma.isBlank()) {
            throw new ServiceException("El idioma del libro no puede estar vacío");
        } else if (!idioma.matches("(?i)es|en|fr|pt")) {
            throw new ServiceException("El idioma del libro debe ser uno de los disponibles. Por favor, intente nuevamente.");
        } else {
           List<Libro> librosIdiomaSeleccionado = libroService.obtenerLibrosPorIdioma(idioma);
            if (librosIdiomaSeleccionado.isEmpty()) {
                System.out.println("\n---------------------------------------------------------------------\n");
                System.out.println("AÚN NO EXISTE NINGÚN LIBRO REGISTRADO EN EL IDIOMA SELECCIONADO");
                System.out.println("\n---------------------------------------------------------------------");
            } else {
                System.out.println("\n---------------------------------------------------------------------\n");
                System.out.println("CANTIDAD DE LIBROS REGISTRADOS EN EL IDIOMA SELECCIONADO: " + librosIdiomaSeleccionado.size());
                System.out.println("\n---------------------------------------------------------------------");
                librosIdiomaSeleccionado.forEach(this::formatoImpresionLibro);
            }
        }
    }



    private void formatoImpresionLibro(Libro libro) {
        System.out.println("\n------------- LIBRO ------------------------\n");
        System.out.println("Titulo: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor().getNombreFormatoApi());
        System.out.println("Idioma: " + libro.getIdioma());
        System.out.println("Número de descargas: " + libro.getNumDescargas());
        System.out.println("\n--------------------------------------------\n");
    }

    private void formatoImpresionAutores(AutorLibrosDTO autores) {
        System.out.println("\n------------- AUTOR ---------------------\n");
        System.out.println("Nombre: " + autores.getAutor().name());
        System.out.println("Fecha de nacimiento: " + autores.getAutor().birth_year());
        System.out.println("Fecha de fallecimiento: " + autores.getAutor().death_year());
        System.out.println("Libros: " + autores.getLibros().stream()
                .map(LibroDTO::titulo).sorted()
                .toList());
        System.out.println("\n-----------------------------------------\n");
    }
}
