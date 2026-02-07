package com.alura.literalura.util;

import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class Menu {

    private Scanner inputUser = new Scanner(System.in);

    public void startApp() {

        int opcionElegida = 0;

        System.out.println("------- Bienvenido a Literalura -------\n\n");
        while (true) {
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
                System.out.println("Opción fuera de rango. Intente nuevamente.\n");
                continue;
            }

            switch (opcionElegida) {
                case (1):
                    System.out.println("Buscar libro");
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
                    System.out.println("Gracias por usar Literalura. Hasta pronto.");
                    return;
            }
        }
    }
}
