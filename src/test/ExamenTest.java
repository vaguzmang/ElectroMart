package test;

import electromart.controller.SistemaController;
import electromart.model.Computadora;
import electromart.model.Electrodomestico;
import electromart.model.Producto;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ExamenTest {

    public static void main(String[] args) {
        probarBusquedaAvanzadaEncuentraProductosPorCategoriaYPrecio();
        probarBusquedaAvanzadaCategoriaInvalidaYDecimalConComa();
        probarBusquedaAvanzadaSinResultados();

        System.out.println("Todas las pruebas de Busqueda Avanzada de Productos pasaron correctamente.");
    }

    private static void probarBusquedaAvanzadaEncuentraProductosPorCategoriaYPrecio() {
        ArrayList<Producto> productos = crearProductosDePrueba();

        String entradaUsuario = "\ncomputadora\n2000\n3500\n0\n";
        String salida = ejecutarBusqueda(productos, entradaUsuario);

        validar(salida.contains("Portatil Lenovo"),
                "Debe mostrar la computadora que cumple con categoria y rango de precio.");
        validar(!salida.contains("PC Gamer"),
                "No debe mostrar computadoras fuera del precio maximo.");
        validar(!salida.contains("Nevera Haceb"),
                "No debe mostrar electrodomesticos cuando la categoria es computadora.");
    }

    private static void probarBusquedaAvanzadaCategoriaInvalidaYDecimalConComa() {
        ArrayList<Producto> productos = crearProductosDePrueba();

        String entradaUsuario = "\ncelulares\nELECTRODOMESTICO\n300,50\n1000,75\n0\n";
        String salida = ejecutarBusqueda(productos, entradaUsuario);

        validar(salida.contains("Alerta: la categoria ingresada no existe en el inventario."),
                "Debe alertar cuando la categoria no existe.");
        validar(salida.contains("Licuadora Oster"),
                "Debe aceptar decimales con coma y mostrar productos dentro del rango.");
        validar(!salida.contains("Nevera Haceb"),
                "No debe mostrar electrodomesticos fuera del precio maximo.");
    }

    private static void probarBusquedaAvanzadaSinResultados() {
        ArrayList<Producto> productos = crearProductosDePrueba();

        String entradaUsuario = "\nelectrodomestico\n10\n20\n0\n";
        String salida = ejecutarBusqueda(productos, entradaUsuario);

        validar(salida.contains("No se encontro ningun producto con los criterios de busqueda ingresados."),
                "Debe mostrar mensaje cuando no hay productos que cumplan los tres criterios.");
    }

    private static ArrayList<Producto> crearProductosDePrueba() {
        ArrayList<Producto> productos = new ArrayList<>();

        productos.add(new Computadora("Intel i5", 8,
                "C001", "Portatil Lenovo", 2500.00, 10));
        productos.add(new Computadora("AMD Ryzen 7", 16,
                "C002", "PC Gamer", 5200.00, 4));
        productos.add(new Electrodomestico("A", 24,
                "E001", "Licuadora Oster", 450.50, 15));
        productos.add(new Electrodomestico("A+", 36,
                "E002", "Nevera Haceb", 2100.00, 6));

        return productos;
    }

    private static String ejecutarBusqueda(ArrayList<Producto> productos, String entradaUsuario) {
        SistemaController controller = new SistemaController();

        InputStream entradaOriginal = System.in;
        PrintStream salidaOriginal = System.out;

        ByteArrayInputStream entradaSimulada = new ByteArrayInputStream(
                entradaUsuario.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream salidaCapturada = new ByteArrayOutputStream();

        try {
            System.setIn(entradaSimulada);
            System.setOut(new PrintStream(salidaCapturada, true, StandardCharsets.UTF_8));

            Scanner sc = new Scanner(System.in);
            controller.busquedaAvanzadaProductos(productos, sc);

            return salidaCapturada.toString(StandardCharsets.UTF_8);
        } finally {
            System.setIn(entradaOriginal);
            System.setOut(salidaOriginal);
        }
    }

    private static void validar(boolean condicion, String mensajeError) {
        if (!condicion) {
            throw new AssertionError(mensajeError);
        }
    }
}