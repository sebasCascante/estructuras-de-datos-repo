package org.example;

public class ListaEnlazada {

    private NodoLista cabeza;

    public ListaEnlazada(){
        cabeza = null;
    }

    public boolean estaVacia(){
        return cabeza == null;
    }

    public void agregar(Ticket ticket){
        NodoLista nuevo = new NodoLista(ticket);
        if (cabeza == null){
            cabeza = nuevo;
        } else{
            NodoLista actual = cabeza;
            while (actual.siguiente != null){
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public Ticket buscarPorId(int id){
        NodoLista actual = cabeza;
        while (actual != null){
            if (actual.ticket.getId() == id) return actual.ticket;
            actual = actual.siguiente;
        }
        return null;
    }

    public void mostrarTodos(){
        if (estaVacia()){
            System.out.println("No hay tickets resueltos.");
            return;
        }
        NodoLista actual = cabeza;
        while (actual != null){
            System.out.println(actual.ticket);
            actual = actual.siguiente;
        }
    }
}
