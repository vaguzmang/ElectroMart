/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package electromart.model;

/**
 *
 * @author VíctorAlfonsoGuzmánG
 */
public class DetallePedido {
    private int id;
    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    public DetallePedido() {
    }

    public DetallePedido(int id, Producto producto, int cantidad, double precioUnitario) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad > 0) {
        this.cantidad = cantidad;
    }
    }
    public double getPrecioUnitario(){
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        if (precioUnitario >= 0) {
        this.precioUnitario = precioUnitario;
    }
    }
    public double calcularSubtotal(){
        return cantidad * precioUnitario;
    }
    @Override
    public String toString() {
        return "DetallePedido\n" +
               "id=" + id + "\n" +
               "producto=" + producto.getNombre() + "\n" +
               "cantidad=" + cantidad + "\n" +
               "precioUnitario=" + precioUnitario + "\n" +
               "subtotal=" + calcularSubtotal();
    }
}
