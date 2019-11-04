/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;
import javax.swing.JTextField;

/**
 *
 * @author Duvan Botello
 */
public class MetodosDaoJDBC implements MetodosDao {

    //METODO PARA TRAER EL CANTIDAD DE REGISTROS DENTRO DE UNA TABLA
    @Override
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

    @Override
    public Stack NumeroRandom(int nCartas, int num) {
        int pos, cont = 0;
        Stack< Integer> pCartas = new Stack<>(); //creo una pila
        while (cont < 3) {
            pos = (int) Math.floor(Math.random() * nCartas);
            while (pCartas.contains(pos)) {//si contiene el numero itero hasta agregar uno no repetido
                pos = (int) Math.floor(Math.random() * nCartas);
            }
            if (pos != 0) {
                pCartas.push(pos);
                cont++;
            }
        }
        return pCartas;
    }

    @Override
    public void AsignarCajaYEmpleado(JTextField c1, JTextField c2, JTextField c3,
            JTextField edoc1, JTextField edoc2, JTextField edoc3, JTextField en1,
             JTextField en2, JTextField en3, JTextField ic1,
            JTextField ic2, JTextField ic3) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //ASIGNAR ALEATORIA MENTE UNA CAJA   
            int cantRegistroEmpleados = numRegistros("empleados");
            Stack id_empelados = NumeroRandom(cantRegistroEmpleados, 3);

            int cantRegistroCajas = numRegistros("cajas");
            Stack caja = NumeroRandom(cantRegistroCajas, 3);

            String SQL_ASIGNAR_CAJA = "SELECT id, nombre_caja FROM cajas WHERE id = " + caja.pop();
            String SQL_ASIGNAR_EMPLEADO = "SELECT doc_empleado, nombre, apellido FROM empleados WHERE num_empleado = " + id_empelados.pop();

            conn = Conexion.getConnection();

            //CAJA1
            stmt = conn.prepareStatement(SQL_ASIGNAR_CAJA);
            rs = stmt.executeQuery();
            rs.next();
            int id_caja = rs.getInt(1);
            String nom_caja = rs.getString(2);
            c1.setText(nom_caja);
            ic1.setText(Integer.toString(id_caja));
            //CAJA1

            //EMPELADO1
            stmt = conn.prepareStatement(SQL_ASIGNAR_EMPLEADO);
            rs = stmt.executeQuery();
            rs.next();
            int id_doc_emple = rs.getInt(1);
            String nom_emple = rs.getString(2);
            String ape_emple = rs.getString(3);

            edoc1.setText(Integer.toString(id_doc_emple));
            en1.setText(nom_emple + " " + ape_emple);
            //EMPELADO1

            //ASIGNAR ALEATORIA MENTE UNA CAJA           
            String SQL_ASIGNAR_CAJA2 = "SELECT id, nombre_caja FROM cajas WHERE id = " + caja.pop();
            String SQL_ASIGNAR_EMPLEADO2 = "SELECT doc_empleado, nombre, apellido FROM empleados WHERE num_empleado = " + id_empelados.pop();

            //CAJA2
            stmt = conn.prepareStatement(SQL_ASIGNAR_CAJA2);
            rs = stmt.executeQuery();
            rs.next();
            id_caja = rs.getInt(1);
            nom_caja = rs.getString(2);
            c2.setText(nom_caja);
            ic2.setText(Integer.toString(id_caja));
            //CAJA2

            //EMPELADO2
            stmt = conn.prepareStatement(SQL_ASIGNAR_EMPLEADO2);
            rs = stmt.executeQuery();
            rs.next();
            id_doc_emple = rs.getInt(1);
            nom_emple = rs.getString(2);
            ape_emple = rs.getString(3);

            edoc2.setText(Integer.toString(id_doc_emple));
            en2.setText(nom_emple + " " + ape_emple);
            //EMPELADO2

            //ASIGNAR ALEATORIA MENTE UNA CAJA
            String SQL_ASIGNAR_CAJA3 = "SELECT id, nombre_caja FROM cajas WHERE id = " + caja.pop();
            String SQL_ASIGNAR_EMPLEADO3 = "SELECT doc_empleado, nombre, apellido FROM empleados WHERE num_empleado = " + id_empelados.pop();

            //CAJA3
            stmt = conn.prepareStatement(SQL_ASIGNAR_CAJA3);
            rs = stmt.executeQuery();
            rs.next();
            id_caja = rs.getInt(1);
            nom_caja = rs.getString(2);
            c3.setText(nom_caja);
            ic3.setText(Integer.toString(id_caja));
            //CAJA3

            //EMPELADO3
            stmt = conn.prepareStatement(SQL_ASIGNAR_EMPLEADO3);
            rs = stmt.executeQuery();
            rs.next();
            id_doc_emple = rs.getInt(1);
            nom_emple = rs.getString(2);
            ape_emple = rs.getString(3);

            edoc3.setText(Integer.toString(id_doc_emple));
            en3.setText(nom_emple + " " + ape_emple);
            //EMPELADO3

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
}
