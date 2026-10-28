package modelo;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Persona es abstracta, por eso se prueba usando un Estudiante.
 *
 * @author santi
 */
public class PersonaTest {

    @Test
    public void testEdadCambiaElDiaDelCumpleanos() {
        Persona persona = new Estudiante();
        persona.setFechaNacimiento(LocalDate.of(2010, 8, 20));

        assertEquals(15, persona.calcularEdad(LocalDate.of(2026, 8, 19)));
        assertEquals(16, persona.calcularEdad(LocalDate.of(2026, 8, 20)));
    }

    @Test
    public void testEdadEsCeroSinFechaDeNacimiento() {
        Persona persona = new Estudiante();
        assertEquals(0, persona.calcularEdad());
    }
}
