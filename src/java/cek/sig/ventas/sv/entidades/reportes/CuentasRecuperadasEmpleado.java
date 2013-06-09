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
public class CuentasRecuperadasEmpleado implements Serializable {

    private String nombreEmpleado;
    private String proyectadoEmpleado;
    private String recuperadoEmpleado;
    private String cumplimientoEmpleado;

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getProyectadoEmpleado() {
        return proyectadoEmpleado;
    }

    public void setProyectadoEmpleado(String proyectadoEmpleado) {
        this.proyectadoEmpleado = proyectadoEmpleado;
    }

    public String getRecuperadoEmpleado() {
        return recuperadoEmpleado;
    }

    public void setRecuperadoEmpleado(String recuperadoEmpleado) {
        this.recuperadoEmpleado = recuperadoEmpleado;
    }

    public String getCumplimientoEmpleado() {
        return cumplimientoEmpleado;
    }

    public void setCumplimientoEmpleado(String cumplimientoEmpleado) {
        this.cumplimientoEmpleado = cumplimientoEmpleado;
    }
}
