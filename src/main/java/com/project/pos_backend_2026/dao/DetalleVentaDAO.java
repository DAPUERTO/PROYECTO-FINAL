package com.project.pos_backend_2026.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.pos_backend_2026.config.Conexiondb;
import com.project.pos_backend_2026.models.DetalleVenta;

public class DetalleVentaDAO {

    /**
     * Inserta un detalle de venta en la base de datos.
     * 
     * @param d Objeto DetalleVenta con los datos a registrar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertar(DetalleVenta d) {
        try (Connection con = Conexiondb.getConexion()) {
            return insertar(d, con);
        } catch (SQLException e) {
            System.out.println("Error al insertar detalle venta: " + e.getMessage());
            return false;
        }
    }

    /**
     * Inserta un detalle de venta usando una conexión existente (Para
     * Transacciones).
     * 
     * @param d   Objeto DetalleVenta.
     * @param con Conexión activa.
     * @return true si se insertó correctamente.
     * @throws SQLException Si ocurre un error SQL.
     */
    public boolean insertar(DetalleVenta d, Connection con) throws SQLException {
        String sql = "INSERT INTO DETALLE_VENTA(id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, d.getIdVenta());
            ps.setInt(2, d.getIdProducto());
            ps.setInt(3, d.getCantidad());
            ps.setDouble(4, d.getPrecioUnitario());
            ps.setDouble(5, d.getSubtotal());

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Obtiene una lista de los detalles de una venta específica.
     * 
     * @param idVenta Identificador de la venta.
     * @return Lista de objetos DetalleVenta.
     */
    public List<DetalleVenta> listarPorVenta(int idVenta) {
        List<DetalleVenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM DETALLE_VENTA WHERE id_venta=?";

        try (Connection con = Conexiondb.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idVenta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DetalleVenta d = new DetalleVenta();
                d.setIdDetalle(rs.getInt("id_detalle"));
                d.setIdVenta(rs.getInt("id_venta"));
                d.setIdProducto(rs.getInt("id_producto"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setPrecioUnitario(rs.getDouble("precio_unitario"));
                d.setSubtotal(rs.getDouble("subtotal"));
                lista.add(d);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar detalle venta: " + e.getMessage());
        }
        return lista;
    }
}
