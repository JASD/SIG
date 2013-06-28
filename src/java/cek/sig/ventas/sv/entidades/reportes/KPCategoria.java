/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.entidades.reportes;

/**
 *
 * @author Ever
 */
public class KPCategoria {

    private String producto;
    private Float proyKg;
    private Float kgVproductos;
    private Float cumplimiento;

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Float getProyKg() {
        return proyKg;
    }

    public void setProyKg(Float proyKg) {
        this.proyKg = proyKg;
    }

    public Float getKgVproductos() {
        return kgVproductos;
    }

    public void setKgVproductos(Float kgVproductos) {
        this.kgVproductos = kgVproductos;
    }

    public Float getCumplimiento() {
        return cumplimiento;
    }

    public void setCumplimiento(Float cumplimiento) {
        this.cumplimiento = cumplimiento;
    }
}
