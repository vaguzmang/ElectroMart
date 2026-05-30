/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package electromart.model;

/**
 *
 * @author VíctorAlfonsoGuzmánG
 */
public class Pedido {
    private int id;
    private Cliente cliente;
    private String fecha;
    private String estado;

    public Pedido() {
    }

    public Pedido(int id, Cliente cliente, String fecha, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Pedido\n" +
               "id=" + id + "\n" +
               "cliente=" + cliente.getNombre() + "\n" +
               "fecha='" + fecha + "'\n" +
               "estado='" + estado + "'";
    }
}
