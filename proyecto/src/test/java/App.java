

import freemarker.template.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class App {
    public static void main(String[] args) {
        try {
            // Configurar FreeMarker
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
            cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
            cfg.setDefaultEncoding("UTF-8");

            // Cargar la plantilla
            Template template = cfg.getTemplate("plantilla.ftl");

            // Datos para la plantilla
            Map<String, Object> datos = new HashMap<>();
            datos.put("titulo", "Página de inicio");
            datos.put("usuario", "Juan Pérez");
            datos.put("productos", List.of("Laptop", "Mouse", "Teclado"));

            // Guardar en la carpeta personalizada
            String rutaSalida = "C:\\Users\\alumTA\\Downloads\\Project_Jon_Iker\\salida.html";
            FileWriter fileWriter = new FileWriter(rutaSalida);
            template.process(datos, fileWriter);
            fileWriter.close();

            System.out.println("✅ Archivo generado en: " + rutaSalida);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
