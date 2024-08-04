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

public class FrmBuscarPorCategoria extends JFrame {
    private static final long serialVersionUID = 1L;
    private GestorLibros gestorLibros;
    private Inventario inventario;
    private JComboBox<String> cmbCategoria;
    private JTable tblResultados;

    public FrmBuscarPorCategoria(GestorLibros gestorLibros, Inventario inventario) {
        this.gestorLibros = gestorLibros;
        this.inventario = inventario;

        setTitle("Buscar Libros por Categoría");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);

        JPanel pnlBusqueda = new JPanel();
        pnlBusqueda.setLayout(new GridLayout(2, 1));
        panel.add(pnlBusqueda, BorderLayout.NORTH);

        JLabel lblCategoria = new JLabel("Selecciona una categoría:");
        pnlBusqueda.add(lblCategoria);

        cmbCategoria = new JComboBox<>(new String[]{"HISTORIA", "BIOLOGIA", "LENGUAJE", "MATEMATICAS", "COMPUTACION"});
        pnlBusqueda.add(cmbCategoria);

        // Crear un panel para los botones
        JPanel pnlBotones = new JPanel();
        pnlBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(pnlBotones, BorderLayout.SOUTH);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarLibros();
            }
        });
        pnlBotones.add(btnBuscar);

        JButton btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblResultados.getSelectedRow();
                if (selectedRow >= 0) {
                    String isbn = (String) tblResultados.getValueAt(selectedRow, 4);
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
        pnlBotones.add(btnSeleccionar);

        tblResultados = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblResultados);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    private void buscarLibros() {
        String categoria = (String) cmbCategoria.getSelectedItem();
        List<Libro> libros = gestorLibros.buscarLibrosPorCategoria(categoria);

        String[] columnas = {"Categoría", "Título", "Autor", "Año", "ISBN", "Disponible"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No editable
            }
        };

        for (Libro libro : libros) {
            Object[] fila = {
                libro.getCategoria(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getAnioPublicacion(),
                libro.getIsbn(),
                libro.isDisponible() ? "Sí" : "No"
            };
            modelo.addRow(fila);
        }

        tblResultados.setModel(modelo);
    }
}
