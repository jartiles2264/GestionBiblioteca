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
     estudiantes.add(new Estudiante("CAMPANIA INTRIAGO ANDREA VALENTINA", "acampana4018@uegds.edu.ec", "4018-abcd", "TERCERO DE BACHILLERATO PARALELO A"));
     estudiantes.add(new Estudiante("MARTINEZ ZAMBRANO ANTHONY SAMUEL", "amartinez2374@uegds.edu.ec", "2374-abcd", "PRIMERO DE BACHILLERATO PARALELO B"));
     estudiantes.add(new Estudiante("MOREIRA MACIAS ARIEL GONZALO", "amoreira3874@uegds.edu.ec", "3874-abcd", "PRIMERO DE BACHILLERATO PARALELO A"));
     estudiantes.add(new Estudiante("ZAMBRANO MACIAS DANIEL FARID", "dzambrano8529@uegds.edu.ec", "8529-abcd", "NOVENO PARALELO B"));
     estudiantes.add(new Estudiante("DUENIAS MENDOZA JAIR ANTONIO", "jduenas4672@uegds.edu.ec", "4672-abcd", "SEGUNDO DE BACHILLERATO PARALELO B"));
     estudiantes.add(new Estudiante("ARTILES VEITIA JAVIER ANTONIO", "jartiles2264@uegds.edu.ec", "2264-abcd", "TERCERO DE BACHILLERATO PARALELO A"));
     estudiantes.add(new Estudiante("LOPEZ VELEZ JOHN STEVEN", "jlopez8751@uegds.edu.ec", "8751-abcd", "OCTAVO PARALELO B"));
     estudiantes.add(new Estudiante("MENDOZA MENDOZA MARLON ADRIAN", "mmendoza3506@uegds.edu.ec", "3506-abcd", "DECIMO PARALELO B"));
     estudiantes.add(new Estudiante("COVENIA ZAMBRANO MATEO RUBEN", "mcovena1485@uegds.edu.ec", "1485-abcd", "DECIMO PARALELO A"));
     estudiantes.add(new Estudiante("HERRERA ZAMBRANO RENE YASSER", "rherrera0170@uegds.edu.ec", "0170-abcd", "NOVENO PARALELO B"));
     estudiantes.add(new Estudiante("VEGA MENDOZA SAMUEL ANDRES", "svega9094@uegds.edu.ec", "9094-abcd", "PRIMERO DE BACHILLERATO PARALELO A"));
     estudiantes.add(new Estudiante("CANTOS CLAVIJO YHONY SAUL", "ycantos5260@uegds.edu.ec", "5260-abcd", "SEGUNDO DE BACHILLERATO PARALELO A"));
     estudiantes.add(new Estudiante("SENI BARCIA YAKOV DAVID", "yseni1485@uegds.edu.ec", "1485-abcd", "SEGUNDO DE BACHILLERATO PARALELO B"));
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
