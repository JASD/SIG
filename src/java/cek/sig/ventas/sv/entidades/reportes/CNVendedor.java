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

    public String getNombreVendedor() {
        return vendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.vendedor = nombreVendedor;
    }

    public Float getProyectadoVendedor() {
        return proyectado;
    }

    public void setProyectadoVendedor(Float proyectadoVendedor) {
        this.proyectado = proyectadoVendedor;
    }

    public Float getNuevasVendedor() {
        return cuentasNuevas;
    }

    public void setNuevasVendedor(Float nuevasVendedor) {
        this.cuentasNuevas = nuevasVendedor;
    }

    public Float getCumplimientoVendedor() {
        return cumplimiento;
    }

    public void setCumplimientoVendedor(Float cumplimientoVendedor) {
        this.cumplimiento = cumplimientoVendedor;
    }
}
