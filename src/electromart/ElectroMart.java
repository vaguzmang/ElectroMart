/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package electromart;

import electromart.model.Computadora;
import electromart.model.Electrodomestico;
import electromart.model.Cliente;
import electromart.model.Pedido;
import electromart.model.EstadoPedido;
import electromart.model.DetallePedido;
import electromart.model.Usuario;
import electromart.model.Rol;

public class ElectroMart {

    
    public static void main(String[] args) {

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

        System.out.println(usuario1);
        System.out.println("¿Es administrador? " + usuario1.esAdministrador());
        System.out.println();

        System.out.println(usuario2);
        System.out.println("¿Es administrador? " + usuario2.esAdministrador());
        System.out.println("¿Es gerente de inventario? " + usuario2.esGerenteInventario());
        System.out.println();

        System.out.println(usuario3);
        System.out.println("¿Es operador de pedidos? " + usuario3.esOperadorPedidos());
        System.out.println();
        
        Computadora pc1 = new Computadora();

        pc1.setCodigo("PC001");
        pc1.setNombre("Asus Gamer");
        pc1.setPrecioBase(3500);
        pc1.setStock(5);
        pc1.setProcesador("Intel i7");
        pc1.setRamGB(16);

        System.out.println(pc1);
        System.out.printf("Precio final computadora: %.2f%n", pc1.calcularPrecioFinal());

        System.out.println();

        Electrodomestico nevera1 = new Electrodomestico();

        nevera1.setCodigo("E001");
        nevera1.setNombre("Nevera Samsung");
        nevera1.setPrecioBase(2200);
        nevera1.setStock(3);
        nevera1.setConsumoEnergetico("A++");
        nevera1.setGarantiaMeses(24);
        System.out.println(nevera1);
        System.out.printf("Precio final electrodomestico: %.2f%n", nevera1.calcularPrecioFinal());

        System.out.println();

        Cliente cliente1 = new Cliente();
        cliente1.setId(1);
        cliente1.setNombre("Carlos Perez");
        cliente1.setEmail("carlos@email.com");
        cliente1.setTelefono("3001234567");

        System.out.println(cliente1);

        System.out.println();

        Pedido pedido1 = new Pedido();
        pedido1.setId(1);
        pedido1.setCliente(cliente1);
        pedido1.setFecha("2026-05-28");
        pedido1.setEstado(EstadoPedido.PENDIENTE);

        System.out.println();

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
        
        System.out.println(pedido1);
        
        System.out.println();
        
        System.out.println(detalle1);

        System.out.println();

        System.out.println(detalle2);

        System.out.printf("Total del pedido: %.2f%n", pedido1.calcularTotal());
        
        System.out.println();
        System.out.println("Stock final computadora: " + pc1.getStock());
        System.out.println("Stock final nevera: " + nevera1.getStock());
    }

}
