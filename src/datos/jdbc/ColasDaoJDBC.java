/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.jdbc;

import static java.lang.Math.E;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Stack;
import javax.swing.JOptionPane;
import objetos.dto.ClienteColaDto;

/**
 *
 * @author Duvan Botello
 */
public class ColasDaoJDBC {

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

    public boolean llenarCola(LinkedList<ClienteColaDto> cola1, LinkedList<ClienteColaDto> cola2, LinkedList<ClienteColaDto> cola3, int cant1, int cant2, int cant3) {
        boolean realizo = false;
        int numeroClientes = numRegistros("clientes");
        int ClientesCola = cant1 + cant2 + cant3;
        if (ClientesCola <= numeroClientes-1) {
            
            Stack NumeroSinRepetir = NumeroRandom(numeroClientes, ClientesCola);
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                conn = Conexion.getConnection();

                //LLENAR COLA 1
                for (int i = 0; i < cant1; i++) {

                    String SQL_CLIENTE = "SELECT doc_cliente, nombre, apellido FROM clientes WHERE id_clientes = " + NumeroSinRepetir.pop();
                    stmt = conn.prepareStatement(SQL_CLIENTE);
                    rs = stmt.executeQuery();
                    rs.next();
                    int id_cliente = rs.getInt(1);
                    String nombre = rs.getString(2);
                    String apellido = rs.getString(3);
                    ClienteColaDto persona = new ClienteColaDto(id_cliente, nombre, apellido);
                    cola1.offer(persona);

                }

                //LLENAR COLA 2
                for (int i = 0; i < cant2; i++) {

                    String SQL_CLIENTE = "SELECT doc_cliente, nombre, apellido FROM clientes WHERE id_clientes = " + NumeroSinRepetir.pop();
                    stmt = conn.prepareStatement(SQL_CLIENTE);
                    rs = stmt.executeQuery();
                    rs.next();
                    int id_cliente = rs.getInt(1);
                    String nombre = rs.getString(2);
                    String apellido = rs.getString(3);
                    ClienteColaDto persona = new ClienteColaDto(id_cliente, nombre, apellido);
                    cola2.offer(persona);

                }

                //LLENAR COLA 3
                for (int i = 0; i < cant3; i++) {

                    String SQL_CLIENTE = "SELECT doc_cliente, nombre, apellido FROM clientes WHERE id_clientes = " + NumeroSinRepetir.pop();
                    stmt = conn.prepareStatement(SQL_CLIENTE);
                    rs = stmt.executeQuery();
                    rs.next();
                    int id_cliente = rs.getInt(1);
                    String nombre = rs.getString(2);
                    String apellido = rs.getString(3);
                    ClienteColaDto persona = new ClienteColaDto(id_cliente, nombre, apellido);
                    cola3.offer(persona);

                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
            }
             return realizo = true;
        }else{
            JOptionPane.showMessageDialog(null, "El numero de clientes supera los existentes en la BD \n Numero de clientes "
                    + "disponibles: "+ numeroClientes);
            return realizo;
        }

    }

    public Stack NumeroRandom(int nCartas, int num) {
        int pos, cont = 0;
        Stack< Integer> pCartas = new Stack<>(); //creo una pila
        while (cont < num) {
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

}
