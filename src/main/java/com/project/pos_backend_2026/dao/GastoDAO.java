package dao;

import config.Conexiondb;
import models.Gasto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GastoDAO {

    public boolean insertar(Gasto g) {
        String sql = "INSERT INTO GASTOS(concepto, valor, entidad, fecha, id_usuario) VALUES (?,?,?,?,?)";
        try (Connection con = Conexiondb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, g.getConcepto());
            ps.setDouble(2, g.getValor());
            ps.setString(3, g.getEntidad());
            ps.setDate(4, g.getFecha());
            ps.setInt(5, g.getIdUsuario());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Gasto> listar() {
        List<Gasto> lista = new ArrayList<>();
        String sql = "SELECT * FROM GASTOS";
        try (Connection con = Conexiondb.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Gasto g = new Gasto();
                g.setIdGasto(rs.getInt("id_gasto"));
                g.setConcepto(rs.getString("concepto"));
                g.setValor(rs.getDouble("valor"));
                g.setEntidad(rs.getString("entidad"));
                g.setFecha(rs.getDate("fecha"));
                g.setIdUsuario(rs.getInt("id_usuario"));
                lista.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
