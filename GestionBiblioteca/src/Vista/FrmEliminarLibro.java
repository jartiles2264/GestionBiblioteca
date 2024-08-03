package Vista;

import Modelo.GestorLibros;
import Modelo.Libro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmEliminarLibro extends JFrame {
    private static final long serialVersionUID = 1L;
    private GestorLibros gestorLibros;
    private JComboBox<String> cboIsbn;

    public FrmEliminarLibro(GestorLibros gestorLibros) {
        this.gestorLibros = gestorLibros;
        inicializar();
    }

    private void inicializar() {
        setTitle("Eliminar Libro");
        setBounds(100, 100, 400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel lblIsbn = new JLabel("ISBN del Libro:");
        cboIsbn = new JComboBox<>();
        JButton btnEliminar = new JButton("Eliminar Libro");

        panel.add(lblIsbn);
        panel.add(cboIsbn);
        panel.add(new JLabel());  // Placeholder
        panel.add(btnEliminar);

        getContentPane().add(panel, BorderLayout.CENTER);

        // Cargar los ISBN en el JComboBox
        cargarIsbns();

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarLibro();
            }
        });

        cboIsbn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Puedes agregar una acción aquí si necesitas actualizar algún detalle
            }
        });
    }

    private void cargarIsbns() {
        List<Libro> libros = gestorLibros.obtenerTodosLosLibros();
        cboIsbn.removeAllItems();  // Asegurarse de limpiar elementos anteriores
        for (Libro libro : libros) {
            cboIsbn.addItem(libro.getIsbn());
        }
    }

    private void eliminarLibro() {
        String isbn = (String) cboIsbn.getSelectedItem();
        if (isbn == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un libro.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Libro libro = gestorLibros.buscarLibroPorIsbn(isbn);
        if (libro != null) {
            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de que desea eliminar este libro?\n\n" +
                            "Título: " + libro.getTitulo() + "\n" +
                            "Autor: " + libro.getAutor() + "\n" +
                            "Año de Publicación: " + libro.getAnioPublicacion() + "\n" +
                            "Categoría: " + libro.getCategoria() + "\n" +
                            "ISBN: " + libro.getIsbn() + "\n",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                gestorLibros.eliminarLibro(isbn);
                JOptionPane.showMessageDialog(this, "Libro eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cboIsbn.removeItem(isbn);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró un libro con el ISBN seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
