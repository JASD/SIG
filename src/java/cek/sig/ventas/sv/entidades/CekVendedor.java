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
@Table(name = "CEK_VENDEDOR")
@NamedQueries({
    @NamedQuery(name = "CekVendedor.findAll", query = "SELECT c FROM CekVendedor c")})
public class CekVendedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "ID_VENDEDOR")
    private String idVendedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "VEND_NOMBRE")
    private String vendNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cekVendedor", fetch = FetchType.LAZY)
    private List<CekIndVendedor> cekIndVendedorList;
    @JoinColumn(name = "VEND_PAIS", referencedColumnName = "ID_PAIS")
    @ManyToOne(fetch = FetchType.LAZY)
    private CekPais vendPais;

    public CekVendedor() {
    }

    public CekVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    public CekVendedor(String idVendedor, String vendNombre) {
        this.idVendedor = idVendedor;
        this.vendNombre = vendNombre;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getVendNombre() {
        return vendNombre;
    }

    public void setVendNombre(String vendNombre) {
        this.vendNombre = vendNombre;
    }

    public List<CekIndVendedor> getCekIndVendedorList() {
        return cekIndVendedorList;
    }

    public void setCekIndVendedorList(List<CekIndVendedor> cekIndVendedorList) {
        this.cekIndVendedorList = cekIndVendedorList;
    }

    public CekPais getVendPais() {
        return vendPais;
    }

    public void setVendPais(CekPais vendPais) {
        this.vendPais = vendPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVendedor != null ? idVendedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CekVendedor)) {
            return false;
        }
        CekVendedor other = (CekVendedor) object;
        if ((this.idVendedor == null && other.idVendedor != null) || (this.idVendedor != null && !this.idVendedor.equals(other.idVendedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cek.sig.ventas.sv.entidades.CekVendedor[ idVendedor=" + idVendedor + " ]";
    }
    
}
