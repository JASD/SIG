/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.entidades.reportes;

import java.io.Serializable;

/**
 *
 * @author Antonio
 */
public class CRVendedor implements Serializable{

    private String nombreVendedor;
    private Float proyectadoVendedor;
    private Float recuperadoVendedor;
    private Float cumplimientoVendedor;

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public Float getProyectadoVendedor() {
        return proyectadoVendedor;
    }

    public void setProyectadoVendedor(Float proyectadoVendedor) {
        this.proyectadoVendedor = proyectadoVendedor;
    }

    public Float getRecuperadoVendedor() {
        return recuperadoVendedor;
    }

    public void setRecuperadoVendedor(Float recuperadoVendedor) {
        this.recuperadoVendedor = recuperadoVendedor;
    }

    public Float getCumplimientoVendedor() {
        return cumplimientoVendedor;
    }

    public void setCumplimientoVendedor(Float cumplimientoVendedor) {
        this.cumplimientoVendedor = cumplimientoVendedor;
    }

   
}
