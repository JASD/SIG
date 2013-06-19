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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CEK_ARTICULO")
@NamedQueries({
    @NamedQuery(name = "CekArticulo.findAll", query = "SELECT c FROM CekArticulo c")})
public class CekArticulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ID_ARTICULO")
    private String idArticulo;
    @Size(max = 254)
    @Column(name = "ARTI_NOMBRE")
    private String artiNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cekArticulo", fetch = FetchType.LAZY)
    private List<CekIndArticulo> cekIndArticuloList;
    @JoinColumn(name = "ARTI_CLASIFI", referencedColumnName = "ID_CLASIFICACION")
    @ManyToOne(fetch = FetchType.LAZY)
    private CekClasificacion idClasificacion;

    public CekArticulo() {
    }

    public CekArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getArtiNombre() {
        return artiNombre;
    }

    public void setArtiNombre(String artiNombre) {
        this.artiNombre = artiNombre;
    }

    public List<CekIndArticulo> getCekIndArticuloList() {
        return cekIndArticuloList;
    }

    public void setCekIndArticuloList(List<CekIndArticulo> cekIndArticuloList) {
        this.cekIndArticuloList = cekIndArticuloList;
    }

    public CekClasificacion getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(CekClasificacion idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArticulo != null ? idArticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CekArticulo)) {
            return false;
        }
        CekArticulo other = (CekArticulo) object;
        if ((this.idArticulo == null && other.idArticulo != null) || (this.idArticulo != null && !this.idArticulo.equals(other.idArticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cek.sig.ventas.sv.entidades.CekArticulo[ idArticulo=" + idArticulo + " ]";
    }
    
}
