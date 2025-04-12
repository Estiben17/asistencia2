package Vista;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import Controlador.Controlador;
import modelo.Asistencia;
import modelo.Estudiante;
import modelo.Asignatura;
import modelo.Departamento;

public class main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Controlador VariableControl = new Controlador();
        String opcion = "";

        while (!opcion.equals("15")) {
            System.out.println("\nMenú principal");
            System.out.println("1) Consultar Departamento");
            System.out.println("2) Modificar Departamento");
            System.out.println("3) Registrar Estudiante en Departamento");
            System.out.println("4) Consultar Estudiante en Departamento");
            System.out.println("5) Modificar Estudiante al Departamento");
            System.out.println("6) Agregar Asignatura");
            System.out.println("7) Consultar Asignatura");
            System.out.println("8) Modificar Asignatura");
            System.out.println("9) Registrar Estudiante en la Asignatura");
            System.out.println("10) Consultar Estudiantes en Asignatura");
            System.out.println("11) Crear Lista de Asistencia Vacía");
            System.out.println("12) Llenar Asistencia");
            System.out.println("13) Modificar Asistencia");
            System.out.println("14) Listar Asistencia");
            System.out.println("15) Salir");
            System.out.print("Seleccione una opción: ");
            opcion = entrada.nextLine();

            if (opcion.equals("1")) {
                System.out.println("Nombre del departamento: " + VariableControl.ConsultarDepartamento());

            } else if (opcion.equals("2")) {
                System.out.print("Ingrese el nombre actual del departamento: ");
                String nombreActual = entrada.nextLine();

                if (VariableControl.ConsultarDepartamento().equalsIgnoreCase(nombreActual)) {
                    System.out.print("Ingrese el nuevo nombre para el departamento: ");
                    String nuevoNombre = entrada.nextLine();
                    VariableControl.ModificarNombreDepartamento(nombreActual, nuevoNombre);
                } else {
                    System.out.println("El nombre ingresado no coincide con el departamento actual.");
                }

            } else if (opcion.equals("3")) {
                // Registrar estudiante en departamento
                System.out.print("Ingrese el nombre completo del estudiante: ");
                String nombreEst = entrada.nextLine();

                System.out.print("Ingrese el tipo de documento (CC, TI, Pasaporte, CE): ");
                String tipoDoc = entrada.nextLine().toUpperCase();

                System.out.print("Ingrese el número del documento: ");
                String numDoc = entrada.nextLine();

                boolean registrado = VariableControl.registrarEstudianteDepartamento(nombreEst, tipoDoc, numDoc);
                if (registrado) {
                    System.out.println("Estudiante registrado exitosamente.");
                } else {
                    System.out.println("Ya existe un estudiante con ese número de documento.");
                }
            } else if (opcion.equals("4")) {
                // Consultar estudiante en departamento

                System.out.println("Consultar estudiante en el departamento:");
                System.out.print("Ingrese tipo de documento (CC, TI, CE, Pasaporte): ");
                String tipo = entrada.nextLine();
                System.out.print("Ingrese número de documento: ");
                String numero = entrada.nextLine();

                Estudiante estudiante = VariableControl.consultarEstudianteDepartamento(tipo, numero);
                if (estudiante != null) {
                    System.out.println("Estudiante encontrado:");
                    System.out.println("Nombre completo: " + estudiante.getNombreCompleto());
                    System.out.println("Tipo de documento: " + estudiante.getTipoDocumento());
                    System.out.println("Número de documento: " + estudiante.getNumeroDocumento());
                } else {
                    System.out.println("No se encontró ningún estudiante con esos datos.");
                }
            } else if (opcion.equals("5")) {
                // Modificar estudiante del departamento

                System.out.println("Modificar estudiante del departamento:");
                System.out.print("Ingrese tipo de documento (CC, TI, CE, Pasaporte): ");
                String tipo = entrada.nextLine();
                System.out.print("Ingrese número de documento actual: ");
                String numeroActual = entrada.nextLine();

                System.out.print("Ingrese nuevo número de documento: ");
                String nuevoNumero = entrada.nextLine();
                System.out.print("Ingrese nuevo nombre completo: ");
                String nuevoNombre = entrada.nextLine();

                boolean modificado = VariableControl.modificarEstudianteDepartamento(tipo, numeroActual, nuevoNumero, nuevoNombre);
                if (modificado) {
                    System.out.println("Estudiante modificado correctamente.");
                } else {
                    System.out.println("No se encontró un estudiante con los datos ingresados.");
                }

            } else if (opcion.equals("6")) {
                // Agregar asignatura

                System.out.println("=== AGREGAR ASIGNATURA ===");

                System.out.print("Nombre de la asignatura: ");
                String nombre = entrada.nextLine();

                System.out.print("Código: ");
                String codigo = entrada.nextLine();

                System.out.print("Créditos: ");
                int creditos = Integer.parseInt(entrada.nextLine());

                System.out.print("Sección o Grupo: ");
                String grupo = entrada.nextLine();

                System.out.print("Semestre: ");
                String semestre = entrada.nextLine();

                boolean resultado = VariableControl.agregarAsignatura(nombre, creditos, codigo, grupo, semestre);

                if (resultado) {
                    System.out.println(" Asignatura registrada exitosamente.");
                } else {
                    System.out.println(" Ocurrió un error al registrar la asignatura.");
                }

            } else if (opcion.equals("7")) {
                // Consultar asignatura

                System.out.print("Ingrese el código de la asignatura: ");
                String codigoConsulta = entrada.nextLine();
                Asignatura asignatura = VariableControl.consultarAsignaturaPorCodigo(codigoConsulta);

                if (asignatura != null) {
                    System.out.println("Nombre: " + asignatura.getNombre());
                    System.out.println("Créditos: " + asignatura.getCreditos());
                    System.out.println("Semestre: " + asignatura.getSemestre());
                    System.out.println("Sección o Grupo: " + asignatura.getGrupo());
                } else {
                    System.out.println("Asignatura no encontrada.");
                }
            } else if (opcion.equals("8")) {
                // Modificar asignatura

                System.out.print("Ingrese el código de la asignatura: ");
                String codigo = entrada.nextLine();

                System.out.print("Ingrese la sección/grupo: ");
                String grupo = entrada.nextLine();

                System.out.print("Ingrese el semestre: ");
                String semestre = entrada.nextLine();

                System.out.print("Ingrese el nuevo nombre de la asignatura: ");
                String nuevoNombre = entrada.nextLine();

                System.out.print("Ingrese la nueva cantidad de créditos: ");
                int nuevosCreditos = Integer.parseInt(entrada.nextLine());

                boolean modificado = VariableControl.modificarAsignatura(codigo, grupo, semestre, nuevoNombre, nuevosCreditos);
                if (modificado) {
                    System.out.println("Asignatura modificada exitosamente.");
                } else {
                    System.out.println("Asignatura no encontrada. No se pudo modificar.");
                }

            } else if (opcion.equals("9")) {
                // Registrar estudiante en asignatura

                System.out.print("Ingrese el tipo de documento del estudiante (CC, TI, CE, Pasaporte): ");
                String tipoDoc = entrada.nextLine();

                System.out.print("Ingrese el número de documento del estudiante: ");
                String numDoc = entrada.nextLine();

                System.out.print("Ingrese el código de la asignatura: ");
                String codigo = entrada.nextLine();

                System.out.print("Ingrese la sección/grupo de la asignatura: ");
                String grupo = entrada.nextLine();

                System.out.print("Ingrese el semestre de la asignatura: ");
                String semestre = entrada.nextLine();

                boolean registrado = VariableControl.registrarEstudianteEnAsignatura(tipoDoc, numDoc, codigo, grupo, semestre);

                if (registrado) {
                    System.out.println("Estudiante registrado exitosamente en la asignatura.");
                } else {
                    System.out.println("No se pudo registrar al estudiante. Verifique que los datos sean correctos.");
                }
            } else if (opcion.equals("10")) {
                // Consultar estudiantes en asignatura
                System.out.print("Ingrese el código de la asignatura: ");
                String codigoConsulta = entrada.nextLine();
                System.out.print("Ingrese el grupo (sección) de la asignatura: ");
                String grupoConsulta = entrada.nextLine();

                ArrayList<Estudiante> estudiantesAsignatura = VariableControl.consultarEstudiantesAsignatura(codigoConsulta, grupoConsulta);

                if (estudiantesAsignatura.isEmpty()) {
                    System.out.println("No se encontraron estudiantes registrados en esta asignatura.");
                } else {
                    System.out.println("Estudiantes registrados en la asignatura:");
                    for (Estudiante est : estudiantesAsignatura) {
                        System.out.println("- " + est.getNombreCompleto() + " | " + est.getTipoDocumento() + " " + est.getNumeroDocumento());
                    }
                }

            } else if (opcion.equals("11")) {
                // Crear lista de asistencia vacía

                System.out.print("Ingrese código de asignatura: ");
                String cod = entrada.nextLine();
                System.out.print("Ingrese grupo: ");
                String grupo = entrada.nextLine();
                System.out.print("Ingrese semestre: ");
                String semestre = entrada.nextLine();
                System.out.print("Ingrese fecha y hora (ej. 2025-04-10 08:00): ");
                String fechaHora = entrada.nextLine();

                boolean creada = VariableControl.crearListaAsistencia(cod, grupo, semestre, fechaHora);
                if (creada) {
                    System.out.println("Lista de asistencia vacía creada exitosamente.");
                } else {
                    System.out.println("Error: no se pudo crear la lista. Verifique que la asignatura exista y tenga estudiantes registrados.");
                }
            } else if (opcion.equals("12")) {
                System.out.println("=== REGISTRO DE ASISTENCIA ===");

                System.out.print("Código de la asignatura: ");
                String codigo = entrada.nextLine();

                System.out.print("Grupo: ");
                String grupo = entrada.nextLine();

                System.out.print("Semestre: ");
                String semestre = entrada.nextLine();

                System.out.print("Fecha (YYYY-MM-DD): ");
                String fecha = entrada.nextLine();

                System.out.print("Hora (HH:MM): ");
                String hora = entrada.nextLine();

                Asistencia asistencia = VariableControl.iniciarRegistroAsistencia(codigo, grupo, semestre, fecha, hora);

                if (asistencia == null) {
                    System.out.println("No se encontró la asignatura o no hay estudiantes inscritos.");
                } else {
                    System.out.println("Asistencia iniciada. Por defecto todos los estudiantes están AUSENTES.");
                    System.out.println("Ahora puede modificar el estado de los estudiantes (P = Presente, A = Ausente, R = Retardo):");

                    ArrayList<Estudiante> estudiantes = VariableControl.consultarEstudiantesAsignatura(codigo, grupo);
                    for (Estudiante estudiante : estudiantes) {
                        System.out.println("\nEstudiante: " + estudiante.getNombreCompleto() + " (" + estudiante.getNumeroDocumento() + ")");
                        System.out.print("Estado (P/A/R): ");
                        String estadoInput = entrada.nextLine().trim().toUpperCase();

                        Asistencia.Estado estado;
                        switch (estadoInput) {
                            case "P":
                                estado = Asistencia.Estado.PRESENTE;
                                break;
                            case "R":
                                estado = Asistencia.Estado.RETARDO;  // Cambio de PRESENTE_CON_RETARDO a RETARDO
                                break;
                            case "A":
                            default:
                                estado = Asistencia.Estado.AUSENTE;
                                break;
                        }
                        asistencia.registrarEstudiante(estudiante.getNumeroDocumento(), estado);
                    }

                    VariableControl.guardarAsistencias(asistencia);
                    System.out.println("Asistencia registrada exitosamente.");
                }

            } else if (opcion.equals("13")) {
                // Modificar asistencia
                // Modificar asistencia para un estudiante específico
                System.out.println("=== MODIFICAR ASISTENCIA DE ESTUDIANTE ===");

                System.out.print("Ingrese código de asignatura: ");
                String codigo = entrada.nextLine();
                System.out.print("Ingrese grupo: ");
                String grupo = entrada.nextLine();
                System.out.print("Ingrese semestre: ");
                String semestre = entrada.nextLine();
                System.out.print("Ingrese fecha y hora (ej. 2025-04-10 08:00): ");
                String fechaHora = entrada.nextLine();

                Asistencia asistencia = VariableControl.buscarAsistencia(codigo, grupo, semestre, fechaHora);

                if (asistencia == null) {
                    System.out.println("No se encontró ninguna lista de asistencia con esos datos.");
                    continue;
                }

                // Pedir datos del estudiante a modificar
                System.out.print("Ingrese tipo de documento del estudiante: ");
                String tipoDoc = entrada.nextLine();
                System.out.print("Ingrese número de documento del estudiante: ");
                String numDoc = entrada.nextLine();

                // Verificar que el estudiante exista en la asignatura
                Estudiante estudiante = VariableControl.consultarEstudianteDepartamento(tipoDoc, numDoc);

                if (estudiante == null) {
                    System.out.println("El estudiante no existe en el sistema.");
                    continue;
                }

                ArrayList<Estudiante> estudiantesAsignatura = VariableControl.consultarEstudiantesAsignatura(codigo, grupo);
                boolean estudianteEnAsignatura = false;

                for (Estudiante est : estudiantesAsignatura) {
                    if (est.getTipoDocumento().equalsIgnoreCase(tipoDoc) &&
                            est.getNumeroDocumento().equalsIgnoreCase(numDoc)) {
                        estudianteEnAsignatura = true;
                        break;
                    }
                }

                if (!estudianteEnAsignatura) {
                    System.out.println("El estudiante no está registrado en esta asignatura.");
                    continue;
                }

                // Mostrar estado actual
                Asistencia.Estado estadoActual = asistencia.obtenerEstado(numDoc);
                String estadoStr = estadoActual == Asistencia.Estado.PRESENTE ? "Presente" :
                        (estadoActual == Asistencia.Estado.RETARDO ? "Con Retardo" : "Ausente");

                System.out.println("Estado actual del estudiante " + estudiante.getNombreCompleto() + ": " + estadoStr);

                // Pedir nuevo estado
                System.out.print("Ingrese nuevo estado (P=Presente, A=Ausente, R=Retardo): ");
                String estadoInput = entrada.nextLine().trim().toUpperCase();

                Asistencia.Estado nuevoEstado;
                switch (estadoInput) {
                    case "P":
                        nuevoEstado = Asistencia.Estado.PRESENTE;
                        break;
                    case "R":
                        nuevoEstado = Asistencia.Estado.RETARDO;
                        break;
                    case "A":
                    default:
                        nuevoEstado = Asistencia.Estado.AUSENTE;
                        break;
                }

                // Actualizar estado
                asistencia.registrarEstudiante(numDoc, nuevoEstado);
                boolean actualizado = VariableControl.guardarAsistencias(asistencia);

                if (actualizado) {
                    System.out.println("Estado de asistencia modificado exitosamente.");
                } else {
                    System.out.println("Error al actualizar el estado de asistencia.");
                }

            } else if (opcion.equals("14")) {

                // Listar asistencia

                System.out.println("=== CONSULTA DE ASISTENCIA FINAL ===");

                System.out.print("Ingrese código de asignatura: ");
                String codigo = entrada.nextLine();

                System.out.print("Ingrese grupo: ");
                String grupo = entrada.nextLine();

                System.out.print("Ingrese semestre: ");
                String semestre = entrada.nextLine();

                System.out.print("Ingrese fecha y hora (ej. 2025-04-10 08:00): ");
                String fechaHora = entrada.nextLine();

                Asistencia asistencia = VariableControl.buscarAsistencia(codigo, grupo, semestre, fechaHora);

                if (asistencia == null) {
                    System.out.println("No se encontró ninguna lista de asistencia con esos datos.");
                } else {
                    // Obtener detalles de la asignatura
                    Asignatura asignatura = VariableControl.consultarAsignaturaPorCodigo(codigo);
                    System.out.println("\n== REGISTRO DE ASISTENCIA ==");
                    System.out.println("Asignatura: " + asignatura.getNombre() + " (" + codigo + ")");
                    System.out.println("Grupo: " + grupo);
                    System.out.println("Semestre: " + semestre);
                    System.out.println("Fecha y hora: " + fechaHora);
                    System.out.println("\nLISTA DE ESTUDIANTES:");
                    System.out.println("--------------------------------------------------");
                    System.out.println("Nombre del Estudiante | Documento | Estado");
                    System.out.println("--------------------------------------------------");

                    // Obtener estudiantes de la asignatura
                    ArrayList<Estudiante> estudiantes = VariableControl.consultarEstudiantesAsignatura(codigo, grupo);

                    for (Estudiante estudiante : estudiantes) {
                        String numDoc = estudiante.getNumeroDocumento();
                        Asistencia.Estado estado = asistencia.obtenerEstado(numDoc);

                        String estadoStr = "Ausente";
                        if (estado == Asistencia.Estado.PRESENTE) {
                            estadoStr = "Presente";
                        } else if (estado == Asistencia.Estado.RETARDO) {
                            estadoStr = "Retardo";
                        }

                        System.out.printf("%-30s %-15s %-10s\n",
                                estudiante.getNombreCompleto(),
                                estudiante.getTipoDocumento() + " " + numDoc,
                                estadoStr);
                    }
                    System.out.println("--------------------------------------------------");
                }
            } else if (opcion.equals("15")) {
                System.out.println("Saliendo del sistema...");
            } else {
                System.out.println("Opción no válida, intente nuevamente.");
            }
        }

        entrada.close();
    }
}
