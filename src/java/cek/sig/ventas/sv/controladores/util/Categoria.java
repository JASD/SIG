/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.controladores.util;

import java.io.Serializable;

/**
 *
 * @author Antonio
 */
public class Categoria implements Serializable {

    private String categoria;
    private int numero;

    public Categoria(String mes, int numero) {
        this.categoria = mes;
        this.numero = numero;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return categoria;

    }
}
