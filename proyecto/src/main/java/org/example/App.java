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
                staticFiles.directory = "/static";
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

            // Crear el usuario y guardarlo
            Usuario nuevoUsuario = new Usuario(nombre, email ,password);
            UsuarioDAO.guardarUsuario(nuevoUsuario);

            // Redirigir a la página de login después de registrarse
            ctx.redirect("/");
        });

        // Ruta para el formulario de login
        app.get("/", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("titulo", "Iniciar Sesión - Tienda de Ropa");
            ctx.render("login.ftl", model);  // Asegúrate de que el archivo login.ftl esté en la carpeta /templates
        });

        app.post("/login", ctx -> {
            String email = ctx.formParam("email");
            String contrasena = ctx.formParam("password");

            if (email == null || contrasena == null || email.isEmpty() || contrasena.isEmpty()) {
                ctx.status(400).result("Faltan campos obligatorios");
                return;
            }

            // Obtener el usuario por email
            Usuario usuario = UsuarioDAO.obtenerUsuarioPorEmail(email);

            // Verificar si el usuario existe y la contraseña es correcta
            if (usuario == null || !usuario.verificarContrasena(contrasena)) {
                Map<String, Object> model = new HashMap<>();
                model.put("titulo", "Iniciar Sesión - Tienda de Ropa");
                model.put("error", "El usuario no existe o la contraseña es incorrecta");
                ctx.render("login.ftl", model);
                return;
            }

            // Guardar al usuario en la sesión
            ctx.sessionAttribute("usuario", usuario);

            // Redirigir a la página personalizada del usuario
            ctx.redirect("/usuarios");
        });

        // Ruta para mostrar el perfil y productos del usuario logueado
        app.get("/usuarios", ctx -> {
            // Obtener el usuario de la sesión
            Usuario usuario = ctx.sessionAttribute("usuario");

            // Si no hay usuario en la sesión, redirigir a la página de inicio de sesión
            if (usuario == null) {
                ctx.redirect("/");
                return;
            }

            // Obtener productos asociados al usuario
            List<Producto> productos = ProductoDAO.obtenerProductosPorEmail(usuario.getEmail());

            // Preparar el modelo con la información del usuario y productos
            Map<String, Object> model = new HashMap<>();
            model.put("usuario", usuario);
            model.put("productos", productos);

            // Renderizar la vista del resultado
            ctx.render("usuarios.ftl", model);
        });

        // Ruta para cerrar sesión
        app.post("/logout", ctx -> {
            ctx.sessionAttribute("usuario", null); // Elimina al usuario de la sesión
            ctx.redirect("/"); // Redirige al inicio de sesión
        });
    }
}