

import freemarker.template.*;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinFreemarker;
import org.example.Main;
import org.example.Usuario;
import org.example.UsuarioDAO;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class App {
    public static void main(String[] args) {
        // Configurar FreeMarker
        Configuration freemarkerConfig = new Configuration(Configuration.VERSION_2_3_32);
        freemarkerConfig.setClassForTemplateLoading(Main.class, "/templates");

        // Inicializar Javalin con FreeMarker
        Javalin app = Javalin.create(config -> {
            config.fileRenderer(new JavalinFreemarker(freemarkerConfig));
        }).start(9000);

        // Ruta para mostrar el formulario (GET)
        app.get("/", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("titulo", "Formulario de Ejemplo");
            ctx.render("form.ftl", model);
        });

        // Ruta para procesar el formulario (POST)
        app.post("/submit", ctx -> {
            Map<String, Object> model = new HashMap<>();

            // Obtener datos del formulario
            String nombre = ctx.formParam("nombre");
            String departamento = ctx.formParam("departamento");
            String contrato = ctx.formParam("contrato");

            double sueldo = 0;
            try {
                sueldo = Double.parseDouble(ctx.formParam("sueldo"));
            } catch (NumberFormatException e) {
                ctx.status(400).result("El campo 'sueldo' debe ser un número válido.");
                return;
            }

            // Crear el objeto Trabajador
            Usuario usuario = new Usuario ();
            // Guardar el trabajador en la base de datos
           UsuarioDAO.guardarUsuario(usuario);

            // Poner el trabajador en el modelo para mostrarlo en la plantilla
            model.put("Usuario", usuario);

            // Renderizar la plantilla result.ftl
            ctx.render("result.ftl", model);
        });
    }
}
