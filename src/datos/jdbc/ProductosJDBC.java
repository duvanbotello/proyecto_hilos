package datos.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import objetos.dto.ProductosDTO;

/**
 *
 * @author Duvan Botello
 */
public class ProductosJDBC {
    
    public boolean select() throws SQLException {
        boolean HayMinimo = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SQL_SELECT = "SELECT  id_producto, nombre, precio, "
                + "stockMinimo, existencia FROM productos where (stockMinimo+10)>existencia;";
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            if (rs.next()) {
                HayMinimo = true;
               
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
            System.out.println("Finalice conexion");

        }
        return HayMinimo;
    }

    public synchronized LinkedList<ProductosDTO> selectLista() throws SQLException {

        LinkedList<ProductosDTO> ProductosStockMini = new LinkedList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SQL_SELECT = "SELECT  id_producto, nombre, precio, "
                + "stockMinimo, existencia FROM productos where (stockMinimo+10)>existencia;";
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idProducTemp = rs.getInt(1);
                int Existencia = rs.getInt(5);               
                ProductosStockMini.add(new ProductosDTO(idProducTemp, Existencia));
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);

        }
        return ProductosStockMini;
    }
    
    public void UpdateExistencia(int id_producto) throws SQLException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SQL_UPDATE = "update productos set existencia=(existencia+20) where id_producto ="+id_producto;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.executeUpdate();

        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);

        }
    }

}
