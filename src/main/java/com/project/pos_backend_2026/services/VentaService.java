package services;

import config.Conexiondb;
import dao.VentaDAO;
import dao.DetalleVentaDAO;
import models.Venta;
import models.DetalleVenta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VentaService {

    private final VentaDAO ventaDAO = new VentaDAO();
    private final DetalleVentaDAO detalleDAO = new DetalleVentaDAO();

    /**
     * Registra una venta completa con sus detalles usando transacciones
     */
    public boolean registrarVentaCompleta(Venta venta, List<DetalleVenta> detalles) {

        Connection con = null;

        try {
            con = Conexiondb.getConexion();
            con.setAutoCommit(false); //  Inicia transacción

            // 1️ Insertar venta
            int idVenta = ventaDAO.registrarVenta(venta, con);

            if (idVenta == 0) {
                con.rollback();
                return false;
            }

            // 2️ Insertar detalles
            for (DetalleVenta d : detalles) {
                d.setIdVenta(idVenta);

                if (!detalleDAO.insertar(d, con)) {
                    con.rollback();
                    return false;
                }
            }

            // 3️ Confirmar transacción
            con.commit();
            return true;

        } catch (SQLException e) {

            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.out.println("Error en rollback: " + ex.getMessage());
            }

            System.out.println("Error en VentaService: " + e.getMessage());
            return false;

        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error cerrando conexión: " + e.getMessage());
            }
        }
    }
}
