package modelo;

import java.time.LocalDate;

/**
 *
 * @author santi
 */
public class Profesor extends Persona {

    public static final double PORCENTAJE_PREPARACION = 0.30;
    public static final double PORCENTAJE_PRESTACIONES = 0.19;

    private String cedula;
    private String area;
    private double valorHora;
    private int horasDictadas;

    public Profesor() {
    }

    public Profesor(String nombre, String direccion, String telefono, LocalDate fechaNacimiento,
            String cedula, String area, double valorHora, int horasDictadas) {
        super(nombre, direccion, telefono, fechaNacimiento);
        this.cedula = cedula;
        this.area = area;
        this.valorHora = valorHora;
        this.horasDictadas = horasDictadas;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public int getHorasDictadas() {
        return horasDictadas;
    }

    public void setHorasDictadas(int horasDictadas) {
        this.horasDictadas = horasDictadas;
    }

    // Pago del mes con las horas que tiene registradas
    public double calcularPago() {
        return calcularPago(horasDictadas);
    }

    // Sobrecarga: pago para un número de horas dado, más el 30% de preparación de clases
    public double calcularPago(int horas) {
        double pagoHoras = valorHora * horas;
        return pagoHoras + pagoHoras * PORCENTAJE_PREPARACION;
    }

    public double calcularPrestaciones() {
        return calcularPago() * PORCENTAJE_PRESTACIONES;
    }

    @Override
    public String describir() {
        return "[" + cedula + "] " + getNombre()
                + " - " + area
                + " - " + horasDictadas + " h"
                + String.format(" - pago $%,.0f - prestaciones $%,.0f", calcularPago(), calcularPrestaciones());
    }
}
