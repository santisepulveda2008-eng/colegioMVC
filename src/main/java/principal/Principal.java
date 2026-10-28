package principal;

import controlador.ControladorColegio;
import modelo.Colegio;
import vista.VentanaColegio;

/**
 * Punto de entrada: crea el modelo, el controlador y la vista, y los conecta.
 *
 * @author santi
 */
public class Principal {

    public static void main(String[] args) {
        Colegio colegio = new Colegio("Colegio San José");
        ControladorColegio controlador = new ControladorColegio(colegio);
        java.awt.EventQueue.invokeLater(() -> new VentanaColegio(controlador).setVisible(true));
    }
}
