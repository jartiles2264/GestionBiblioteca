package Vista;

import Modelo.GestorLibros;
import Modelo.Libro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmEliminarLibro extends JFrame {
    private static final long serialVersionUID = 1L;
    private GestorLibros gestorLibros;
    private JTextField txtIsbn;
    private JTextArea txtResultado;

    public FrmEliminarLibro(GestorLibros gestorLibros) {
        this.gestorLibros = gestorLibros;
        inicializar();
    }

    private void inicializar() {
        setTitle("Eliminar Libro");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel lblIsbn = new JLabel("ISBN del Libro:");
        txtIsbn = new JTextField();
        JButton btnEliminar = new JButton("Eliminar Libro");
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);

        panel.add(lblIsbn);
        panel.add(txtIsbn);
        panel.add(new JLabel());
        panel.add(btnEliminar);

        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(txtResultado), BorderLayout.CENTER);

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarLibro();
            }
        });
    }

    private void eliminarLibro() {
        String isbn = txtIsbn.getText().trim();
        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el ISBN del libro.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Libro libro = gestorLibros.buscarLibroPorIsbn(isbn);
        if (libro != null) {
            gestorLibros.eliminarLibro(isbn);
            txtResultado.setText("Libro eliminado exitosamente:\n" + libro);
            txtIsbn.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró un libro con el ISBN proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
