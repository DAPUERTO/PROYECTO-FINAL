package com.project.pos_backend_2026.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.pos_backend_2026.config.Conexiondb;
import com.project.pos_backend_2026.models.Proveedor;

public class ProveedorDAO {

    /**
     * Inserta un nuevo proveedor en la base de datos.
     * 
     * @param p Objeto Proveedor con los datos a registrar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertar(Proveedor p) {
        String sql = "INSERT INTO PROVEEDORES(nombre, numero_identificacion, celular, direccion, correo) VALUES (?,?,?,?,?)";
        try (Connection con = Conexiondb.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getNumeroIdentificacion());
            ps.setString(3, p.getCelular());
            ps.setString(4, p.getDireccion());
            ps.setString(5, p.getCorreo());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene una lista de todos los proveedores registrados.
     * 
     * @return Lista de objetos Proveedor.
     */
    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM PROVEEDORES";
        try (Connection con = Conexiondb.getConexion();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("id_proveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setNumeroIdentificacion(rs.getString("numero_identificacion"));
                p.setCelular(rs.getString("celular"));
                p.setDireccion(rs.getString("direccion"));
                p.setCorreo(rs.getString("correo"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
