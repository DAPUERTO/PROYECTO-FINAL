package dao;

import config.Conexiondb;
import models.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // ===============================
    // INSERTAR PRODUCTO
    // ===============================
    public boolean insertar(Producto producto) {

        String sql = "INSERT INTO PRODUCTOS "
                + "(nombre_producto, precio_venta, costo_compra, stock, id_categoria) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, producto.getNombreProducto());
            ps.setDouble(2, producto.getPrecioVenta());
            ps.setDouble(3, producto.getCostoCompra());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getIdCategoria());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al insertar producto: " + e.getMessage());
            return false;
        }
    }

    // ===============================
    // ACTUALIZAR PRODUCTO
    // ===============================
    public boolean actualizar(Producto producto) {

        String sql = "UPDATE PRODUCTOS SET "
                + "nombre_producto = ?, "
                + "precio_venta = ?, "
                + "costo_compra = ?, "
                + "id_categoria = ? "
                + "WHERE id_producto = ?";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, producto.getNombreProducto());
            ps.setDouble(2, producto.getPrecioVenta());
            ps.setDouble(3, producto.getCostoCompra());
            ps.setInt(4, producto.getIdCategoria());
            ps.setInt(5, producto.getIdProducto());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    // ===============================
    // ACTUALIZAR STOCK
    // ===============================
    public boolean actualizarStock(int idProducto, int stock) {

        String sql = "UPDATE PRODUCTOS SET stock = ? WHERE id_producto = ?";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, stock);
            ps.setInt(2, idProducto);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al actualizar stock: " + e.getMessage());
            return false;
        }
    }

    // ===============================
    // BUSCAR PRODUCTO POR ID
    // ===============================
    public Producto buscarPorId(int idProducto) {

        String sql = "SELECT id_producto, nombre_producto, precio_venta, "
                   + "costo_compra, stock, id_categoria "
                   + "FROM PRODUCTOS WHERE id_producto = ?";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombreProducto(rs.getString("nombre_producto"));
                p.setPrecioVenta(rs.getDouble("precio_venta"));
                p.setCostoCompra(rs.getDouble("costo_compra"));
                p.setStock(rs.getInt("stock"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                return p;
            }

        } catch (Exception e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }

        return null;
    }

    // ===============================
    // LISTAR PRODUCTOS
    // ===============================
    public List<Producto> listar() {

        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT id_producto, nombre_producto, precio_venta, "
                   + "costo_compra, stock, id_categoria FROM PRODUCTOS";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombreProducto(rs.getString("nombre_producto"));
                p.setPrecioVenta(rs.getDouble("precio_venta"));
                p.setCostoCompra(rs.getDouble("costo_compra"));
                p.setStock(rs.getInt("stock"));
                p.setIdCategoria(rs.getInt("id_categoria"));

                productos.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }

        return productos;
    }
}
