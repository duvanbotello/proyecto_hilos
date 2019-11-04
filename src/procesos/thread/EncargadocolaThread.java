/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos.thread;

import gui.run.Principal;
import java.awt.Color;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import objetos.dto.ClienteColaDto;

/**
 *
 * @author Duvan Botello
 */
public class EncargadocolaThread extends Thread {

    JTextField estado1, estado2, estado3, cliendoc1, clientenomb1, cliendoc2, clientenomb2, cliendoc3, clientenomb3;
    JTable tb1, tb2, tb3;
    DefaultTableModel tb1_cola, tb2_cola, tb3_cola;
    JTextArea tregistro;

    public EncargadocolaThread(String NombreHilo, JTextField estado1,
            JTextField estado2, JTextField estado3, JTextField cliendoc1,
            JTextField clientenomb1, JTextField cliendoc2, JTextField clientenomb2,
            JTextField cliendoc3, JTextField clientenomb3, JTable tb1, JTable tb2, JTable tb3,
            DefaultTableModel tb1_cola, DefaultTableModel tb2_cola,
            DefaultTableModel tb3_cola, JTextArea tregistro) {
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
        this.tb1 = tb1;
        this.tb2 = tb2;
        this.tb3 = tb3;
        this.tb1_cola = tb1_cola;
        this.tb2_cola = tb2_cola;
        this.tb3_cola = tb3_cola;
        this.tregistro = tregistro;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(EncargadoVenderThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {
            System.out.println("ddddd");
            if (Principal.EstadoCaja1 == true && !Principal.cola1.isEmpty()) {
                ClienteColaDto d = Principal.cola1.poll();
                String nombre = d.getNombre();
                String apellido = d.getApellido();
                String Doc = Integer.toString(d.getDoc_cliente());
                MensajeRegistro(nombre, apellido);
                cliendoc1.setText(Doc);
                clientenomb1.setText(nombre + " " + apellido);
                Principal.EstadoCaja1 = false;
                ActualizarEstadosCajas();
                MostrarTabla(1);
            }

            if (Principal.EstadoCaja2 == true && !Principal.cola2.isEmpty()) {
                ClienteColaDto t = Principal.cola2.poll();
                String nombre = t.getNombre();
                String apellido = t.getApellido();
                String Doc = Integer.toString(t.getDoc_cliente());
                MensajeRegistro(nombre, apellido);
                cliendoc2.setText(Doc);
                clientenomb2.setText(nombre + " " + apellido);
                Principal.EstadoCaja2 = false;
                ActualizarEstadosCajas();
                MostrarTabla(2);
            }

            if (Principal.EstadoCaja3 == true && !Principal.cola3.isEmpty()) {
                ClienteColaDto u = Principal.cola3.poll();
                String nombre = u.getNombre();
                String apellido = u.getApellido();
                String Doc = Integer.toString(u.getDoc_cliente());
                MensajeRegistro(nombre, apellido);
                cliendoc3.setText(Doc);
                clientenomb3.setText(nombre + " " + apellido);
                Principal.EstadoCaja3 = false;
                ActualizarEstadosCajas();
                MostrarTabla(3);
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(EncargadocolaThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private synchronized void MensajeRegistro(String nombre, String apellido) {
        tregistro.append("|**HiloCola**|Se Asigno el cliente: " + nombre + " " + apellido + " a una caja..." + "\n");
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

    private synchronized void MostrarTabla(int opt) {

      switch (opt) {
            case 1:
                tb1_cola.setRowCount(0);
                Object file[] = new Object[tb1.getColumnCount()];
                int cont = 1;
                for (ClienteColaDto f : Principal.cola1) {
                    file[0] = cont;
                    cont++;
                    file[1] = f.getDoc_cliente();
                    file[2] = f.getNombre() + " " + f.getApellido();
                    tb1_cola.addRow(file);
                }
                break;
            case 2:
                tb2_cola.setRowCount(0);
                Object file1[] = new Object[tb2.getColumnCount()];
                cont = 1;
                for (ClienteColaDto d : Principal.cola2) {
                    file1[0] = cont;
                    cont++;
                    file1[1] = d.getDoc_cliente();
                    file1[2] = d.getNombre() + " " + d.getApellido();
                    tb2_cola.addRow(file1);
                }
                break;
            case 3:
                tb3_cola.setRowCount(0);
                Object file2[] = new Object[tb2.getColumnCount()];
                cont = 1;
                for (ClienteColaDto g : Principal.cola3) {
                    file2[0] = cont;
                    cont++;
                    file2[1] = g.getDoc_cliente();
                    file2[2] = g.getNombre() + " " + g.getApellido();
                    tb3_cola.addRow(file2);
                }
                break;
        }
    }
}
