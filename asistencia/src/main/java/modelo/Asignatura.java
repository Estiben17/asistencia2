package modelo;

import java.util.ArrayList;

public class Asignatura {
    private String codigo;
    private String grupo;
    private String semestre;
    private String nombre;
    private int creditos;
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Asistencia> asistencias = new ArrayList<>();

    public Asignatura(String codigo, String grupo, String semestre, String nombre, int creditos) {
        this.codigo = codigo;
        this.grupo = grupo;
        this.semestre = semestre;
        this.nombre = nombre;
        this.creditos = creditos;
        this.estudiantes = new ArrayList<>();
    }

    public boolean registrarEstudiante(Estudiante estudiante) {
        for (Estudiante e : estudiantes) {
            if (e.getNumeroDocumento().equalsIgnoreCase(estudiante.getNumeroDocumento())
                    && e.getTipoDocumento().equalsIgnoreCase(estudiante.getTipoDocumento())) {
                return false;
            }
        }
        estudiantes.add(estudiante);
        return true;
    }

    public boolean registrarAsistencia(Estudiante estudiante, String fecha, boolean presente) {
        // Puedes implementar una lógica real aquí más adelante
        return true;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getSemestre() {
        return semestre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
}
