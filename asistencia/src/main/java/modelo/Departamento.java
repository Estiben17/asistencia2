package modelo;

import java.util.ArrayList;

public class Departamento {

    private String nombre;
    private static Departamento instancia = null;

    private ArrayList<Asignatura> asignaturas;
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Asistencia> asistencias;

    private Departamento() {
        this.nombre = "Ingeniería de Sistemas y Computación";
        this.asignaturas = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
        this.asistencias = new ArrayList<>();
    }

    public static Departamento singleton() {
        if (instancia == null) {
            instancia = new Departamento();
        }
        return instancia;
    }

    // ===========================
    //         GETTERS/SETTERS
    // ===========================

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // ===========================
    //       ESTUDIANTES
    // ===========================

    public boolean registrarEstudiante(String nombre, String tipoDoc, String numDoc) {
        if (consultarEstudiante(tipoDoc, numDoc) != null) return false;
        estudiantes.add(new Estudiante(nombre, tipoDoc, numDoc));
        return true;
    }

    public Estudiante consultarEstudiante(String tipoDocumento, String numeroDocumento) {
        for (Estudiante e : estudiantes) {
            if (e.getTipoDocumento().equalsIgnoreCase(tipoDocumento) &&
                    e.getNumeroDocumento().equalsIgnoreCase(numeroDocumento)) {
                return e;
            }
        }
        return null;
    }

    public boolean modificarEstudiante(String tipoDocumento, String numeroDocumento,
                                       String nuevoNumeroDocumento, String nuevoNombre) {
        Estudiante estudiante = consultarEstudiante(tipoDocumento, numeroDocumento);
        if (estudiante != null) {
            estudiante.setNumeroDocumento(nuevoNumeroDocumento);
            estudiante.setNombreCompleto(nuevoNombre);
            return true;
        }
        return false;
    }

    // ===========================
    //         ASIGNATURAS
    // ===========================

    public boolean agregarAsignatura(String nombre, int creditos, String codigo, String grupo, String semestre) {
        if (buscarAsignatura(codigo, grupo, semestre) != null) return false;
        asignaturas.add(new Asignatura(codigo, grupo, semestre, nombre, creditos));
        return true;
    }

    public Asignatura consultarAsignaturaPorCodigo(String codigo) {
        for (Asignatura a : asignaturas) {
            if (a.getCodigo().equalsIgnoreCase(codigo)) {
                return a;
            }
        }
        return null;
    }

    public boolean modificarAsignatura(String codigo, String grupo, String semestre, String nuevoNombre, int nuevosCreditos) {
        Asignatura asignatura = buscarAsignatura(codigo, grupo, semestre);
        if (asignatura != null) {
            asignatura.setNombre(nuevoNombre);
            asignatura.setCreditos(nuevosCreditos);
            return true;
        }
        return false;
    }

    public Asignatura buscarAsignatura(String codigo, String grupo, String semestre) {
        for (Asignatura a : asignaturas) {
            if (a.getCodigo().equalsIgnoreCase(codigo) &&
                    a.getGrupo().equalsIgnoreCase(grupo) &&
                    a.getSemestre().equalsIgnoreCase(semestre)) {
                return a;
            }
        }
        return null;
    }

    // ===========================
    //     REGISTRO EN ASIGNATURA
    // ===========================

    public boolean registrarEstudianteEnAsignatura(String tipoDoc, String numDoc, String codigo, String grupo, String semestre) {
        Estudiante estudiante = consultarEstudiante(tipoDoc, numDoc);
        Asignatura asignatura = buscarAsignatura(codigo, grupo, semestre);
        if (estudiante != null && asignatura != null) {
            return asignatura.registrarEstudiante(estudiante);
        }
        return false;
    }

    public ArrayList<Estudiante> consultarEstudiantesEnAsignatura(String codigo, String grupo) {
        for (Asignatura a : asignaturas) {
            if (a.getCodigo().equalsIgnoreCase(codigo) &&
                    a.getGrupo().equalsIgnoreCase(grupo)) {
                return a.getEstudiantes();
            }
        }
        return new ArrayList<>();
    }

