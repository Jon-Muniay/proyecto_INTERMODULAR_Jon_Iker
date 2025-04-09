package org.example;

import jakarta.persistence.*;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "productos") // Nombre de la tabla en la base de datos
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática de ID
    private int id;

    @Column(name = "nombre", nullable = false) // Asegura que el nombre no sea nulo
    private String nombre;

    @Column(name = "precio", nullable = false) // Asegura que el precio no sea nulo
    private double precio;




    // Constructor vacío requerido por Hibernate
    public Producto() {}

    // Constructor con parámetros
    public Producto(String nombre, double precio ) {
        this.nombre = nombre;
        this.precio = precio;

    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }



    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +

                '}';
    }
}
