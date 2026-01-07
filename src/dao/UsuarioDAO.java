package dao;

import config.Conexiondb;
import models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // 1️ REGISTRAR USUARIO
    public boolean insertar(Usuario u) {
        String sql = "INSERT INTO USUARIOS(nombre_usuario, password_hash, nombre_completo, estado) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getPasswordHash());
            ps.setString(3, u.getNombreCompleto());
            ps.setBoolean(4, u.isEstado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    // 2️ VALIDAR LOGIN
    public Usuario validarLogin(String usuario, String passwordHash) {
        String sql = "SELECT * FROM USUARIOS WHERE nombre_usuario=? AND password_hash=? AND estado=TRUE";
        Usuario u = null;

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, passwordHash);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombreUsuario(rs.getString("nombre_usuario"));
                u.setNombreCompleto(rs.getString("nombre_completo"));
                u.setEstado(rs.getBoolean("estado"));
            }

        } catch (SQLException e) {
            System.out.println("Error en login: " + e.getMessage());
        }
        return u;
    }

    // 3️ LISTAR USUARIOS
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM USUARIOS";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombreUsuario(rs.getString("nombre_usuario"));
                u.setNombreCompleto(rs.getString("nombre_completo"));
                u.setEstado(rs.getBoolean("estado"));
                lista.add(u);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }

    // 4️ ACTUALIZAR USUARIO
    public boolean actualizar(Usuario u) {
        String sql = "UPDATE USUARIOS SET nombre_usuario=?, nombre_completo=?, estado=? WHERE id_usuario=?";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getNombreCompleto());
            ps.setBoolean(3, u.isEstado());
            ps.setInt(4, u.getIdUsuario());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    // 5️ ELIMINAR USUARIO
    public boolean eliminar(int id) {
        String sql = "DELETE FROM USUARIOS WHERE id_usuario=?";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
}
