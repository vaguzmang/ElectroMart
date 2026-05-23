/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package electromart.model;

/**
 *
 * @author VíctorAlfonsoGuzmánG
 */
public class Computadora extends Producto {
    private String procesador;
    private int ramGB;
    
    @Override
    public double calcularPrecioFinal() {
        return getPrecioBase() * 1.10;
    }

    public Computadora() {
    }

    public Computadora(String procesador, int ramGB) {
        this.procesador = procesador;
        this.ramGB = ramGB;
    }

    public Computadora(String procesador, int ramGB, String codigo, String nombre, double precioBase, int stock) {
        
        super(codigo, nombre, precioBase, stock);
        
        this.procesador = procesador;
        this.ramGB = ramGB;
    }

    
    public String getProcesador() {
        return procesador;
    }

    public int getRamGB() {
        return ramGB;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

   public void setRamGB(int ramGB) {
        if (ramGB > 0) {
            this.ramGB = ramGB;
        }
    }
    
    @Override
    public String toString() {
        return  "Computadora\n" +
                "codigo='" + getCodigo() + '\'' + "\n" +
                "nombre='" + getNombre() + '\'' + "\n" +
                "precioBase=" + getPrecioBase() + "\n" +
                "stock=" + getStock() + "\n" +
                "procesador='" + procesador + '\'' + "\n" +
                "ramGB=" + ramGB;
    }
}
