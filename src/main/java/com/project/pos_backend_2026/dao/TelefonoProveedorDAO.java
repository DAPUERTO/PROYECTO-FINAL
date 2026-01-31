package com.project.pos_backend_2026.dao;

import com.project.pos_backend_2026.config.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelefonoProveedorDAO {

    /**
     * Registra un número de teléfono asociado a un proveedor.
     * 
     * @param idProveedor Identificador del proveedor.
     * @param telefono    Número de teléfono.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
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

    /**
     * Elimina todos los números de teléfono asociados a un proveedor.
     * 
     * @param idProveedor Identificador del proveedor.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
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
