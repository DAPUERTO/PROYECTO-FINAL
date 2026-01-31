package dao;

import config.Conexiondb;
import models.DetalleCompra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DetalleCompraDAO {

    public boolean insertar(DetalleCompra d) {
        String sql = "INSERT INTO DETALLE_COMPRA(id_pedido, id_producto, cantidad_comprada, costo_unitario, subtotal_compra) VALUES (?,?,?,?,?)";
        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, d.getIdPedido());
            ps.setInt(2, d.getIdProducto());
            ps.setInt(3, d.getCantidadComprada());
            ps.setDouble(4, d.getCostoUnitario());
            ps.setDouble(5, d.getSubtotalCompra());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<DetalleCompra> listar() {
        List<DetalleCompra> lista = new ArrayList<>();
        String sql = "SELECT * FROM DETALLE_COMPRA";
        try (Connection con = Conexiondb.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                DetalleCompra d = new DetalleCompra();
                d.setIdDetalleCompra(rs.getInt("id_detalle_compra"));
                d.setIdPedido(rs.getInt("id_pedido"));
                d.setIdProducto(rs.getInt("id_producto"));
                d.setCantidadComprada(rs.getInt("cantidad_comprada"));
                d.setCostoUnitario(rs.getDouble("costo_unitario"));
                d.setSubtotalCompra(rs.getDouble("subtotal_compra"));
                lista.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
