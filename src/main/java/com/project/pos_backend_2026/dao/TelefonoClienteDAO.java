//DAO AUXILIARES telefonos y correos clientes “Las tablas de teléfonos y correos se manejan con DAOs auxiliares porque son datos dependientes, por eso solo permiten inserción y eliminación.”


package dao;

import config.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelefonoClienteDAO {

    public boolean insertar(int idCliente, String telefono) {
        String sql = "INSERT INTO TELEFONOS_CLIENTE(id_cliente, telefono) VALUES (?, ?)";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ps.setString(2, telefono);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean eliminarPorCliente(int idCliente) {
        String sql = "DELETE FROM TELEFONOS_CLIENTE WHERE id_cliente=?";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }
}
