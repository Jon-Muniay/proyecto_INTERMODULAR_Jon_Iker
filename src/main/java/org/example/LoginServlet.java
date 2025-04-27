package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login") // Anotación para mapear el servlet a la URL /login
public class LoginServlet extends HttpServlet {

    // Instancia del servicio que se encarga de verificar si el usuario es admin
    private AdministradorDAO usuarioService = new AdministradorDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros enviados desde el formulario de login
        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");

        // Verificar si las credenciales son correctas y si el usuario es administrador
        if (usuarioService.esAdministrador(email, contrasena)) {
            // Si es admin, redirigir al perfil del administrador
            request.getRequestDispatcher("/Administradores.ftl").forward(request, response);  // Página que se mostrará si es admin
        } else {
            // Si no es admin, redirigir a la página de inicio o mostrar mensaje de acceso denegado
            response.sendRedirect("/inicio"); // O la URL que prefieras para el inicio o error
        }
    }
}
