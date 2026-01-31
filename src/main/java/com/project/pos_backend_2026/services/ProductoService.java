package com.project.pos_backend_2026.services;

import com.project.pos_backend_2026.dao.ProductoDAO;
import com.project.pos_backend_2026.models.Producto;
import java.util.List;

public class ProductoService {

    private ProductoDAO productoDAO;

    public ProductoService() {
        this.productoDAO = new ProductoDAO();
    }

    // 1️ Crear producto
    public boolean crearProducto(Producto producto) {

        if (producto.getNombreProducto() == null || producto.getNombreProducto().isEmpty()) {
            System.out.println("Nombre de producto inválido");
            return false;
        }

        if (producto.getPrecioVenta() <= 0) {
            System.out.println("El precio debe ser mayor a cero");
            return false;
        }

        if (producto.getStock() < 0) {
            System.out.println("Stock inválido");
            return false;
        }

        return productoDAO.insertar(producto);
    }

    // 2️ Actualizar producto
    public boolean actualizarProducto(Producto producto) {

        if (producto.getPrecioVenta() <= 0) {
            System.out.println("Precio inválido");
            return false;
        }

        return productoDAO.actualizar(producto);
    }

    // 3️ Aumentar stock (compras)
    public boolean aumentarStock(int idProducto, int cantidad) {

        if (cantidad <= 0) {
            System.out.println("Cantidad inválida");
            return false;
        }

        Producto producto = productoDAO.buscarPorId(idProducto);

        if (producto == null) {
            System.out.println("Producto no encontrado");
            return false;
        }

        int nuevoStock = producto.getStock() + cantidad;
        return productoDAO.actualizarStock(idProducto, nuevoStock);
    }

    // 4️ Disminuir stock (ventas)
    public boolean disminuirStock(int idProducto, int cantidad) {

        if (cantidad <= 0) {
            System.out.println("Cantidad inválida");
            return false;
        }

        Producto producto = productoDAO.buscarPorId(idProducto);

        if (producto == null) {
            System.out.println("Producto no encontrado");
            return false;
        }

        if (producto.getStock() < cantidad) {
            System.out.println("Stock insuficiente");
            return false;
        }

        int nuevoStock = producto.getStock() - cantidad;
        return productoDAO.actualizarStock(idProducto, nuevoStock);
    }

    // 5️ Listar productos
    public List<Producto> listarProductos() {
        return productoDAO.listar();
    }

    // 6️ Buscar por ID
    public Producto buscarPorId(int idProducto) {
        return productoDAO.buscarPorId(idProducto);
    }
}
