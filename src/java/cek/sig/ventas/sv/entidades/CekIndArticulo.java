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
@Table(name = "CEK_IND_ARTICULO")
@NamedQueries({
    @NamedQuery(name = "CekIndArticulo.findAll", query = "SELECT c FROM CekIndArticulo c")})
public class CekIndArticulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CekIndArticuloPK cekIndArticuloPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ATTRIBUTE_13")
    private Double attribute13;
    @Column(name = "INDA_KG")
    private Double indaKg;
    @Column(name = "INDA_PROY_KG")
    private Double indaProyKg;
    @Column(name = "INDA_CUMP_PROY")
    private Long indaCumpProy;
    @JoinColumn(name = "ID_PERIODO", referencedColumnName = "ID_PERIODO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CekPeriodo cekPeriodo;
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID_PAIS", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CekPais cekPais;
    @JoinColumn(name = "ID_ARTICULO", referencedColumnName = "ID_ARTICULO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CekArticulo cekArticulo;

    public CekIndArticulo() {
    }

    public CekIndArticulo(CekIndArticuloPK cekIndArticuloPK) {
        this.cekIndArticuloPK = cekIndArticuloPK;
    }

    public CekIndArticulo(String idPais, String idPeriodo, String idArticulo) {
        this.cekIndArticuloPK = new CekIndArticuloPK(idPais, idPeriodo, idArticulo);
    }

    public CekIndArticuloPK getCekIndArticuloPK() {
        return cekIndArticuloPK;
    }

    public void setCekIndArticuloPK(CekIndArticuloPK cekIndArticuloPK) {
        this.cekIndArticuloPK = cekIndArticuloPK;
    }

    public Double getAttribute13() {
        return attribute13;
    }

    public void setAttribute13(Double attribute13) {
        this.attribute13 = attribute13;
    }

    public Double getIndaKg() {
        return indaKg;
    }

    public void setIndaKg(Double indaKg) {
        this.indaKg = indaKg;
    }

    public Double getIndaProyKg() {
        return indaProyKg;
    }

    public void setIndaProyKg(Double indaProyKg) {
        this.indaProyKg = indaProyKg;
    }

    public Long getIndaCumpProy() {
        return indaCumpProy;
    }

    public void setIndaCumpProy(Long indaCumpProy) {
        this.indaCumpProy = indaCumpProy;
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

    public CekArticulo getCekArticulo() {
        return cekArticulo;
    }

    public void setCekArticulo(CekArticulo cekArticulo) {
        this.cekArticulo = cekArticulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cekIndArticuloPK != null ? cekIndArticuloPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CekIndArticulo)) {
            return false;
        }
        CekIndArticulo other = (CekIndArticulo) object;
        if ((this.cekIndArticuloPK == null && other.cekIndArticuloPK != null) || (this.cekIndArticuloPK != null && !this.cekIndArticuloPK.equals(other.cekIndArticuloPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cek.sig.ventas.sv.entidades.CekIndArticulo[ cekIndArticuloPK=" + cekIndArticuloPK + " ]";
    }
    
}
