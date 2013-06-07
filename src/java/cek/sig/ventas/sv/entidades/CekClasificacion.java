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
@Table(name = "CEK_CLASIFICACION")
@NamedQueries({
    @NamedQuery(name = "CekClasificacion.findAll", query = "SELECT c FROM CekClasificacion c")})
public class CekClasificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "ID_CLASIFICACION")
    private String idClasificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "CLAS_NOMBRE")
    private String clasNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cekClasificacion", fetch = FetchType.LAZY)
    private List<CekIndClasificacion> cekIndClasificacionList;

    public CekClasificacion() {
    }

    public CekClasificacion(String idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public CekClasificacion(String idClasificacion, String clasNombre) {
        this.idClasificacion = idClasificacion;
        this.clasNombre = clasNombre;
    }

    public String getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(String idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public String getClasNombre() {
        return clasNombre;
    }

    public void setClasNombre(String clasNombre) {
        this.clasNombre = clasNombre;
    }

    public List<CekIndClasificacion> getCekIndClasificacionList() {
        return cekIndClasificacionList;
    }

    public void setCekIndClasificacionList(List<CekIndClasificacion> cekIndClasificacionList) {
        this.cekIndClasificacionList = cekIndClasificacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClasificacion != null ? idClasificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CekClasificacion)) {
            return false;
        }
        CekClasificacion other = (CekClasificacion) object;
        if ((this.idClasificacion == null && other.idClasificacion != null) || (this.idClasificacion != null && !this.idClasificacion.equals(other.idClasificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cek.sig.ventas.sv.entidades.CekClasificacion[ idClasificacion=" + idClasificacion + " ]";
    }
    
}
