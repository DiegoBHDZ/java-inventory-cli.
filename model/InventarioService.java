package model;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class InventarioService {

    private ArrayList<Producto> listaProductos;
    private int sequenceid = 1;
    private static final String ARCHIVO = "inventario.csv";

    public InventarioService(){
        this.listaProductos = new ArrayList<>(); 
        this.sequenceid = 1;

        cargarDatos();
    }

    public void agregarProducto( String nombre, double precio, int cantidad ){
        Producto nuevoProducto = new Producto(sequenceid++, nombre, precio, cantidad);
        listaProductos.add(nuevoProducto);

        guardarEnArchivo(nuevoProducto);
    }

    public List<Producto> listarProductos(){
        return listaProductos;
    }

    public void guardarEnArchivo(Producto productoGuardado){
        try {
            FileWriter fileWriter = new FileWriter(ARCHIVO, true);
            BufferedWriter bufferedw = new BufferedWriter(fileWriter);
            String linea = productoGuardado.getId() + ","  + productoGuardado.getNombre() + "," + productoGuardado.getPrecio() + "," + productoGuardado.getCantidad();
            bufferedw.write(linea); 
            bufferedw.newLine(); 
            bufferedw.close(); 

        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " +  e.getMessage());
        }
    }

    private void cargarDatos() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return; 
        }

        try ( Scanner scanner = new Scanner(archivo) ) {
            while (scanner.hasNextLine()) { 
                String linea = scanner.nextLine(); 

                String[] partes = linea.split(","); 
                if (partes.length == 4) {
                    int id = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    double precio = Double.parseDouble(partes[2]);
                    int cantidad = Integer.parseInt(partes[3]);
                

                    Producto productoCargado = new Producto(id, nombre, precio, cantidad);
                    listaProductos.add(productoCargado); 

                    if (id >= sequenceid) {
                        this.sequenceid = id + 1;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage()); 
        }
    }
}

