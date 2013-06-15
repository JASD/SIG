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
public class UCategoria implements Serializable {

    public String categoria;
    public Float vBrutas;
    public Float vNetas;
    public Float descuento;
    public Float costo;
    public Float costoPorc;
    public Float gastos;
    public Float gastosPorc;
    public Float utilidad;
    public Float utilidadPorc;

    public UCategoria() {
        this.costo = Float.valueOf(0);
        this.vBrutas = Float.valueOf(0);
        this.vNetas = Float.valueOf(0);
        this.descuento = Float.valueOf(0);
        this.costoPorc = Float.valueOf(0);
        this.gastos = Float.valueOf(0);
        this.gastosPorc = Float.valueOf(0);
        this.utilidad = Float.valueOf(0);
        this.utilidadPorc = Float.valueOf(0);
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Float getvBrutas() {
        return vBrutas;
    }

    public void setvBrutas(Float vBrutas) {
        this.vBrutas = vBrutas;
    }

    public Float getvNetas() {
        return vNetas;
    }

    public void setvNetas(Float vNetas) {
        this.vNetas = vNetas;
    }

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public Float getCostoPorc() {
        return costoPorc;
    }

    public void setCostoPorc(Float costoPorc) {
        this.costoPorc = costoPorc;
    }

    public Float getGastos() {
        return gastos;
    }

    public void setGastos(Float gastos) {
        this.gastos = gastos;
    }

    public Float getGastosPorc() {
        return gastosPorc;
    }

    public void setGastosPorc(Float gastosPorc) {
        this.gastosPorc = gastosPorc;
    }

    public Float getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(Float utilidad) {
        this.utilidad = utilidad;
    }

    public Float getUtilidadPorc() {
        return utilidadPorc;
    }

    public void setUtilidadPorc(Float utilidadPorc) {
        this.utilidadPorc = utilidadPorc;
    }
}
