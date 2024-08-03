package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GestorLibros {
    private List<Libro> libros;
    private static final String ARCHIVO_LIBROS = "libros.dat";

    public GestorLibros() {
        this.libros = new ArrayList<>();
        cargarDatosLibros();
    }

    @SuppressWarnings("unchecked")
	private void cargarDatosLibros() {
        File archivo = new File(ARCHIVO_LIBROS);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                libros = (List<Libro>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            datosIniciales();
            guardarDatosLibros();
        }
    }

    private void datosIniciales() {
        libros.add(new Libro("HISTORIA", "El Siglo de las Luces", "Alejandro Perez", 2005, "HST001", true));
        libros.add(new Libro("HISTORIA", "La Revolucion Francesa", "Maria Rodriguez", 2010, "HST002", true));
        libros.add(new Libro("HISTORIA", "Imperios Antiguos", "Juan Gomez", 2008, "HST003", true));
        libros.add(new Libro("BIOLOGIA", "El Origen de las Especies", "Charles Darwin", 1859, "BLG001", true));
        libros.add(new Libro("BIOLOGIA", "Biologia Molecular: Fundamentos y Aplicaciones", "Ana Garcia", 2015, "BLG002", true));
        libros.add(new Libro("BIOLOGIA", "Ecologia y Medio Ambiente", "Luis Martinez", 2009, "BLG003", true));
        libros.add(new Libro("LENGUAJE", "Cien Anios de Soledad", "Gabriel Garcia Marquez", 1967, "LGJ001", true));
        libros.add(new Libro("LENGUAJE", "1984", "George Orwell", 1949, "LGJ002", true));
        libros.add(new Libro("LENGUAJE", "El Hobbit", "J.R.R. Tolkien", 1937, "LGJ003", true));
        libros.add(new Libro("MATEMATICAS", "El Hombre que Calculaba", "Malba Tahan", 1938, "MTC001", true));
        libros.add(new Libro("MATEMATICAS", "Teoria de Numeros", "Alberto Gomez", 2012, "MTC002", true));
        libros.add(new Libro("MATEMATICAS", "Geometria Analitica", "Laura Perez", 2000, "MTC003", true));
        libros.add(new Libro("COMPUTACION", "Codigo Limpio: Manual de Artesania de Software", "Robert C. Martin", 2008, "CPT001", true));
        libros.add(new Libro("COMPUTACION", "Introduccion a la Programacion con Python", "Ana Maria Gonzalez", 2019, "CPT002", true));
        libros.add(new Libro("COMPUTACION", "La Maquina Diferencial", "William Gibson", 2014, "CPT003", true));
    }

    private void guardarDatosLibros() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_LIBROS))) {
            oos.writeObject(libros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
        ordenarLibrosPorCategoria();
        guardarDatosLibros(); 
    }

    private void ordenarLibrosPorCategoria() {
        libros.sort(Comparator.comparing(Libro::getCategoria).thenComparing(Libro::getTitulo));
    }

    public void eliminarLibro(String isbn) {
        libros.removeIf(libro -> libro.getIsbn().equals(isbn));
        guardarDatosLibros();
    }

    public Libro buscarLibroPorIsbn(String isbn) {
        for (Libro libro : libros) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }

    public List<Libro> obtenerTodosLosLibros() {
        return new ArrayList<>(libros);
    }

    public void prestarLibro(String isbn) {
        Libro libro = buscarLibroPorIsbn(isbn);
        if (libro != null && libro.isDisponible()) {
            libro.setDisponible(false);
            guardarDatosLibros(); 
        }
    }

    public void devolverLibro(String isbn) {
        Libro libro = buscarLibroPorIsbn(isbn);
        if (libro != null && !libro.isDisponible()) {
            libro.setDisponible(true);
            guardarDatosLibros(); 
        }
    }
}
