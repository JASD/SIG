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
public class CVVendedor implements Serializable {

    private String vendedor;
    private Float proyectado;
    private Float ventas;
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

    public Float getVentas() {
        return ventas;
    }

    public void setVentas(Float ventas) {
        this.ventas = ventas;
    }

    public Float getCumplimiento() {
        return cumplimiento;
    }

    public void setCumplimiento(Float cumplimiento) {
        this.cumplimiento = cumplimiento;
    }
}
