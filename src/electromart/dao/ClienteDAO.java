package electromart.dao;

import electromart.config.ConexionBD;
import electromart.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    public ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * FROM clientes ORDER BY id";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clientes.add(crearClienteDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes desde la base de datos.");
            System.out.println("Detalle: " + e.getMessage());
        }

        return clientes;
    }

    public boolean insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, email, telefono) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefono());

            int filasInsertadas = ps.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar cliente en la base de datos.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    public Cliente buscarClientePorEmail(String email) {
        String sql = "SELECT * FROM clientes WHERE email = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearClienteDesdeResultSet(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar cliente en la base de datos.");
            System.out.println("Detalle: " + e.getMessage());
        }

        return null;
    }

    private Cliente crearClienteDesdeResultSet(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setEmail(rs.getString("email"));
        cliente.setTelefono(rs.getString("telefono"));
        return cliente;
    }
}
