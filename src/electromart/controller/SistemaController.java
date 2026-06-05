package electromart.controller;

import electromart.model.Cliente;
import electromart.model.Computadora;
import electromart.model.DetallePedido;
import electromart.model.Electrodomestico;
import electromart.model.Pedido;
import electromart.model.Producto;
import electromart.model.Usuario;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaController {

    public void mostrarUsuarios(ArrayList<Usuario> usuarios) {
        System.out.println("===== USUARIOS =====");
        System.out.printf("%-5s %-15s %-25s%n", "ID", "USUARIO", "ROL");
        System.out.println("------------------------------------------------");

        for (Usuario usuario : usuarios) {
            System.out.printf("%-5d %-15s %-25s%n",
                    usuario.getId(),
                    usuario.getNombreUsuario(),
                    usuario.getRol());
        }

        System.out.println();
    }

    public void mostrarProductos(ArrayList<Producto> productos) {
        System.out.println("===== PRODUCTOS =====");
        System.out.printf("%-10s %-20s %-18s %-12s %-10s %-12s%n",
                "CODIGO", "NOMBRE", "TIPO", "PRECIO", "STOCK", "P. FINAL");
        System.out.println("--------------------------------------------------------------------------------");

        for (Producto producto : productos) {
            System.out.printf("%-10s %-20s %-18s %-12.2f %-10d %-12.2f%n",
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getClass().getSimpleName(),
                    producto.getPrecioBase(),
                    producto.getStock(),
                    producto.calcularPrecioFinal());
        }

        System.out.println();
    }

    public void mostrarClientes(ArrayList<Cliente> clientes) {
        System.out.println("===== CLIENTES =====");
        System.out.printf("%-5s %-20s %-25s %-15s%n",
                "ID", "NOMBRE", "EMAIL", "TELEFONO");
        System.out.println("--------------------------------------------------------------------");

        for (Cliente cliente : clientes) {
            System.out.printf("%-5d %-20s %-25s %-15s%n",
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getEmail(),
                    cliente.getTelefono());
        }

        System.out.println();
    }

    public void mostrarPedidos(ArrayList<Pedido> pedidos) {
        System.out.println("===== PEDIDOS =====");
        System.out.printf("%-5s %-20s %-15s %-15s %-12s%n",
                "ID", "CLIENTE", "FECHA", "ESTADO", "TOTAL");
        System.out.println("--------------------------------------------------------------------------");

        for (Pedido pedido : pedidos) {
            System.out.printf("%-5d %-20s %-15s %-15s %-12.2f%n",
                    pedido.getId(),
                    pedido.getCliente().getNombre(),
                    pedido.getFecha(),
                    pedido.getEstado(),
                    pedido.calcularTotal());

            System.out.println();
            System.out.println("Detalles del pedido:");
            System.out.printf("%-5s %-20s %-10s %-15s %-12s%n",
                    "ID", "PRODUCTO", "CANTIDAD", "P. UNITARIO", "SUBTOTAL");
            System.out.println("----------------------------------------------------------------");

            for (DetallePedido detalle : pedido.getDetalles()) {
                System.out.printf("%-5d %-20s %-10d %-15.2f %-12.2f%n",
                        detalle.getId(),
                        detalle.getProducto().getNombre(),
                        detalle.getCantidad(),
                        detalle.getPrecioUnitario(),
                        detalle.calcularSubtotal());
            }

            System.out.println();
        }
    }

    public void mostrarPermisos(ArrayList<Usuario> usuarios) {
        System.out.println("===== PERMISOS DEL SISTEMA =====");
        System.out.printf("%-15s %-25s %-40s%n",
                "USUARIO", "ROL", "PERMISO");
        System.out.println("--------------------------------------------------------------------------------");

        for (Usuario usuario : usuarios) {
            if (usuario.esAdministrador()) {
                System.out.printf("%-15s %-25s %-40s%n",
                        usuario.getNombreUsuario(),
                        usuario.getRol(),
                        "Puede ver reportes");
            }

            if (usuario.esGerenteInventario()) {
                System.out.printf("%-15s %-25s %-40s%n",
                        usuario.getNombreUsuario(),
                        usuario.getRol(),
                        "Puede gestionar productos e inventario");
            }

            if (usuario.esOperadorPedidos()) {
                System.out.printf("%-15s %-25s %-40s%n",
                        usuario.getNombreUsuario(),
                        usuario.getRol(),
                        "Puede gestionar pedidos");
            }
        }

        System.out.println();
    }

    public void mostrarStock(ArrayList<Producto> productos) {
        System.out.println("===== STOCK FINAL =====");
        System.out.printf("%-10s %-20s %-18s %-10s%n",
                "CODIGO", "PRODUCTO", "TIPO", "STOCK");
        System.out.println("--------------------------------------------------------------");

        for (Producto producto : productos) {
            System.out.printf("%-10s %-20s %-18s %-10d%n",
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getClass().getSimpleName(),
                    producto.getStock());
        }

        System.out.println();
    }

    public void registrarComputadora(ArrayList<Producto> productos, Scanner sc) {
        Computadora computadora = new Computadora();

        System.out.println("===== REGISTRAR COMPUTADORA =====");

        System.out.print("Codigo: ");
        computadora.setCodigo(sc.next());

        sc.nextLine();

        System.out.print("Nombre: ");
        computadora.setNombre(sc.nextLine());

        System.out.print("Precio base: ");
        computadora.setPrecioBase(sc.nextDouble());

        System.out.print("Stock: ");
        computadora.setStock(sc.nextInt());

        sc.nextLine();

        System.out.print("Procesador: ");
        computadora.setProcesador(sc.nextLine());

        System.out.print("RAM GB: ");
        computadora.setRamGB(sc.nextInt());

        productos.add(computadora);

        System.out.println("Computadora registrada correctamente.");
        System.out.println();
    }

    public void registrarCliente(ArrayList<Cliente> clientes, Scanner sc) {
        Cliente cliente = new Cliente();

        System.out.println("===== REGISTRAR CLIENTE =====");

        System.out.print("ID: ");
        cliente.setId(sc.nextInt());

        sc.nextLine();

        System.out.print("Nombre: ");
        cliente.setNombre(sc.nextLine());

        System.out.print("Email: ");
        cliente.setEmail(sc.nextLine());

        System.out.print("Telefono: ");
        cliente.setTelefono(sc.nextLine());

        clientes.add(cliente);

        System.out.println("Cliente registrado correctamente.");
        System.out.println();
    }

    public void registrarElectrodomestico(ArrayList<Producto> productos, Scanner sc) {
        Electrodomestico electrodomestico = new Electrodomestico();

        System.out.println("===== REGISTRAR ELECTRODOMESTICO =====");

        System.out.print("Codigo: ");
        electrodomestico.setCodigo(sc.next());

        sc.nextLine();

        System.out.print("Nombre: ");
        electrodomestico.setNombre(sc.nextLine());

        System.out.print("Precio base: ");
        electrodomestico.setPrecioBase(sc.nextDouble());

        System.out.print("Stock: ");
        electrodomestico.setStock(sc.nextInt());

        sc.nextLine();

        System.out.print("Consumo energetico: ");
        electrodomestico.setConsumoEnergetico(sc.nextLine());

        System.out.print("Garantia en meses: ");
        electrodomestico.setGarantiaMeses(sc.nextInt());

        productos.add(electrodomestico);

        System.out.println("Electrodomestico registrado correctamente.");
        System.out.println();
    }

    public Usuario login(ArrayList<Usuario> usuarios, String nombreUsuario, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)
                    && usuario.getPassword().equals(password)) {
                return usuario;
            }
        }

        return null;
    }
}