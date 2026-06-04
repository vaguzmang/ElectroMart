package electromart;

import electromart.model.Computadora;
import electromart.model.Electrodomestico;
import electromart.model.Cliente;
import electromart.model.Pedido;
import electromart.model.EstadoPedido;
import electromart.model.DetallePedido;
import electromart.model.Usuario;
import electromart.model.Rol;
import java.util.Scanner;
import java.util.ArrayList;
import electromart.model.Producto;
import electromart.view.MenuConsola;
import electromart.controller.SistemaController;

public class ElectroMart {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;
        
        MenuConsola menu = new MenuConsola();
        SistemaController controller = new SistemaController();

        Usuario usuario1 = new Usuario();
        usuario1.setId(1);
        usuario1.setNombreUsuario("admin");
        usuario1.setPassword("1234");
        usuario1.setRol(Rol.ADMINISTRADOR);

        Usuario usuario2 = new Usuario();
        usuario2.setId(2);
        usuario2.setNombreUsuario("inventario");
        usuario2.setPassword("1234");
        usuario2.setRol(Rol.GERENTE_INVENTARIO);

        Usuario usuario3 = new Usuario();
        usuario3.setId(3);
        usuario3.setNombreUsuario("pedidos");
        usuario3.setPassword("1234");
        usuario3.setRol(Rol.OPERADOR_PEDIDOS);
        
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        
       Usuario usuarioActual = null;
        int intentos = 0;

        while (usuarioActual == null && intentos < 3) {
            System.out.println("===== LOGIN ELECTROMART =====");
            System.out.print("Usuario: ");
            String nombreUsuario = sc.next();

            System.out.print("Password: ");
            String password = sc.next();

            usuarioActual = controller.login(usuarios, nombreUsuario, password);

            if (usuarioActual == null) {
                intentos++;
                System.out.println("Credenciales incorrectas. Intento " + intentos + " de 3.");
                System.out.println();
            }
        }

        if (usuarioActual == null) {
            System.out.println("Demasiados intentos fallidos. Cerrando sistema.");
            return;
        }

        System.out.println("Bienvenido, " + usuarioActual.getNombreUsuario());
        System.out.println("Rol: " + usuarioActual.getRol());
        System.out.println();
        
        Computadora pc1 = new Computadora();
        pc1.setCodigo("PC001");
        pc1.setNombre("Asus Gamer");
        pc1.setPrecioBase(3500);
        pc1.setStock(5);
        pc1.setProcesador("Intel i7");
        pc1.setRamGB(16);

        Electrodomestico nevera1 = new Electrodomestico();
        nevera1.setCodigo("E001");
        nevera1.setNombre("Nevera Samsung");
        nevera1.setPrecioBase(2200);
        nevera1.setStock(3);
        nevera1.setConsumoEnergetico("A++");
        nevera1.setGarantiaMeses(24);

        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(pc1);
        productos.add(nevera1);
        
        Cliente cliente1 = new Cliente();
        cliente1.setId(1);
        cliente1.setNombre("Carlos Perez");
        cliente1.setEmail("carlos@email.com");
        cliente1.setTelefono("3001234567");
        
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente1);

        Pedido pedido1 = new Pedido();
        pedido1.setId(1);
        pedido1.setCliente(cliente1);
        pedido1.setFecha("2026-05-28");
        pedido1.setEstado(EstadoPedido.PENDIENTE);

        DetallePedido detalle1 = new DetallePedido();
        detalle1.setId(1);
        detalle1.setProducto(pc1);
        detalle1.setCantidad(1);
        detalle1.setPrecioUnitario(pc1.getPrecioBase());

        pedido1.agregarDetalle(detalle1);

        DetallePedido detalle2 = new DetallePedido();
        detalle2.setId(2);
        detalle2.setProducto(nevera1);
        detalle2.setCantidad(1);
        detalle2.setPrecioUnitario(nevera1.getPrecioBase());

        pedido1.agregarDetalle(detalle2);
        
        ArrayList<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido1);

        do {
            menu.mostrarMenu();

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println();
                    controller.mostrarUsuarios(usuarios);
                    break;

                case 2:
                    System.out.println();
                    controller.mostrarProductos(productos);
                    break;

                case 3:
                    System.out.println();
                    controller.mostrarClientes(clientes);
                    break;

                case 4:
                    System.out.println();
                    controller.mostrarPedidos(pedidos);
                    break;

                case 5:
                    System.out.println();
                    controller.mostrarPermisos(usuarios);
                    break;

                case 6:
                    System.out.println();
                    controller.mostrarStock(productos);
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    System.out.println();
                    break;
            }

        } while (opcion != 0);

        sc.close();
    }
}