package electromart.view;

import electromart.model.Usuario;
import java.util.Scanner;

public class MenuConsola {

    public void mostrarMenu(Usuario usuarioActual) {
        imprimirEncabezado("MENU ELECTROMART");
        System.out.println("Usuario: " + usuarioActual.getNombreUsuario() + " | Rol: " + usuarioActual.getRol());
        System.out.println("------------------------------------------------------------");

        if (usuarioActual.esAdministrador()) {
            System.out.println("[USUARIOS]");
            System.out.println(" 1. Ver usuarios");
            System.out.println(" 5. Ver permisos");
            System.out.println("14. Registrar usuario");
            System.out.println("15. Buscar usuarios por rol");
            System.out.println("16. Editar usuario");
            System.out.println("17. Eliminar usuario");
            System.out.println();

            System.out.println("[INVENTARIO]");
            System.out.println(" 2. Ver productos");
            System.out.println(" 6. Ver stock final");
            System.out.println(" 8. Registrar computadora");
            System.out.println("10. Registrar electrodomestico");
            System.out.println();

            System.out.println("[CLIENTES Y PEDIDOS]");
            System.out.println(" 3. Ver clientes");
            System.out.println(" 4. Ver pedidos");
            System.out.println(" 9. Registrar cliente");
            System.out.println("11. Crear pedido");
            System.out.println();

            System.out.println("[REPORTES]");
            System.out.println("12. Ver reportes");
            System.out.println("13. Acerca del sistema");
        }

        if (usuarioActual.esGerenteInventario()) {
            System.out.println("[INVENTARIO]");
            System.out.println(" 2. Ver productos");
            System.out.println(" 6. Ver stock final");
            System.out.println(" 8. Registrar computadora");
            System.out.println("10. Registrar electrodomestico");
            System.out.println("13. Acerca del sistema");
        }

        if (usuarioActual.esOperadorPedidos()) {
            System.out.println("[CLIENTES Y PEDIDOS]");
            System.out.println(" 3. Ver clientes");
            System.out.println(" 4. Ver pedidos");
            System.out.println(" 9. Registrar cliente");
            System.out.println("11. Crear pedido");
            System.out.println("13. Acerca del sistema");
        }

        System.out.println();
        System.out.println("99. Cerrar sesion");
        System.out.println(" 0. Salir del sistema");
        System.out.println("------------------------------------------------------------");
        System.out.print("Seleccione una opcion: ");
    }

    public void imprimirEncabezado(String titulo) {
        System.out.println();
        System.out.println("============================================================");
        System.out.println("                 " + titulo);
        System.out.println("============================================================");
    }

    public void pausar(Scanner sc) {
        System.out.println();
        System.out.print("Presione ENTER para continuar...");
        sc.nextLine();
        sc.nextLine();
    }
}
