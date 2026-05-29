/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package electromart.model;

/**
 *
 * @author VíctorAlfonsoGuzmánG
 */
public class Electrodomestico extends Producto {
    
    private String consumoEnergetico;
    private int garantiaMeses;
    
    @Override
    public double calcularPrecioFinal() {
        return getPrecioBase() * 1.05;
    }

    public Electrodomestico() {
    }

    public Electrodomestico(String consumoEnergetico, int garantiaMeses) {
        this.consumoEnergetico = consumoEnergetico;
        this.garantiaMeses = garantiaMeses;
    }
    
    public Electrodomestico(String consumoEnergetico, int garantiaMeses,
                        String codigo, String nombre,
                        double precioBase, int stock) {

        super(codigo, nombre, precioBase, stock);

        this.consumoEnergetico = consumoEnergetico;
        this.garantiaMeses = garantiaMeses;
    }

    public String getConsumoEnergetico() {
        return consumoEnergetico;
    }

    public void setConsumoEnergetico(String consumoEnergetico) {
        this.consumoEnergetico = consumoEnergetico;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
         if (garantiaMeses >= 0) {
            this.garantiaMeses = garantiaMeses;
         }
    }
    
    @Override
    public String toString() {
        return "Electrodomestico\n" +
           "codigo='" + getCodigo() + '\'' + "\n" +
           "nombre='" + getNombre() + '\'' + "\n" +
           "precioBase=" + getPrecioBase() + "\n" +
           "stock=" + getStock() + "\n" +
           "consumoEnergetico='" + consumoEnergetico + '\'' + "\n" +
           "garantiaMeses=" + garantiaMeses;
    }
}
    
