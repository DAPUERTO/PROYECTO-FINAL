package dao;

import config.Conexiondb;
import models.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    // INSERT
    public boolean insertar(Categoria categoria) {
        String sql = "INSERT INTO CATEGORIAS (nombre_categoria) VALUES (?)";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, categoria.getNombreCategoria());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // SELECT ALL
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIAS";

        try (Connection con = Conexiondb.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombreCategoria(rs.getString("nombre_categoria"));
                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // SELECT BY ID
    public Categoria buscarPorId(int id) {
        Categoria categoria = null;
        String sql = "SELECT * FROM CATEGORIAS WHERE id_categoria = ?";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombreCategoria(rs.getString("nombre_categoria"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoria;
    }

    // UPDATE
    public boolean actualizar(Categoria categoria) {
        String sql = "UPDATE CATEGORIAS SET nombre_categoria = ? WHERE id_categoria = ?";

        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, categoria.getNombreCategoria());
            ps.setInt(2, categoria.getIdCategoria());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean eliminar(int id) {
        String sql = "DELETE FROM CATEGORIAS WHERE id_categoria = ?";

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
