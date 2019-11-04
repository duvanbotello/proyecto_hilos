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
public class VentaDTO {
    int id_venta;
    int doc_cliente;
    int doc_empleado;
    int id_caja;
    double saldo_total;

    public VentaDTO(int id_venta, int doc_cliente, int doc_empleado, int id_caja, double saldo_total) {
        this.id_venta = id_venta;
        this.doc_cliente = doc_cliente;
        this.doc_empleado = doc_empleado;
        this.id_caja = id_caja;
        this.saldo_total = saldo_total;
    }
    
    

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getDoc_cliente() {
        return doc_cliente;
    }

    public void setDoc_cliente(int doc_cliente) {
        this.doc_cliente = doc_cliente;
    }

    public int getDoc_empleado() {
        return doc_empleado;
    }

    public void setDoc_empleado(int doc_empleado) {
        this.doc_empleado = doc_empleado;
    }

    public int getId_caja() {
        return id_caja;
    }

    public void setId_caja(int id_caja) {
        this.id_caja = id_caja;
    }

    public double getSaldo_total() {
        return saldo_total;
    }

    public void setSaldo_total(double saldo_total) {
        this.saldo_total = saldo_total;
    }
    
    
}
