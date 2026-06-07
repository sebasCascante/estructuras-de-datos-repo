package org.example;

public class ColaPrioridad {

    private static final int capacidadInicial = 16;

    private Ticket[] heap;
    private int tamanio;

    public ColaPrioridad() {
        heap = new Ticket[capacidadInicial];
        tamanio = 0;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public void encolar(Ticket ticket) {
        if (tamanio == heap.length) {
            Ticket[] nuevo = new Ticket[heap.length * 2];
            System.arraycopy(heap, 0, nuevo, 0, heap.length);
            heap = nuevo;
        }
        heap[tamanio] = ticket;
        subirUltimo(tamanio);
        tamanio++;
    }

    public Ticket desencolar() {
        Ticket raiz = heap[0];
        tamanio--;
        heap[0] = heap[tamanio];
        heap[tamanio] = null;
        if (!estaVacia()) bajarDesde(0);
        return raiz;
    }

    public Ticket frente() {
        return heap[0];
    }

    private boolean tieneMasPrioridad(Ticket a, Ticket b) {
        int cmp = a.getPrioridad().compareTo(b.getPrioridad());
        if (cmp != 0) return cmp < 0;
        return a.getId() < b.getId();
    }

    private void subirUltimo(int indice) {
        while (indice > 0) {
            int padre = (indice - 1) / 2;
            if (tieneMasPrioridad(heap[indice], heap[padre])) {
                intercambiar(indice, padre);
                indice = padre;
            } else {
                break;
            }
        }
    }

    private void bajarDesde(int indice) {
        while (true) {
            int izq = 2 * indice + 1;
            int der = 2 * indice + 2;
            int menor = indice;

            if (izq < tamanio && tieneMasPrioridad(heap[izq], heap[menor])) menor = izq;
            if (der < tamanio && tieneMasPrioridad(heap[der], heap[menor])) menor = der;
            if (menor == indice) break;

            intercambiar(indice, menor);
            indice = menor;
        }
    }

    private void intercambiar(int i, int j) {
        Ticket temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
