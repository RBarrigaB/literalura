package com.alura.literalura.util;

import com.alura.literalura.model.DTOs.LibroDTO;
import com.alura.literalura.model.Entities.Libro;
import com.alura.literalura.model.exception.ServiceException;
import com.alura.literalura.services.LibroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class Menu {

    private final Scanner inputUser = new Scanner(System.in);
    private final LibroService libroService;

    public Menu(LibroService libroService) {
        this.libroService = libroService;
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
                if (!inputUser.hasNextInt()) {
                    System.out.println("Entrada inválida. Debe ser un número.\n");
                    inputUser.nextLine(); // limpiar buffer
                    continue;
                }

                opcionElegida = inputUser.nextInt();
                inputUser.nextLine(); // limpiar salto de línea

                if (opcionElegida < 0 || opcionElegida > 5) {
                    System.out.println("Opción inválida. Intente nuevamente.\n");
                    continue;
                }

                switch (opcionElegida) {
                    case (1):
                        opc1();
                        break;
                    case (2):
                        System.out.println("Listar libros registrados");
                        break;
                    case (3):
                        System.out.println("Listar autores registrados");
                        break;
                    case (4):
                        System.out.println("Listar actores vivos en determinado año");
                        break;
                    case (5):
                        System.out.println("Listar libros por idiomas");
                        break;
                    case (0):
                        System.out.println("\nGracias por usar Literalura. \nHasta pronto.\n");
                        return;
                }
            } catch (ServiceException ex) {
                System.out.println("⚠ " + ex.getMessage());
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
            throw new ServiceException("El título no puede estar vacío");
        }
                Libro libro = libroService.buscarYRegistrarLibro(titulo);
                System.out.println("\n---------- LIBRO ----------");
                System.out.println("Titulo: "+libro.getTitulo());
                System.out.println("Autor: "+libro.getAutor().getNombre());
                System.out.println("Idioma: "+libro.getIdioma());
                System.out.println("Número de descargas: "+libro.getNumDescargas());
                System.out.println("\n---------------------------");
    }
}
