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
public class CNVendedor implements Serializable {

    private String vendedor;
    private Float proyectado;
    private Float cuentasNuevas;
    private Float cumplimiento;

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Float getProyectado() {
        return proyectado;
    }

    public void setProyectado(Float proyectado) {
        this.proyectado = proyectado;
    }

    public Float getCuentasNuevas() {
        return cuentasNuevas;
    }

    public void setCuentasNuevas(Float cuentasNuevas) {
        this.cuentasNuevas = cuentasNuevas;
    }

    public Float getCumplimiento() {
        return cumplimiento;
    }

    public void setCumplimiento(Float cumplimiento) {
        this.cumplimiento = cumplimiento;
    }
}
