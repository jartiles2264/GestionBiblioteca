package Vista;

import Modelo.GestorLibros;
import Modelo.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmMostrarLibros extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable tableLibros;

    public FrmMostrarLibros(GestorLibros gestorLibros) {
        setTitle("Mostrar Todos los Libros");
        setBounds(100, 100, 800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableLibros = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableLibros);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        mostrarLibros(gestorLibros);
    }

    private void mostrarLibros(GestorLibros gestorLibros) {
        List<Libro> libros = gestorLibros.obtenerTodosLosLibros();
        
        String[] columnNames = {"Categoría", "Título", "Autor", "Año", "ISBN", "Disponible"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {

			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        for (Libro libro : libros) {
            Object[] row = {
                libro.getCategoria(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getAnioPublicacion(),
                libro.getIsbn(),
                libro.isDisponible() ? "Sí" : "No"
            };
            model.addRow(row);
        }
        
        tableLibros.setModel(model);
    }
}
