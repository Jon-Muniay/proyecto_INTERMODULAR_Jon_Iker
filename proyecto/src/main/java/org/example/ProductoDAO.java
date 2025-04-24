package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class ProductoDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ROPAZON");

    // Obtener todos los productos
    public static List<Producto> obtenerTodosProductos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Obtener un producto por su ID
    public static Producto obtenerProductoPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    // Obtener productos por el email del usuario asociado
    public static List<Producto> obtenerProductosPorEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT p FROM Producto p WHERE p.usuario.email = :email", Producto.class)
                    .setParameter("email", email)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Guardar un nuevo producto
    public static void guardarProducto(Producto producto) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(producto);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Actualizar un producto existente
    public static void actualizarProducto(Producto producto) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(producto);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Eliminar un producto por su ID
    public static void eliminarProducto(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            Producto producto = em.find(Producto.class, id);
            if (producto != null) {
                tx.begin();
                em.remove(producto);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public static void insertarDatosAutomaticos() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            // Consulta SQL nativa con INSERT INTO
            String sql = "INSERT INTO productos (descripcion, nombre, precio, usuario_id) VALUES "
                    + "('Camiseta de algodón, varios colores', 'Camiseta básica', 15.99, NULL), "
                    + "('Vaquero clásico azul', 'Pantalón vaquero', 29.95, NULL), "
                    + "('Sudadera con capucha, unisex', 'Sudadera oversize', 34.99, NULL), "
                    + "('Zapatillas running, ligeras', 'Zapatillas deportivas', 49.50, NULL), "
                    + "('Perfecta para el invierno', 'Chaqueta acolchada', 59.99, NULL);";

            // Ejecuta la consulta de inserción
            em.createNativeQuery(sql).executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Cerrar la conexión con la base de datos
    public static void cerrar() {
        if (emf.isOpen()) emf.close();
    }
}
