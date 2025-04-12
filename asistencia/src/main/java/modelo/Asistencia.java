package modelo;

import java.util.HashMap;

public class Asistencia {

    public enum Estado {
        PRESENTE,
        AUSENTE,
        RETARDO  // Este es el valor que se está utilizando en lugar de PRESENTE_CON_RETARDO
    }

    private String codigoAsignatura;
    private String grupo;
    private String semestre;
    private String fecha;
    private HashMap<String, Estado> registroAsistencia;
    // Clave: número de documento del estudiante

    public Asistencia(String codigoAsignatura, String grupo, String semestre, String fecha) {
        this.codigoAsignatura = codigoAsignatura;
        this.grupo = grupo;
        this.semestre = semestre;
        this.fecha = fecha;
        this.registroAsistencia = new HashMap<>();
    }

    // Registrar estado para un estudiante
    public void registrarEstudiante(String numeroDocumento, Estado estado) {
        registroAsistencia.put(numeroDocumento, estado);
    }


    // Consultar estado de un estudiante
    public Estado obtenerEstado(String numeroDocumento) {
        return registroAsistencia.getOrDefault(numeroDocumento, null);
    }

    public String getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getSemestre() {
        return semestre;
    }

    public String getFecha() {
        return fecha;
    }

    public HashMap<String, Estado> getRegistroAsistencia() {
        return registroAsistencia;
    }
}
