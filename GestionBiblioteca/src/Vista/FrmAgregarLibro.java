package Vista;

import Modelo.GestorLibros;
import Modelo.Libro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAgregarLibro extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAnio;
    private JTextField txtCodigo;
    private JComboBox<String> cmbCategoria;
    private GestorLibros gestorLibros;

    public FrmAgregarLibro(GestorLibros gestorLibros) {
        this.gestorLibros = gestorLibros;

        setTitle("Agregar Libro");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        getContentPane().add(panel, BorderLayout.CENTER);

        JLabel lblCategoria = new JLabel("Categoría:");
        panel.add(lblCategoria);

        cmbCategoria = new JComboBox<>(new String[]{"HISTORIA", "BIOLOGIA", "LENGUAJE", "MATEMATICAS", "COMPUTACION"});
        panel.add(cmbCategoria);

        JLabel lblTitulo = new JLabel("Título:");
        panel.add(lblTitulo);

        txtTitulo = new JTextField();
        panel.add(txtTitulo);

        JLabel lblAutor = new JLabel("Autor:");
        panel.add(lblAutor);

        txtAutor = new JTextField();
        panel.add(txtAutor);

        JLabel lblAnio = new JLabel("Año:");
        panel.add(lblAnio);

        txtAnio = new JTextField();
        panel.add(txtAnio);

        JLabel lblCodigo = new JLabel("Código:");
        panel.add(lblCodigo);

        txtCodigo = new JTextField();
        panel.add(txtCodigo);

        JLabel lblDisponible = new JLabel("Disponible:");
        panel.add(lblDisponible);

        JCheckBox chkDisponible = new JCheckBox("Sí");
        panel.add(chkDisponible);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarLibro(chkDisponible.isSelected());
            }
        });
        getContentPane().add(btnAgregar, BorderLayout.SOUTH);
    }

    private void agregarLibro(boolean disponible) {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        int anio = Integer.parseInt(txtAnio.getText());
        String isbn = txtCodigo.getText();
        String categoria = (String) cmbCategoria.getSelectedItem();

        if (gestorLibros.buscarLibroPorIsbn(isbn) != null) {
            JOptionPane.showMessageDialog(this, "El código del libro ya existe. Por favor, ingrese un código diferente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Libro nuevoLibro = new Libro(categoria, titulo, autor, anio, isbn, disponible);
        gestorLibros.agregarLibro(nuevoLibro);

        JOptionPane.showMessageDialog(this, "Libro agregado: " + titulo, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
