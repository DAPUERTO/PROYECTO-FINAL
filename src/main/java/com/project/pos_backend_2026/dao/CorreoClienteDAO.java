package com.project.pos_backend_2026.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.project.pos_backend_2026.config.Conexiondb;

public class CorreoClienteDAO {

    public boolean insertar(int idCliente, String correo) {
        String sql = "INSERT INTO CORREO_CLIENTE(id_cliente, correo) VALUES (?, ?)";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ps.setString(2, correo);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean eliminarPorCliente(int idCliente) {
        String sql = "DELETE FROM CORREO_CLIENTE WHERE id_cliente=?";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }
}
