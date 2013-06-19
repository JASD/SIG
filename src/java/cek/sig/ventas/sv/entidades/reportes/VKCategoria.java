/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.entidades.reportes;

import java.io.Serializable;

/**
 *
 * @author Ever
 */
public class VKCategoria implements Serializable{

    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Float getKilogramos() {
        return kilogramos;
    }

    public void setKilogramos(Float kilogramos) {
        this.kilogramos = kilogramos;
    }

    public Float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Float porcentaje) {
        this.porcentaje = porcentaje;
    }
    private Float kilogramos;
    private Float porcentaje;
    



   
}
