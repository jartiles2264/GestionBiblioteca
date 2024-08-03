package Modelo;

public class Estudiante {
    private String nombre;
    private String correo;
    private String contrasena;
    private String curso;

    public Estudiante(String nombre, String correo, String contrasena, String curso) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getCurso() {
        return curso;
    }
}
