package com.ejemplo;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class Main {
    public static void main(String[] args) {
        // Crear la instancia de Javalin
        Javalin app = Javalin.create().start(7000);

        // Definir la ruta raíz con un ejemplo usando FreeMarker
        app.get("/", new Handler() {
            @Override
            public void handle(Context ctx) {
                // Aquí puedes usar FreeMarker para renderizar un template
                ctx.render("index.ftl");
            }
        });

        // Ejemplo de una ruta para mostrar un trabajador
        app.get("/trabajador/:id", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            Trabajador trabajador = new Trabajador(id, "Juan Pérez", "Desarrollador");
            ctx.json(trabajador);
        });
    }
}
