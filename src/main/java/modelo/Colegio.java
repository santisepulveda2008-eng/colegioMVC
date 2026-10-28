package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author santi
 */
public class Colegio {

    private final String nombre;
    private final List<Estudiante> estudiantes = new ArrayList<>();
    private final List<Profesor> profesores = new ArrayList<>();

    public Colegio(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Se devuelven listas de solo lectura para que nadie las cambie desde afuera
    public List<Estudiante> getEstudiantes() {
        return Collections.unmodifiableList(estudiantes);
    }

    public List<Profesor> getProfesores() {
        return Collections.unmodifiableList(profesores);
    }

    public void registrarEstudiante(Estudiante estudiante) {
        if (buscarEstudiante(estudiante.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe un estudiante con el código " + estudiante.getCodigo());
        }
        estudiantes.add(estudiante);
    }

    public void registrarProfesor(Profesor profesor) {
        if (buscarProfesor(profesor.getCedula()) != null) {
            throw new IllegalArgumentException("Ya existe un profesor con la cédula " + profesor.getCedula());
        }
        profesores.add(profesor);
    }

    public Estudiante buscarEstudiante(String codigo) {
        for (Estudiante e : estudiantes) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                return e;
            }
        }
        return null;
    }

    public Profesor buscarProfesor(String cedula) {
        for (Profesor p : profesores) {
            if (p.getCedula().equals(cedula)) {
                return p;
            }
        }
        return null;
    }

    // Copia de la lista ordenada de mayor a menor pago; la lista original no se toca
    public List<Profesor> profesoresPorPago() {
        List<Profesor> ordenados = new ArrayList<>(profesores);
        ordenados.sort(Comparator.comparingDouble((Profesor p) -> p.calcularPago()).reversed());
        return ordenados;
    }

    public double calcularTotalPrestaciones() {
        double total = 0;
        for (Profesor p : profesores) {
            total += p.calcularPrestaciones();
        }
        return total;
    }

    public String generarReporteEstudiantes() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("ESTUDIANTES DE ").append(nombre.toUpperCase()).append("\n\n");
        if (estudiantes.isEmpty()) {
            return reporte.append("Todavía no hay estudiantes registrados.\n").toString();
        }
        agregarLineas(reporte, estudiantes);
        reporte.append("\nTotal: ").append(estudiantes.size()).append(" estudiante(s)\n");
        return reporte.toString();
    }

    public String generarReporteProfesores() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("PROFESORES DE ").append(nombre.toUpperCase()).append(" (de mayor a menor pago)\n\n");
        if (profesores.isEmpty()) {
            return reporte.append("Todavía no hay profesores registrados.\n").toString();
        }
        agregarLineas(reporte, profesoresPorPago());
        reporte.append(String.format("%nTotal de prestaciones sociales: $%,.0f%n", calcularTotalPrestaciones()));
        return reporte.toString();
    }

    // Recibe cualquier lista de personas: cada una se describe a su manera (polimorfismo)
    private void agregarLineas(StringBuilder reporte, List<? extends Persona> personas) {
        int numero = 1;
        for (Persona persona : personas) {
            reporte.append(numero).append(". ").append(persona.describir()).append("\n");
            numero++;
        }
    }
}
