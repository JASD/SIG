/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.controladores;

/**
 *
 * @author antonio
 */
public class Auxiliares {

    public String getMes(int mes) {
        String r = "";
        switch (mes) {
            case 1:
                r = "enero";
                break;
            case 2:
                r = "febrero";
                break;
            case 3:
                r = "marzo";
                break;
            case 4:
                r = "abril";
                break;
            case 5:
                r = "mayo";
                break;
            case 6:
                r = "junio";
                break;
            case 7:
                r = "julio";
                break;
            case 8:
                r = "agosto";
                break;
            case 9:
                r = "septiembre";
                break;
            case 10:
                r = "octubre";
                break;
            case 11:
                r = "noviembre";
                break;
            case 12:
                r = "diciembre";
                break;
        }
        return r;
    }
}
