package Controlador;

import Modelo.Estudiante;
import Modelo.GestorEstudiantes;
import Modelo.GestorAdministradores;
import Modelo.Administrador;
import Modelo.GestorLibros; // Asegúrate de importar el GestorLibros
import Vista.FrmVistaPrincipal;
import Vista.JOptionLogin;

import javax.swing.*;

public class CtrlInicioSesion {
    private GestorEstudiantes gestorEstudiantes;
    private GestorAdministradores gestorAdministradores;
    private JOptionLogin vistaLogin;
    private GestorLibros gestorLibros; // Añade el gestor de libros aquí

    public CtrlInicioSesion(GestorEstudiantes gestorEstudiantes, GestorAdministradores gestorAdministradores, JOptionLogin vistaLogin) {
        this.gestorEstudiantes = gestorEstudiantes;
        this.gestorAdministradores = gestorAdministradores;
        this.vistaLogin = vistaLogin;
        this.gestorLibros = new GestorLibros(); // Inicializa el GestorLibros

        // Establecer el controlador como el manejador de eventos para el botón de inicio de sesión
        if (this.vistaLogin != null) {
            this.vistaLogin.setControlador(this);
        } else {
            JOptionPane.showMessageDialog(null, "Vista de inicio de sesión no inicializada.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void iniciarSesion(String correo, String clave, String cargo) {
        if (vistaLogin == null) {
            JOptionPane.showMessageDialog(null, "Vista de inicio de sesión no inicializada.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cargo.equals("Estudiante")) {
            if (gestorEstudiantes != null && gestorEstudiantes.validarInicioSesion(correo, clave)) {
                Estudiante estudiante = gestorEstudiantes.obtenerEstudiantePorCorreo(correo);
                vistaLogin.dispose();
                JOptionPane.showMessageDialog(null, "Bienvenido al sistema, " + estudiante.getNombre(), "Ingreso Exitoso", JOptionPane.INFORMATION_MESSAGE);
                FrmVistaPrincipal vistaPrincipal = new FrmVistaPrincipal(gestorLibros, false); // false para estudiante
                vistaPrincipal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Error usuario o contraseña incorrectas", "ERROR", JOptionPane.ERROR_MESSAGE);
                vistaLogin.limpiarCampos();
            }
        } else if (cargo.equals("ADMIN")) {
            if (gestorAdministradores != null && gestorAdministradores.validarInicioSesion(correo, clave)) {
                Administrador admin = gestorAdministradores.obtenerAdministradorPorCorreo(correo);
                vistaLogin.dispose();
                JOptionPane.showMessageDialog(null, "Bienvenido al sistema, " + admin.getNombre(), "Ingreso Exitoso", JOptionPane.INFORMATION_MESSAGE);
                FrmVistaPrincipal vistaPrincipal = new FrmVistaPrincipal(gestorLibros, true); // true para administrador
                vistaPrincipal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Error usuario o contraseña incorrectas", "ERROR", JOptionPane.ERROR_MESSAGE);
                vistaLogin.limpiarCampos();
            }
        }
    }
}
