package controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import modelo.Colegio;
import modelo.Estudiante;
import modelo.Profesor;

/**
 * Recibe lo que el usuario escribe en la ventana, lo valida y se lo pasa al
 * modelo. Si algo está mal lanza IllegalArgumentException con un mensaje
 * que la vista le muestra al usuario.
 *
 * @author santi
 */
public class ControladorColegio {

    // STRICT hace que fechas como 31/02/2010 se rechacen en vez de corregirse solas
    private static final DateTimeFormatter FORMATO_FECHA
            = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private final Colegio colegio;
    private final LocalDate fechaFija;

    public ControladorColegio(Colegio colegio) {
        this(colegio, null);
    }

    // Sobrecarga para las pruebas: permite fijar "hoy" y que los resultados no cambien con el tiempo
    public ControladorColegio(Colegio colegio, LocalDate fechaFija) {
        this.colegio = colegio;
        this.fechaFija = fechaFija;
    }

    public Colegio getColegio() {
        return colegio;
    }

    public Estudiante registrarEstudiante(String nombre, String direccion, String telefono,
            String fechaNacimiento, String codigo, String grado) {
        Estudiante estudiante = new Estudiante(
                obligatorio(nombre, "nombre"),
                obligatorio(direccion, "dirección"),
                obligatorio(telefono, "teléfono"),
                convertirFecha(fechaNacimiento),
                obligatorio(codigo, "código"),
                obligatorio(grado, "grado"));
        colegio.registrarEstudiante(estudiante);
        return estudiante;
    }

    public Profesor registrarProfesor(String nombre, String direccion, String telefono,
            String fechaNacimiento, String cedula, String area, String valorHora, String horasDictadas) {
        Profesor profesor = new Profesor(
                obligatorio(nombre, "nombre"),
                obligatorio(direccion, "dirección"),
                obligatorio(telefono, "teléfono"),
                convertirFecha(fechaNacimiento),
                obligatorio(cedula, "cédula"),
                obligatorio(area, "área"),
                convertirDecimal(valorHora, "valor por hora"),
                convertirEntero(horasDictadas, "horas dictadas"));
        if (!profesor.esMayorDeEdad(hoy())) {
            throw new IllegalArgumentException("El profesor debe ser mayor de edad.");
        }
        colegio.registrarProfesor(profesor);
        return profesor;
    }

    public String reporteEstudiantes() {
        return colegio.generarReporteEstudiantes();
    }

    public String reporteProfesores() {
        return colegio.generarReporteProfesores();
    }

    private LocalDate hoy() {
        return fechaFija != null ? fechaFija : LocalDate.now();
    }

    private String obligatorio(String texto, String campo) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio.");
        }
        return texto.trim();
    }

    private LocalDate convertirFecha(String texto) {
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(obligatorio(texto, "fecha de nacimiento"), FORMATO_FECHA);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("La fecha de nacimiento debe ser real y estar en formato dd/mm/aaaa.");
        }
        if (fecha.isAfter(hoy())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede estar en el futuro.");
        }
        return fecha;
    }

    private double convertirDecimal(String texto, String campo) {
        double valor;
        try {
            valor = Double.parseDouble(obligatorio(texto, campo));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser un número (sin puntos de miles).");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser mayor que cero.");
        }
        return valor;
    }

    private int convertirEntero(String texto, String campo) {
        int valor;
        try {
            valor = Integer.parseInt(obligatorio(texto, campo));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser un número entero.");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser mayor que cero.");
        }
        return valor;
    }
}
