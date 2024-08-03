package Modelo;

import java.io.Serializable;

public class Libro implements Serializable {
    private static final long serialVersionUID = 1L; 
    private String categoria;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String isbn;
    private boolean disponible;

    public Libro(String categoria, String titulo, String autor, int anioPublicacion, String isbn, boolean disponible) {
        this.categoria = categoria;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.isbn = isbn;
        this.disponible = disponible;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return titulo + " - " + autor + " (" + anioPublicacion + ")";
    }
}
