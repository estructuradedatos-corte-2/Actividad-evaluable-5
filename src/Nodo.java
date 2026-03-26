public class Nodo {

    private Producto producto;
    private Nodo siguiente;

    public Nodo(Producto producto){
        this.producto=producto;
        this.siguiente=null;
    }

    public Producto getProducto(){
        return producto;
    }

    public Nodo getSiguiente(){
        return siguiente;
    }

    //set
    public void setSiguiente(Nodo siguiente){
        this.siguiente=siguiente;
    }

}