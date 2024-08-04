package Vista;

import Modelo.GestorLibros;
import Modelo.Libro;
import Modelo.Inventario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class FrmMostrarLibros extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable tableLibros;
    private GestorLibros gestorLibros;
    private Inventario inventario;

    public FrmMostrarLibros(GestorLibros gestorLibros, Inventario inventario) {
        this.gestorLibros = gestorLibros;
        this.inventario = inventario;
        
        setTitle("Mostrar Todos los Libros");
        setBounds(100, 100, 800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableLibros = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableLibros);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        mostrarLibros();

        // Botón para seleccionar libro
        JButton btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.setBounds(650, 300, 120, 40);
        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableLibros.getSelectedRow();
                if (selectedRow >= 0) {
                    String isbn = (String) tableLibros.getValueAt(selectedRow, 4);
                    Libro libro = gestorLibros.buscarLibroPorIsbn(isbn);
                    if (libro != null && libro.isDisponible()) {
                        // Agregar al inventario
                        try {
                            inventario.agregarLibro(libro, new Date());
                            JOptionPane.showMessageDialog(null, "Libro seleccionado y agregado al inventario.");
                        } catch (IllegalStateException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El libro no está disponible.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un libro.");
                }
            }
        });
        getContentPane().add(btnSeleccionar, BorderLayout.SOUTH);
    }

    private void mostrarLibros() {
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
