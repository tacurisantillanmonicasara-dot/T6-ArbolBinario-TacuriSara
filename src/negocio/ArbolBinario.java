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
}