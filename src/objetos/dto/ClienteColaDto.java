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
public class ClienteColaDto {

    int doc_cliente;
    String nombre;
    String apellido;

    public ClienteColaDto(int doc_cliente, String nombre, String apellido) {
        this.doc_cliente = doc_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getDoc_cliente() {
        return doc_cliente;
    }

    public void setDoc_cliente(int doc_cliente) {
        this.doc_cliente = doc_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    

}
