package dao;

import config.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelefonoProveedorDAO {

    public boolean insertar(int idProveedor, String telefono) {
        String sql = "INSERT INTO TELEFONOS_PROVEEDOR(id_proveedor, telefono) VALUES (?, ?)";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProveedor);
            ps.setString(2, telefono);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean eliminarPorProveedor(int idProveedor) {
        String sql = "DELETE FROM TELEFONOS_PROVEEDOR WHERE id_proveedor=?";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProveedor);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }
}
