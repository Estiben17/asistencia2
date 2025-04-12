package Controlador;

import modelo.Asignatura;
import modelo.Asistencia;
import modelo.Departamento;
import modelo.Estudiante;
import java.util.ArrayList;


public class Controlador {

    private Departamento departamento = null;

    public Controlador() {
        departamento = Departamento.singleton();

        // Mostrar el nombre del departamento por defecto al iniciar
        System.out.println("Departamento creado por defecto: " + departamento.getNombre());
    }

    // CRUD DE DEPARTAMENTO

    public void ModificarNombreDepartamento(String nombreActual, String nuevoNombre){
        if (departamento.getNombre().equalsIgnoreCase(nombreActual)) {
            departamento.setNombre(nuevoNombre);
            System.out.println("Nombre del departamento modificado con éxito.");
        } else {
            System.out.println("El nombre del departamento no coincide con el existente.");
        }
    }

    public String ConsultarDepartamento() {
        return departamento.getNombre();
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }


    public boolean registrarEstudianteDepartamento(String nombre, String tipoDoc, String numDoc) {
        return departamento.registrarEstudiante(nombre, tipoDoc, numDoc);
    }

    public Estudiante consultarEstudianteDepartamento(String tipoDoc, String numDoc) {
        return departamento.consultarEstudiante(tipoDoc, numDoc);
    }

    public boolean modificarEstudianteDepartamento(String tipoDoc, String numDoc, String nuevoNumDoc, String nuevoNombre) {
        return departamento.modificarEstudiante(tipoDoc, numDoc, nuevoNumDoc, nuevoNombre);
    }

    public boolean agregarAsignatura(String nombre, int creditos, String codigo, String grupo, String semestre){
        return departamento.agregarAsignatura(nombre, creditos, codigo, grupo, semestre);
    }

    public Asignatura consultarAsignaturaPorCodigo(String codigo) {
        return departamento.consultarAsignaturaPorCodigo(codigo);
    }

    public boolean modificarAsignatura(String codigo, String grupo, String semestre, String nuevoNombre, int nuevosCreditos) {
        return departamento.modificarAsignatura(codigo, grupo, semestre, nuevoNombre, nuevosCreditos);
    }

    public boolean registrarEstudianteEnAsignatura(String tipoDoc, String numDoc, String codigo, String grupo, String semestre) {
        return departamento.registrarEstudianteEnAsignatura(tipoDoc, numDoc, codigo, grupo, semestre);
    }

    public ArrayList<Estudiante> consultarEstudiantesAsignatura(String codigo, String grupo) {
        return departamento.consultarEstudiantesEnAsignatura(codigo, grupo);
    }

    public boolean crearListaAsistencia(String codigo, String grupo, String semestre, String fechaHora) {
        return departamento.registrarAsistencia(codigo, grupo, semestre, fechaHora);
    }

    public Asistencia iniciarRegistroAsistencia(String codigo, String grupo, String semestre, String fecha, String hora) {
        return departamento.iniciarRegistroAsistencia(codigo, grupo, semestre, fecha, hora);
    }

    public boolean guardarAsistencias(Asistencia asistencia) {
        return departamento.guardarAsistencias(asistencia);
    }

    public Asistencia buscarAsistencia(String codigo, String grupo, String semestre, String fechaHora) {
        return departamento.buscarAsistencia(codigo, grupo, semestre, fechaHora);
    }

    public ArrayList<Asistencia> listarAsistencias(String codigo, String grupo, String semestre) {
        return departamento.getAsistenciasPorAsignatura(codigo, grupo, semestre);
    }

    public boolean modificarAsistencia(String codigo, String grupo, String semestre, String fechaHora,
                                       String numDocEstudiante, Asistencia.Estado nuevoEstado) {
        Asistencia asistencia = departamento.buscarAsistencia(codigo, grupo, semestre, fechaHora);
        if (asistencia != null) {
            asistencia.registrarEstudiante(numDocEstudiante, nuevoEstado);
            return departamento.guardarAsistencias(asistencia);
        }
        return false;
    }
}