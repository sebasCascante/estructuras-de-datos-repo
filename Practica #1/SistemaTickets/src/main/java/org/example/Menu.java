package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private final ColaPrioridad colaPendientes;
    private final ListaEnlazada listaResueltos;
    private final Scanner scanner;

    public Menu(){
        colaPendientes = new ColaPrioridad();
        listaResueltos = new ListaEnlazada();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal(){
        int opcion = -1;
        do{
            System.out.println("\n=== Sistema de Tickets ===");
            System.out.println("1. Menu Usuario");
            System.out.println("2. Menu Administrador");
            System.out.println("0. Salir");
            opcion = leerEntero("Seleccione una opcion: ");

            switch(opcion){
                case 1 -> menuUsuario();
                case 2 -> menuAdministrador();
                case 0 -> System.out.println("Hasta luego.");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    private void menuUsuario(){
        int opcion = -1;
        do{
            System.out.println("\n--- Menu Usuario ---");
            System.out.println("1. Crear ticket");
            System.out.println("2. Buscar ticket resuelto");
            System.out.println("0. Volver");
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion){
                case 1 -> crearTicket();
                case 2 -> buscarTicketResuelto();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opcion no valida.");
            }
        } while(opcion != 0);
    }

    private void menuAdministrador(){
        int opcion = -1;
        do{
            System.out.println("\n--- Menu Administrador ---");
            System.out.println("1. Ver ticket al frente de la cola");
            System.out.println("2. Resolver ticket al frente de la cola");
            System.out.println("0. Volver");
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion){
                case 1 -> verFrenteCola();
                case 2 -> resolverTicket();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    private void crearTicket(){
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()){
            System.out.println("El nombre no puede estar vacio.");
            return;
        }

        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine().trim();
        if (descripcion.isEmpty()){
            System.out.println("La descripcion no puede estar vacia.");
            return;
        }

        Ticket.Prioridad prioridad = null;
        while (prioridad == null){
            System.out.print("Prioridad (1=Alta / 2=Media / 3=Baja): ");
            String entrada = scanner.nextLine().trim();
            try{
                prioridad = Ticket.Prioridad.fromString(entrada);
            } catch (IllegalArgumentException e){
                System.out.println("Ingrese 1, 2 o 3.");
            }
        }

        Ticket ticket = new Ticket(descripcion, nombre, prioridad);
        colaPendientes.encolar(ticket);
        System.out.println("Ticket creado. Su ID es: #" + ticket.getId());
    }

    private void buscarTicketResuelto(){
        int id = leerEntero("Ingrese el ID del ticket: ");
        if (id <= 0){
            System.out.println("El ID debe ser un numero positivo.");
            return;
        }

        Ticket encontrado = listaResueltos.buscarPorId(id);
        if (encontrado != null){
            System.out.println(encontrado);
        } else if (id > Ticket.getCantidad()){
            System.out.println("No existe un ticket con ese ID.");
        } else{
            System.out.println("El ticket #" + id + " esta pendiente de resolucion.");
        }
    }

    private void verFrenteCola(){
        if (colaPendientes.estaVacia()){
            System.out.println("No hay tickets pendientes.");
            return;
        }
        System.out.println("Proximo ticket a resolver:");
        System.out.println(colaPendientes.frente());
    }

    private void resolverTicket(){
        if (colaPendientes.estaVacia()){
            System.out.println("No hay tickets pendientes.");
            return;
        }
        Ticket ticket = colaPendientes.desencolar();
        ticket.resolver();
        listaResueltos.agregar(ticket);
        System.out.println("Ticket resuelto:");
        System.out.println(ticket);
    }

    private int leerEntero(String prompt){
        System.out.print(prompt);
        try{
            int valor = scanner.nextInt();
            scanner.nextLine();
            return valor;
        } catch (InputMismatchException e){
            scanner.nextLine();
            return -1;
        }
    }
}
