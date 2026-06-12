package electromart.controller;

import electromart.dao.ClienteDAO;
import electromart.dao.ProductoDAO;
import electromart.model.Cliente;
import electromart.model.Computadora;
import electromart.model.DetallePedido;
import electromart.model.Electrodomestico;
import electromart.model.EstadoPedido;
import electromart.model.Pedido;
import electromart.model.Producto;
import electromart.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import electromart.dao.PedidoDAO;

public class SistemaController {

    public void mostrarUsuarios(ArrayList<Usuario> usuarios) {
        System.out.println("===== USUARIOS =====");
        System.out.printf("%-5s %-15s %-25s%n", "ID", "USUARIO", "ROL");
        System.out.println("------------------------------------------------");

        for (Usuario usuario : usuarios) {
            System.out.printf("%-5d %-15s %-25s%n",
                    usuario.getId(),
                    usuario.getNombreUsuario(),
                    usuario.getRol());
        }

        System.out.println();
    }

    public void mostrarProductos(ArrayList<Producto> productos) {
        System.out.println("===== PRODUCTOS =====");
        System.out.printf("%-10s %-20s %-18s %-12s %-10s %-12s%n",
                "CODIGO", "NOMBRE", "TIPO", "PRECIO", "STOCK", "P. FINAL");
        System.out.println("--------------------------------------------------------------------------------");

        for (Producto producto : productos) {
            System.out.printf("%-10s %-20s %-18s %-12.2f %-10d %-12.2f%n",
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getClass().getSimpleName(),
                    producto.getPrecioBase(),
                    producto.getStock(),
                    producto.calcularPrecioFinal());
        }

        System.out.println();
    }

    public void mostrarClientes(ArrayList<Cliente> clientes) {
        System.out.println("===== CLIENTES =====");
        System.out.printf("%-5s %-20s %-25s %-15s%n",
                "ID", "NOMBRE", "EMAIL", "TELEFONO");
        System.out.println("--------------------------------------------------------------------");

        for (Cliente cliente : clientes) {
            System.out.printf("%-5d %-20s %-25s %-15s%n",
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getEmail(),
                    cliente.getTelefono());
        }

        System.out.println();
    }

    public void mostrarPedidos(ArrayList<Pedido> pedidos) {
        System.out.println("===== PEDIDOS =====");
        System.out.printf("%-5s %-20s %-15s %-15s %-12s%n",
                "ID", "CLIENTE", "FECHA", "ESTADO", "TOTAL");
        System.out.println("--------------------------------------------------------------------------");

        for (Pedido pedido : pedidos) {
            System.out.printf("%-5d %-20s %-15s %-15s %-12.2f%n",
                    pedido.getId(),
                    pedido.getCliente().getNombre(),
                    pedido.getFecha(),
                    pedido.getEstado(),
                    pedido.calcularTotal());

            System.out.println();
            System.out.println("Detalles del pedido:");
            System.out.printf("%-5s %-20s %-10s %-15s %-12s%n",
                    "ID", "PRODUCTO", "CANTIDAD", "P. UNITARIO", "SUBTOTAL");
            System.out.println("----------------------------------------------------------------");

            for (DetallePedido detalle : pedido.getDetalles()) {
                System.out.printf("%-5d %-20s %-10d %-15.2f %-12.2f%n",
                        detalle.getId(),
                        detalle.getProducto().getNombre(),
                        detalle.getCantidad(),
                        detalle.getPrecioUnitario(),
                        detalle.calcularSubtotal());
            }

            System.out.println();
        }
    }

    public void mostrarPermisos(ArrayList<Usuario> usuarios) {
        System.out.println("===== PERMISOS DEL SISTEMA =====");
        System.out.printf("%-15s %-25s %-40s%n",
                "USUARIO", "ROL", "PERMISO");
        System.out.println("--------------------------------------------------------------------------------");

        for (Usuario usuario : usuarios) {
            if (usuario.esAdministrador()) {
                System.out.printf("%-15s %-25s %-40s%n",
                        usuario.getNombreUsuario(),
                        usuario.getRol(),
                        "Puede ver reportes");
            }

            if (usuario.esGerenteInventario()) {
                System.out.printf("%-15s %-25s %-40s%n",
                        usuario.getNombreUsuario(),
                        usuario.getRol(),
                        "Puede gestionar productos e inventario");
            }

            if (usuario.esOperadorPedidos()) {
                System.out.printf("%-15s %-25s %-40s%n",
                        usuario.getNombreUsuario(),
                        usuario.getRol(),
                        "Puede gestionar pedidos");
            }
        }

        System.out.println();
    }

