/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos.thread;

import datos.jdbc.Conexion;
import datos.jdbc.Mventa;
import datos.jdbc.VentaJDBC;
import gui.run.Principal;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import objetos.dto.DetallesVentaDTO;
import objetos.dto.VentaDTO;

/**
 *
 * @author Duvan Botello
 */
public class EncargadoVenderThread extends Thread {

    Mventa mostarVenta = new Mventa();

    boolean EstadoCaja;
    JTextArea tregistro;
    JTextField estado1, estado2, estado3,
            cliendoc1, clientenomb1, cliendoc2,
            clientenomb2, cliendoc3, clientenomb3, edoc1, edoc2, edoc3, ic1, ic2, ic3;
    private final Lock lock = new ReentrantLock();

    public EncargadoVenderThread(String NombreHilo, JTextField estado1,
            JTextField estado2, JTextField estado3, JTextField cliendoc1,
            JTextField clientenomb1, JTextField cliendoc2, JTextField clientenomb2,
            JTextField cliendoc3, JTextField clientenomb3, JTextField edoc1, JTextField edoc2,
            JTextField edoc3, JTextField ic1, JTextField ic2, JTextField ic3, JTextArea tregistro) {
        super(NombreHilo);
        this.estado1 = estado1;
        this.estado2 = estado2;
        this.estado3 = estado3;
        this.cliendoc1 = cliendoc1;
        this.clientenomb1 = clientenomb1;
        this.cliendoc2 = cliendoc2;
        this.clientenomb2 = clientenomb2;
        this.cliendoc3 = cliendoc3;
        this.clientenomb3 = clientenomb3;
        this.edoc1 = edoc1;
        this.edoc2 = edoc2;
        this.edoc3 = edoc3;
        this.ic1 = ic1;
        this.ic2 = ic2;
        this.ic3 = ic3;
        this.tregistro = tregistro;
    }

