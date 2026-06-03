/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package electromart.model;

import java.util.ArrayList;
/**
 *
 * @author VíctorAlfonsoGuzmánG
 */
public class Pedido {
    private int id;
    private Cliente cliente;
    private String fecha;
    private EstadoPedido estado;
    private ArrayList<DetallePedido> detalles;
    
    
    public Pedido() {
        this.detalles = new ArrayList<>();
    }

    public Pedido(int id, Cliente cliente, String fecha, EstadoPedido estado) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.estado = estado;
        this.detalles = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
    
    public ArrayList<DetallePedido> getDetalles() {
        return detalles;
    }

    public void agregarDetalle(DetallePedido detalle) {
        if (detalle.getCantidad() <= detalle.getProducto().getStock()) {
            detalles.add(detalle);
            
            int nuevoStock = detalle.getProducto().getStock() - detalle.getCantidad();
            detalle.getProducto().setStock(nuevoStock);
            
        } else {
            System.out.println("Stock insuficiente para el producto: " + detalle.getProducto().getNombre());
        }
    }

    public double calcularTotal() {
        double total = 0;

        for (DetallePedido detalle : detalles) {
            total += detalle.calcularSubtotal();
        }

        return total;
    }
    @Override
    public String toString() {
        return "Pedido\n" +
               "id=" + id + "\n" +
               "cliente=" + cliente.getNombre() + "\n" +
               "fecha='" + fecha + "'\n" +
               "estado='" + estado + "'\n" +
               "total=" + calcularTotal();
    }
}
