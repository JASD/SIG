/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Antonio
 */
@Entity
@Table(name = "CEK_USUARIO")
@NamedQueries({
    @NamedQuery(name = "CekUsuario.findAll", query = "SELECT c FROM CekUsuario c")})
public class CekUsuario implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "CONTRASENA_USUARIO")
    private String contrasenaUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTA_HABILITADO_USUARIO")
    private boolean estaHabilitadoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "PRIMER_NOMBRE_USUARIO")
    private String primerNombreUsuario;
    @Size(max = 64)
    @Column(name = "SEGUNDO_NOMBRE_USUARIO")
    private String segundoNombreUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "PRIMER_APELLIDO_USUARIO")
    private String primerApellidoUsuario;
    @Size(max = 64)
    @Column(name = "SEGUNDO_APELLIDO_USUARIO")
    private String segundoApellidoUsuario;
    @ManyToMany(mappedBy = "cekUsuarioList", fetch = FetchType.EAGER)
    private List<CekRol> cekRolList;

    public CekUsuario() {
    }

    public CekUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public CekUsuario(String nombreUsuario, String contrasenaUsuario, boolean estaHabilitadoUsuario, String primerNombreUsuario, String primerApellidoUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.estaHabilitadoUsuario = estaHabilitadoUsuario;
        this.primerNombreUsuario = primerNombreUsuario;
        this.primerApellidoUsuario = primerApellidoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public boolean getEstaHabilitadoUsuario() {
        return estaHabilitadoUsuario;
    }

    public void setEstaHabilitadoUsuario(boolean estaHabilitadoUsuario) {
        this.estaHabilitadoUsuario = estaHabilitadoUsuario;
    }

    public String getPrimerNombreUsuario() {
        return primerNombreUsuario;
    }

    public void setPrimerNombreUsuario(String primerNombreUsuario) {
        this.primerNombreUsuario = primerNombreUsuario;
    }

    public String getSegundoNombreUsuario() {
        return segundoNombreUsuario;
    }

    public void setSegundoNombreUsuario(String segundoNombreUsuario) {
        this.segundoNombreUsuario = segundoNombreUsuario;
    }

    public String getPrimerApellidoUsuario() {
        return primerApellidoUsuario;
    }

    public void setPrimerApellidoUsuario(String primerApellidoUsuario) {
        this.primerApellidoUsuario = primerApellidoUsuario;
    }

    public String getSegundoApellidoUsuario() {
        return segundoApellidoUsuario;
    }

    public void setSegundoApellidoUsuario(String segundoApellidoUsuario) {
        this.segundoApellidoUsuario = segundoApellidoUsuario;
    }

    public List<CekRol> getCekRolList() {
        return cekRolList;
    }

    public void setCekRolList(List<CekRol> cekRolList) {
        this.cekRolList = cekRolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreUsuario != null ? nombreUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CekUsuario)) {
            return false;
        }
        CekUsuario other = (CekUsuario) object;
        if ((this.nombreUsuario == null && other.nombreUsuario != null) || (this.nombreUsuario != null && !this.nombreUsuario.equals(other.nombreUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cek.sig.ventas.sv.entidades.CekUsuario[ nombreUsuario=" + nombreUsuario + " ]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return cekRolList;
    }

    @Override
    public String getPassword() {
        return contrasenaUsuario;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return estaHabilitadoUsuario;
    }
}
