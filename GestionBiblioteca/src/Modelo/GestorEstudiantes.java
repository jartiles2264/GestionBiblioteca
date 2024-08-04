package Modelo;

import java.util.ArrayList;
import java.util.List;

public class GestorEstudiantes {
    private List<Estudiante> estudiantes;

    public GestorEstudiantes() {
        this.estudiantes = new ArrayList<>();
        cargarDatosEstudiantes();
    }

    private void cargarDatosEstudiantes() {
        estudiantes.add(new Estudiante("E01", "CAMPANIA INTRIAGO ANDREA VALENTINA", "acampana4018@uegds.edu.ec", "4018-abcd", "TERCERO DE BACHILLERATO PARALELO A"));
        estudiantes.add(new Estudiante("E02", "MARTINEZ ZAMBRANO ANTHONY SAMUEL", "amartinez2374@uegds.edu.ec", "2374-abcd", "PRIMERO DE BACHILLERATO PARALELO B"));
        estudiantes.add(new Estudiante("E03", "MOREIRA MACIAS ARIEL GONZALO", "amoreira3874@uegds.edu.ec", "3874-abcd", "PRIMERO DE BACHILLERATO PARALELO A"));
        estudiantes.add(new Estudiante("E04", "ZAMBRANO MACIAS DANIEL FARID", "dzambrano8529@uegds.edu.ec", "8529-abcd", "NOVENO PARALELO B"));
        estudiantes.add(new Estudiante("E05", "DUENIAS MENDOZA JAIR ANTONIO", "jduenas4672@uegds.edu.ec", "4672-abcd", "SEGUNDO DE BACHILLERATO PARALELO B"));
        estudiantes.add(new Estudiante("E06", "ARTILES VEITIA JAVIER ANTONIO", "jartiles2264@uegds.edu.ec", "2264-abcd", "TERCERO DE BACHILLERATO PARALELO A"));
        estudiantes.add(new Estudiante("E07", "LOPEZ VELEZ JOHN STEVEN", "jlopez8751@uegds.edu.ec", "8751-abcd", "OCTAVO PARALELO B"));
        estudiantes.add(new Estudiante("E08", "MENDOZA MENDOZA MARLON ADRIAN", "mmendoza3506@uegds.edu.ec", "3506-abcd", "DECIMO PARALELO B"));
        estudiantes.add(new Estudiante("E09", "COVENIA ZAMBRANO MATEO RUBEN", "mcovena1485@uegds.edu.ec", "1485-abcd", "DECIMO PARALELO A"));
        estudiantes.add(new Estudiante("E10", "HERRERA ZAMBRANO RENE YASSER", "rherrera0170@uegds.edu.ec", "0170-abcd", "NOVENO PARALELO B"));
        estudiantes.add(new Estudiante("E11", "VEGA MENDOZA SAMUEL ANDRES", "svega9094@uegds.edu.ec", "9094-abcd", "PRIMERO DE BACHILLERATO PARALELO A"));
        estudiantes.add(new Estudiante("E12", "CANTOS CLAVIJO YHONY SAUL", "ycantos5260@uegds.edu.ec", "5260-abcd", "SEGUNDO DE BACHILLERATO PARALELO A"));
        estudiantes.add(new Estudiante("E13", "SENI BARCIA YAKOV DAVID", "yseni1485@uegds.edu.ec", "1485-abcd", "SEGUNDO DE BACHILLERATO PARALELO B"));
    }

    public boolean validarInicioSesion(String correo, String contrasena) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCorreo().equals(correo) && estudiante.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public Estudiante obtenerEstudiantePorCorreo(String correo) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCorreo().equals(correo)) {
                return estudiante;
            }
        }
        return null;
    }
}
