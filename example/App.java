package org.example;

import freemarker.template.Configuration;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinFreemarker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        // Configurar FreeMarker
        Configuration freemarkerConfig = new Configuration(Configuration.VERSION_2_3_32);
        freemarkerConfig.setClassForTemplateLoading(App.class, "/templates");

        // Inicializar Javalin con FreeMarker
        Javalin app = Javalin.create(config -> {
            config.fileRenderer(new JavalinFreemarker(freemarkerConfig));
            config.staticFiles.add(staticFiles -> {
                staticFiles.directory = "/static";  // Ruta de archivos estáticos
                staticFiles.hostedPath = "/static";
                staticFiles.precompress = false;
            });
        }).start(8080);

        // Ruta para el formulario de registro
        app.get("/registro.html", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("titulo", "Registrar - Tienda de Ropa");
            ctx.render("registro.ftl", model);
        });

        // Ruta para procesar el registro de usuarios
        app.post("/registro", ctx -> {
            String nombre = ctx.formParam("nombre");
            String email = ctx.formParam("email");
            String password = ctx.formParam("password");

            if (nombre == null || email == null || password == null || nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
                ctx.status(400).result("Faltan campos obligatorios");
                return;
            }

            Usuario usuarioExistente = UsuarioDAO.obtenerUsuarioPorEmail(email);
            if (usuarioExistente != null) {
                ctx.status(400).result("El usuario ya existe");
                return;
            }

            // Crear y guardar el nuevo usuario
            Usuario nuevoUsuario = new Usuario(nombre, email, password);
            UsuarioDAO.guardarUsuario(nuevoUsuario);

            // Insertar productos automáticamente después de crear el usuario
            ProductoDAO.insertarDatosAutomaticos();  // Aquí pasamos el usuario para asociar los productos

            // Redirigir a la página de login o a otra página de éxito
            ctx.redirect("/");
        });

        // Ruta para el login
        app.get("/", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("titulo", "Iniciar Sesión - Tienda de Ropa");
            ctx.render("login.ftl", model);
        });

        // Procesar login
        app.post("/login", ctx -> {
            String email = ctx.formParam("email");
            String contrasena = ctx.formParam("password");

            if (email == null || contrasena == null || email.isEmpty() || contrasena.isEmpty()) {
                ctx.status(400).result("Faltan campos obligatorios");
                return;
            }

            Usuario usuario = UsuarioDAO.obtenerUsuarioPorEmail(email);

            if (usuario == null || !usuario.verificarContrasena(contrasena)) {
                Map<String, Object> model = new HashMap<>();
                model.put("titulo", "Iniciar Sesión - Tienda de Ropa");
                model.put("error", "El usuario no existe o la contraseña es incorrecta");
                ctx.render("login.ftl", model);
                return;
            }

            ctx.sessionAttribute("usuario", usuario);

            if (email.equals("admin@gmail.com")) {
                ctx.redirect("/administradores");
            } else {
                ctx.redirect("/usuarios");
            }
        });

        // Vista para administradores
        app.get("/administradores", ctx -> {
            Usuario usuario = ctx.sessionAttribute("usuario");

            if (usuario == null || !usuario.getEmail().equals("admin@gmail.com")) {
                ctx.redirect("/");
                return;
            }

            // Obtener lista de productos
            List<Producto> productos = ProductoDAO.obtenerTodosProductos();

            Map<String, Object> model = new HashMap<>();
            model.put("titulo", "Panel de Administración");
            model.put("productos", productos);
            ctx.render("administradores.ftl", model);
        });

        // Vista para usuarios
        app.get("/usuarios", ctx -> {
            Usuario usuario = ctx.sessionAttribute("usuario");

            if (usuario == null) {
                ctx.redirect("/");
                return;
            }

            List<Producto> productos = ProductoDAO.obtenerProductosPorEmail(usuario.getEmail());

            Map<String, Object> model = new HashMap<>();
            model.put("usuario", usuario);
            model.put("productos", productos);

            ctx.render("usuarios.ftl", model);
        });
        // Ruta para las subastas
        app.get("/listaPujas", ctx -> {
            // Obtén los productos o subastas relevantes (esto depende de tu lógica)
            List<Producto> pujas = ProductoDAO.obtenerTodosProductos();

            Map<String, Object> model = new HashMap<>();
            model.put("titulo", "Lista de Pujas");
            model.put("pujas", pujas);

            // Renderiza la vista listaPujas.ftl
            ctx.render("listaPujas.ftl", Map.of("productos", pujas));
        });
        app.post("/api/productos", ctx -> {
            // Obtener el cuerpo de la solicitud como texto
            String body = ctx.body();

            // Procesar el cuerpo manualmente (ejemplo simple)
            String[] params = body.split("&");
            String nombre = null;
            double precio = 0;
            String descripcion = null;

            for (String param : params) {
                String[] keyValue = param.split("=");
                if (keyValue[0].equals("nombre")) {
                    nombre = keyValue[1];
                } else if (keyValue[0].equals("precio")) {
                    precio = Double.parseDouble(keyValue[1]);
                } else if (keyValue[0].equals("descripcion")) {
                    descripcion = keyValue[1];
                }
            }

            // Crear el producto y guardarlo (ejemplo)
            Producto producto = new Producto(nombre, precio, descripcion);
            ProductoDAO.guardarProducto(producto);

            ctx.result("Producto añadido correctamente");
        });










        //--------------------------------------------

        app.get("/subasta", ctx -> {
            // Obtener todos los productos disponibles
            List<Producto> productos = ProductoDAO.obtenerTodosProductos();

            Map<String, Object> model = new HashMap<>();
            model.put("titulo", "Subasta de Productos");
            model.put("productos", productos);  // Pasar la lista de productos a la plantilla

            ctx.render("subasta.ftl", model);
        });

        app.get("/api/productos-pujables", ctx -> {
            List<Producto> productos = ProductoDAO.obtenerProductosPujables();
            ctx.json(productos);
        });

