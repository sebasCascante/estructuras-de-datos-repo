package org.example;

//Lista enlazada simple para almacenar los tickets ya resueltos.
public class ListaEnlazada {

    private NodoLista cabeza;

    public ListaEnlazada(){
        cabeza = null;
    }

    public boolean estaVacia(){
        return cabeza == null;
    }

    //Agrega el ticket al final de la lista.
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
    //Recorre la lista buscando el ticket por ID, devuelve null si no lo encuentra.
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
