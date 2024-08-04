package Modelo;

import java.io.Serializable;
import java.util.Date;

public class LibroPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    private Libro libro;
    private Date fechaPedido;

    public LibroPedido(Libro libro, Date fechaPedido) {
        this.libro = libro;
        this.fechaPedido = fechaPedido;
    }

    public Libro getLibro() {
        return libro;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }
}
