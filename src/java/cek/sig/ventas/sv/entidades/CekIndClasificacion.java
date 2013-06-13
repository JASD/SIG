/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Antonio
 */
@Entity
@Table(name = "CEK_IND_CLASIFICACION")
@NamedQueries({
    @NamedQuery(name = "CekIndClasificacion.findAll", query = "SELECT c FROM CekIndClasificacion c"),
    @NamedQuery(name = "CekIndClasificacion.cuentasRecuperadasUltimo",
            query = "SELECT c "
            + "FROM CekIndClasificacion c "
            + "WHERE c.cekIndClasificacionPK.idPeriodo = (SELECT MAX(p.idPeriodo) FROM CekPeriodo p)")})
public class CekIndClasificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CekIndClasificacionPK cekIndClasificacionPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "INDC_VENTA_BRUTA")
    private Double indcVentaBruta;
    @Column(name = "INDC_TOT_DESC")
    private Double indcTotDesc;
    @Column(name = "INDC_VENTA_NETA")
    private Double indcVentaNeta;
    @Column(name = "INDC_COSTO_VENTA")
    private Double indcCostoVenta;
    @Column(name = "INDC_GASTOS_IND")
    private Double indcGastosInd;
    @Column(name = "INDC_UTILIDAD")
    private Double indcUtilidad;
    @Column(name = "INDC_KG")
    private Double indcKg;
    @Column(name = "INDC_VAR_PPTO")
    private Long indcVarPpto;
    @Column(name = "INDC_PPTO")
    private Double indcPpto;
    @JoinColumn(name = "ID_PERIODO", referencedColumnName = "ID_PERIODO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CekPeriodo cekPeriodo;
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID_PAIS", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CekPais cekPais;
    @JoinColumn(name = "ID_CLASIFICACION", referencedColumnName = "ID_CLASIFICACION", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CekClasificacion cekClasificacion;

    public CekIndClasificacion() {
    }

    public CekIndClasificacion(CekIndClasificacionPK cekIndClasificacionPK) {
        this.cekIndClasificacionPK = cekIndClasificacionPK;
    }

    public CekIndClasificacion(String idClasificacion, String idPeriodo, String idPais) {
        this.cekIndClasificacionPK = new CekIndClasificacionPK(idClasificacion, idPeriodo, idPais);
    }

    public CekIndClasificacionPK getCekIndClasificacionPK() {
        return cekIndClasificacionPK;
    }

    public void setCekIndClasificacionPK(CekIndClasificacionPK cekIndClasificacionPK) {
        this.cekIndClasificacionPK = cekIndClasificacionPK;
    }

    public Double getIndcVentaBruta() {
        return indcVentaBruta;
    }

    public void setIndcVentaBruta(Double indcVentaBruta) {
        this.indcVentaBruta = indcVentaBruta;
    }

    public Double getIndcTotDesc() {
        return indcTotDesc;
    }

    public void setIndcTotDesc(Double indcTotDesc) {
        this.indcTotDesc = indcTotDesc;
    }

    public Double getIndcVentaNeta() {
        return indcVentaNeta;
    }

    public void setIndcVentaNeta(Double indcVentaNeta) {
        this.indcVentaNeta = indcVentaNeta;
    }

    public Double getIndcCostoVenta() {
        return indcCostoVenta;
    }

    public void setIndcCostoVenta(Double indcCostoVenta) {
        this.indcCostoVenta = indcCostoVenta;
    }

    public Double getIndcGastosInd() {
        return indcGastosInd;
    }

    public void setIndcGastosInd(Double indcGastosInd) {
        this.indcGastosInd = indcGastosInd;
    }

    public Double getIndcUtilidad() {
        return indcUtilidad;
    }

    public void setIndcUtilidad(Double indcUtilidad) {
        this.indcUtilidad = indcUtilidad;
    }

    public Double getIndcKg() {
        return indcKg;
    }

    public void setIndcKg(Double indcKg) {
        this.indcKg = indcKg;
    }

    public Long getIndcVarPpto() {
        return indcVarPpto;
    }

    public void setIndcVarPpto(Long indcVarPpto) {
        this.indcVarPpto = indcVarPpto;
    }

    public Double getIndcPpto() {
        return indcPpto;
    }

    public void setIndcPpto(Double indcPpto) {
        this.indcPpto = indcPpto;
    }

    public CekPeriodo getCekPeriodo() {
        return cekPeriodo;
    }

    public void setCekPeriodo(CekPeriodo cekPeriodo) {
        this.cekPeriodo = cekPeriodo;
    }

    public CekPais getCekPais() {
        return cekPais;
    }

    public void setCekPais(CekPais cekPais) {
        this.cekPais = cekPais;
    }

    public CekClasificacion getCekClasificacion() {
        return cekClasificacion;
    }

    public void setCekClasificacion(CekClasificacion cekClasificacion) {
        this.cekClasificacion = cekClasificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cekIndClasificacionPK != null ? cekIndClasificacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CekIndClasificacion)) {
            return false;
        }
        CekIndClasificacion other = (CekIndClasificacion) object;
        if ((this.cekIndClasificacionPK == null && other.cekIndClasificacionPK != null) || (this.cekIndClasificacionPK != null && !this.cekIndClasificacionPK.equals(other.cekIndClasificacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cek.sig.ventas.sv.entidades.CekIndClasificacion[ cekIndClasificacionPK=" + cekIndClasificacionPK + " ]";
    }
}
