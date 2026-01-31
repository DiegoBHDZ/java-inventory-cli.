package app;

import java.util.List;
import java.util.Scanner;

import model.InventarioService;
import model.Producto;

public class App {

    public static void main(String[] args) {
        InventarioService servicio = new InventarioService();
        Scanner sc = new Scanner(System.in);

        boolean ejecutar = true;
        while(ejecutar){
            System.out.println("Gestor de productos");
            System.out.println("1. Agregar producto producto");
            System.out.println("2. Ver lista de los productos");
            System.out.println("3. Salir del sistema.");
            System.out.print("Selecciona la opción a realizar: ");

            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el nombre del producto: ");
                    String nombre = sc.nextLine();
                    System.out.print("Ingresa precio del producto: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Ingresa cantidad del producto: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();
                    servicio.agregarProducto(nombre, precio, cantidad);
                    System.out.println("Producto agregado correctamente.\n");
                    break;
                case 2:
                    System.out.println("Todos los productos de la lista son los siguientes\n");
                    List<Producto> listaProductos = servicio.listarProductos();
                    if( listaProductos.isEmpty() ){
                        System.out.println("No se encuentran productos\n");
                    }else{
                        for( Producto producto : listaProductos ){
                            System.out.println(producto);
                        }
                    }
                    break;
                case 3:
                    ejecutar=false;
                    break;
                default:
                    System.out.println("Opcion invalida, ingresa una opcion valida.");
                    break;
            }
        }
        sc.close();
    }
}
