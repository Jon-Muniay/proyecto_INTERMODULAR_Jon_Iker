package org.example;

import jakarta.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "Usuarios") // Nombre de la tabla en la base de datos
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Producto> productos = new ArrayList<>();

    // Constructor vacío requerido por Hibernate
    public Usuario() {}

    public Usuario(String nombre, String email, String contrasena) {
        this.nombre = nombre;
        this.email = email;
        this.setContrasena(contrasena); // Hashea la contraseña automáticamente
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = BCrypt.hashpw(contrasena, BCrypt.gensalt());
    }

    // Método para verificar la contraseña
    public boolean verificarContrasena(String contrasenaPlana) {
        return BCrypt.checkpw(contrasenaPlana, this.contrasena);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        producto.setUsuario(this);
    }
}