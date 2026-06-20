package org.example;

import java.util.Scanner;

public class Main{

    public static void main(String[] args){
        menu();
    }

    public static void menu(){
        Scanner sc = new Scanner(System.in);
        Pila pila = new Pila();
        int opcion;

        do{
            System.out.println("\n##### Analizador de Strings #####");
            System.out.println("1. Apilar token (push)");
            System.out.println("2. Desapilar token (pop)");
            System.out.println("3. Ver tope de la pila (peek)");
            System.out.println("4. Mostrar contenido de la pila");
            System.out.println("5. Analizar expresion de cadena de impresion");
            System.out.println("6. Salir");
            System.out.print("Elija una opcion: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion){
                case 1:
                    System.out.print("Ingrese el token a apilar: ");
                    String token = sc.nextLine();
                    pila.push(token);
                    System.out.println("Token apilado: [" + token + "]");
                    break;

                case 2:
                    String desapilado = pila.pop();
                    if (desapilado != null)
                        System.out.println("Token desapilado: [" + desapilado + "]");
                    break;

                case 3:
                    String tope = pila.peek();
                    if (tope != null)
                        System.out.println("Tope actual: [" + tope + "]");
                    break;

                case 4:
                    pila.mostrar();
                    break;

                case 5:
                    System.out.println("Ingrese la expresion a analizar.");
                    System.out.println("Separe cada token con espacios.");
                    System.out.println("Ejemplo: \"Hola\" + nombre + \"!\"");
                    System.out.print("> ");
                    String expresion = sc.nextLine();
                    boolean valida = pila.analizarExpresion(expresion);
                    if (valida)
                        System.out.println("Resultado: la expresion es valida.");
                    else
                        System.out.println("Resultado: la expresion es valida.");
                    break;

                case 6:
                    System.out.println("Adios");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 6);

        sc.close();
    }
}