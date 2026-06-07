package org.example;

//Cola de prioridad implementada con un Min-Heap manual.
//El de mayor prioridad siempre queda en heap[0], si empatan sale el de menor ID.
public class ColaPrioridad {

    private static final int capacidadInicial = 16;

    private Ticket[] heap;
    private int tamano;

    public ColaPrioridad(){
        heap = new Ticket[capacidadInicial];
        tamano = 0;
    }

    public boolean estaVacia(){
        return tamano == 0;
    }

    //Inserta el ticket y lo sube hasta donde le toca.
    public void encolar(Ticket ticket){
        if (tamano == heap.length){
            Ticket[] nuevo = new Ticket[heap.length * 2];
            System.arraycopy(heap, 0, nuevo, 0, heap.length);
            heap = nuevo;
        }
        heap[tamano] = ticket;
        subirUltimo(tamano);
        tamano++;
    }

    //Saca la raiz, pone el ultimo en su lugar y lo baja.
    public Ticket desencolar(){
        Ticket raiz = heap[0];
        tamano--;
        heap[0] = heap[tamano];
        heap[tamano] = null;
        if (!estaVacia()) bajarDesde(0);
        return raiz;
    }

    public Ticket frente(){
        return heap[0];
    }

    private boolean tieneMasPrioridad(Ticket a, Ticket b){
        int cmp = a.getPrioridad().compareTo(b.getPrioridad());
        if (cmp != 0) return cmp < 0;
        return a.getId() < b.getId();
    }

    //Sube el elemento mientras tenga mas prioridad que su padre.
    private void subirUltimo(int indice){
        while (indice > 0){
            int padre = (indice - 1) / 2;
            if (tieneMasPrioridad(heap[indice], heap[padre])){
                intercambiar(indice, padre);
                indice = padre;
            } else{
                break;
            }
        }
    }

    //Baja el elemento hacia el hijo con mayor prioridad.
    private void bajarDesde(int indice){
        while (true){
            int izq = 2 * indice + 1;
            int der = 2 * indice + 2;
            int menor = indice;

            if (izq < tamano && tieneMasPrioridad(heap[izq], heap[menor])) menor = izq;
            if (der < tamano && tieneMasPrioridad(heap[der], heap[menor])) menor = der;
            if (menor == indice) break;

            intercambiar(indice, menor);
            indice = menor;
        }
    }

    private void intercambiar(int i, int j){
        Ticket temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void mostrarTodos(){
        if (estaVacia()){
            System.out.println("No hay tickets pendientes.");
            return;
        }
        for (int i = 0; i < tamano; i++){
            System.out.println(heap[i]);
        }
    }
}
