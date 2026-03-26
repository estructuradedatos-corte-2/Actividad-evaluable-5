import java.util.Scanner; // Para leer datos

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // Crear lector
        ListaSimple lista = new ListaSimple(); // Crear lista

        int opcion; // Variable para el menú

        do {
            System.out.println("\n--- INVENTARIO ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar");
            System.out.println("3. Buscar");
            System.out.println("4. Eliminar");
            System.out.println("5. Ordenar");
            System.out.println("6. Guardar");
            System.out.println("7. Cargar");
            System.out.println("0. Salir");

            opcion = sc.nextInt(); // Leer opción

            switch (opcion) {

                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();

                    lista.insertarFinal(new Producto(id, nombre, precio));
                    break;

                case 2:
                    lista.mostrar();
                    break;

                case 3:
                    System.out.print("ID: ");
                    Producto p = lista.buscarPorId(sc.nextInt());

                    if (p != null) {
                        System.out.println("Encontrado: " + p.getNombre());
                    } else {
                        System.out.println("No encontrado");
                    }
                    break;

                case 4:
                    System.out.print("ID: ");
                    lista.eliminarPorId(sc.nextInt());
                    break;

                case 5:
                    lista.ordenarPorId();
                    break;

                case 6:
                    lista.guardarEnArchivo("inventario.csv");
                    break;

                case 7:
                    lista.cargarDesdeArchivo("inventario.csv");
                    break;
            }

        } while (opcion != 0); // Repite hasta salir
    }
}