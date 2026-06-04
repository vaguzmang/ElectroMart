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

public class ElectroMart {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

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
            System.out.println("===== MENU ELECTROMART =====");
            System.out.println("1. Ver usuarios");
            System.out.println("2. Ver productos");
            System.out.println("3. Ver cliente");
            System.out.println("4. Ver pedido");
            System.out.println("5. Ver permisos");
            System.out.println("6. Ver stock final");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println();

                    for (Usuario usuario : usuarios) {
                        System.out.println(usuario);
                        System.out.println();
                    }

                    break;

                case 2:
                    System.out.println();

                    for (Producto producto : productos) {
                        System.out.println(producto);
                        System.out.printf("Precio final: %.2f%n", producto.calcularPrecioFinal());
                        System.out.println();
                    }

                    break;

                case 3:
                        System.out.println();

                        for (Cliente cliente : clientes) {
                            System.out.println(cliente);
                            System.out.println();
                        }

                        break;

                case 4:
                        System.out.println();

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

                        break;

                case 5:
                    System.out.println();
                    System.out.println("Permisos del sistema:");

                    if (usuario1.esAdministrador()) {
                        System.out.println(usuario1.getNombreUsuario() + " puede ver reportes.");
                    }

                    if (usuario2.esGerenteInventario()) {
                        System.out.println(usuario2.getNombreUsuario() + " puede gestionar productos e inventario.");
                    }

                    if (usuario3.esOperadorPedidos()) {
                        System.out.println(usuario3.getNombreUsuario() + " puede gestionar pedidos.");
                    }

                    System.out.println();
                    break;

                case 6:
                    System.out.println();
                    System.out.println("Stock final computadora: " + pc1.getStock());
                    System.out.println("Stock final nevera: " + nevera1.getStock());
                    System.out.println();
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