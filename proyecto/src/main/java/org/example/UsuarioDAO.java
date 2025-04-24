package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ROPAZON");

    // Configuración de la base de datos
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Ropazon";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    public static Usuario obtenerUsuarioPorEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static Usuario obtenerUsuarioPorEmailYPassword(String email, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.password = :password", Usuario.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    // Método para obtener todos los usuarios
    public static List<Usuario> obtenerTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        // Conexión a la base de datos
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM usuarios";  // Asegúrate de que la tabla sea la correcta
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String password = rs.getString("password");

                // Suponiendo que la clase Usuario tiene un constructor adecuado
                Usuario usuario = new Usuario(nombre, email, password);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de errores
        }
        return usuarios;
    }

    // Método para guardar un nuevo usuario
    public static void guardarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }
}
