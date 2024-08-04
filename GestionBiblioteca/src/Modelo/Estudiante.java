package Modelo;

public class Estudiante {
    private String id;  // Identificador único
    private String nombre;
    private String correo;
    private String contrasena;
    private String curso;

    public Estudiante(String id, String nombre, String correo, String contrasena, String curso) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.curso = curso;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
