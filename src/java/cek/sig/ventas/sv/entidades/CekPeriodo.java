/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Antonio
 */
@Entity
@Table(name = "CEK_PERIODO")
@NamedQueries({
    @NamedQuery(name = "CekPeriodo.findAll", query = "SELECT c FROM CekPeriodo c"),
@NamedQuery(name = "CekPeriodos.periodosDesc", query = "SELECT c FROM CekPeriodo c order by c.idPeriodo desc")})
public class CekPeriodo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "ID_PERIODO")
    private String idPeriodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERI_ANIO")
    private int periAnio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERI_MES")
    private int periMes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cekPeriodo", fetch = FetchType.LAZY)
    private List<CekIndArticulo> cekIndArticuloList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cekPeriodo", fetch = FetchType.LAZY)
    private List<CekIndClasificacion> cekIndClasificacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cekPeriodo", fetch = FetchType.LAZY)
    private List<CekIndVendedor> cekIndVendedorList;

    public CekPeriodo() {
    }

    public CekPeriodo(String idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public CekPeriodo(String idPeriodo, int periAnio, int periMes) {
        this.idPeriodo = idPeriodo;
        this.periAnio = periAnio;
        this.periMes = periMes;
    }

    public String getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(String idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public int getPeriAnio() {
        return periAnio;
    }

    public void setPeriAnio(int periAnio) {
        this.periAnio = periAnio;
    }

    public int getPeriMes() {
        return periMes;
    }

    public void setPeriMes(int periMes) {
        this.periMes = periMes;
    }

    public List<CekIndArticulo> getCekIndArticuloList() {
        return cekIndArticuloList;
    }

    public void setCekIndArticuloList(List<CekIndArticulo> cekIndArticuloList) {
        this.cekIndArticuloList = cekIndArticuloList;
    }

    public List<CekIndClasificacion> getCekIndClasificacionList() {
        return cekIndClasificacionList;
    }

    public void setCekIndClasificacionList(List<CekIndClasificacion> cekIndClasificacionList) {
        this.cekIndClasificacionList = cekIndClasificacionList;
    }

    public List<CekIndVendedor> getCekIndVendedorList() {
        return cekIndVendedorList;
    }

    public void setCekIndVendedorList(List<CekIndVendedor> cekIndVendedorList) {
        this.cekIndVendedorList = cekIndVendedorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodo != null ? idPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CekPeriodo)) {
            return false;
        }
        CekPeriodo other = (CekPeriodo) object;
        if ((this.idPeriodo == null && other.idPeriodo != null) || (this.idPeriodo != null && !this.idPeriodo.equals(other.idPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cek.sig.ventas.sv.entidades.CekPeriodo[ idPeriodo=" + idPeriodo + " ]";
    }
    
}
