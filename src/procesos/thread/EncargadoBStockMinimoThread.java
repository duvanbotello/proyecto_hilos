/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos.thread;

import datos.jdbc.ProductosJDBC;
import gui.run.Principal;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Duvan Botello
 */
public class EncargadoBStockMinimoThread extends Thread {

    JLabel mensaje;

    public EncargadoBStockMinimoThread(JLabel x) {
        this.mensaje = x;
    }

    @Override
    public void run() {
        ProductosJDBC p = new ProductosJDBC();
        while (true) {

            try {
                Thread.sleep(2000);

                boolean hay = p.select();
                if (hay == true) {
                    mensajes("Hay Poductos con existencia minima a su StockMinimo");
                    mensaje.setForeground(Color.YELLOW);
                    Principal.StockMinimo = true;
                    System.out.println("sssss");
                    Thread.sleep(5000);
                } else {
                    mensajes("No hay Productos Con Existencia Minima");
                    mensaje.setForeground(Color.green);
                }

            } catch (SQLException ex) {
                Logger.getLogger(EncargadoBStockMinimoThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(EncargadoBStockMinimoThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void mensajes(String M) {
        
        mensaje.setText("");
        mensaje.setText(M);
    }

}
