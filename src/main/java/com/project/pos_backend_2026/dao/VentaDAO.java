package dao;

import config.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.Venta;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    // 1️ REGISTRAR VENTA
    public int registrarVenta(Venta v) {
        try (Connection con = Conexiondb.getConexion()) {
            return registrarVenta(v, con);
        } catch (SQLException e) {
            System.out.println("Error al registrar venta: " + e.getMessage());
            return 0;
        }
    }

    // 1.1 REGISTRAR VENTA
    public int registrarVenta(Venta v, Connection con) throws SQLException {
        int idVenta = 0;
        String sql = "INSERT INTO VENTAS(fecha_venta, hora_venta, total_pagar, estado, id_cliente, id_usuario) VALUES (CURDATE(), CURTIME(), ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, v.getTotalPagar());
            ps.setString(2, v.getEstado());
            ps.setInt(3, v.getIdCliente());
            ps.setInt(4, v.getIdUsuario());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                idVenta = rs.getInt(1);
            }
        }
        return idVenta;
    }

    // 2️ LISTAR VENTAS
    public List<Venta> listar() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM VENTAS";

        try (Connection con = Conexiondb.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Venta v = new Venta();
                v.setIdVenta(rs.getInt("id_venta"));
                v.setFechaVenta(rs.getDate("fecha_venta").toLocalDate());
                v.setHoraVenta(rs.getTime("hora_venta").toLocalTime());
                v.setTotalPagar(rs.getDouble("total_pagar"));
                v.setEstado(rs.getString("estado"));
                v.setIdCliente(rs.getInt("id_cliente"));
                v.setIdUsuario(rs.getInt("id_usuario"));
                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar ventas: " + e.getMessage());
        }
        return lista;
    }

    // 3️ BUSCAR VENTA POR ID
    public Venta buscarPorId(int id) {
        String sql = "SELECT * FROM VENTAS WHERE id_venta=?";
        Venta v = null;

        try (Connection con = Conexiondb.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                v = new Venta();
                v.setIdVenta(rs.getInt("id_venta"));
                v.setFechaVenta(rs.getDate("fecha_venta").toLocalDate());
                v.setHoraVenta(rs.getTime("hora_venta").toLocalTime());
                v.setTotalPagar(rs.getDouble("total_pagar"));
                v.setEstado(rs.getString("estado"));
                v.setIdCliente(rs.getInt("id_cliente"));
                v.setIdUsuario(rs.getInt("id_usuario"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar venta: " + e.getMessage());
        }
        return v;
    }

    // 4️ ACTUALIZAR ESTADO
    public boolean actualizarEstado(int idVenta, String estado) {
        String sql = "UPDATE VENTAS SET estado=? WHERE id_venta=?";

        try (Connection con = Conexiondb.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, idVenta);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar estado venta: " + e.getMessage());
            return false;
        }
    }
}
