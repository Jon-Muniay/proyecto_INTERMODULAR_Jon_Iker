package org.example;

public class Compra {
    private int id;
    private String nombreOferta;
    private double montoPuja;
    private String emailUsuario;

    // Constructor
    public Compra(String nombreOferta, double montoPuja, String emailUsuario) {
        this.nombreOferta = nombreOferta;
        this.montoPuja = montoPuja;
        this.emailUsuario = emailUsuario;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreOferta() {
        return nombreOferta;
    }

    public void setNombreOferta(String nombreOferta) {
        this.nombreOferta = nombreOferta;
    }

    public double getMontoPuja() {
        return montoPuja;
    }

    public void setMontoPuja(double montoPuja) {
        this.montoPuja = montoPuja;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
}
