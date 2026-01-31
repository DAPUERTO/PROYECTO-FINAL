// Declaración del paquete donde se encuentra esta clase
package com.project.pos_backend_2026.models;

// Declaración de la clase pública DetalleVenta
public class DetalleVenta {

    // Atributo privado que almacena el identificador único del detalle de venta
    private int idDetalle;
    // Atributo privado que almacena el identificador de la venta a la que pertenece
    // este detalle
    private int idVenta;
    // Atributo privado que almacena el identificador del producto vendido
    private int idProducto;
    // Atributo privado que almacena la cantidad de unidades vendidas del producto
    private int cantidad;
    // Atributo privado que almacena el precio unitario del producto al momento de
    // la venta
    private double precioUnitario;
    // Atributo privado que almacena el subtotal (cantidad * precioUnitario) de este
    // detalle
    private double subtotal;

    // Constructor vacío sin parámetros que permite crear instancias de DetalleVenta
    public DetalleVenta() {
    }

    // Método getter que retorna el identificador del detalle de venta
    public int getIdDetalle() {
        // Retorna el valor del atributo idDetalle
        return idDetalle;
    }

    // Método setter que establece el identificador del detalle de venta
    public void setIdDetalle(int idDetalle) {
        // Asigna el parámetro recibido al atributo idDetalle de la instancia actual
        this.idDetalle = idDetalle;
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

    // Método getter que retorna el identificador del producto
    public int getIdProducto() {
        // Retorna el valor del atributo idProducto
        return idProducto;
    }

    // Método setter que establece el identificador del producto
    public void setIdProducto(int idProducto) {
        // Asigna el parámetro recibido al atributo idProducto de la instancia actual
        this.idProducto = idProducto;
    }

    // Método getter que retorna la cantidad de unidades vendidas
    public int getCantidad() {
        // Retorna el valor del atributo cantidad
        return cantidad;
    }

    // Método setter que establece la cantidad de unidades vendidas
    public void setCantidad(int cantidad) {
        // Asigna el parámetro recibido al atributo cantidad de la instancia actual
        this.cantidad = cantidad;
    }

    // Método getter que retorna el precio unitario del producto
    public double getPrecioUnitario() {
        // Retorna el valor del atributo precioUnitario
        return precioUnitario;
    }

    // Método setter que establece el precio unitario del producto
    public void setPrecioUnitario(double precioUnitario) {
        // Asigna el parámetro recibido al atributo precioUnitario de la instancia
        // actual
        this.precioUnitario = precioUnitario;
    }

    // Método getter que retorna el subtotal del detalle de venta
    public double getSubtotal() {
        // Retorna el valor del atributo subtotal
        return subtotal;
    }

    // Método setter que establece el subtotal del detalle de venta
    public void setSubtotal(double subtotal) {
        // Asigna el parámetro recibido al atributo subtotal de la instancia actual
        this.subtotal = subtotal;
    }
}
