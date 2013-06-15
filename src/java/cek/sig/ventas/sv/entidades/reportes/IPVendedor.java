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
public class IPVendedor implements Serializable {

    private String vendedor;
    private Float mes1;
    private Float mes2;
    private Float mes3;
    private Float mes4;
    private Float mes5;
    private Float mes6;

    public IPVendedor() {
        this.mes1 = Float.valueOf(0);
        this.mes2 = Float.valueOf(0);
        this.mes3 = Float.valueOf(0);
        this.mes4 = Float.valueOf(0);
        this.mes5 = Float.valueOf(0);
        this.mes6 = Float.valueOf(0);
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Float getMes1() {
        return mes1;
    }

    public void setMes1(Float mes1) {
        this.mes1 = mes1;
    }

    public Float getMes2() {
        return mes2;
    }

    public void setMes2(Float mes2) {
        this.mes2 = mes2;
    }

    public Float getMes3() {
        return mes3;
    }

    public void setMes3(Float mes3) {
        this.mes3 = mes3;
    }

    public Float getMes4() {
        return mes4;
    }

    public void setMes4(Float mes4) {
        this.mes4 = mes4;
    }

    public Float getMes5() {
        return mes5;
    }

    public void setMes5(Float mes5) {
        this.mes5 = mes5;
    }

    public Float getMes6() {
        return mes6;
    }

    public void setMes6(Float mes6) {
        this.mes6 = mes6;
    }
}
