/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.jdbc;

import java.sql.*;

/**
 *
 * @author thisduvan
 */
public class Conexion {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bdalgoritmos?useSSL=false";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "3156845188s1";
    private static Driver driver = null;

    public static synchronized Connection getConnection()
            throws SQLException {
        if (driver == null) {
            try {
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
                System.out.println("Se ha conectado con Exito");
            } catch (Exception e) {
                System.out.println("Fallo en cargar el driver JDBC");
                e.printStackTrace();
            }
        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    
    //Cierre de la conexion
  
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

}
