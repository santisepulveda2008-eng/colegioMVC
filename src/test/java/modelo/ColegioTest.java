package modelo;

import java.time.LocalDate;
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
}
