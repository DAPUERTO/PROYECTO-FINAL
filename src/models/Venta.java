// Declaración del paquete donde se encuentra esta clase
package models;

// Importación de la clase LocalDate para manejar fechas sin hora
import java.time.LocalDate;
// Importación de la clase LocalTime para manejar horas sin fecha
import java.time.LocalTime;

// Declaración de la clase pública Venta
public class Venta {

    // Atributo privado que almacena el identificador único de la venta
    private int idVenta;
    // Atributo privado que almacena la fecha en que se realizó la venta
    private LocalDate fechaVenta;
    // Atributo privado que almacena la hora en que se realizó la venta
    private LocalTime horaVenta;
    // Atributo privado que almacena el monto total a pagar de la venta
    private double totalPagar;
    // Atributo privado que almacena el estado de la venta (ej: "Completada",
    // "Pendiente", "Cancelada")
    private String estado;
    // Atributo privado que almacena el identificador del cliente que realizó la
    // compra
    private int idCliente;
    // Atributo privado que almacena el identificador del usuario que registró la
    // venta
    private int idUsuario;

    // Constructor vacío sin parámetros que permite crear instancias de Venta
    public Venta() {
    }

    // Método getter que retorna el identificador de la venta
    public int getIdVenta() {
        // Retorna el valor del atributo idVenta
        return idVenta;
    }

    // Método setter que establece el identificador de la venta
    public void setIdVenta(int idVenta) {
        // Asigna el parámetro recibido al atributo idVenta de la instancia actual
        this.idVenta = idVenta;
    }

    // Método getter que retorna la fecha de la venta
    public LocalDate getFechaVenta() {
        // Retorna el valor del atributo fechaVenta
        return fechaVenta;
    }

    // Método setter que establece la fecha de la venta
    public void setFechaVenta(LocalDate fechaVenta) {
        // Asigna el parámetro recibido al atributo fechaVenta de la instancia actual
        this.fechaVenta = fechaVenta;
    }

    // Método getter que retorna la hora de la venta
    public LocalTime getHoraVenta() {
        // Retorna el valor del atributo horaVenta
        return horaVenta;
    }

    // Método setter que establece la hora de la venta
    public void setHoraVenta(LocalTime horaVenta) {
        // Asigna el parámetro recibido al atributo horaVenta de la instancia actual
        this.horaVenta = horaVenta;
    }

    // Método getter que retorna el total a pagar de la venta
    public double getTotalPagar() {
        // Retorna el valor del atributo totalPagar
        return totalPagar;
    }

    // Método setter que establece el total a pagar de la venta
    public void setTotalPagar(double totalPagar) {
        // Asigna el parámetro recibido al atributo totalPagar de la instancia actual
        this.totalPagar = totalPagar;
    }

    // Método getter que retorna el estado de la venta
    public String getEstado() {
        // Retorna el valor del atributo estado
        return estado;
    }

    // Método setter que establece el estado de la venta
    public void setEstado(String estado) {
        // Asigna el parámetro recibido al atributo estado de la instancia actual
        this.estado = estado;
    }

    // Método getter que retorna el identificador del cliente
    public int getIdCliente() {
        // Retorna el valor del atributo idCliente
        return idCliente;
    }

    // Método setter que establece el identificador del cliente
    public void setIdCliente(int idCliente) {
        // Asigna el parámetro recibido al atributo idCliente de la instancia actual
        this.idCliente = idCliente;
    }

    // Método getter que retorna el identificador del usuario que registró la venta
    public int getIdUsuario() {
        // Retorna el valor del atributo idUsuario
        return idUsuario;
    }

    // Método setter que establece el identificador del usuario que registró la
    // venta
    public void setIdUsuario(int idUsuario) {
        // Asigna el parámetro recibido al atributo idUsuario de la instancia actual
        this.idUsuario = idUsuario;
    }
}