    @Override
    public void run() {
        
        VentaJDBC ven = new VentaJDBC();
        mostarVenta.setVisible(true);
        while (true) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(EncargadoVenderThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            /**
             * *********************************************************************
             * MODULO 1
             * ********************************************************************
             */
            if (Principal.EstadoCaja1 == false) {

                int doc_cliente = Integer.parseInt(cliendoc1.getText().trim());
                int doc_empleado = Integer.parseInt(edoc1.getText().trim());
                int id_caja = Integer.parseInt(ic1.getText().trim());
                int idd_venta = 0;
                /**
                 * ********* INSERTAR VENTA ******************
                 */
                try {
                    idd_venta = ven.insertVenta(new VentaDTO(1, doc_cliente, doc_empleado, id_caja, 0));

                } catch (SQLException ex) {
                    Logger.getLogger(EncargadoVenderThread.class.getName()).log(Level.SEVERE, null, ex);
                }

                /**
                 * ********* FIN INSERTAR VENTA ******************
                 */
                /**
                 * ********* INSERTAR DETALLES VENTA ******************
                 */
                try {

                    ven.insertar_detalles(idd_venta);

                } catch (SQLException ex) {
                    Logger.getLogger(EncargadoVenderThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                /**
                 * ********* FIN INSERTAR DETALLES VENTA ******************
                 */

                /**
                 * ********* MOSTRAR VENTA ******************
                 */
//                mostarVenta.setVisible(true);
                mostarVenta.colcoarInicio();
                mostarVenta.colocar_empleado(idd_venta, id_caja, doc_empleado);
                mostarVenta.colocar_cliente(Integer.parseInt(cliendoc1.getText()), clientenomb1.getText());
                mostarVenta.colocar_deta();

                try {
                    LinkedList<DetallesVentaDTO> detalles = select(idd_venta);
                    for (DetallesVentaDTO d : detalles) {
                        mostarVenta.colocar_detalles(d.getDecri(), d.getCant(), d.getTotal());
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(EncargadoVenderThread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(EncargadoVenderThread.class.getName()).log(Level.SEVERE, null, ex);
                }

                /**
                 * ********* FIN MOSTRAR VENTA ******************
                 */
                MensajeRegistro(clientenomb1.getText());
                Principal.EstadoCaja1 = true;
                limpiarTxt(cliendoc1, clientenomb1);               
                ActualizarEstadosCajas();
            }
            /**
             * *********************************************************************
             * FIN MODULO 1
             * ********************************************************************
             */

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(EncargadoVenderThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            /**
             * *********************************************************************
             * MODULO 2
             * ********************************************************************
             */

            if (Principal.EstadoCaja2 == false) {
                int doc_cliente = Integer.parseInt(cliendoc2.getText().trim());
                int doc_empleado = Integer.parseInt(edoc2.getText().trim());
                int id_caja = Integer.parseInt(ic2.getText().trim());
                int idd_venta = 0;
                /**
                 * ********* INSERTAR VENTA ******************
                 */
                try {
                    idd_venta = ven.insertVenta(new VentaDTO(1, doc_cliente, doc_empleado, id_caja, 0));

                } catch (SQLException ex) {
                    Logger.getLogger(EncargadoVenderThread.class.getName()).log(Level.SEVERE, null, ex);
                }

                /**
                 * ********* FIN INSERTAR VENTA ******************
                 */
                /**
                 * ********* INSERTAR DETALLES VENTA ******************
                 */
                try {

                    ven.insertar_detalles(idd_venta);

                } catch (SQLException ex) {
                    Logger.getLogger(EncargadoVenderThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                /**
                 * ********* FIN INSERTAR DETALLES VENTA ******************
                 */

                /**
                 * ********* MOSTRAR VENTA ******************
                 */
//                mostarVenta.setVisible(true);
                mostarVenta.colcoarInicio();
                mostarVenta.colocar_empleado(idd_venta, id_caja, doc_empleado);
                mostarVenta.colocar_cliente(Integer.parseInt(cliendoc2.getText()), clientenomb2.getText());
                mostarVenta.colocar_deta();

                try {
                    LinkedList<DetallesVentaDTO> detalles = select(idd_venta);
                    for (DetallesVentaDTO d : detalles) {
                        mostarVenta.colocar_detalles(d.getDecri(), d.getCant(), d.getTotal());
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(EncargadoVenderThread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(EncargadoVenderThread.class.getName()).log(Level.SEVERE, null, ex);
                }

                /**
                 * ********* FIN MOSTRAR VENTA ******************
                 */
                MensajeRegistro(clientenomb2.getText());
                Principal.EstadoCaja2 = true;
                limpiarTxt(cliendoc2, clientenomb2);
                ActualizarEstadosCajas();
            }
            /**
             * *********************************************************************
             * FIN MODULO 2
             * ********************************************************************
             */

            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(EncargadoVenderThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            /**
             * *********************************************************************
             * MODULO 3
             * ********************************************************************
             */
            if (Principal.EstadoCaja3 == false) {
                int doc_cliente = Integer.parseInt(cliendoc3.getText().trim());
                int doc_empleado = Integer.parseInt(edoc3.getText().trim());
                int id_caja = Integer.parseInt(ic3.getText().trim());
                int idd_venta = 0;
                /**
                 * ********* INSERTAR VENTA ******************
                 */
                try {
                    idd_venta = ven.insertVenta(new VentaDTO(1, doc_cliente, doc_empleado, id_caja, 0));

                } catch (SQLException ex) {
                    Logger.getLogger(EncargadoVenderThread.class.getName()).log(Level.SEVERE, null, ex);
                }

                /**
                 * ********* FIN INSERTAR VENTA ******************
                 */
                /**
                 * ********* INSERTAR DETALLES VENTA ******************
                 */
                try {

                    ven.insertar_detalles(idd_venta);

                } catch (SQLException ex) {
                    Logger.getLogger(EncargadoVenderThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                /**
                 * ********* FIN INSERTAR DETALLES VENTA ******************
                 */

                /**
                 * ********* MOSTRAR VENTA ******************
                 */
//                mostarVenta.setVisible(true);
                mostarVenta.colcoarInicio();
                mostarVenta.colocar_empleado(idd_venta, id_caja, doc_empleado);
                mostarVenta.colocar_cliente(Integer.parseInt(cliendoc3.getText()), clientenomb3.getText());
                mostarVenta.colocar_deta();

                try {
                    LinkedList<DetallesVentaDTO> detalles = select(idd_venta);
                    for (DetallesVentaDTO d : detalles) {
                        mostarVenta.colocar_detalles(d.getDecri(), d.getCant(), d.getTotal());
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(EncargadoVenderThread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(EncargadoVenderThread.class.getName()).log(Level.SEVERE, null, ex);
                }

                /**
                 * ********* FIN MOSTRAR VENTA ******************
                 */
                MensajeRegistro(clientenomb3.getText());
                Principal.EstadoCaja3 = true;
                limpiarTxt(cliendoc3, clientenomb3);
                ActualizarEstadosCajas();
            }
            /**
             * *********************************************************************
             * FIN MODULO 2
             * ********************************************************************
             */
        }
    }

    private void limpiarTxt(JTextField id_cliente, JTextField nom_cliente) {
        id_cliente.setText("");
        nom_cliente.setText("");
    }

    public void ActualizarEstadosCajas() {
        if (Principal.EstadoCaja1 == true) {
            estado1.setBackground(Color.GREEN);
            estado1.setText("DISPONIBLE");
        } else {
            estado1.setBackground(Color.RED);
            estado1.setText("NO DISPONIBLE");
        }

        if (Principal.EstadoCaja2 == true) {
            estado2.setBackground(Color.GREEN);
            estado2.setText("DISPONIBLE");
        } else {
            estado2.setBackground(Color.RED);
            estado2.setText("NO DISPONIBLE");
        }

        if (Principal.EstadoCaja3 == true) {
            estado3.setBackground(Color.GREEN);
            estado3.setText("DISPONIBLE");
        } else {
            estado3.setBackground(Color.RED);
            estado3.setText("NO DISPONIBLE");
        }

    }

    public LinkedList<DetallesVentaDTO> select(int id_venta) throws SQLException {

        String SQL_CONSULTA = "select descripcion, cant, total from detalles_venta where id_venta =" + id_venta;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList<DetallesVentaDTO> personas = new LinkedList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CONSULTA);
            rs = stmt.executeQuery();

            while (rs.next()) {

                String descripro = rs.getString(1);
                int cant = rs.getInt(2);
                double total = rs.getDouble(3);
                DetallesVentaDTO detalle = new DetallesVentaDTO(descripro, cant, total);
                personas.add(detalle);

            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return personas;
    }

    private synchronized void MensajeRegistro(String nombre) {
        tregistro.append("|**HiloVenta**|Se Realizo una venta al cliente: " + nombre + "\n");
    }

}
