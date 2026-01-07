package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import config.Conexiondb;
import models.Rol;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {

    public boolean insertar(Rol rol) {
        String sql = "INSERT INTO ROLES(nombre_rol, id_usuario) VALUES (?,?)";
        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, rol.getNombreRol());
            ps.setInt(2, rol.getIdUsuario());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Rol> listar() {
        List<Rol> lista = new ArrayList<>();
        String sql = "SELECT * FROM ROLES";
        try (Connection con = Conexiondb.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Rol r = new Rol();
                r.setIdRol(rs.getInt("id_rol"));
                r.setNombreRol(rs.getString("nombre_rol"));
                r.setIdUsuario(rs.getInt("id_usuario"));
                lista.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Rol buscarPorId(int id) {
        Rol rol = null;
        String sql = "SELECT * FROM ROLES WHERE id_rol=?";
        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rol = new Rol();
                rol.setIdRol(rs.getInt("id_rol"));
                rol.setNombreRol(rs.getString("nombre_rol"));
                rol.setIdUsuario(rs.getInt("id_usuario"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rol;
    }

    public boolean actualizar(Rol rol) {
        String sql = "UPDATE ROLES SET nombre_rol=?, id_usuario=? WHERE id_rol=?";
        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, rol.getNombreRol());
            ps.setInt(2, rol.getIdUsuario());
            ps.setInt(3, rol.getIdRol());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM ROLES WHERE id_rol=?";
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
