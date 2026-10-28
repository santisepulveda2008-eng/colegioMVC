package modelo;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author santi
 */
public class ColegioTest {

    private Colegio colegio;

    @BeforeEach
    public void setUp() {
        colegio = new Colegio("Colegio de prueba");
    }

    private Estudiante crearEstudiante(String codigo) {
        return new Estudiante("Juan Torres", "Calle 5", "3150001122", LocalDate.of(2012, 1, 15), codigo, "7B");
    }

    @Test
    public void testRegistrarEstudiante() {
        colegio.registrarEstudiante(crearEstudiante("E01"));

        assertEquals(1, colegio.getEstudiantes().size());
        assertNotNull(colegio.buscarEstudiante("E01"));
    }

    @Test
    public void testNoPermiteCodigoRepetido() {
        colegio.registrarEstudiante(crearEstudiante("E01"));

        assertThrows(IllegalArgumentException.class,
                () -> colegio.registrarEstudiante(crearEstudiante("E01")));
        assertEquals(1, colegio.getEstudiantes().size());
    }

    @Test
    public void testProfesoresOrdenadosDeMayorAMenorPago() {
        // Se registran en desorden a propósito
        colegio.registrarProfesor(crearProfesor("Carlos", "3", 10000, 40));
        colegio.registrarProfesor(crearProfesor("Ana", "1", 20000, 80));
        colegio.registrarProfesor(crearProfesor("Beatriz", "2", 15000, 60));

        List<Profesor> ordenados = colegio.profesoresPorPago();

        assertEquals("Ana", ordenados.get(0).getNombre());
        assertEquals("Beatriz", ordenados.get(1).getNombre());
        assertEquals("Carlos", ordenados.get(2).getNombre());
    }

    private Profesor crearProfesor(String nombre, String cedula, double valorHora, int horas) {
        return new Profesor(nombre, "Calle 1", "3000000000", LocalDate.of(1985, 4, 2),
                cedula, "Sociales", valorHora, horas);
    }
}
