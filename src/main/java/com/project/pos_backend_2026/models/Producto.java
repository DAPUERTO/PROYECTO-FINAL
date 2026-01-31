// Declaración del paquete donde se encuentra esta clase
package models;

// Declaración de la clase pública Producto
public class Producto {

    // Atributo privado que almacena el identificador único del producto
    private int idProducto;
    // Atributo privado que almacena el nombre del producto
    private String nombreProducto;
    // Atributo privado que almacena el precio de venta del producto
    private double precioVenta;
    // Atributo privado que almacena el costo de compra del producto
    private double costoCompra;
    // Atributo privado que almacena la cantidad disponible en inventario
    private int stock;
    // Atributo privado que almacena el identificador de la categoría a la que
    // pertenece el producto
    private int idCategoria;

    // Constructor vacío sin parámetros que permite crear instancias de Producto
    public Producto() {
    }

    // Método getter que retorna el valor del identificador del producto
    public int getIdProducto() {
        // Retorna el valor del atributo idProducto
        return idProducto;
    }

    // Método setter que establece el valor del identificador del producto
    public void setIdProducto(int idProducto) {
        // Asigna el parámetro recibido al atributo idProducto de la instancia actual
        this.idProducto = idProducto;
    }

    // Método getter que retorna el nombre del producto
    public String getNombreProducto() {
        // Retorna el valor del atributo nombreProducto
        return nombreProducto;
    }

    // Método setter que establece el nombre del producto
    public void setNombreProducto(String nombreProducto) {
        // Asigna el parámetro recibido al atributo nombreProducto de la instancia
        // actual
        this.nombreProducto = nombreProducto;
    }

    // Método getter que retorna el precio de venta del producto
    public double getPrecioVenta() {
        // Retorna el valor del atributo precioVenta
        return precioVenta;
    }

    // Método setter que establece el precio de venta del producto
    public void setPrecioVenta(double precioVenta) {
        // Asigna el parámetro recibido al atributo precioVenta de la instancia actual
        this.precioVenta = precioVenta;
    }

    // Método getter que retorna el costo de compra del producto
    public double getCostoCompra() {
        // Retorna el valor del atributo costoCompra
        return costoCompra;
    }

    // Método setter que establece el costo de compra del producto
    public void setCostoCompra(double costoCompra) {
        // Asigna el parámetro recibido al atributo costoCompra de la instancia actual
        this.costoCompra = costoCompra;
    }

    // Método getter que retorna la cantidad en stock del producto
    public int getStock() {
        // Retorna el valor del atributo stock
        return stock;
    }

    // Método setter que establece la cantidad en stock del producto
    public void setStock(int stock) {
        // Asigna el parámetro recibido al atributo stock de la instancia actual
        this.stock = stock;
    }

    // Método getter que retorna el identificador de la categoría del producto
    public int getIdCategoria() {
        // Retorna el valor del atributo idCategoria
        return idCategoria;
    }

    // Método setter que establece el identificador de la categoría del producto
    public void setIdCategoria(int idCategoria) {
        // Asigna el parámetro recibido al atributo idCategoria de la instancia actual
        this.idCategoria = idCategoria;
    }
}
