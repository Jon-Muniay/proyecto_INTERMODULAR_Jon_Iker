package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UsuarioDAO {

    // SessionFactory ya está configurado correctamente
    private static final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml") // archivo de configuración Hibernate
            .addAnnotatedClass(Usuario.class) // Asegúrate de añadir la clase Usuario
            .buildSessionFactory();

    public static void guardarUsuario(Usuario usuario) {
        // Abre una sesión con Hibernate
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            // Inicia una transacción
            transaction = session.beginTransaction();
            session.persist(usuario);  // Guarda el usuario
            transaction.commit();      // Commit de la transacción
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Si ocurre un error, rollback
            e.printStackTrace();
        } finally {
            session.close();  // Cierra la sesión
        }
    }

    public static Usuario obtenerUsuarioPorEmail(String email) {
        // Abre una sesión con Hibernate
        Session session = sessionFactory.openSession();
        Usuario usuario = null;

        try {
            // Realiza la consulta para buscar el usuario por email
            usuario = session.createQuery("FROM Usuario WHERE email = :email", Usuario.class)
                    .setParameter("email", email)
                    .uniqueResult();  // Retorna el resultado único
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();  // Cierra la sesión
        }

        return usuario;
    }

    public static Usuario obtenerUsuarioPorEmailYPassword(String email, String password) {
        // Abre una sesión con Hibernate
        Session session = sessionFactory.openSession();
        Usuario usuario = null;

        try {
            // Realiza la consulta para buscar el usuario por email y contraseña
            usuario = session.createQuery("FROM Usuario WHERE email = :email AND contraseña = :password", Usuario.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .uniqueResult();  // Retorna el resultado único
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();  // Cierra la sesión
        }

        return usuario;  // Retorna el usuario encontrado o null si no se encuentra
    }
}
