package Modelo;

import java.util.ArrayList;
import java.util.List;

public class GestorAdministradores {
    private List<Administrador> administradores;

    public GestorAdministradores() {
        administradores = new ArrayList<>();
        administradores.add(new Administrador("Juan Perez", "juanperez@gmail.com", "12345"));
    }

    public boolean validarInicioSesion(String correo, String contrasena) {
        for (Administrador admin : administradores) {
            if (admin.getCorreo().equals(correo) && admin.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public Administrador obtenerAdministradorPorCorreo(String correo) {
        for (Administrador admin : administradores) {
            if (admin.getCorreo().equals(correo)) {
                return admin;
            }
        }
        return null;
    }
}
