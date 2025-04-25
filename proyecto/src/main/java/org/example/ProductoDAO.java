    package org.example;

    import com.mysql.cj.Session;
    import jakarta.persistence.EntityManager;
    import jakarta.persistence.EntityManagerFactory;
    import jakarta.persistence.EntityTransaction;
    import jakarta.persistence.Persistence;
    import jakarta.transaction.Transaction;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;
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

                // Comprobación: ¿hay ya productos?
                Long count = (Long) em.createQuery("SELECT COUNT(p) FROM Producto p").getSingleResult();

                if (count == 0) {
                    // Solo si no hay productos, insertamos
                    String sql = "INSERT INTO productos (descripcion, nombre, precio, usuario_id) VALUES "
                            + "('Camiseta de algodón, varios colores', 'Camiseta básica', 15.99, NULL), "
                            + "('Vaquero clásico azul', 'Pantalón vaquero', 29.95, NULL), "
                            + "('Sudadera con capucha, unisex', 'Sudadera oversize', 34.99, NULL), "
                            + "('Zapatillas running, ligeras', 'Zapatillas deportivas', 49.50, NULL), "
                            + "('Perfecta para el invierno', 'Chaqueta acolchada', 59.99, NULL);";

                    em.createNativeQuery(sql).executeUpdate();
                }

                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                throw e;
            } finally {
                em.close();
            }
        }

        public static void modificarProducto(int id, String nombre, double precio, String descripcion) {
            String sql = "UPDATE productos SET nombre = ?, precio = ?, descripcion = ? WHERE id = ?";
            try (Connection conn = Database.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setDouble(2, precio);
                pstmt.setString(3, descripcion);
                pstmt.setInt(4, id);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public static void anadirProducto(Producto nuevoProducto) {
            String sql = "INSERT INTO productos (nombre, precio, descripcion) VALUES (?, ?, ?)";
            try (Connection conn = Database.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // Usamos los métodos get para obtener los valores del objeto Producto
                pstmt.setString(1, nuevoProducto.getNombre());
                pstmt.setDouble(2, nuevoProducto.getPrecio());
                pstmt.setString(3, nuevoProducto.getDescripcion());

                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static List<Producto> obtenerProductosPujables() {
            EntityManager em = emf.createEntityManager();
            try {
                return em.createQuery(
                                "SELECT p FROM Producto p WHERE p.validado = true AND p.usuario IS NULL", Producto.class)
                        .getResultList();
            } finally {
                em.close();
            }
        }



        // Método para actualizar una puja
        public static boolean actualizarPuja(int idProducto, double nuevaPuja, Usuario usuario) {
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                Producto producto = em.find(Producto.class, idProducto);
                if (producto != null) {
                    // Verificar que la nueva puja es mayor que la actual
                    if (nuevaPuja > producto.getPrecio()) {
                        producto.setPrecio(nuevaPuja);
                        producto.setUsuario(usuario);
                        em.merge(producto);
                        tx.commit();
                        return true;
                    }
                }
                tx.rollback();
                return false;
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                throw e;
            } finally {
                em.close();
            }
        }

        // Método para obtener productos por usuario (sus pujas)
        public static List<Producto> obtenerProductosPorUsuario(int usuarioId) {
            EntityManager em = emf.createEntityManager();
            try {
                return em.createQuery(
                                "SELECT p FROM Producto p WHERE p.usuario.id = :usuarioId", Producto.class)
                        .setParameter("usuarioId", usuarioId)
                        .getResultList();
            } finally {
                em.close();
            }
        }


        // Cerrar la conexión con la base de datos
        public static void cerrar() {
            if (emf.isOpen()) emf.close();
        }


    }
