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

            Map<String, Object> model = new HashMap<>();
            model.put("titulo", "Panel de Administración");
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

        app.get("/listaPujas", ctx -> {
            Usuario usuario = ctx.sessionAttribute("usuario");
            if (usuario == null) {
                ctx.redirect("/");
                return;
            }

            Map<String, Object> model = new HashMap<>();
            model.put("usuario", usuario);
            // Lo que necesites cargar, por ejemplo:
            ctx.render("listaPujas.ftl", model);
        });

        // Logout
        app.post("/logout", ctx -> {
            ctx.sessionAttribute("usuario", null);
            ctx.redirect("/");
        });
    }

}
