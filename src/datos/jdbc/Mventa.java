/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.jdbc;

/**
 *
 * @author Duvan Botello
 */
import java.awt.*;

public class Mventa extends Frame {

    public static TextArea txt;

    public Mventa() {
        inicializar();

    }

    private void inicializar() {
        txt = new TextArea();
        this.setLocationRelativeTo(null);
        this.setTitle("Registro de Ventas");
        this.setResizable(false);
        this.setMinimumSize(new Dimension(100, 290)); 
       
        setLayout(new BorderLayout(50, 50));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                salir(e);
            }
        });

      
        add(txt, java.awt.BorderLayout.CENTER);
        pack();
    }
    public void colcoarInicio(){
        txt.setText(txt.getText()+"\n \n |---------------VENTA REALIZADA---------------| \n");
    }

    public void colocar_empleado(int id_venta, int id_Caja, int doc_emple) {
        txt.setText(txt.getText() + "ID VENTA: " + id_venta + "  ID CAJA: " + id_Caja + "  EMPLEADO: " + doc_emple + "\n");
    }

    public void colocar_cliente(int doc, String nombreCliente) {
        txt.setText(txt.getText() + "|---------------DATOS CLIENTE---------------| \n");
        txt.setText(txt.getText() + "DOC: " + doc + "  NOMBRE: " + nombreCliente + "\n");
    }

    public void colocar_deta() {
        txt.setText(txt.getText() + "|---------------DETALLES DE VENTA---------------| \n");
        txt.setText(txt.getText() + "Descripcion \t Cant \t total \n");
    }

    public void colocar_detalles(String descrip, int cant, double total) {
        txt.setText(txt.getText()+descrip+"\t \t" + cant + "\t"+ total+"\n");
    }
    
    public void colocar_total_compra(double total){
        txt.setText(txt.getText() + "\n TOTAL COMPRA: " + total + "\n");
    }

    private void salir(java.awt.event.WindowEvent e) {
        System.exit(0);
    }

}
