package Vista;

import Controlador.CtrlInicioSesion;
import Modelo.GestorEstudiantes;
import Modelo.GestorAdministradores;
import Modelo.GestorLibros;
import Modelo.Inventario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmVistaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    private GestorLibros gestorLibros;
    private Inventario inventario;
    private boolean esAdmin;

    public FrmVistaPrincipal(GestorLibros gestorLibros, Inventario inventario, boolean esAdmin) {
        this.gestorLibros = gestorLibros;
        this.inventario = inventario;
        this.esAdmin = esAdmin;
        inicializar();
    }

    private void inicializar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 721, 572);
        setTitle("Vista Principal");

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnOpciones = new JMenu("Opciones");
        mnOpciones.setFont(new Font("Segoe UI", Font.BOLD, 18));
        menuBar.add(mnOpciones);

        JMenuItem menuItemCerrarSesion = new JMenuItem("Cerrar Sesión");
        menuItemCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(
                        null, "¿Está seguro que desea cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    dispose();

                    GestorEstudiantes gestorEstudiantes = new GestorEstudiantes();
                    GestorAdministradores gestorAdministradores = new GestorAdministradores();

                    JOptionLogin vistaLogin = new JOptionLogin();

                    // Crear el controlador con todos los parámetros necesarios
                    CtrlInicioSesion controlador = new CtrlInicioSesion(
                        gestorEstudiantes, 
                        gestorAdministradores, 
                        gestorLibros, 
                        inventario, 
                        vistaLogin
                    );

                    vistaLogin.setControlador(controlador);

                    vistaLogin.setVisible(true);
                }
            }
        });
        mnOpciones.add(menuItemCerrarSesion);

        JMenu mnBiblioteca = new JMenu("Biblioteca");
        mnBiblioteca.setFont(new Font("Segoe UI", Font.BOLD, 18));
        menuBar.add(mnBiblioteca);

        JMenuItem menuItemMostrarLibros = new JMenuItem("Mostrar Todos los Libros");
        menuItemMostrarLibros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmMostrarLibros frmMostrarLibros = new FrmMostrarLibros(gestorLibros, inventario);
                frmMostrarLibros.setVisible(true);
            }
        });
        mnBiblioteca.add(menuItemMostrarLibros);

        JMenuItem menuItemBuscarPorCategoria = new JMenuItem("Buscar por Categoría");
        menuItemBuscarPorCategoria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmBuscarPorCategoria frmBuscarPorCategoria = new FrmBuscarPorCategoria(gestorLibros, inventario);
                frmBuscarPorCategoria.setVisible(true);
            }
        });
        mnBiblioteca.add(menuItemBuscarPorCategoria);

        // Condicional para mostrar el menú de Inventario solo para estudiantes
        if (!esAdmin) {
            JMenu mnInventario = new JMenu("Inventario");
            mnInventario.setFont(new Font("Segoe UI", Font.BOLD, 18));
            menuBar.add(mnInventario);

            JMenuItem menuItemVerInventario = new JMenuItem("Ver Inventario");
            menuItemVerInventario.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    FrmInventario frmInventario = new FrmInventario(inventario);
                    frmInventario.setVisible(true);
                }
            });
            mnInventario.add(menuItemVerInventario);
        }

        // Menú de gestión de libros solo para administradores
        if (esAdmin) {
            JMenu mnGestionarLibros = new JMenu("Gestionar Libros");
            mnGestionarLibros.setFont(new Font("Segoe UI", Font.BOLD, 18));
            menuBar.add(mnGestionarLibros);

            JMenuItem menuItemAgregarLibro = new JMenuItem("Agregar Libro");
            menuItemAgregarLibro.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    FrmAgregarLibro frmAgregarLibro = new FrmAgregarLibro(gestorLibros);
                    frmAgregarLibro.setVisible(true);
                }
            });
            mnGestionarLibros.add(menuItemAgregarLibro);

            JMenuItem menuItemEliminarLibro = new JMenuItem("Eliminar Libro");
            menuItemEliminarLibro.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    FrmEliminarLibro frmEliminarLibro = new FrmEliminarLibro(gestorLibros);
                    frmEliminarLibro.setVisible(true);
                }
            });
            mnGestionarLibros.add(menuItemEliminarLibro);
        }

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
    }
}
