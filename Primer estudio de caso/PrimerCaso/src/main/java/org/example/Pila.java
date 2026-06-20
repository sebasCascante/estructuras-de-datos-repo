package org.example;

public class Pila{
    private Nodo tope;
    private int tamano;

    public Pila(){
        this.tope = null;
        this.tamano = 0;
    }

    //Apila un token en el tope
    public void push(String token){
        Nodo nuevo = new Nodo(token);
        nuevo.setSig(this.tope);
        this.tope = nuevo;
        this.tamano++;
    }

    //Desapila y retorna el token del tope
    public String pop(){
        if (estaVacia()){
            System.out.println("Error: la pila esta vacia.");
            return null;
        }
        String token = this.tope.getToken();
        this.tope = this.tope.getSig();
        this.tamano--;
        return token;
    }

    //Consulta el tope sin desapilar
    public String peek(){
        if (estaVacia()){
            System.out.println("Error: la pila esta vacia.");
            return null;
        }
        return this.tope.getToken();
    }

    public boolean estaVacia(){
        return this.tope == null;
    }

    public int gettamano(){
        return this.tamano;
    }

    //Imprime el contenido de la pila de tope a fondo
    public void mostrar(){
        if (estaVacia()){
            System.out.println("La pila esta vacia.");
            return;
        }
        Nodo actual = this.tope;
        System.out.print("Tope -> ");
        while (actual != null){
            System.out.print("[" + actual.getToken() + "]");
            if (actual.getSig() != null) System.out.print(" -> ");
            actual = actual.getSig();
        }
        System.out.println();
    }

    //Analiza si la expresion de cadena de impresion es sintacticamente valida
    public boolean analizarExpresion(String expresion){
        Pila pila = new Pila();
        //Tokenizar por espacios
        String[] tokens = expresion.trim().split("\\s+");

        if (tokens.length == 0) return false;

        for (int i = 0; i < tokens.length; i++){
            String t = tokens[i];

            if (t.equals("+")){
                //Operador: debe haber exactamente un operando ya apilado
                if (pila.estaVacia()){
                    System.out.println("Error: operador '+' sin operando izquierdo.");
                    return false;
                }
                String cima = pila.peek();
                if (cima.equals("+")){
                    System.out.println("Error: dos operadores consecutivos.");
                    return false;
                }
                pila.push(t);
            } else{
                //Operando: cadena literal o variable
                if (!pila.estaVacia() && !pila.peek().equals("+")){
                    System.out.println("Error: dos operandos consecutivos sin operador.");
                    return false;
                }
                //Si habia un operador en el tope, consumirlo junto con el operando anterior
                if (!pila.estaVacia() && pila.peek().equals("+")){
                    pila.pop(); //quita el '+'
                    pila.pop(); //quita el operando izquierdo
                }
                pila.push(t);
            }
        }

        //Al final debe quedar exactamente un operando (el resultado acumulado)
        if (pila.gettamano() == 1 && !pila.peek().equals("+")){
            return true;
        } else{
            System.out.println("Error: la expresion termina de forma incorrecta.");
            return false;
        }
    }
}
