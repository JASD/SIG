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
public class VPPTOCategoria implements Serializable {

    private String categoria;
    private Float ventas;
    private Float presupuesto;
    private Float variacion;
    private Float ventasAcum;
    private Float presupuestoAcum;
    private Float variacionAcum;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Float getVentas() {
        return ventas;
    }

    public void setVentas(Float ventas) {
        this.ventas = ventas;
    }

    public Float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Float presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Float getVariacion() {
        return variacion;
    }

    public void setVariacion(Float variacion) {
        this.variacion = variacion;
    }

    public Float getVentasAcum() {
        return ventasAcum;
    }

    public void setVentasAcum(Float ventasAcum) {
        this.ventasAcum = ventasAcum;
    }

    public Float getPresupuestoAcum() {
        return presupuestoAcum;
    }

    public void setPresupuestoAcum(Float presupuestoAcum) {
        this.presupuestoAcum = presupuestoAcum;
    }

    public Float getVariacionAcum() {
        return variacionAcum;
    }

    public void setVariacionAcum(Float variacionAcum) {
        this.variacionAcum = variacionAcum;
    }
}
