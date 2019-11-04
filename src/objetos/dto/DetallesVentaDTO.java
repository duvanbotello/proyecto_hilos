/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.dto;

/**
 *
 * @author Duvan Botello
 */
public class DetallesVentaDTO {
    private String decri;
    private int cant;
    private double total;

    public DetallesVentaDTO(String decri, int cant, double total) {
        this.decri = decri;
        this.cant = cant;
        this.total = total;
    }

    public String getDecri() {
        return decri;
    }

    public void setDecri(String decri) {
        this.decri = decri;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
