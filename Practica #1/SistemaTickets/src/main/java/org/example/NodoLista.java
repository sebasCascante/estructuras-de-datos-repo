package org.example;

public class NodoLista {

    Ticket ticket;
    NodoLista siguiente;

    public NodoLista(Ticket ticket){
        this.ticket = ticket;
        this.siguiente = null;
    }
}