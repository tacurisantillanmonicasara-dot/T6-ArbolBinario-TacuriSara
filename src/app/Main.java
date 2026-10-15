package app;

import java.util.Scanner;
import modelo.Nodo;
import negocio.ArbolBinario;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArbolBinario<String> arbol = new ArbolBinario<>();
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion(scanner);
            switch (opcion) {
                case 1:
                    crearArbolInicial(arbol);
                    System.out.println("Arbol inicial creado correctamente");
                    break;
                case 2:
                    arbol.mostrarArbol();
                    break;
                case 3:
                    System.out.println("Numero de nodos: " + arbol.contarNodos());
                    break;
                case 4:
                    System.out.println("Numero de hojas: " + arbol.contarHojas());
                    break;
                case 5:
                    System.out.println("Altura del arbol: " + arbol.altura());
                    break;
                case 6:
                    consultarGrado(arbol, scanner);
                    break;
                case 7:
                    verificarHoja(arbol, scanner);
                    break;
                case 8:
                    System.out.println("Programa finalizado");
                    break;
                case 9:
                    ejecutarCasosDePrueba();
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 8);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("===== ORGANIZACION UNIVERSITARIA =====");
        System.out.println("1. Crear el arbol inicial");
        System.out.println("2. Visualizar el arbol");
        System.out.println("3. Consultar el numero de nodos");
        System.out.println("4. Consultar el numero de hojas");
        System.out.println("5. Consultar la altura");
        System.out.println("6. Consultar el grado de un nodo");
        System.out.println("7. Verificar si un nodo es hoja");
        System.out.println("8. Salir");
        System.out.println("9. Ejecutar casos de prueba");
        System.out.print("Seleccione una opcion: ");
    }

    private static int leerOpcion(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void crearArbolInicial(ArbolBinario<String> arbol) {
        arbol.crearRaiz("UNIVERSIDAD");
        Nodo<String> raiz = arbol.getRaiz();

        arbol.agregarIzquierdo(raiz, "FISEI");
        arbol.agregarDerecho(raiz, "DIDE");

        Nodo<String> fisei = raiz.getIzquierdo();
        Nodo<String> dide = raiz.getDerecho();

        arbol.agregarIzquierdo(fisei, "SOFTWARE");
        arbol.agregarDerecho(fisei, "TI");
        arbol.agregarIzquierdo(dide, "INVEST.");
        arbol.agregarDerecho(dide, "VINCULACION");

        Nodo<String> software = fisei.getIzquierdo();
        arbol.agregarIzquierdo(software, "NIVEL1");
        arbol.agregarDerecho(software, "NIVEL3");
    }

    private static Nodo<String> pedirNodo(ArbolBinario<String> arbol, Scanner scanner) {
        if (arbol.esVacio()) {
            System.out.println("El arbol esta vacio. Cree el arbol inicial primero");
            return null;
        }
        System.out.print("Nombre del nodo: ");
        String nombre = scanner.nextLine().trim().toUpperCase();
        Nodo<String> nodo = arbol.buscar(nombre);
        if (nodo == null) {
            System.out.println("El nodo " + nombre + " no existe en el arbol");
        }
        return nodo;
    }

    private static void consultarGrado(ArbolBinario<String> arbol, Scanner scanner) {
        Nodo<String> nodo = pedirNodo(arbol, scanner);
        if (nodo != null) {
            System.out.println("Grado de " + nodo.getDato() + ": " + nodo.grado());
        }
    }

    private static void verificarHoja(ArbolBinario<String> arbol, Scanner scanner) {
        Nodo<String> nodo = pedirNodo(arbol, scanner);
        if (nodo != null) {
            if (nodo.esHoja()) {
                System.out.println(nodo.getDato() + " es una hoja");
            } else {
                System.out.println(nodo.getDato() + " no es una hoja");
            }
        }
    }

    private static void ejecutarCasosDePrueba() {
        System.out.println();
        System.out.println("===== CASOS DE PRUEBA =====");

        ArbolBinario<String> vacio = new ArbolBinario<>();
        System.out.println("Caso 1: arbol vacio");
        System.out.println("  Nodos: " + vacio.contarNodos() + " (esperado 0)");
        System.out.println("  Altura: " + vacio.altura() + " (esperado -1)");

        ArbolBinario<String> unico = new ArbolBinario<>();
        unico.crearRaiz("UNIVERSIDAD");
        System.out.println("Caso 2: arbol con un solo nodo");
        System.out.println("  Hojas: " + unico.contarHojas() + " (esperado 1)");
        System.out.println("  Altura: " + unico.altura() + " (esperado 0)");

        ArbolBinario<String> inicial = new ArbolBinario<>();
        crearArbolInicial(inicial);
        System.out.println("Caso 3: arbol inicial");
        System.out.println("  Nodos: " + inicial.contarNodos() + " (esperado 9)");
        System.out.println("  Hojas: " + inicial.contarHojas() + " (esperado 5)");
        System.out.println("  Altura: " + inicial.altura() + " (esperado 3)");

        System.out.println("Caso 4: insertar en posicion ocupada");
        Nodo<String> fisei = inicial.buscar("FISEI");
        boolean insertado = inicial.agregarIzquierdo(fisei, "NUEVO");
        if (insertado) {
            System.out.println("  Resultado: se inserto (incorrecto)");
        } else {
            System.out.println("  Resultado: posicion ocupada, no se inserto (correcto)");
        }

        System.out.println("Caso 5: grado de FISEI y SOFTWARE");
        System.out.println("  Grado de FISEI: " + inicial.buscar("FISEI").grado() + " (esperado 2)");
        System.out.println("  Grado de SOFTWARE: " + inicial.buscar("SOFTWARE").grado() + " (esperado 2)");
    }
}