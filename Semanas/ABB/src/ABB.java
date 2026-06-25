

public class ABB {

    //something missing here, gotta see the replay


    //Metodos
    //Constructor
    public ABB(){
        raiz = null;
    }

    //Getters
    public NodoArbol getRaiz(){
        return raiz;
    }

    //Setters
    public void setRaiz(NodoArbol raiz){
        this.raiz = raiz;
    }

    //Operaciones
    private boolean estaVacia(){
        return raiz == null;
    }

    public void insertar(int clave, String nombre, String familia, int hablantes){
        NodoArbol nodo = new NodoArbol(clave, nombre, familia, hablantes);
        if(estaVacia()){
            setRaiz(nodo);
            return;
        }
        NodoArbol temp = raiz;
        NodoArbol padreTemp = temp;
        while(temp != null){
            padreTemp = temp;
            if(temp.getClave() < clave) temp = temp.getDer();
            else if(temp.getClave() > clave) temp = temp.getIzq();
            else{
                System.out.println("La clave ya se encuentra en el arbol.\n");
                return;
            }
        }
        if(padreTemp.getClave() < clave) padreTemp.setDer(nodo);
        else padreTemp.setIzq(nodo);
    }

    public NodoArbol buscar(int clave) {
        if (estaVacia()) {
            System.out.println("El árbol está vacío.\n");
            return null;
        }
        NodoArbol temp = raiz;
        while (temp != null) {
            if (temp.getClave() < clave) temp = temp.getDer();
            else if (temp.getClave() > clave) temp = temp.getIzq();
            else return temp;
        }
        System.out.println("La clave no está en el árbol.\n");
        return null;
    }

    private NodoArbol buscarPadre(int clave) {
        if (estaVacia()) {
            System.out.println("El árbol está vacío.\n");
            return null;
        }
        NodoArbol temp = raiz;
        NodoArbol padreTemp = temp;
        while (temp != null) {
            if(temp.getClave() == clave) return padreTemp;
            padreTemp = temp;
            if (temp.getClave() < clave) temp = temp.getDer();
            else temp = temp.getIzq();
        }
        System.out.println("La clave no está en el árbol.\n");
        return null;
    }
}