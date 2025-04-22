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

            // Validación de campos
            if (nombre == null || email == null || password == null || nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
                ctx.status(400).result("Faltan campos obligatorios");
                return;
            }

            // Verificar si el usuario ya existe
            Usuario usuarioExistente = UsuarioDAO.obtenerUsuarioPorEmail(email);
            if (usuarioExistente != null) {
                ctx.status(400).result("El usuario ya existe");
                return;
            }

            // Crear el nuevo usuario y guardarlo en la base de datos
            Usuario nuevoUsuario = new Usuario(nombre, email, password);
            UsuarioDAO.guardarUsuario(nuevoUsuario);

            // Redirigir al login después del registro
            ctx.redirect("/");
        });

        // Ruta para el formulario de login
        app.get("/", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("titulo", "Iniciar Sesión - Tienda de Ropa");
            ctx.render("login.ftl", model); // Asegúrate de que login.ftl esté en /templates
        });

        // Ruta para procesar el login
        app.post("/login", ctx -> {
            String email = ctx.formParam("email");
            String contrasena = ctx.formParam("password");

            // Validación de campos vacíos
            if (email == null || contrasena == null || email.isEmpty() || contrasena.isEmpty()) {
                ctx.status(400).result("Faltan campos obligatorios");
                return;
            }

            // Obtener el usuario por email
            Usuario usuario = UsuarioDAO.obtenerUsuarioPorEmail(email);

            // Verificar existencia del usuario y si la contraseña es correcta
            if (usuario == null || !usuario.verificarContrasena(contrasena)) {
                Map<String, Object> model = new HashMap<>();
                model.put("titulo", "Iniciar Sesión - Tienda de Ropa");
                model.put("error", "El usuario no existe o la contraseña es incorrecta");
                ctx.render("login.ftl", model);
                return;
            }

            // Si el usuario es admin, redirigir a la vista de administrador
            if (email.equals("admin@gmail.com")) {
                ctx.sessionAttribute("usuario", usuario);
                ctx.redirect("/administradores"); // Redirige a la página de administradores
            } else {
                ctx.sessionAttribute("usuario", usuario);
                ctx.redirect("/usuarios"); // Redirige a la página de usuarios
            }
        });

        // Ruta para la vista de administradores
        app.get("/administradores", ctx -> {
            Usuario usuario = ctx.sessionAttribute("usuario");

            // Verificar si el usuario está autenticado y si es el administrador
            if (usuario == null || !usuario.getEmail().equals("admin@gmail.com")) {
                ctx.redirect("/");  // Si no es admin, redirige al login
                return;
            }

            // Renderizar la vista de administrador
            Map<String, Object> model = new HashMap<>();
            model.put("titulo", "Panel de Administración");
            ctx.render("administradores.ftl", model); // Renderiza administradores.ftl
        });

        // Ruta para mostrar el perfil del usuario logueado
        app.get("/usuarios", ctx -> {
            Usuario usuario = ctx.sessionAttribute("usuario");

            // Si no hay usuario en la sesión, redirigir al login
            if (usuario == null) {
                ctx.redirect("/");  // Redirige al login si no hay usuario en sesión
                return;
            }

            // Obtener productos asociados al usuario
            List<Producto> productos = ProductoDAO.obtenerProductosPorEmail(usuario.getEmail());

            // Preparar los datos para la vista
            Map<String, Object> model = new HashMap<>();
            model.put("usuario", usuario);
            model.put("productos", productos);

            // Renderizar la vista del perfil de usuario
            ctx.render("usuarios.ftl", model); // Renderiza usuarios.ftl
        });

        // Ruta para cerrar sesión
        app.post("/logout", ctx -> {
            ctx.sessionAttribute("usuario", null); // Elimina el usuario de la sesión
            ctx.redirect("/"); // Redirige al login
        });
    }
}
