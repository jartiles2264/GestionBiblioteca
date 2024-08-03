package Vista;

import Controlador.CtrlInicioSesion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JOptionLogin extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtEmail;
    private JPasswordField JPassContrasena;
    private String cargo;
    private CtrlInicioSesion controlador;

    public JOptionLogin(CtrlInicioSesion controlador) {
        this.controlador = controlador;
        initialize();
    }

    public JOptionLogin() {
        initialize();
    }

    private void initialize() {
        setTitle("Ariel y su Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 654, 252);

        int eleccion = JOptionPane.showOptionDialog(
                null,
                "Seleccione el sistema al cual desea ingresar.",
                "Opciones",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Estudiantil", "Docente/Administrativo"},
                "Estudiantil"
        );

        if (eleccion == 0) {
            cargo = "Estudiante";
        } else if (eleccion == 1) {
            cargo = "ADMIN";
        } else {
            System.exit(0);
        }

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo2 = new JLabel("Inicio de sesión");
        lblTitulo2.setBounds(138, 22, 202, 29);
        lblTitulo2.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo2.setFont(new Font("Dialog", Font.PLAIN, 20));
        contentPane.add(lblTitulo2);

        JLabel lblTitulo_1 = new JLabel(cargo);
        lblTitulo_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo_1.setFont(new Font("Dialog", Font.BOLD, 20));
        lblTitulo_1.setBounds(293, 22, 133, 29);
        contentPane.add(lblTitulo_1);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Uni Neue Regular", Font.PLAIN, 17));
        txtEmail.setBounds(202, 76, 218, 29);
        contentPane.add(txtEmail);
        txtEmail.setColumns(10);

        JLabel lblEmail = new JLabel("Correo:");
        lblEmail.setBounds(59, 78, 78, 22);
        lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
        lblEmail.setFont(new Font("Uni Neue Regular", Font.PLAIN, 20));
        contentPane.add(lblEmail);

        JPassContrasena = new JPasswordField();
        JPassContrasena.setBounds(202, 127, 218, 29);
        contentPane.add(JPassContrasena);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(59, 134, 133, 22);
        lblContrasena.setHorizontalAlignment(SwingConstants.LEFT);
        lblContrasena.setFont(new Font("Uni Neue Regular", Font.PLAIN, 20));
        contentPane.add(lblContrasena);

        JButton btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBounds(469, 89, 139, 44);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (controlador != null) {
                    String correo = txtEmail.getText();
                    char[] clave = JPassContrasena.getPassword();
                    String claveFinal = new String(clave);
                    controlador.iniciarSesion(correo, claveFinal, cargo);
                } else {
                    JOptionPane.showMessageDialog(null, "Controlador no inicializado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setBackground(new Color(255, 255, 255));
        btnLogin.setFont(new Font("Uni Neue Regular", Font.BOLD, 14));
        contentPane.add(btnLogin);
    }

    public void setControlador(CtrlInicioSesion controlador) {
        this.controlador = controlador;
    }

    public void limpiarCampos() {
        txtEmail.setText("");
        JPassContrasena.setText("");
        txtEmail.requestFocus();
    }
}
