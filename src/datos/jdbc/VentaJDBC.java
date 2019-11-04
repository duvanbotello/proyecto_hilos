/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.jdbc;

import static java.lang.Integer.max;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import objetos.dto.VentaDTO;

/**
 *
 * @author Duvan Botello
 */
public class VentaJDBC {

    public int insertVenta(VentaDTO v) throws SQLException {

        String SQL_INSERT_VENTA = "insert into ventas "
                + "(id_venta, doc_cliente, doc_empleado, id_caja, "
                + "saldo_total, fecha_venta) "
                + "values (null," + v.getDoc_cliente() + "," + v.getDoc_empleado() + "," + v.getId_caja() + ",0,curdate())";
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        int id_venta = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_VENTA, PreparedStatement.RETURN_GENERATED_KEYS);
            int t = stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id_venta = rs.getInt(1);
            }

        } finally {
            Conexion.close(rs);
            Conexion.close(conn);
            Conexion.close(stmt);
        }

        return id_venta;
    }

    public void insertar_detalles(int id_venta) throws SQLException {
        
        int num_detalles = numramdon(10);

        int num_productos_existentes = numRegistros("productos");

        Connection conn = null;
        PreparedStatement stmt = null;

        System.out.println("Antes de la pila: ");
        Stack<Integer> productos = NumeroRandom(num_productos_existentes, num_detalles);
        System.out.println("Despues de la Pila: ");
        for (int i = 0; i < num_detalles; i++) {
            int cant_produc = numramdon(10);
            String SQL_INSERT_DETALLE = "INSERT INTO detalles_venta (`id_venta`, `id_producto`, `cant`) "
                    + "VALUES ('" + id_venta + "', '" + productos.pop() + "', '" + cant_produc + "')";

            conn = Conexion.getConnection();
            try {

                stmt = conn.prepareStatement(SQL_INSERT_DETALLE);
                int t = stmt.executeUpdate();
            } finally {

                Conexion.close(conn);
                Conexion.close(stmt);
            }
        }
    }

    public Stack<Integer> NumeroRandom(int rango, int num) {
        int pos, cont = 0;
        Stack<Integer> pCartas = new Stack<>(); //creo una pila
        while (cont < num) {
            pos = (int) Math.floor(Math.random() * rango);
            while (pCartas.contains(pos)) {//si contiene el numero itero hasta agregar uno no repetido
                pos = (int) Math.floor(Math.random() * rango);
            }
            if (pos != 0) {
                pCartas.push(pos);
                cont++;
            }
        }
        return pCartas;
    }

    private int numramdon(int n) {
        int num = 0;
        while (num == 0) {
            num = (int) Math.floor(Math.random() * n);
        }
        return num;
    }

    public int numRegistros(String NombreTabla) {
        String SQL_CONTEO = "SELECT count(*) FROM" + " " + NombreTabla;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int NumRegis = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CONTEO);
            rs = stmt.executeQuery();
            rs.next();
            NumRegis = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return NumRegis;
    }
}
