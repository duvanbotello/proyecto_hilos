/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos.thread;

import datos.jdbc.ProductosJDBC;
import gui.run.Principal;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import objetos.dto.ProductosDTO;

/**
 *
 * @author Duvan Botello
 */
public class EncargadoSurtirThread extends Thread {

    JTextArea tregistro;

    public EncargadoSurtirThread(JTextArea tregistro) {
        this.tregistro = tregistro;
    }

    @Override
    public void run() {
        ProductosJDBC po = new ProductosJDBC();
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(EncargadoSurtirThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (Principal.StockMinimo == true) {
               
                try {
                    
                    LinkedList<ProductosDTO> list;
                    list = po.selectLista();
                    for (ProductosDTO d : list) {
                        po.UpdateExistencia(d.getId_producto());
                        MensajeRegistro(d.getId_producto());
                    }
                    Principal.StockMinimo = false;
                } catch (SQLException ex) {
                    Logger.getLogger(EncargadoSurtirThread.class.getName()).log(Level.SEVERE, null, ex);
                } 

            }
        }
    }

    private void MensajeRegistro(int id_producto) {
        tregistro.append("|**HiloSutir**| Se Modificado la Existencia del Producto: " + id_producto + "\n");
    }

}
