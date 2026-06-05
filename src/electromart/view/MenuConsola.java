package electromart.view;

import electromart.model.Usuario;
import java.util.Scanner;

public class MenuConsola {

    public void mostrarMenu(Usuario usuarioActual) {
        System.out.println("===== MENU ELECTROMART =====");

        if (usuarioActual.esAdministrador()) {
            System.out.println("1. Ver usuarios");
            System.out.println("2. Ver productos");
            System.out.println("3. Ver clientes");
            System.out.println("4. Ver pedidos");
            System.out.println("5. Ver permisos");
            System.out.println("6. Ver stock final");
            System.out.println("8. Registrar computadora");
            System.out.println("9. Registrar cliente");
            System.out.println("10. Registrar electrodomestico");
        }

        if (usuarioActual.esGerenteInventario()) {
            System.out.println("2. Ver productos");
            System.out.println("6. Ver stock final");
            System.out.println("8. Registrar computadora");
            System.out.println("10. Registrar electrodomestico");
        }

        if (usuarioActual.esOperadorPedidos()) {
            System.out.println("3. Ver clientes");
            System.out.println("4. Ver pedidos");
            System.out.println("9. Registrar cliente");
        }

        System.out.println("99. Cerrar sesion");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    public void pausar(Scanner sc) {
        System.out.println("Presione ENTER para continuar...");
        sc.nextLine();
        sc.nextLine();
    }
}