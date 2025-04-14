package org.example;

import freemarker.template.Configuration;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinFreemarker;
import org.example.Usuario;
import org.example.UsuarioDAO;
import org.example.Producto;
import org.example.ProductoDAO;

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
        }).start(9000);

        // Ruta para el formulario de registro
        app.get("/registro", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("titulo", "Registrar - Tienda de Ropa");
            ctx.render("registro.ftl", model);
        });

        // Ruta para procesar el formulario de registro
        app.post("/registro", ctx -> {
            String nombre = ctx.formParam("nombre");
            String email = ctx.formParam("email");
            String password = ctx.formParam("password");

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
            Usuario nuevoUsuario = new Usuario(email, password);
            UsuarioDAO.guardarUsuario(nuevoUsuario);

            // Redirigir a la página de login después de registrarse
            ctx.redirect("/");
        });

        // Ruta para el formulario de login
        app.get("/", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("titulo", "Iniciar Sesión - Tienda de Ropa");
            ctx.render("login.ftl", model);
        });

        // Ruta para procesar el login
        app.post("/plantilla", ctx -> {
            String email = ctx.formParam("email");
            String password = ctx.formParam("password");

            if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
                ctx.status(400).result("Faltan campos obligatorios");
                return;
            }

            // Buscar usuario en la base de datos
            Usuario usuario = UsuarioDAO.obtenerUsuarioPorEmailYPassword(email, password);

            if (usuario == null) {
                ctx.status(401).result("Credenciales incorrectas");
                return;
            }

            // Obtener los productos asociados al usuario
            List<Producto> productos = ProductoDAO.obtenerProductosPorEmail(email);

            // Preparar el modelo con la información del usuario y los productos
            Map<String, Object> model = new HashMap<>();
            model.put("usuario", usuario);
            model.put("productos", productos);

            // Renderizar el dashboard con la información del usuario
            ctx.render("dashboard.ftl", model);
        });
    }
}
