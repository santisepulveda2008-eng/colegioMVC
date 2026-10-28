package controlador;

import java.time.LocalDate;
import modelo.Colegio;
import modelo.Estudiante;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Se fija la fecha de "hoy" para que las pruebas den lo mismo cualquier día.
 *
 * @author santi
 */
public class ControladorColegioTest {

    private static final LocalDate HOY = LocalDate.of(2026, 10, 28);

    private Colegio colegio;
    private ControladorColegio controlador;

    @BeforeEach
    public void setUp() {
        colegio = new Colegio("Colegio de prueba");
        controlador = new ControladorColegio(colegio, HOY);
    }

    @Test
    public void testRegistraEstudianteConDatosValidos() {
        Estudiante e = controlador.registrarEstudiante("  Sofía Rojas ", "Calle 8", "3204445566",
                "05/09/2013", "E10", "6A");

        assertEquals("Sofía Rojas", e.getNombre());
        assertEquals(LocalDate.of(2013, 9, 5), e.getFechaNacimiento());
        assertEquals(1, colegio.getEstudiantes().size());
    }

    @Test
    public void testCampoVacioDaError() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> controlador.registrarEstudiante("", "Calle 8", "3204445566", "05/09/2013", "E10", "6A"));

        assertTrue(error.getMessage().contains("nombre"));
        assertTrue(colegio.getEstudiantes().isEmpty());
    }

    @Test
    public void testFechaQueNoExisteDaError() {
        // El 31 de febrero no existe
        assertThrows(IllegalArgumentException.class,
                () -> controlador.registrarEstudiante("Sofía", "Calle 8", "320", "31/02/2013", "E10", "6A"));
    }

    @Test
    public void testFechaFuturaDaError() {
        // Hoy es 28/10/2026, así que el día siguiente ya es futuro
        assertThrows(IllegalArgumentException.class,
                () -> controlador.registrarEstudiante("Sofía", "Calle 8", "320", "29/10/2026", "E10", "6A"));
    }

    @Test
    public void testProfesorMenorDeEdadDaError() {
        // Nacido en 2010: en 2026 tiene 16 años
        assertThrows(IllegalArgumentException.class,
                () -> controlador.registrarProfesor("Pablo", "Calle 3", "310", "01/01/2010",
                        "2001", "Música", "15000", "40"));
        assertTrue(colegio.getProfesores().isEmpty());
    }
}
