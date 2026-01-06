package models;

public class DetalleCompra {

    private int idDetalleCompra;
    private int idPedido;
    private int idProducto;
    private int cantidadComprada;
    private double costoUnitario;
    private double subtotalCompra;

    public DetalleCompra() {
    }

    public int getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(int idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadComprada() {
        return cantidadComprada;
    }

    public void setCantidadComprada(int cantidadComprada) {
        this.cantidadComprada = cantidadComprada;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public double getSubtotalCompra() {
        return subtotalCompra;
    }

    public void setSubtotalCompra(double subtotalCompra) {
        this.subtotalCompra = subtotalCompra;
    }
}
