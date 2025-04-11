package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class ProductoDAO {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ROPAZON");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();



    public static List<Producto> obtenerTodosProductos() {
        String query = "SELECT p FROM Producto p";
        return entityManager.createQuery(query, Producto.class).getResultList();
    }

    // Obtener un producto por su ID
    public static Producto obtenerProductoPorId(int id) {
        return entityManager.find(Producto.class, id);
    }

    // Método para obtener productos por email (usuario)
    public static List<Producto> obtenerProductosPorEmail(String email) {
        return entityManager.createQuery("SELECT p FROM Producto p WHERE p.emailUsuario = :email", Producto.class)
                .setParameter("email", email)
                .getResultList();
    }


    public static void guardarProducto(Producto producto) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(producto);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    // Actualizar un producto existente
    public static void actualizarProducto(Producto producto) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(producto);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    // Eliminar un producto por su ID
    public static void eliminarProducto(int id) {
        Producto producto = obtenerProductoPorId(id);
        if (producto != null) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.remove(producto);
                transaction.commit();
            } catch (RuntimeException e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }

    // Cerrar la conexión
    public static void cerrar() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}



