// Declaración del paquete donde se encuentra esta clase
package com.project.pos_backend_2026.models;

// Declaración de la clase pública Cliente
public class Cliente {

    // Atributo privado que almacena el identificador único del cliente
    private int idCliente;
    // Atributo privado que almacena el nombre completo del cliente
    private String nombre;
    // Atributo privado que almacena el número de identificación del cliente
    // (cédula, DNI, etc.)
    private String numeroIdentificacion;
    // Atributo privado que almacena el número de celular del cliente
    private String celular;
    // Atributo privado que almacena la dirección física del cliente
    private String direccion;
    // Atributo privado que almacena el correo electrónico del cliente
    private String correo;

    // Constructor vacío sin parámetros que permite crear instancias de Cliente
    public Cliente() {
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

    // Método getter que retorna el nombre del cliente
    public String getNombre() {
        // Retorna el valor del atributo nombre
        return nombre;
    }

    // Método setter que establece el nombre del cliente
    public void setNombre(String nombre) {
        // Asigna el parámetro recibido al atributo nombre de la instancia actual
        this.nombre = nombre;
    }

    // Método getter que retorna el número de identificación del cliente
    public String getNumeroIdentificacion() {
        // Retorna el valor del atributo numeroIdentificacion
        return numeroIdentificacion;
    }

    // Método setter que establece el número de identificación del cliente
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        // Asigna el parámetro recibido al atributo numeroIdentificacion de la instancia
        // actual
        this.numeroIdentificacion = numeroIdentificacion;
    }

    // Método getter que retorna el número de celular del cliente
    public String getCelular() {
        // Retorna el valor del atributo celular
        return celular;
    }

    // Método setter que establece el número de celular del cliente
    public void setCelular(String celular) {
        // Asigna el parámetro recibido al atributo celular de la instancia actual
        this.celular = celular;
    }

    // Método getter que retorna la dirección del cliente
    public String getDireccion() {
        // Retorna el valor del atributo direccion
        return direccion;
    }

    // Método setter que establece la dirección del cliente
    public void setDireccion(String direccion) {
        // Asigna el parámetro recibido al atributo direccion de la instancia actual
        this.direccion = direccion;
    }

    // Método getter que retorna el correo electrónico del cliente
    public String getCorreo() {
        // Retorna el valor del atributo correo
        return correo;
    }

    // Método setter que establece el correo electrónico del cliente
    public void setCorreo(String correo) {
        // Asigna el parámetro recibido al atributo correo de la instancia actual
        this.correo = correo;
    }
}
