package modelo;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author santi
 */
public class ProfesorTest {

    private Profesor profesor;

    @BeforeEach
    public void setUp() {
        // 10.000 por hora y 100 horas dictadas
        profesor = new Profesor("Marta Díaz", "Calle 10", "3001112233", LocalDate.of(1980, 5, 20),
                "1001", "Ciencias", 10000, 100);
    }

    @Test
    public void testPagoIncluyeTreintaPorCientoDePreparacion() {
        // 10.000 x 100 = 1.000.000 y el 30% de preparación son 300.000
        assertEquals(1300000, profesor.calcularPago(), 0.01);
    }

    @Test
    public void testPagoConOtraCantidadDeHoras() {
        // Sobrecarga: 10.000 x 50 = 500.000 + 30% = 650.000
        assertEquals(650000, profesor.calcularPago(50), 0.01);
    }

    @Test
    public void testPrestacionesSonDiecinuevePorCiento() {
        // 19% de 1.300.000 = 247.000
        assertEquals(247000, profesor.calcularPrestaciones(), 0.01);
    }
}
