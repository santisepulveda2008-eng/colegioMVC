/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package modelo;

/**
 *
 * @author santi
 */
import java.time.LocalDate;
import java.time.Period;

public abstract class Persona {

    public static final int MAYORIA_DE_EDAD = 18;

    private String nombre;
    private String direccion;
    private String telefono;
    private LocalDate fechaNacimiento;

    public Persona() {
    }

    public Persona(String nombre, String direccion, String telefono, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // Edad cumplida al día de hoy
    public int calcularEdad() {
        return calcularEdad(LocalDate.now());
    }

    // Sobrecarga: edad cumplida en una fecha cualquiera
    public int calcularEdad(LocalDate fecha) {
        if (fechaNacimiento == null || fecha == null || fecha.isBefore(fechaNacimiento)) {
            return 0;
        }
        return Period.between(fechaNacimiento, fecha).getYears();
    }

    public boolean esMayorDeEdad(LocalDate fecha) {
        return calcularEdad(fecha) >= MAYORIA_DE_EDAD;
    }

    // Cada tipo de persona arma su propia línea para los reportes
    public abstract String describir();
}
