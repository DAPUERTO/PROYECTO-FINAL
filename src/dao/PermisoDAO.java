package dao;

import config.Conexiondb;
import models.Permiso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PermisoDAO {

    public boolean insertar(Permiso p) {
        String sql = "INSERT INTO PERMISOS(nombre_permiso, codigo_modulo) VALUES (?,?)";
        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombrePermiso());
            ps.setString(2, p.getCodigoModulo());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Permiso> listar() {
        List<Permiso> lista = new ArrayList<>();
        String sql = "SELECT * FROM PERMISOS";
        try (Connection con = Conexiondb.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Permiso p = new Permiso();
                p.setIdPermiso(rs.getInt("id_permiso"));
                p.setNombrePermiso(rs.getString("nombre_permiso"));
                p.setCodigoModulo(rs.getString("codigo_modulo"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM PERMISOS WHERE id_permiso=?";
        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
