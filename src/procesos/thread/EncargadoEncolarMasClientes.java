/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos.thread;

import datos.jdbc.ColasDaoJDBC;
import gui.run.Principal;
import static gui.run.Principal.cola1;
import static gui.run.Principal.cola2;
import static gui.run.Principal.cola3;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Duvan Botello
 */
public class EncargadoEncolarMasClientes extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
                if (Principal.cola1.isEmpty() & Principal.cola2.isEmpty() & Principal.cola3.isEmpty()) {
                    int encolar = JOptionPane.showConfirmDialog(null, "¿Desaa Encolar mas Clientes?");
                    if (encolar == JOptionPane.YES_OPTION) {
                        ColasDaoJDBC colasDao = new ColasDaoJDBC();
                        try {
                            int numClientes = colasDao.numRegistros("clientes") - 1;
                            int NumCola1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Total de Clientes Diponibles: " + numClientes
                                    + "\n ¿Cuantos Clientes Desea Encolar En la Primera Fila.?"));
                            int NumCola2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Total de Clientes Diponibles: " + (numClientes - NumCola1)
                                    + "\n ¿Cuantos Clientes Desea Encolar En la Segunda Fila.?"));
                            int NumCola3 = Integer.parseInt(JOptionPane.showInputDialog(null, "Total de Clientes Diponibles: " + (numClientes - NumCola1 - NumCola2)
                                    + "\n ¿Cuantos Clientes Desea Encolar En la Segunda Fila.?"));
                            colasDao.llenarCola(cola1, cola2, cola3, NumCola1, NumCola2, NumCola3);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Ocurrio un Error al Ingresar los Datos.");
                        }

                    } else {
                        Thread.sleep(500000000);
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(EncargadoEncolarMasClientes.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
