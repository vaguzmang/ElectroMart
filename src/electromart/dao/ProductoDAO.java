package electromart.dao;

import electromart.config.ConexionBD;
import electromart.model.Computadora;
import electromart.model.Electrodomestico;
import electromart.model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDAO {

    public ArrayList<Producto> listarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();

        String sql = "SELECT * FROM productos";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String tipo = rs.getString("tipo");

                if (tipo.equalsIgnoreCase("COMPUTADORA")) {
                    Computadora computadora = new Computadora();

                    computadora.setCodigo(rs.getString("codigo"));
                    computadora.setNombre(rs.getString("nombre"));
                    computadora.setPrecioBase(rs.getDouble("precio_base"));
                    computadora.setStock(rs.getInt("stock"));
                    computadora.setProcesador(rs.getString("procesador"));
                    computadora.setRamGB(rs.getInt("ram_gb"));

                    productos.add(computadora);

                } else if (tipo.equalsIgnoreCase("ELECTRODOMESTICO")) {
                    Electrodomestico electrodomestico = new Electrodomestico();

                    electrodomestico.setCodigo(rs.getString("codigo"));
                    electrodomestico.setNombre(rs.getString("nombre"));
                    electrodomestico.setPrecioBase(rs.getDouble("precio_base"));
                    electrodomestico.setStock(rs.getInt("stock"));
                    electrodomestico.setConsumoEnergetico(rs.getString("consumo_energetico"));
                    electrodomestico.setGarantiaMeses(rs.getInt("garantia_meses"));

                    productos.add(electrodomestico);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos desde la base de datos.");
            System.out.println("Detalle: " + e.getMessage());
        }

        return productos;
    }

    public boolean insertarComputadora(Computadora computadora) {
        String sql = """
                     INSERT INTO productos (
                         codigo,
                         nombre,
                         tipo,
                         precio_base,
                         stock,
                         procesador,
                         ram_gb,
                         consumo_energetico,
                         garantia_meses
                     ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                     """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, computadora.getCodigo());
            ps.setString(2, computadora.getNombre());
            ps.setString(3, "COMPUTADORA");
            ps.setDouble(4, computadora.getPrecioBase());
            ps.setInt(5, computadora.getStock());
            ps.setString(6, computadora.getProcesador());
            ps.setInt(7, computadora.getRamGB());
            ps.setString(8, null);
            ps.setObject(9, null);

            int filasInsertadas = ps.executeUpdate();

            return filasInsertadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar computadora en la base de datos.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    public boolean insertarElectrodomestico(Electrodomestico electrodomestico) {
        String sql = """
                     INSERT INTO productos (
                         codigo,
                         nombre,
                         tipo,
                         precio_base,
                         stock,
                         procesador,
                         ram_gb,
                         consumo_energetico,
                         garantia_meses
                     ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                     """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, electrodomestico.getCodigo());
            ps.setString(2, electrodomestico.getNombre());
            ps.setString(3, "ELECTRODOMESTICO");
            ps.setDouble(4, electrodomestico.getPrecioBase());
            ps.setInt(5, electrodomestico.getStock());
            ps.setString(6, null);
            ps.setObject(7, null);
            ps.setString(8, electrodomestico.getConsumoEnergetico());
            ps.setInt(9, electrodomestico.getGarantiaMeses());

            int filasInsertadas = ps.executeUpdate();

            return filasInsertadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar electrodomestico en la base de datos.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }
}