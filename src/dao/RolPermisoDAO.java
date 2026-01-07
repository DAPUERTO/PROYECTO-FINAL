package dao;

import config.Conexiondb;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolPermisoDAO {

    public boolean asignar(int idRol, int idPermiso) {
        String sql = "INSERT INTO ROL_PERMISOS(id_rol, id_permiso) VALUES (?, ?)";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idRol);
            ps.setInt(2, idPermiso);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error asignando permiso: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idRol, int idPermiso) {
        String sql = "DELETE FROM ROL_PERMISOS WHERE id_rol=? AND id_permiso=?";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idRol);
            ps.setInt(2, idPermiso);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminando permiso: " + e.getMessage());
            return false;
        }
    }

    public List<Integer> listarPermisosPorRol(int idRol) {
        List<Integer> lista = new ArrayList<>();
        String sql = "SELECT id_permiso FROM ROL_PERMISOS WHERE id_rol=?";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idRol);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(rs.getInt("id_permiso"));
            }

        } catch (SQLException e) {
            System.out.println("Error listando permisos: " + e.getMessage());
        }
        return lista;
    }
}
