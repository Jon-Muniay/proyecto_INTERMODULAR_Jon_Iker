    package org.example;

    import jakarta.persistence.*;


    @Entity // Indica que esta clase es una entidad JPA
    @Table(name = "Usuarios") // Nombre de la tabla en la base de datos
    public class Usuario {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)

       private int id;

        @Column(name = "nombre", nullable = false)
        private String nombre;

            @Column(name = "email", nullable = false)
            private String email;

            @Column(name = "contrasena", nullable = false)
            private String contrasena;

            // Constructor vacío requerido por Hibernate
            public Usuario() {}

            public Usuario(String nombre,String email, String contrasena) {
                this.nombre = nombre;
                this.email = email;
                this.contrasena = contrasena;
            }

            // Getters y setters
            public int getId() { return id; }
            public String getEmail() { return email; }
            public String getContrasena() { return contrasena; }

            public void setEmail(String email) { this.email = email; }
            public void setContrasena(String contrasena) { this.contrasena = contrasena; }
            public String getNombre() { return nombre; }
            public void setNombre(String nombre) { this.nombre = nombre; }
        }
