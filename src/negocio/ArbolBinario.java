package negocio;

import modelo.Nodo;

public class ArbolBinario<T> {

    private Nodo<T> raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public void crearRaiz(T dato) {
        raiz = new Nodo<>(dato);
    }

    public boolean agregarIzquierdo(Nodo<T> padre, T dato) {
        if (padre == null || padre.getIzquierdo() != null) {
            return false;
        }
        padre.setIzquierdo(new Nodo<>(dato));
        return true;
    }

    public boolean agregarDerecho(Nodo<T> padre, T dato) {
        if (padre == null || padre.getDerecho() != null) {
            return false;
        }
        padre.setDerecho(new Nodo<>(dato));
        return true;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public Nodo<T> getRaiz() {
        return raiz;
    }

    public Nodo<T> buscar(T dato) {
        return buscar(raiz, dato);
    }

    private Nodo<T> buscar(Nodo<T> actual, T dato) {
        if (actual == null) {
            return null;
        }
        if (actual.getDato().equals(dato)) {
            return actual;
        }
        Nodo<T> encontrado = buscar(actual.getIzquierdo(), dato);
        if (encontrado != null) {
            return encontrado;
        }
        return buscar(actual.getDerecho(), dato);
    }

    public int contarNodos() {
        return contarNodos(raiz);
    }

    private int contarNodos(Nodo<T> actual) {
        if (actual == null) {
            return 0;
        }
        return 1 + contarNodos(actual.getIzquierdo()) + contarNodos(actual.getDerecho());
    }

    public int contarHojas() {
        return contarHojas(raiz);
    }

    private int contarHojas(Nodo<T> actual) {
        if (actual == null) {
            return 0;
        }
        if (actual.esHoja()) {
            return 1;
        }
        return contarHojas(actual.getIzquierdo()) + contarHojas(actual.getDerecho());
    }

    public int altura() {
        return altura(raiz);
    }

    private int altura(Nodo<T> actual) {
        if (actual == null) {
            return -1;
        }
        return 1 + Math.max(altura(actual.getIzquierdo()), altura(actual.getDerecho()));
    }

    public void mostrarArbol() {
        if (raiz == null) {
            System.out.println("El arbol esta vacio");
            return;
        }
        System.out.println(raiz.getDato());
        mostrarHijos(raiz, "");
    }

    private void mostrarHijos(Nodo<T> actual, String prefijo) {
        Nodo<T> izq = actual.getIzquierdo();
        Nodo<T> der = actual.getDerecho();
        if (izq != null) {
            boolean ultimo = der == null;
            System.out.println(prefijo + (ultimo ? "\\-- " : "+-- ") + izq.getDato());
            mostrarHijos(izq, prefijo + (ultimo ? "    " : "|   "));
        }
        if (der != null) {
            System.out.println(prefijo + "\\-- " + der.getDato());
            mostrarHijos(der, prefijo + "    ");
        }
    }
}