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
public class ProductosDTO {

    private int id_producto;
    private int existencia;

    public ProductosDTO(int id_producto, int existencia) {
        this.id_producto = id_producto;
        this.existencia = existencia;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    
}