    public void mostrarStock(ArrayList<Producto> productos) {
        System.out.println("===== STOCK FINAL =====");
        System.out.printf("%-10s %-20s %-18s %-10s%n",
                "CODIGO", "PRODUCTO", "TIPO", "STOCK");
        System.out.println("--------------------------------------------------------------");

        for (Producto producto : productos) {
            System.out.printf("%-10s %-20s %-18s %-10d%n",
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getClass().getSimpleName(),
                    producto.getStock());
        }

        System.out.println();
    }

    public void registrarComputadora(ArrayList<Producto> productos, Scanner sc) {
        Computadora computadora = new Computadora();

        System.out.println("===== REGISTRAR COMPUTADORA =====");

        System.out.print("Codigo: ");
        computadora.setCodigo(sc.next());

        sc.nextLine();

        System.out.print("Nombre: ");
        computadora.setNombre(sc.nextLine());

        computadora.setPrecioBase(leerDoublePositivo(sc, "Precio base: "));
        computadora.setStock(leerEnteroPositivo(sc, "Stock: "));

        sc.nextLine();

        System.out.print("Procesador: ");
        computadora.setProcesador(sc.nextLine());

        computadora.setRamGB(leerEnteroPositivo(sc, "RAM GB: "));

        ProductoDAO productoDAO = new ProductoDAO();

        if (productoDAO.insertarComputadora(computadora)) {
            System.out.println("Computadora registrada correctamente en la base de datos.");

            productos.clear();
            productos.addAll(productoDAO.listarProductos());
        } else {
            System.out.println("No se pudo registrar la computadora.");
        }

        System.out.println();
    }

    public void registrarCliente(ArrayList<Cliente> clientes, Scanner sc) {
        Cliente cliente = new Cliente();

        System.out.println("===== REGISTRAR CLIENTE =====");

        sc.nextLine();

        System.out.print("Nombre: ");
        cliente.setNombre(sc.nextLine());

        System.out.print("Email: ");
        cliente.setEmail(sc.nextLine());

        System.out.print("Telefono: ");
        cliente.setTelefono(sc.nextLine());

        ClienteDAO clienteDAO = new ClienteDAO();

        if (clienteDAO.insertarCliente(cliente)) {
            System.out.println("Cliente registrado correctamente en la base de datos.");

            clientes.clear();
            clientes.addAll(clienteDAO.listarClientes());
        } else {
            System.out.println("No se pudo registrar el cliente.");
        }

        System.out.println();
    }

    public void registrarElectrodomestico(ArrayList<Producto> productos, Scanner sc) {
        Electrodomestico electrodomestico = new Electrodomestico();

        System.out.println("===== REGISTRAR ELECTRODOMESTICO =====");

        System.out.print("Codigo: ");
        electrodomestico.setCodigo(sc.next());

        sc.nextLine();

        System.out.print("Nombre: ");
        electrodomestico.setNombre(sc.nextLine());

        electrodomestico.setPrecioBase(leerDoublePositivo(sc, "Precio base: "));
        electrodomestico.setStock(leerEnteroPositivo(sc, "Stock: "));

        sc.nextLine();

        System.out.print("Consumo energetico: ");
        electrodomestico.setConsumoEnergetico(sc.nextLine());

        electrodomestico.setGarantiaMeses(leerEnteroPositivo(sc, "Garantia en meses: "));

        ProductoDAO productoDAO = new ProductoDAO();

        if (productoDAO.insertarElectrodomestico(electrodomestico)) {
            System.out.println("Electrodomestico registrado correctamente en la base de datos.");

            productos.clear();
            productos.addAll(productoDAO.listarProductos());
        } else {
            System.out.println("No se pudo registrar el electrodomestico.");
        }

        System.out.println();
    }

    public Cliente buscarClientePorId(ArrayList<Cliente> clientes, int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }

