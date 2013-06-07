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
@Table(name = "CEK_PAIS")
@NamedQueries({
    @NamedQuery(name = "CekPais.findAll", query = "SELECT c FROM CekPais c")})
public class CekPais implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "ID_PAIS")
    private String idPais;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "PAIS_NOMBRE")
    private String paisNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cekPais", fetch = FetchType.LAZY)
    private List<CekIndArticulo> cekIndArticuloList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cekPais", fetch = FetchType.LAZY)
    private List<CekIndClasificacion> cekIndClasificacionList;
    @OneToMany(mappedBy = "vendPais", fetch = FetchType.LAZY)
    private List<CekVendedor> cekVendedorList;

    public CekPais() {
    }

    public CekPais(String idPais) {
        this.idPais = idPais;
    }

    public CekPais(String idPais, String paisNombre) {
        this.idPais = idPais;
        this.paisNombre = paisNombre;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public String getPaisNombre() {
        return paisNombre;
    }

    public void setPaisNombre(String paisNombre) {
        this.paisNombre = paisNombre;
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

    public List<CekVendedor> getCekVendedorList() {
        return cekVendedorList;
    }

    public void setCekVendedorList(List<CekVendedor> cekVendedorList) {
        this.cekVendedorList = cekVendedorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPais != null ? idPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CekPais)) {
            return false;
        }
        CekPais other = (CekPais) object;
        if ((this.idPais == null && other.idPais != null) || (this.idPais != null && !this.idPais.equals(other.idPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cek.sig.ventas.sv.entidades.CekPais[ idPais=" + idPais + " ]";
    }
    
}
