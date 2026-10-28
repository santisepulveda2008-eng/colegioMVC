package modelo;

import java.time.LocalDate;

/**
 *
 * @author santi
 */
public class Estudiante extends Persona {

    private String codigo;
    private String grado;

    public Estudiante() {
    }

    public Estudiante(String nombre, String direccion, String telefono, LocalDate fechaNacimiento,
            String codigo, String grado) {
        super(nombre, direccion, telefono, fechaNacimiento);
        this.codigo = codigo;
        this.grado = grado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    @Override
    public String describir() {
        return "[" + codigo + "] " + getNombre()
                + " - grado " + grado
                + " - " + calcularEdad() + " años"
                + " - tel. " + getTelefono()
                + " - " + getDireccion();
    }
}
