package Controlador;

import Modelo.GestorEstudiantes;
import Modelo.GestorAdministradores;
import Vista.JOptionLogin;

public class Main {
    public static void main(String[] args) {
        // Inicializar gestores
        GestorEstudiantes gestorEstudiantes = new GestorEstudiantes();
        GestorAdministradores gestorAdministradores = new GestorAdministradores();
        
        // Crear instancia del login
        JOptionLogin login = new JOptionLogin();
        
        // Crear el controlador y pasar la vista de login
        CtrlInicioSesion controlador = new CtrlInicioSesion(gestorEstudiantes, gestorAdministradores, login);
        
        // Configurar el controlador en la vista de inicio de sesión
        login.setControlador(controlador);
        
        // Mostrar la ventana de inicio de sesión
        login.setVisible(true);
    }
}
