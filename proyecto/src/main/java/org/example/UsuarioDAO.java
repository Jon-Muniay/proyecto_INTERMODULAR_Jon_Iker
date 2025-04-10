package org.example;

import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UsuarioDAO {

    private static final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();
    public static void guardarUsuario(Usuario usuario) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            System.out.println("Guardando usuario: " + usuario.getNombre());
            session.persist(usuario);
            transaction.commit();
            System.out.println("Usuario guardado correctamente.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error al guardar el usuario:");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static Usuario obtenerUsuarioPorEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Usuario WHERE email = :email", Usuario.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }

    public static Usuario obtenerUsuarioPorEmailYPassword(String email, String password) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Usuario WHERE email = :email AND contraseña = :password", Usuario.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .uniqueResult();
        }
    }
}