    // ===========================
    //         ASISTENCIA
    // ===========================

    public boolean registrarAsistencia(String codigo, String grupo, String semestre, String fechaHora) {
        Asignatura asignatura = buscarAsignatura(codigo, grupo, semestre);
        if (asignatura == null || asignatura.getEstudiantes().isEmpty()) {
            return false;
        }

        // Verificar si ya existe una asistencia para esa fecha y asignatura
        for (Asistencia a : asistencias) {
            if (a.getCodigoAsignatura().equals(codigo) &&
                    a.getGrupo().equals(grupo) &&
                    a.getSemestre().equals(semestre) &&
                    a.getFecha().equals(fechaHora)) {
                return false; // Ya existe
            }
        }

        // Crear nueva asistencia con todos los estudiantes marcados como ausentes
        Asistencia nuevaAsistencia = new Asistencia(codigo, grupo, semestre, fechaHora);

        for (Estudiante e : asignatura.getEstudiantes()) {
            nuevaAsistencia.registrarEstudiante(e.getNumeroDocumento(), Asistencia.Estado.AUSENTE);
        }

        asistencias.add(nuevaAsistencia);
        return true;
    }

    public Asistencia iniciarRegistroAsistencia(String codigo, String grupo, String semestre, String fecha, String hora) {
        Asignatura asignatura = buscarAsignatura(codigo, grupo, semestre);
        if (asignatura == null || asignatura.getEstudiantes().isEmpty()) return null;

        String fechaHora = fecha + " " + hora;

        // Buscar si ya existe una asistencia para esta fecha/hora
        for (Asistencia a : asistencias) {
            if (a.getCodigoAsignatura().equals(codigo) &&
                    a.getGrupo().equals(grupo) &&
                    a.getSemestre().equals(semestre) &&
                    a.getFecha().equals(fechaHora)) {
                return a; // Devolvemos la asistencia existente para modificarla
            }
        }

        // Si no existe, creamos una nueva
        Asistencia asistencia = new Asistencia(codigo, grupo, semestre, fechaHora);

        for (Estudiante e : asignatura.getEstudiantes()) {
            asistencia.registrarEstudiante(e.getNumeroDocumento(), Asistencia.Estado.AUSENTE);
        }

        return asistencia;
    }

    public boolean guardarAsistencias(Asistencia asistencia) {
        // Verificar si ya existe una asistencia para esta fecha y asignatura
        for (int i = 0; i < asistencias.size(); i++) {
            Asistencia a = asistencias.get(i);
            if (a.getCodigoAsignatura().equals(asistencia.getCodigoAsignatura()) &&
                    a.getGrupo().equals(asistencia.getGrupo()) &&
                    a.getSemestre().equals(asistencia.getSemestre()) &&
                    a.getFecha().equals(asistencia.getFecha())) {
                // Si ya existe, la reemplazamos
                asistencias.set(i, asistencia);
                return true;
            }
        }

        // Si no existe, la agregamos
        return asistencias.add(asistencia);
    }

    public Asistencia buscarAsistencia(String codigo, String grupo, String semestre, String fechaHora) {
        for (Asistencia a : asistencias) {
            if (a.getCodigoAsignatura().equals(codigo) &&
                    a.getGrupo().equals(grupo) &&
                    a.getSemestre().equals(semestre) &&
                    a.getFecha().equals(fechaHora)) {
                return a;
            }
        }
        return null;
    }

    public ArrayList<Asistencia> getAsistencias() {
        return asistencias;
    }

    public ArrayList<Asistencia> getAsistenciasPorAsignatura(String codigo, String grupo, String semestre) {
        ArrayList<Asistencia> resultado = new ArrayList<>();
        for (Asistencia a : asistencias) {
            if (a.getCodigoAsignatura().equals(codigo) &&
                    a.getGrupo().equals(grupo) &&
                    a.getSemestre().equals(semestre)) {
                resultado.add(a);
            }
        }
        return resultado;
    }
}