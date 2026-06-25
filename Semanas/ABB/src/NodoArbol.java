public class NodoArbol {

    //atributos
    private final int clave;
    private String nombre;
    private String familia;
    private int hablantes;
    private NodoArbol izq;
    private NodoArbol der;

    //metodos
    //constructor
    public NodoArbol(int clave, String nombre, String familia, int hablantes){
        this.clave = clave;
        this.nombre = nombre;
        this.familia = familia;
        this.hablantes = hablantes;
        izq = der = null;
    }

    //getters
    public int getClave(){ return clave; }
}
