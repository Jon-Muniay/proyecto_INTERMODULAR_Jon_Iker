package org.example;

import jakarta.persistence.*;
import lombok.Data;

@Data // Genera getters, setters, toString, etc. (opcional, requiere Lombok)
@Entity // Indica que esta clase es una entidad JPA
@Table(name = "usuarios") // Nombre de la tabla en la base de datos
public class Usuario {

    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrementable
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private String rol; // "usuario" o "administrador"
}