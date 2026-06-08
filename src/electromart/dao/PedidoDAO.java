package electromart.dao;

import electromart.config.ConexionBD;
import electromart.model.Cliente;
import electromart.model.Computadora;
import electromart.model.DetallePedido;
import electromart.model.Electrodomestico;
import electromart.model.EstadoPedido;
import electromart.model.Pedido;
import electromart.model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoDAO {

    public ArrayList<Pedido> listarPedidos() {
        ArrayList<Pedido> pedidos = new ArrayList<>();

        String sqlPedidos = """
                            SELECT p.id AS pedido_id,
                                   p.fecha,
                                   p.estado,
                                   c.id AS cliente_id,
                                   c.nombre AS cliente_nombre,
                                   c.email,
                                   c.telefono
                            FROM pedidos p
                            INNER JOIN clientes c ON p.cliente_id = c.id
                            ORDER BY p.id
                            """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sqlPedidos);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("cliente_id"));
                cliente.setNombre(rs.getString("cliente_nombre"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getString("telefono"));

                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("pedido_id"));
                pedido.setCliente(cliente);
                pedido.setFecha(rs.getString("fecha"));
                pedido.setEstado(EstadoPedido.valueOf(rs.getString("estado")));

                ArrayList<DetallePedido> detalles = listarDetallesPorPedido(pedido.getId());

                for (DetallePedido detalle : detalles) {
                    pedido.agregarDetalle(detalle);
                }

                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pedidos desde la base de datos.");
            System.out.println("Detalle: " + e.getMessage());
        }

        return pedidos;
    }

    private ArrayList<DetallePedido> listarDetallesPorPedido(int pedidoId) {
        ArrayList<DetallePedido> detalles = new ArrayList<>();

        String sqlDetalles = """
                             SELECT dp.id AS detalle_id,
                                    dp.cantidad,
                                    dp.precio_unitario,
                                    pr.codigo,
                                    pr.nombre,
                                    pr.tipo,
                                    pr.precio_base,
                                    pr.stock,
                                    pr.procesador,
                                    pr.ram_gb,
                                    pr.consumo_energetico,
                                    pr.garantia_meses
                             FROM detalle_pedido dp
                             INNER JOIN productos pr ON dp.producto_id = pr.id
                             WHERE dp.pedido_id = ?
                             """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sqlDetalles)) {

            ps.setInt(1, pedidoId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto producto = crearProductoDesdeResultSet(rs);

                    DetallePedido detalle = new DetallePedido();
                    detalle.setId(rs.getInt("detalle_id"));
                    detalle.setProducto(producto);
                    detalle.setCantidad(rs.getInt("cantidad"));
                    detalle.setPrecioUnitario(rs.getDouble("precio_unitario"));

                    detalles.add(detalle);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar detalles del pedido.");
            System.out.println("Detalle: " + e.getMessage());
        }

        return detalles;
    }

    private Producto crearProductoDesdeResultSet(ResultSet rs) throws SQLException {
        String tipo = rs.getString("tipo");

        if (tipo.equalsIgnoreCase("COMPUTADORA")) {
            Computadora computadora = new Computadora();

            computadora.setCodigo(rs.getString("codigo"));
            computadora.setNombre(rs.getString("nombre"));
            computadora.setPrecioBase(rs.getDouble("precio_base"));
            computadora.setStock(rs.getInt("stock"));
            computadora.setProcesador(rs.getString("procesador"));
            computadora.setRamGB(rs.getInt("ram_gb"));

            return computadora;
        }

        Electrodomestico electrodomestico = new Electrodomestico();

        electrodomestico.setCodigo(rs.getString("codigo"));
        electrodomestico.setNombre(rs.getString("nombre"));
        electrodomestico.setPrecioBase(rs.getDouble("precio_base"));
        electrodomestico.setStock(rs.getInt("stock"));
        electrodomestico.setConsumoEnergetico(rs.getString("consumo_energetico"));
        electrodomestico.setGarantiaMeses(rs.getInt("garantia_meses"));

        return electrodomestico;
    }
}