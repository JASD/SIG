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
public class Mes implements Serializable {

    private String mes;
    private int numero;

    public Mes(String mes, int numero) {
        this.mes = mes;
        this.numero = numero;
    }

    public String getMes() {
        return mes;
    }

    public void setMens(String mes) {
        this.mes = mes;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return mes;

    }
}
