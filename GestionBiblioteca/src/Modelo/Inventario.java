package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Inventario implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<LibroPedido> pedidos;
    private String estudianteId; // ID del estudiante
    private static final String FILE_NAME_PREFIX = "inventario_"; // Prefijo para el archivo de inventario
    private static final String FILE_EXTENSION = ".dat"; // Extensión del archivo

    public Inventario(String estudianteId) {
        this.estudianteId = estudianteId;
        this.pedidos = new ArrayList<>();
        cargarInventario(); // Cargar inventario al iniciar
    }

    private String getFileName() {
        return FILE_NAME_PREFIX + estudianteId + FILE_EXTENSION;
    }

    public void agregarLibro(Libro libro, Date fecha) {
        if (libro.isDisponible()) {
            pedidos.add(new LibroPedido(libro, fecha));
            libro.setDisponible(false);
            guardarInventario(); // Guardar inventario después de agregar un libro
        } else {
            throw new IllegalStateException("El libro no está disponible.");
        }
    }

    public List<LibroPedido> obtenerPedidos() {
        return new ArrayList<>(pedidos);
    }

    public void eliminarLibro(String isbn) {
        // Eliminar el libro de la lista de pedidos
        boolean libroEliminado = pedidos.removeIf(libroPedido -> libroPedido.getLibro().getIsbn().equals(isbn));
        if (libroEliminado) {
            // Si se eliminó un libro, actualizar el estado del libro en la lista de pedidos
            Libro libro = buscarLibroPorIsbn(isbn);
            if (libro != null) {
                libro.setDisponible(true);
            }
            guardarInventario(); // Guardar inventario después de eliminar un libro
        }
    }


    public Libro buscarLibroPorIsbn(String isbn) {
        for (LibroPedido libroPedido : pedidos) {
            if (libroPedido.getLibro().getIsbn().equals(isbn)) {
                return libroPedido.getLibro();
            }
        }
        return null;
    }

    private void guardarInventario() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getFileName()))) {
            oos.writeObject(this.pedidos);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar el inventario.");
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarInventario() {
        File file = new File(getFileName());
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getFileName()))) {
                this.pedidos = (List<LibroPedido>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Error al cargar el inventario.");
            }
        } else {
            this.pedidos = new ArrayList<>();
        }
    }
}
