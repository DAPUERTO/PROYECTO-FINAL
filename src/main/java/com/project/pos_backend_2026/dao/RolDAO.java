package com.project.pos_backend_2026.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.pos_backend_2026.config.Conexiondb;
import com.project.pos_backend_2026.models.Rol;

public class RolDAO {

    /**
     * Inserta un nuevo rol en la base de datos.
     * 
     * @param rol Objeto Rol con los datos a registrar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
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

    /**
     * Obtiene una lista de todos los roles registrados.
     * 
     * @return Lista de objetos Rol.
     */
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

    /**
     * Busca un rol por su ID.
     * 
     * @param id Identificador único del rol.
     * @return Objeto Rol si se encuentra, null si no existe.
     */
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

    /**
     * Actualiza la información de un rol existente.
     * 
     * @param rol Objeto Rol con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
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

    /**
     * Elimina un rol de la base de datos por su ID.
     * 
     * @param id Identificador del rol a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
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
