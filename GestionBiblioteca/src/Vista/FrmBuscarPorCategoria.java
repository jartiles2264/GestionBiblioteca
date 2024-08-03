package Vista;

import Modelo.GestorLibros;
import Modelo.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmBuscarPorCategoria extends JFrame {
    private static final long serialVersionUID = 1L;
    private GestorLibros gestorLibros;
    private JComboBox<String> cmbCategoria;
    private JTable tblResultados;

    public FrmBuscarPorCategoria(GestorLibros gestorLibros) {
        this.gestorLibros = gestorLibros;

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

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarLibros();
            }
        });
        panel.add(btnBuscar, BorderLayout.SOUTH);

        tblResultados = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblResultados);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    private void buscarLibros() {
        String categoria = (String) cmbCategoria.getSelectedItem();
        List<Libro> libros = gestorLibros.buscarLibrosPorCategoria(categoria);

        String[] columnas = {"Categoría", "Título", "Autor", "Año", "Código", "Disponible"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

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
