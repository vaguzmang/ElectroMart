package electromart.controller;

import electromart.model.Cliente;
import electromart.model.DetallePedido;
import electromart.model.Pedido;
import electromart.model.Producto;
import electromart.model.Usuario;
import java.util.ArrayList;

public class SistemaController {

    public void mostrarUsuarios(ArrayList<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
            System.out.println();
        }
    }

    public void mostrarProductos(ArrayList<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto);
            System.out.printf("Precio final: %.2f%n", producto.calcularPrecioFinal());
            System.out.println();
        }
    }

    public void mostrarClientes(ArrayList<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
            System.out.println();
        }
    }

    public void mostrarPedidos(ArrayList<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
            System.out.println();

            for (DetallePedido detalle : pedido.getDetalles()) {
                System.out.println(detalle);
                System.out.println();
            }

            System.out.printf("Total del pedido: %.2f%n", pedido.calcularTotal());
            System.out.println();
        }
    }

    public void mostrarPermisos(ArrayList<Usuario> usuarios) {
        System.out.println("Permisos del sistema:");

        for (Usuario usuario : usuarios) {
            if (usuario.esAdministrador()) {
                System.out.println(usuario.getNombreUsuario() + " puede ver reportes.");
            }

            if (usuario.esGerenteInventario()) {
                System.out.println(usuario.getNombreUsuario() + " puede gestionar productos e inventario.");
            }

            if (usuario.esOperadorPedidos()) {
                System.out.println(usuario.getNombreUsuario() + " puede gestionar pedidos.");
            }
        }

        System.out.println();
    }

    public void mostrarStock(ArrayList<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println("Stock final " + producto.getNombre() + ": " + producto.getStock());
        }

        System.out.println();
    }
}