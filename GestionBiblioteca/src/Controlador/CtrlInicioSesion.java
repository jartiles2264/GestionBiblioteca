package Controlador;

import Modelo.Administrador;
import Modelo.Estudiante;
import Modelo.GestorEstudiantes;
import Modelo.GestorAdministradores;
import Modelo.GestorLibros;
import Modelo.Inventario;
import Vista.FrmVistaPrincipal;
import Vista.JOptionLogin;

import javax.swing.*;

public class CtrlInicioSesion {
    private GestorEstudiantes gestorEstudiantes;
    private GestorAdministradores gestorAdministradores;
    private GestorLibros gestorLibros;
    private Inventario inventario;
    private JOptionLogin vistaLogin;

    // Constructor para estudiantes
    public CtrlInicioSesion(GestorEstudiantes gestorEstudiantes, GestorAdministradores gestorAdministradores, GestorLibros gestorLibros, Inventario inventario, JOptionLogin vistaLogin) {
        this.gestorEstudiantes = gestorEstudiantes;
        this.gestorAdministradores = gestorAdministradores;
        this.gestorLibros = gestorLibros;
        this.inventario = inventario;
        this.vistaLogin = vistaLogin;
    }

    // Constructor para administradores
    public CtrlInicioSesion(GestorEstudiantes gestorEstudiantes, GestorAdministradores gestorAdministradores, GestorLibros gestorLibros, JOptionLogin vistaLogin) {
        this.gestorEstudiantes = gestorEstudiantes;
        this.gestorAdministradores = gestorAdministradores;
        this.gestorLibros = gestorLibros;
        this.vistaLogin = vistaLogin;
        this.inventario = null; // No se usa para administradores
    }

    public void iniciarSesion(String correo, String clave, String cargo) {
        if (vistaLogin == null) {
            JOptionPane.showMessageDialog(null, "Vista de inicio de sesión no inicializada.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cargo.equals("Estudiante")) {
            if (gestorEstudiantes != null && gestorEstudiantes.validarInicioSesion(correo, clave)) {
                Estudiante estudiante = gestorEstudiantes.obtenerEstudiantePorCorreo(correo);
                Inventario inventario = new Inventario(estudiante.getId()); // Crear inventario para el estudiante
                vistaLogin.dispose();
                JOptionPane.showMessageDialog(null, "Bienvenido al sistema, " + estudiante.getNombre(), "Ingreso Exitoso", JOptionPane.INFORMATION_MESSAGE);
                FrmVistaPrincipal vistaPrincipal = new FrmVistaPrincipal(gestorLibros, inventario, false); // false para estudiante
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
                FrmVistaPrincipal vistaPrincipal = new FrmVistaPrincipal(gestorLibros, null, true); // null para inventario de admin
                vistaPrincipal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Error usuario o contraseña incorrectas", "ERROR", JOptionPane.ERROR_MESSAGE);
                vistaLogin.limpiarCampos();
            }
        }
    }
}
