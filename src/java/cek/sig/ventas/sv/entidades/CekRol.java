/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Antonio
 */
@Entity
@Table(name = "CEK_ROL")
@NamedQueries({
    @NamedQuery(name = "CekRol.findAll", query = "SELECT c FROM CekRol c")})
public class CekRol implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_ROL")
    private Short numeroRol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "NOMBRE_ROL")
    private String nombreRol;
    @Size(max = 128)
    @Column(name = "DESCRIPCION_ROL")
    private String descripcionRol;
    @JoinTable(name = "CEK_USUARIO_ROL", joinColumns = {
        @JoinColumn(name = "NUMERO_ROL", referencedColumnName = "NUMERO_ROL")}, inverseJoinColumns = {
        @JoinColumn(name = "NOMBRE_USUARIO", referencedColumnName = "NOMBRE_USUARIO")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<CekUsuario> cekUsuarioList;

    public CekRol() {
    }

    public CekRol(Short numeroRol) {
        this.numeroRol = numeroRol;
    }

    public CekRol(Short numeroRol, String nombreRol) {
        this.numeroRol = numeroRol;
        this.nombreRol = nombreRol;
    }

    public Short getNumeroRol() {
        return numeroRol;
    }

    public void setNumeroRol(Short numeroRol) {
        this.numeroRol = numeroRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public List<CekUsuario> getCekUsuarioList() {
        return cekUsuarioList;
    }

    public void setCekUsuarioList(List<CekUsuario> cekUsuarioList) {
        this.cekUsuarioList = cekUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroRol != null ? numeroRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CekRol)) {
            return false;
        }
        CekRol other = (CekRol) object;
        if ((this.numeroRol == null && other.numeroRol != null) || (this.numeroRol != null && !this.numeroRol.equals(other.numeroRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cek.sig.ventas.sv.entidades.CekRol[ numeroRol=" + numeroRol + " ]";
    }

    @Override
    public String getAuthority() {
        return nombreRol;
    }
}
