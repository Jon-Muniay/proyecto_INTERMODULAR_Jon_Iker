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
    private static Cache HibernateUtil;

    public static void guardarUsuario(Usuario usuario) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public static Usuario obtenerUsuarioPorEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Usuario usuario = null;
        try {
            usuario = session.createQuery("FROM Usuario WHERE email = :email", Usuario.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return usuario;
    }

    public static Usuario obtenerUsuarioPorEmailYPassword(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Usuario usuario = null;
        try {
            // Consulta HQL que busca un usuario con un email y una contraseña específicos
            usuario = session.createQuery("FROM Usuario WHERE email = :email AND contraseña = :password", Usuario.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .uniqueResult(); // Devuelve un único resultado
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close(); // Cierra la sesión para liberar recursos
        }
        return usuario; // Retorna el usuario encontrado o null si no se encuentra
    }

}