        return null;
    }

    public Producto buscarProductoPorCodigo(ArrayList<Producto> productos, String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo().equalsIgnoreCase(codigo)) {
                return producto;
            }
        }

        return null;
    }

    public void crearPedido(ArrayList<Pedido> pedidos,
            ArrayList<Cliente> clientes,
            ArrayList<Producto> productos,
            Scanner sc) {

        System.out.println("===== CREAR PEDIDO =====");

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        System.out.println();
        mostrarClientes(clientes);

        int idCliente = leerEnteroPositivo(sc, "Ingrese ID del cliente: ");

        Cliente cliente = buscarClientePorId(clientes, idCliente);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.println();
        mostrarProductos(productos);

        System.out.print("Ingrese codigo del producto: ");
        String codigoProducto = sc.next();

        Producto producto = buscarProductoPorCodigo(productos, codigoProducto);

        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        int cantidad = leerEnteroPositivo(sc, "Ingrese cantidad: ");

        if (cantidad > producto.getStock()) {
            System.out.println("Stock insuficiente para el producto: " + producto.getNombre());
            return;
        }

        Pedido pedido = new Pedido();
        pedido.setId(pedidos.size() + 1);
        pedido.setCliente(cliente);
        pedido.setFecha("2026-06-04");
        pedido.setEstado(EstadoPedido.PENDIENTE);

        DetallePedido detalle = new DetallePedido();
        detalle.setId(1);
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(producto.getPrecioBase());

        pedido.agregarDetalle(detalle);
        pedidos.add(pedido);

        System.out.println("Pedido creado correctamente.");
        System.out.printf("Total del pedido: %.2f%n", pedido.calcularTotal());
        System.out.println();
    }


    public void busquedaAvanzadaProductos(ArrayList<Producto> productos, Scanner sc) {
        sc.nextLine();

        boolean repetirBusqueda = true;

        while (repetirBusqueda) {
            if (productos.isEmpty()) {
                System.out.println("No hay productos registrados en el inventario.");
                return;
            }

            String categoria = solicitarCategoriaProducto(productos, sc);
            if (categoria == null) {
                return;
            }

            Double precioMinimo = solicitarPrecio(sc, "Ingrese precio minimo: ");
            if (precioMinimo == null) {
                continue;
            }

            Double precioMaximo = solicitarPrecioMaximo(sc, precioMinimo);
            if (precioMaximo == null) {
                continue;
            }

            List<Producto> productosFiltrados = productos.stream()
                    .filter(producto -> obtenerCategoriaProducto(producto).equalsIgnoreCase(categoria))
                    .filter(producto -> producto.getPrecioBase() >= precioMinimo)
                    .filter(producto -> producto.getPrecioBase() <= precioMaximo)
                    .collect(Collectors.toList());

            System.out.println();
            System.out.println("===== RESULTADOS BUSQUEDA AVANZADA DE PRODUCTOS =====");
            System.out.println("Categoria: " + categoria.toUpperCase());
            System.out.printf("Precio minimo: %.2f%n", precioMinimo);
            System.out.printf("Precio maximo: %.2f%n", precioMaximo);
            System.out.println();

            if (productosFiltrados.isEmpty()) {
                System.out.println("No se encontro ningun producto con los criterios de busqueda ingresados.");
            } else {
                mostrarTablaProductosDetallada(productosFiltrados);
            }

            int opcion = solicitarOpcionFinalBusqueda(sc);
            if (opcion == 0) {
                repetirBusqueda = false;
            }
        }
    }

    private String solicitarCategoriaProducto(ArrayList<Producto> productos, Scanner sc) {
        while (true) {
            System.out.println("===== BUSQUEDA AVANZADA DE PRODUCTOS =====");
            System.out.println("Categorias disponibles:");
            mostrarCategoriasDisponibles(productos);
            System.out.println("0. Atras");
            System.out.print("Ingrese categoria del producto: ");

            String entrada = sc.nextLine().trim();

            if (entrada.equals("0")) {
                return null;
            }

            String categoriaEncontrada = buscarCategoriaExacta(productos, entrada);

            if (categoriaEncontrada != null) {
                return categoriaEncontrada;
            }

            System.out.println();
            System.out.println("Alerta: la categoria ingresada no existe en el inventario.");
            System.out.println("Seleccione una categoria valida de la siguiente lista:");
            mostrarCategoriasDisponibles(productos);
            System.out.println();
        }
    }

    private Double solicitarPrecio(Scanner sc, String mensaje) {
        while (true) {
            System.out.println("0. Atras");
            System.out.print(mensaje);

            String entrada = sc.nextLine().trim();

            if (entrada.equals("0")) {
                return null;
            }

            try {
                double precio = Double.parseDouble(entrada.replace(",", "."));

                if (precio < 0) {
                    System.out.println("Alerta: el precio no puede ser negativo.");
                    System.out.println();
                } else {
                    return precio;
                }
            } catch (NumberFormatException e) {
                System.out.println("Alerta: debe ingresar un numero valido. Puede usar enteros o decimales.");
                System.out.println();
            }
        }
    }

    private Double solicitarPrecioMaximo(Scanner sc, double precioMinimo) {
        while (true) {
            Double precioMaximo = solicitarPrecio(sc, "Ingrese precio maximo: ");

            if (precioMaximo == null) {
                return null;
            }

            if (precioMaximo < precioMinimo) {
                System.out.println("Alerta: el precio maximo no puede ser menor que el precio minimo.");
                System.out.println();
            } else {
                return precioMaximo;
            }
        }
    }

    private int solicitarOpcionFinalBusqueda(Scanner sc) {
        while (true) {
            System.out.println();
            System.out.println("1. Realizar otra busqueda");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            String entrada = sc.nextLine().trim();

            if (entrada.equals("1")) {
                System.out.println();
                return 1;
            }

            if (entrada.equals("0")) {
                System.out.println();
                return 0;
            }

            System.out.println("Alerta: opcion no valida. Ingrese 1 o 0.");
        }
    }

    private String buscarCategoriaExacta(ArrayList<Producto> productos, String categoriaIngresada) {
        String categoriaNormalizada = normalizarTexto(categoriaIngresada);

        return productos.stream()
                .map(this::obtenerCategoriaProducto)
                .distinct()
                .filter(categoria -> normalizarTexto(categoria).equals(categoriaNormalizada))
                .findFirst()
                .orElse(null);
    }

    private void mostrarCategoriasDisponibles(ArrayList<Producto> productos) {
        productos.stream()
                .map(this::obtenerCategoriaProducto)
                .distinct()
                .forEach(categoria -> System.out.println("- " + categoria));
    }

    private String obtenerCategoriaProducto(Producto producto) {
        if (producto instanceof Computadora) {
            return "COMPUTADORA";
        }

        if (producto instanceof Electrodomestico) {
            return "ELECTRODOMESTICO";
        }

        return producto.getClass().getSimpleName().toUpperCase();
    }

    private String normalizarTexto(String texto) {
        return texto.trim()
                .toUpperCase()
                .replace("Á", "A")
                .replace("É", "E")
                .replace("Í", "I")
                .replace("Ó", "O")
                .replace("Ú", "U");
    }

    private void mostrarTablaProductosDetallada(List<Producto> productos) {
        System.out.printf("%-5s %-10s %-24s %-18s %-13s %-8s %-18s %-10s %-20s %-12s%n",
                "No.", "CODIGO", "NOMBRE", "CATEGORIA", "P. BASE", "STOCK",
                "PROCESADOR", "RAM", "CONSUMO", "GARANTIA");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

        int numero = 1;

        for (Producto producto : productos) {
            String procesador = "N/A";
            String ram = "N/A";
            String consumo = "N/A";
            String garantia = "N/A";

            if (producto instanceof Computadora) {
                Computadora computadora = (Computadora) producto;
                procesador = computadora.getProcesador();
                ram = computadora.getRamGB() + " GB";
            }

            if (producto instanceof Electrodomestico) {
                Electrodomestico electrodomestico = (Electrodomestico) producto;
                consumo = electrodomestico.getConsumoEnergetico();
                garantia = electrodomestico.getGarantiaMeses() + " meses";
            }

            System.out.printf("%-5d %-10s %-24s %-18s %-13.2f %-8d %-18s %-10s %-20s %-12s%n",
                    numero,
                    producto.getCodigo(),
                    producto.getNombre(),
                    obtenerCategoriaProducto(producto),
                    producto.getPrecioBase(),
                    producto.getStock(),
                    procesador,
                    ram,
                    consumo,
                    garantia);

            numero++;
        }

        System.out.println();
    }

    public void mostrarReportes(ArrayList<Pedido> pedidos,
            ArrayList<Producto> productos,
            ArrayList<Cliente> clientes) {

        double totalVentas = 0;

        for (Pedido pedido : pedidos) {
            totalVentas += pedido.calcularTotal();
        }

        int productosStockBajo = 0;

        for (Producto producto : productos) {
            if (producto.getStock() <= 3) {
                productosStockBajo++;
            }
        }

        System.out.println("===== REPORTES GENERALES =====");
        System.out.printf("%-35s %-15s%n", "REPORTE", "VALOR");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-35s %-15d%n", "Cantidad de pedidos", pedidos.size());
        System.out.printf("%-35s %-15.2f%n", "Total ventas", totalVentas);
        System.out.printf("%-35s %-15d%n", "Productos registrados", productos.size());
        System.out.printf("%-35s %-15d%n", "Clientes registrados", clientes.size());
        System.out.printf("%-35s %-15d%n", "Productos con stock bajo", productosStockBajo);
        System.out.println();

        System.out.println("===== PRODUCTOS CON STOCK BAJO =====");
        System.out.printf("%-10s %-20s %-10s%n", "CODIGO", "PRODUCTO", "STOCK");
        System.out.println("---------------------------------------------");

        for (Producto producto : productos) {
            if (producto.getStock() <= 3) {
                System.out.printf("%-10s %-20s %-10d%n",
                        producto.getCodigo(),
                        producto.getNombre(),
                        producto.getStock());
            }
        }

        System.out.println();
    }

    public void mostrarAcercaDelSistema() {
        System.out.println("===== ACERCA DEL SISTEMA =====");
        System.out.println("Nombre: ElectroMart");
        System.out.println("Version: 1.0");
        System.out.println("Lenguaje: Java 17");
        System.out.println("Arquitectura: Modelo - Vista - Controlador");
        System.out.println();
        System.out.println("Descripcion:");
        System.out.println("Sistema de gestion para una tienda de tecnologia y electrodomesticos.");
        System.out.println("Permite administrar productos, clientes, pedidos, usuarios, roles,");
        System.out.println("control de stock, calculo de totales y reportes basicos.");
        System.out.println();
        System.out.println("Modulos principales:");
        System.out.println("- Gestion de usuarios y roles");
        System.out.println("- Gestion de productos");
        System.out.println("- Gestion de clientes");
        System.out.println("- Gestion de pedidos");
        System.out.println("- Reportes basicos");
        System.out.println();
    }

    public boolean esEnteroValido(Scanner sc) {
        if (sc.hasNextInt()) {
            return true;
        }

        sc.next();
        return false;
    }

    public int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);

            if (sc.hasNextInt()) {
                int valor = sc.nextInt();
                return valor;
            } else {
                System.out.println("Error: debe ingresar un numero entero.");
                sc.next();
            }
        }
    }

    public double leerDouble(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);

            if (sc.hasNextDouble()) {
                double valor = sc.nextDouble();
                return valor;
            } else {
                System.out.println("Error: debe ingresar un numero valido.");
                sc.next();
            }
        }
    }

    public int leerEnteroPositivo(Scanner sc, String mensaje) {
        int valor;

        do {
            valor = leerEntero(sc, mensaje);

            if (valor <= 0) {
                System.out.println("Error: el valor debe ser mayor que cero.");
            }

        } while (valor <= 0);

        return valor;
    }

    public double leerDoublePositivo(Scanner sc, String mensaje) {
        double valor;

        do {
            valor = leerDouble(sc, mensaje);

            if (valor < 0) {
                System.out.println("Error: el valor no puede ser negativo.");
            }

        } while (valor < 0);

        return valor;
    }

    public Usuario login(ArrayList<Usuario> usuarios, String nombreUsuario, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)
                    && usuario.getPassword().equals(password)) {
                return usuario;
            }
        }

        return null;
    }
}