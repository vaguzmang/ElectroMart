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

    public Usuario buscarUsuarioPorNombre(String nombreUsuario) {
        String sql = """
                     SELECT u.id, u.nombre_usuario, u.password, r.nombre AS rol
                     FROM usuarios u
                     INNER JOIN roles r ON u.rol_id = r.id
                     WHERE u.nombre_usuario = ?
                     """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearUsuarioDesdeResultSet(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar usuario en la base de datos.");
            System.out.println("Detalle: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Usuario> buscarUsuariosPorRol(Rol rol) {
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

    private Usuario crearUsuarioDesdeResultSet(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setNombreUsuario(rs.getString("nombre_usuario"));
        usuario.setPassword(rs.getString("password"));
        usuario.setRol(Rol.valueOf(rs.getString("rol")));
        return usuario;
    }
}
