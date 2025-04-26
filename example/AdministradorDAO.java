package org.example;

public class AdministradorDAO {
    public boolean esAdministrador(String email, String contrasena) {
        // Aquí puedes tener tu lógica para validar el email y la contraseña del admin
        return email.equals("admin@gmail.com") && contrasena.equals("12345");
    }
}
