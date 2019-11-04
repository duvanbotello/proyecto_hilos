/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.jdbc;

import java.util.Stack;
import javax.swing.JTextField;

/**
 *
 * @author Duvan Botello
 */
public interface MetodosDao {
    
    public int numRegistros(String NombreTabla);
        
    public Stack NumeroRandom(int nCartas,int num);
    
    public void AsignarCajaYEmpleado(JTextField c1, JTextField c2, JTextField c3,
            JTextField edoc1, JTextField edoc2, JTextField edoc3, JTextField en1
            , JTextField en2, JTextField en3, JTextField ic1,
            JTextField ic2, JTextField ic3);
    
}
