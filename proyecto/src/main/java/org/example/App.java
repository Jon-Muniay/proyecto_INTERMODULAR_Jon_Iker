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
            Usuario nuevoUsuario = new Usuario(nombre, email );
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
            String password = ctx.formParam("password");


            if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
                ctx.status(400).result("Faltan campos obligatorios");
                return;
            }


            Usuario usuario = UsuarioDAO.obtenerUsuarioPorEmailYPassword(email, password);

            // Verificar si el usuario existe
            if (usuario == null) {
                Map<String, Object> model = new HashMap<>();
                model.put("titulo", "Iniciar Sesión - Tienda de Ropa");
                model.put("error", "El usuario no existe o la contraseña es incorrecta");
                ctx.render("login.ftl", model);
                return;
            }

            // Guardar al usuario en la sesión
            ctx.sessionAttribute("usuario", usuario); // Asegúrate de que esto esté funcionando

            // Redirigir a la página de resultados
            ctx.redirect("/resultado");
        });

        // Ruta para mostrar el perfil y productos del usuario logueado
        app.get("/resultado", ctx -> {
            // Obtener el usuario de la sesión
            Usuario usuario = ctx.sessionAttribute("usuario");

            // Si no hay usuario en la sesión, redirigir a la página de inicio de sesión
            if (usuario == null) {
                ctx.redirect("/"); // Asegúrate de que el usuario sea redirigido si no está en sesión
                return;
            }

            // Obtener productos asociados al usuario
            List<Producto> productos = ProductoDAO.obtenerProductosPorEmail(usuario.getEmail());

            // Preparar el modelo con la información del usuario y productos
            Map<String, Object> model = new HashMap<>();
            model.put("usuario", usuario);
            model.put("productos", productos);

            // Renderizar la vista del resultado
            ctx.render("resultado.ftl", model);
        });


    }
}
