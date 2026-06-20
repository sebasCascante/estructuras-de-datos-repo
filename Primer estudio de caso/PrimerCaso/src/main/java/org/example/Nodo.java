package org.example;

//Nodo que almacena un token de tipo String
public class Nodo{
    private String token;
    private Nodo sig;

    public Nodo(String token){
        this.token = token;
        this.sig = null;
    }

    public String getToken(){ 
        return token; 
    }
    public void setToken(String token){ 
        this.token = token; 
    }
    public Nodo getSig(){ 
        return sig; 
    }
    public void setSig(Nodo sig){ 
        this.sig = sig; 
    }
}
