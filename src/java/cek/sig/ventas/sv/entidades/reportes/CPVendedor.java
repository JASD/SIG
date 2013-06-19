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
public class CPVendedor implements Serializable {
     private String nombreVendedor;
    private Float perdidoVendedor;

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public Float getPerdidoVendedor() {
        return perdidoVendedor;
    }

    public void setPerdidoVendedor(Float perdidoVendedor) {
        this.perdidoVendedor = perdidoVendedor;
    }

    public Float getTotalCartera() {
        return totalCartera;
    }

    public void setTotalCartera(Float totalCartera) {
        this.totalCartera = totalCartera;
    }

    public Float getPorcentajeCartera() {
        return porcentajeCartera;
    }

    public void setPorcentajeCartera(Float porcentajeCartera) {
        this.porcentajeCartera = porcentajeCartera;
    }
    private Float totalCartera;
    private Float porcentajeCartera;
    
}
