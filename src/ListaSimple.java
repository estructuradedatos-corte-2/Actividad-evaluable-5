import java.io.*; // Importa clases para manejo de archivos

// Clase que implementa la lista enlazada
public class ListaSimple {

    private Nodo cabeza; // Apunta al primer nodo de la lista

    // Constructor que inicializa la lista vacía
    public ListaSimple() {
        cabeza = null; // No hay nodos al inicio
    }

    // INSERTAR AL FINAL
    public void insertarFinal(Producto p) {

        Nodo nuevo = new Nodo(p); // Crea un nodo con el producto

        if (cabeza == null) { // Si la lista está vacía
            cabeza = nuevo; // El nuevo nodo es la cabeza
        } else {

            Nodo actual = cabeza; // Empieza desde la cabeza

            while (actual.getSiguiente() != null) { 
                actual = actual.getSiguiente(); // Avanza hasta el último nodo
            }

            actual.setSiguiente(nuevo); // Enlaza el nuevo nodo al final
        }
    }

    // MOSTRAR LISTA
    public void mostrar() {

        Nodo actual = cabeza; // Empieza desde la cabeza

        while (actual != null) { // Recorre toda la lista

            Producto p = actual.getProducto(); // Obtiene el producto

            System.out.println(
                p.getId() + " - " +       // Imprime ID
                p.getNombre() + " - $" + // Imprime nombre
                p.getPrecio()            // Imprime precio
            );

            actual = actual.getSiguiente(); // Avanza al siguiente nodo
        }
    }

    // BUSCAR POR ID
    public Producto buscarPorId(int id) {

        Nodo actual = cabeza; // Empieza desde la cabeza

        while (actual != null) { // Recorre la lista

            if (actual.getProducto().getId() == id) { 
                return actual.getProducto(); // Retorna el producto encontrado
            }

            actual = actual.getSiguiente(); // Avanza
        }

        return null; // No encontró el producto
    }

    // ELIMINAR POR ID
    public void eliminarPorId(int id) {

        if (cabeza == null) return; // Si la lista está vacía

        // Caso especial: eliminar el primero
        if (cabeza.getProducto().getId() == id) {
            cabeza = cabeza.getSiguiente(); // Cambia la cabeza
            return;
        }

        Nodo actual = cabeza; // Empieza desde la cabeza

        // Busca el nodo anterior al que se eliminará
        while (actual.getSiguiente() != null &&
               actual.getSiguiente().getProducto().getId() != id) {

            actual = actual.getSiguiente(); // Avanza
        }

        // Si encontró el nodo
        if (actual.getSiguiente() != null) {
            actual.setSiguiente(actual.getSiguiente().getSiguiente()); // Salta el nodo eliminado
        }
    }

    // ORDENAR POR ID (SOLO CAMBIANDO PUNTEROS)
    public void ordenarPorId() {

        if (cabeza == null || cabeza.getSiguiente() == null) return;

        boolean cambio;

        do {
            cambio = false;

            Nodo actual = cabeza;
            Nodo anterior = null;

            while (actual.getSiguiente() != null) {

                Nodo siguiente = actual.getSiguiente();

                // Si están desordenados
                if (actual.getProducto().getId() > siguiente.getProducto().getId()) {

                    if (anterior == null) {
                        cabeza = siguiente; // Cambia la cabeza
                    } else {
                        anterior.setSiguiente(siguiente); // Enlaza anterior con siguiente
                    }

                    actual.setSiguiente(siguiente.getSiguiente()); // Salta el siguiente
                    siguiente.setSiguiente(actual); // Intercambia posiciones

                    cambio = true; // Hubo cambio
                    anterior = siguiente;

                } else {
                    anterior = actual; // Avanza normalmente
                    actual = actual.getSiguiente();
                }
            }

        } while (cambio);

        System.out.println("Lista ordenada");
    }

    // GUARDAR EN ARCHIVO CSV
    public void guardarEnArchivo(String nombreArchivo) {

        try {
            FileWriter writer = new FileWriter(nombreArchivo); // Abre archivo

            Nodo actual = cabeza; // Empieza desde la cabeza

            while (actual != null) {

                Producto p = actual.getProducto(); // Obtiene producto

                writer.write(
                    p.getId() + "," +
                    p.getNombre() + "," +
                    p.getPrecio() + "\n"
                ); // Escribe en formato CSV

                actual = actual.getSiguiente(); // Avanza
            }

            writer.close(); // Cierra archivo

            System.out.println("Guardado exitoso");

        } catch (IOException e) {
            System.out.println("Error al guardar");
        }
    }

    // CARGAR DESDE ARCHIVO CSV
    public void cargarDesdeArchivo(String nombreArchivo) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo)); // Abre archivo

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(","); // Separa por comas

                int id = Integer.parseInt(datos[0]); // Convierte ID
                String nombre = datos[1]; // Nombre
                double precio = Double.parseDouble(datos[2]); // Precio

                insertarFinal(new Producto(id, nombre, precio)); // Inserta en lista
            }

            br.close(); // Cierra archivo

            System.out.println("Carga exitosa");

        } catch (IOException e) {
            System.out.println("Error al cargar");
        }
    }
}