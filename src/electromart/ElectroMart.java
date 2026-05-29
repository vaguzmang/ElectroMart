/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package electromart;
import electromart.model.Computadora;
import electromart.model.Electrodomestico;
/**
 *
 * @author camper
 */
public class ElectroMart {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Computadora pc1 = new Computadora();
        
            pc1.setCodigo("PC001");
            pc1.setNombre("Asus Gamer");
            pc1.setPrecioBase(3500);
            pc1.setStock(5);
            pc1.setProcesador("Intel i7");
            pc1.setRamGB(16);
            
            System.out.println(pc1);
            
            System.out.println();
            
            Electrodomestico nevera1 = new Electrodomestico();
            
            nevera1.setCodigo("E001");
            nevera1.setNombre("Nevera Samsung");
            nevera1.setPrecioBase(2200);
            nevera1.setStock(3);
            nevera1.setConsumoEnergetico("A++");
            nevera1.setGarantiaMeses(24);
            System.out.println(nevera1);

    }
    
}
