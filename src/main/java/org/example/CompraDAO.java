package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Método para insertar una nueva compra en la base de datos
    public boolean insertarCompra(Compra compra) {
        String query = "INSERT INTO compras (nombre_oferta, monto_puja, email_usuario) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, compra.getNombreOferta());
            ps.setDouble(2, compra.getMontoPuja());
            ps.setString(3, compra.getEmailUsuario());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todas las compras de la base de datos
    public List<Compra> obtenerCompras() {
        List<Compra> compras = new ArrayList<>();
        String query = "SELECT * FROM compras";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Compra compra = new Compra(
                        rs.getString("nombre_oferta"),
                        rs.getDouble("monto_puja"),
                        rs.getString("email_usuario")
                );
                compra.setId(rs.getInt("id"));
                compras.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compras;
    }
}
