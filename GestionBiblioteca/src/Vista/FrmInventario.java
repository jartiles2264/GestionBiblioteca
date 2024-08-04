package Vista;

import Modelo.Inventario;
import Modelo.Libro;
import Modelo.LibroPedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FrmInventario extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable tableInventario;
    private Inventario inventario;
    private SimpleDateFormat dateFormat;
    private JButton btnEliminar;

    public FrmInventario(Inventario inventario) {
        this.inventario = inventario;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Formato de la fecha
        setTitle("Inventario de Libros");
        setBounds(100, 100, 900, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear la tabla y el panel de desplazamiento
        tableInventario = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableInventario);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Crear el panel para el botón de eliminar y configurar el diseño
        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Alineación del botón a la derecha

        // Crear el botón de eliminar y agregarlo al panel
        btnEliminar = new JButton("Eliminar Libro");
        btnEliminar.setPreferredSize(new Dimension(120, 30)); // Establecer el tamaño del botón
        panelBoton.add(btnEliminar);

        // Añadir el panel del botón al borde inferior del panel principal
        getContentPane().add(panelBoton, BorderLayout.SOUTH);

        // Mostrar el inventario
        mostrarInventario();

        // Agregar el manejador de eventos para el botón de eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarLibro();
            }
        });
    }

    private void mostrarInventario() {
        List<LibroPedido> pedidos = inventario.obtenerPedidos();

        String[] columnNames = {"Categoría", "Título", "Autor", "Año", "ISBN", "Fecha de Pedido"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (LibroPedido libroPedido : pedidos) {
            Libro libro = libroPedido.getLibro();
            Date fechaPedido = libroPedido.getFechaPedido();
            Object[] row = {
                libro.getCategoria(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getAnioPublicacion(),
                libro.getIsbn(),
                fechaPedido != null ? dateFormat.format(fechaPedido) : "N/A"
            };
            model.addRow(row);
        }
        
        tableInventario.setModel(model);
    }

    private void eliminarLibro() {
        int selectedRow = tableInventario.getSelectedRow();
        if (selectedRow >= 0) {
            String isbn = (String) tableInventario.getValueAt(selectedRow, 4); // La columna ISBN es la 4
            Libro libro = inventario.buscarLibroPorIsbn(isbn);

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
                    inventario.eliminarLibro(isbn);
                    mostrarInventario(); // Actualizar la tabla después de eliminar
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un libro con el ISBN seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un libro para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
