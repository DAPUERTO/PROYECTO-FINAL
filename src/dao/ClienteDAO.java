package dao;

import config.Conexiondb;
import models.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // 1️ INSERTAR CLIENTE
    public boolean insertar(Cliente cliente) {
        String sql = "INSERT INTO CLIENTES(nombre, numero_identificacion, celular, direccion, correo) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getNumeroIdentificacion());
            ps.setString(3, cliente.getCelular());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getCorreo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    // 2️ LISTAR CLIENTES
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTES";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setNumeroIdentificacion(rs.getString("numero_identificacion"));
                c.setCelular(rs.getString("celular"));
                c.setDireccion(rs.getString("direccion"));
                c.setCorreo(rs.getString("correo"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    // 3️ BUSCAR CLIENTE POR ID
    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM CLIENTES WHERE id_cliente = ?";
        Cliente c = null;

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setNumeroIdentificacion(rs.getString("numero_identificacion"));
                c.setCelular(rs.getString("celular"));
                c.setDireccion(rs.getString("direccion"));
                c.setCorreo(rs.getString("correo"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
        return c;
    }

    // 4️ ACTUALIZAR CLIENTE
    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE CLIENTES SET nombre=?, numero_identificacion=?, celular=?, direccion=?, correo=? WHERE id_cliente=?";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getNumeroIdentificacion());
            ps.setString(3, cliente.getCelular());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getCorreo());
            ps.setInt(6, cliente.getIdCliente());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    // 5️ ELIMINAR CLIENTE
    public boolean eliminar(int id) {
        String sql = "DELETE FROM CLIENTES WHERE id_cliente = ?";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}
