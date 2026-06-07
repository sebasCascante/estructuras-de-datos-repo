package org.example;

import java.time.LocalDate;

public class Ticket{
    public enum Prioridad {
        P1, P2, P3;

        public static Prioridad fromString(String valor){
            switch(valor.trim()){
                case "1": return P1;
                case "2": return P2;
                case "3": return P3;
                default: throw new IllegalArgumentException("Prioridad invalida.");
            }
        }
    }

    private static int cantidad = 0;

    private final int id;
    private final String descripcion;
    private final String nombreCompleto;
    private final String fechaCreacion;
    private final Prioridad prioridad;
    private String fechaResolucion;

    public Ticket(String descripcion, String nombreCompleto, Prioridad prioridad){
        this.id = ++cantidad;
        this.descripcion = descripcion;
        this.nombreCompleto = nombreCompleto;
        this.prioridad = prioridad;
        this.fechaCreacion = LocalDate.now().toString();
        this.fechaResolucion = null;
    }

    public void resolver(){
        this.fechaResolucion = LocalDate.now().toString();
    }

    @Override
    public String toString(){
        return "ID: " + id +
                " | Prioridad: " + prioridad +
                " | Usuario: " + nombreCompleto +
                " | Descripcion: " + descripcion +
                " | Creado: " + fechaCreacion +
                " | Resuelto: " + (fechaResolucion != null ? fechaResolucion : "Pendiente");
    }

    public int getId(){ return id; }
    public String getDescripcion(){ return descripcion; }
    public String getNombreCompleto(){ return nombreCompleto; }
    public String getFechaCreacion(){ return fechaCreacion; }
    public Prioridad getPrioridad(){ return prioridad; }
    public String getFechaResolucion(){ return fechaResolucion; }
    public static int getCantidad(){ return cantidad; }
}
