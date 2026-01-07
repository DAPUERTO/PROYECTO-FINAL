package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import config.Conexiondb;
import models.PedidoCompra;
import java.util.ArrayList;
import java.util.List;

public class PedidoCompraDAO {

    public boolean insertar(PedidoCompra p) {
        String sql = "INSERT INTO PEDIDOS_COMPRA(id_proveedor, fecha_pedido, fecha_recepcion, monto_total, estado_pedido) VALUES (?,?,?,?,?)";
        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getIdProveedor());
            ps.setDate(2, p.getFechaPedido());
            ps.setDate(3, p.getFechaRecepcion());
            ps.setDouble(4, p.getMontoTotal());
            ps.setString(5, p.getEstadoPedido());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PedidoCompra> listar() {
        List<PedidoCompra> lista = new ArrayList<>();
        String sql = "SELECT * FROM PEDIDOS_COMPRA";
        try (Connection con = Conexiondb.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                PedidoCompra p = new PedidoCompra();
                p.setIdPedido(rs.getInt("id_pedido"));
                p.setIdProveedor(rs.getInt("id_proveedor"));
                p.setFechaPedido(rs.getDate("fecha_pedido"));
                p.setFechaRecepcion(rs.getDate("fecha_recepcion"));
                p.setMontoTotal(rs.getDouble("monto_total"));
                p.setEstadoPedido(rs.getString("estado_pedido"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
