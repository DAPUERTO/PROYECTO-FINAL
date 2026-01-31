package com.project.pos_backend_2026.dao;

import com.project.pos_backend_2026.config.Conexiondb;
import com.project.pos_backend_2026.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    /**
     * Registra un nuevo usuario en el sistema.
     * 
     * @param u Objeto Usuario con los datos a registrar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
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

    /**
     * Verifica si existe un usuario con el nombre dado.
     * 
     * @param nombreUsuario Nombre a verificar
     * @return true si existe, false si no.
     */
    public boolean existeUsuario(String nombreUsuario) {
        String sql = "SELECT id_usuario FROM USUARIOS WHERE nombre_usuario = ?";
        try (Connection con = Conexiondb.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar existencia de usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca un usuario por su nombre de usuario.
     * 
     * @param nombreUsuario El nombre del usuario a buscar.
     * @return El objeto Usuario si existe, null en caso contrario.
     */
    public Usuario buscarPorNombre(String nombreUsuario) {
        String sql = "SELECT * FROM USUARIOS WHERE nombre_usuario = ?";
        Usuario u = null;
        try (Connection con = Conexiondb.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario();
                    u.setIdUsuario(rs.getInt("id_usuario"));
                    u.setNombreUsuario(rs.getString("nombre_usuario"));
                    u.setPasswordHash(rs.getString("password_hash"));
                    u.setNombreCompleto(rs.getString("nombre_completo"));
                    u.setEstado(rs.getBoolean("estado"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario por nombre: " + e.getMessage());
        }
        return u;
    }

    /**
     * Actualiza el estado de un usuario.
     * 
     * @param idUsuario ID del usuario.
     * @param estado    Nuevo estado.
     * @return true si se actualizó correctamente.
     */
    public boolean actualizarEstado(int idUsuario, boolean estado) {
        String sql = "UPDATE USUARIOS SET estado = ? WHERE id_usuario = ?";
        try (Connection con = Conexiondb.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, estado);
            ps.setInt(2, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar estado del usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Valida las credenciales de un usuario para el inicio de sesión.
     * 
     * @param usuario      Nombre de usuario.
     * @param passwordHash Hash de la contraseña.
     * @return Objeto Usuario si las credenciales son válidas y el usuario está
     *         activo, null en caso contrario.
     */
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

    /**
     * Obtiene una lista de todos los usuarios registrados.
     * 
     * @return Lista de objetos Usuario.
     */
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

    /**
     * Actualiza la información de un usuario existente.
     * 
     * @param u Objeto Usuario con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
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

    /**
     * Elimina un usuario de la base de datos por su ID.
     * 
     * @param id Identificador único del usuario a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
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
