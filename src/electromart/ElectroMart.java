package electromart;

import electromart.controller.SistemaController;
import electromart.dao.ClienteDAO;
import electromart.dao.ProductoDAO;
import electromart.model.Cliente;
import electromart.model.DetallePedido;
import electromart.model.EstadoPedido;
import electromart.model.Pedido;
import electromart.model.Producto;
import electromart.model.Rol;
import electromart.model.Usuario;
import electromart.view.MenuConsola;
import java.util.ArrayList;
import java.util.Scanner;
import electromart.dao.UsuarioDAO;

public class ElectroMart {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        MenuConsola menu = new MenuConsola();
        SistemaController controller = new SistemaController();

        ProductoDAO productoDAO = new ProductoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();

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

        ArrayList<Producto> productos = productoDAO.listarProductos();
        ArrayList<Cliente> clientes = clienteDAO.listarClientes();

        ArrayList<Pedido> pedidos = new ArrayList<>();

        if (!clientes.isEmpty() && productos.size() >= 26) {
            Cliente cliente1 = clientes.get(0);
            Producto producto1 = productos.get(0);
            Producto producto2 = productos.get(25);

            Pedido pedido1 = new Pedido();
            pedido1.setId(1);
            pedido1.setCliente(cliente1);
            pedido1.setFecha("2026-05-28");
            pedido1.setEstado(EstadoPedido.PENDIENTE);

            DetallePedido detalle1 = new DetallePedido();
            detalle1.setId(1);
            detalle1.setProducto(producto1);
            detalle1.setCantidad(1);
            detalle1.setPrecioUnitario(producto1.getPrecioBase());

            pedido1.agregarDetalle(detalle1);

            DetallePedido detalle2 = new DetallePedido();
            detalle2.setId(2);
            detalle2.setProducto(producto2);
            detalle2.setCantidad(1);
            detalle2.setPrecioUnitario(producto2.getPrecioBase());

            pedido1.agregarDetalle(detalle2);

            pedidos.add(pedido1);
        }

        boolean salirSistema = false;

        while (!salirSistema) {

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
                salirSistema = true;
            } else {
                System.out.println("Bienvenido, " + usuarioActual.getNombreUsuario());
                System.out.println("Rol: " + usuarioActual.getRol());
                System.out.println();

                do {
                    menu.mostrarMenu(usuarioActual);

                    if (sc.hasNextInt()) {
                        opcion = sc.nextInt();
                    } else {
                        System.out.println("Debe ingresar un numero valido.");
                        sc.next();
                        opcion = -1;
                    }

                    switch (opcion) {
                        case 1:
                            if (usuarioActual.esAdministrador()) {
                                System.out.println();
                                controller.mostrarUsuarios(usuarios);
                            } else {
                                System.out.println("No tiene permisos para ver usuarios.");
                                System.out.println();
                            }
                            menu.pausar(sc);
                            break;

                        case 2:
                            if (usuarioActual.esAdministrador() || usuarioActual.esGerenteInventario()) {
                                System.out.println();
                                controller.mostrarProductos(productos);
                            } else {
                                System.out.println("No tiene permisos para ver productos.");
                                System.out.println();
                            }
                            menu.pausar(sc);
                            break;

                        case 3:
                            if (usuarioActual.esAdministrador() || usuarioActual.esOperadorPedidos()) {
                                System.out.println();
                                controller.mostrarClientes(clientes);
                            } else {
                                System.out.println("No tiene permisos para ver clientes.");
                                System.out.println();
                            }
                            menu.pausar(sc);
                            break;

                        case 4:
                            if (usuarioActual.esAdministrador() || usuarioActual.esOperadorPedidos()) {
                                System.out.println();
                                controller.mostrarPedidos(pedidos);
                            } else {
                                System.out.println("No tiene permisos para ver pedidos.");
                                System.out.println();
                            }
                            menu.pausar(sc);
                            break;

                        case 5:
                            if (usuarioActual.esAdministrador()) {
                                System.out.println();
                                controller.mostrarPermisos(usuarios);
                            } else {
                                System.out.println("No tiene permisos para ver permisos del sistema.");
                                System.out.println();
                            }
                            menu.pausar(sc);
                            break;

                        case 6:
                            if (usuarioActual.esAdministrador() || usuarioActual.esGerenteInventario()) {
                                System.out.println();
                                controller.mostrarStock(productos);
                            } else {
                                System.out.println("No tiene permisos para ver stock.");
                                System.out.println();
                            }
                            menu.pausar(sc);
                            break;

                        case 8:
                            if (usuarioActual.esAdministrador() || usuarioActual.esGerenteInventario()) {
                                System.out.println();
                                controller.registrarComputadora(productos, sc);
                            } else {
                                System.out.println("No tiene permisos para registrar computadoras.");
                                System.out.println();
                            }
                            menu.pausar(sc);
                            break;

                        case 9:
                            if (usuarioActual.esAdministrador() || usuarioActual.esOperadorPedidos()) {
                                System.out.println();
                                controller.registrarCliente(clientes, sc);
                            } else {
                                System.out.println("No tiene permisos para registrar clientes.");
                                System.out.println();
                            }
                            menu.pausar(sc);
                            break;

                        case 10:
                            if (usuarioActual.esAdministrador() || usuarioActual.esGerenteInventario()) {
                                System.out.println();
                                controller.registrarElectrodomestico(productos, sc);
                            } else {
                                System.out.println("No tiene permisos para registrar electrodomesticos.");
                                System.out.println();
                            }
                            menu.pausar(sc);
                            break;

                        case 11:
                            if (usuarioActual.esAdministrador() || usuarioActual.esOperadorPedidos()) {
                                System.out.println();
                                controller.crearPedido(pedidos, clientes, productos, sc);
                            } else {
                                System.out.println("No tiene permisos para crear pedidos.");
                                System.out.println();
                            }
                            menu.pausar(sc);
                            break;

                        case 12:
                            if (usuarioActual.esAdministrador()) {
                                System.out.println();
                                controller.mostrarReportes(pedidos, productos, clientes);
                            } else {
                                System.out.println("No tiene permisos para ver reportes.");
                                System.out.println();
                            }
                            menu.pausar(sc);
                            break;

                        case 13:
                            System.out.println();
                            controller.mostrarAcercaDelSistema();
                            menu.pausar(sc);
                            break;

                        case 99:
                            System.out.println("Sesion cerrada.");
                            System.out.println();
                            break;

                        case 0:
                            System.out.println("Saliendo del sistema...");
                            salirSistema = true;
                            break;

                        default:
                            System.out.println("Opcion no valida.");
                            System.out.println();
                            menu.pausar(sc);
                            break;
                    }

                } while (opcion != 0 && opcion != 99);
            }
        }

        sc.close();
    }
}