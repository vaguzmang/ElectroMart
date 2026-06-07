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
}