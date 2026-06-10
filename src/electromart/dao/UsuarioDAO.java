package electromart.dao;

import electromart.config.ConexionBD;
import electromart.model.Rol;
import electromart.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    public ArrayList<Usuario> listarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        String sql = """
                     SELECT u.id, u.nombre_usuario, u.password, r.nombre AS rol
                     FROM usuarios u
                     INNER JOIN roles r ON u.rol_id = r.id
                     ORDER BY u.id
                     """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                usuarios.add(crearUsuarioDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar usuarios desde la base de datos.");
            System.out.println("Detalle: " + e.getMessage());
        }

        return usuarios;
    }

    public ArrayList<Usuario> listarUsuariosPorRol(Rol rol) {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        String sql = """
                     SELECT u.id, u.nombre_usuario, u.password, r.nombre AS rol
                     FROM usuarios u
                     INNER JOIN roles r ON u.rol_id = r.id
                     WHERE r.nombre = ?
                     ORDER BY u.id
                     """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, rol.name());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(crearUsuarioDesdeResultSet(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar usuarios por rol.");
            System.out.println("Detalle: " + e.getMessage());
        }

        return usuarios;
    }

    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre_usuario, password, rol_id) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, obtenerRolId(usuario.getRol()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar usuario en la base de datos.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre_usuario = ?, password = ?, rol_id = ? WHERE id = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, obtenerRolId(usuario.getRol()));
            ps.setInt(4, usuario.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario en la base de datos.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario en la base de datos.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    public boolean existeNombreUsuario(String nombreUsuario) {
        String sql = "SELECT id FROM usuarios WHERE nombre_usuario = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.out.println("Error al validar nombre de usuario.");
            System.out.println("Detalle: " + e.getMessage());
            return true;
        }
    }

    private Usuario crearUsuarioDesdeResultSet(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();

        usuario.setId(rs.getInt("id"));
        usuario.setNombreUsuario(rs.getString("nombre_usuario"));
        usuario.setPassword(rs.getString("password"));
        usuario.setRol(Rol.valueOf(rs.getString("rol")));

        return usuario;
    }

    private int obtenerRolId(Rol rol) {
        if (rol == Rol.ADMINISTRADOR) {
            return 1;
        }

        if (rol == Rol.GERENTE_INVENTARIO) {
            return 2;
        }

        return 3;
    }
}