// API para manejar pujas
        app.post("/api/pujar", ctx -> {
            Usuario usuario = ctx.sessionAttribute("usuario");
            if (usuario == null) {
                ctx.status(401).json(Map.of("error", "Debes iniciar sesión para pujar"));
                return;
            }

            try {
                Map<String, Object> body = ctx.bodyAsClass(Map.class);
                int idProducto = Integer.parseInt(body.get("idProducto").toString());
                double cantidad = Double.parseDouble(body.get("cantidad").toString());

                boolean exito = ProductoDAO.actualizarPuja(idProducto, cantidad, usuario);

                if (exito) {
                    ctx.json(Map.of(
                            "success", true,
                            "nuevoPrecio", cantidad,
                            "mensaje", "Puja realizada con éxito"
                    ));
                } else {
                    ctx.status(400).json(Map.of(
                            "error", "La puja debe ser mayor al precio actual",
                            "success", false
                    ));
                }
            } catch (Exception e) {
                ctx.status(500).json(Map.of("error", "Error al procesar la puja"));
            }
        });

// API para obtener productos de un usuario
        app.get("/mis-pujas", ctx -> {
            Usuario usuario = ctx.sessionAttribute("usuario");
            if (usuario == null) {
                ctx.redirect("/");
                return;
            }

            List<Producto> misPujas = ProductoDAO.obtenerProductosPorUsuario(usuario.getId());

            Map<String, Object> model = new HashMap<>();
            model.put("titulo", "Mis Pujas");
            model.put("productos", misPujas);

            ctx.render("misPujas.ftl", model);
        });

        // ----------------------------------------------------------------------

        app.post("/api/productos", ctx -> {
            Usuario usuario = ctx.sessionAttribute("usuario");
            if (usuario == null || "admin@gmail.com".equals(usuario.getEmail())) {
                ctx.status(403).json(Map.of("error", "Acceso no autorizado"));
                return;
            }

            Producto producto = ctx.bodyAsClass(Producto.class);
            producto.setUsuario(usuario);
            ProductoDAO.guardarProducto(producto);

            ctx.json(Map.of("success", true));
        });

        // Ruta para listar productos pujables
        app.get("/api/productos/pujables", ctx -> {
            ctx.json(ProductoDAO.obtenerProductosPujables());
        });

        // Ruta para realizar una puja
        app.post("/api/pujas", ctx -> {
            Usuario usuario = ctx.sessionAttribute("usuario");
            if (usuario == null) {
                ctx.status(401).json(Map.of("error", "Debes iniciar sesión"));
                return;
            }

            Map<String, Object> body = ctx.bodyAsClass(Map.class);
            int productoId = (int) body.get("productoId");
            double cantidad = (double) body.get("cantidad");

            boolean exito = ProductoDAO.realizarPuja(productoId, cantidad, usuario);
            if (exito) {
                ctx.json(Map.of("success", true));
            } else {
                ctx.status(400).json(Map.of("error", "Puja no válida"));
            }
        });

        // Ruta para admin - liberar productos
        app.post("/admin/productos/{id}/liberar", ctx -> {
            Usuario usuario = ctx.sessionAttribute("usuario");
            if (usuario == null || !"admin@gmail.com".equals(usuario.getEmail())) {
                ctx.status(403);
                return;
            }

            int productoId = Integer.parseInt(ctx.pathParam("id"));
            ProductoDAO.liberarProducto(productoId);
            ctx.json(Map.of("success", true));
        });






        //--------------------------------------------

        // Ruta para eliminar producto
        app.post("/administrador/eliminar-producto", ctx -> {
            int idProducto = Integer.parseInt(ctx.formParam("id_producto"));
            ProductoDAO.eliminarProducto(idProducto);
            ctx.redirect("/administradores");
        });

        // Ruta para modificar producto
        app.post("/administrador/modificar-producto", ctx -> {
            int idProducto = Integer.parseInt(ctx.formParam("id_producto"));
            String nombre = ctx.formParam("nombre");
            double precio = Double.parseDouble(ctx.formParam("precio"));
            String descripcion = ctx.formParam("descripcion");

            Producto producto = ProductoDAO.obtenerProductoPorId(idProducto);
            if (producto != null) {
                producto.setNombre(nombre);
                producto.setPrecio(precio);
                producto.setDescripcion(descripcion);

                ProductoDAO.actualizarProducto(producto);
            }



            ctx.redirect("/administradores");
        });
        app.post("/administrador/anadirProducto", ctx -> {
            // Obtener los parámetros del formulario
            String nombre = ctx.formParam("nombre");
            double precio = Double.parseDouble(ctx.formParam("precio"));
            String descripcion = ctx.formParam("descripcion");

            // Crear un nuevo producto
            Producto nuevoProducto = new Producto(nombre, precio, descripcion);

            // Llamar al método de DAO para guardar el producto en la base de datos
            ProductoDAO.anadirProducto(nuevoProducto);

            // Redirigir al administrador a la página de gestión de productos
            ctx.redirect("/administrador/gestionar-productos");
        });
        app.get("/administrador/gestionar-productos", ctx -> {
            // Obtener todos los productos desde la base de datos
            List<Producto> productos = ProductoDAO.obtenerTodosProductos();

            // Pasar los productos a la vista
            ctx.attribute("productos", productos);
            ctx.render("administradores.ftl");
        });



        // Logout
        app.post("/logout", ctx -> {
            ctx.sessionAttribute("usuario", null);
            ctx.redirect("/");
        });
    }
}
