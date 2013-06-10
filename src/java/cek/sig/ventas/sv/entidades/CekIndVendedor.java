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
@Table(name = "CEK_IND_VENDEDOR")
@NamedQueries({
    @NamedQuery(name = "CekIndVendedor.findAll", query = "SELECT c FROM CekIndVendedor c"),
    @NamedQuery(name = "CekIndVendedor.cuentasRecuperadasUltimo",
            query = "SELECT c "
            + "FROM CekIndVendedor c "
            + "WHERE c.cekIndVendedorPK.idPeriodo = (SELECT MAX(p.idPeriodo) FROM CekPeriodo p)"),
    @NamedQuery(name = "CekIndVendedor.Ultimo",
            query = "SELECT c "
            + "FROM CekIndVendedor c "
            + "WHERE c.cekIndVendedorPK.idPeriodo = (SELECT MAX(p.idPeriodo) FROM CekPeriodo p)")})
public class CekIndVendedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CekIndVendedorPK cekIndVendedorPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "INDIV_VENTA_NETA")
    private Double indivVentaNeta;
    @Column(name = "INDIV_PROY_VENTA")
    private Double indivProyVenta;
    @Column(name = "INDIV_CUMP_VENTA")
    private Long indivCumpVenta;
    @Column(name = "INDIV_PENETRACION")
    private Long indivPenetracion;
    @Column(name = "INDIV_COBERTURA")
    private Long indivCobertura;
    @Column(name = "INDIV_CLI_NUEVOS")
    private Short indivCliNuevos;
    @Column(name = "INDIV_PROY_CNUEVOS")
    private Short indivProyCnuevos;
    @Column(name = "INDIV_CUMP_CNUEVOS")
    private Long indivCumpCnuevos;
    @Column(name = "INDIV_CLI_RECU")
    private Short indivCliRecu;
    @Column(name = "INDIV_PROY_CRECUP")
    private Short indivProyCrecup;
    @Column(name = "INDIV_CUMPL_CRECUP")
    private Long indivCumplCrecup;
    @Column(name = "INDIV_CLI_PERD")
    private Short indivCliPerd;
    @Column(name = "INDIV_TOT_CARTERA")
    private Short indivTotCartera;
    @Column(name = "INDIV_TOT_CVENDIO")
    private Integer indivTotCvendio;
    @Column(name = "INDIV_TOT_PVENDIO")
    private Integer indivTotPvendio;
    @JoinColumn(name = "ID_VENDEDOR", referencedColumnName = "ID_VENDEDOR", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CekVendedor cekVendedor;
    @JoinColumn(name = "ID_PERIODO", referencedColumnName = "ID_PERIODO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CekPeriodo cekPeriodo;

    public CekIndVendedor() {
    }

    public CekIndVendedor(CekIndVendedorPK cekIndVendedorPK) {
        this.cekIndVendedorPK = cekIndVendedorPK;
    }

    public CekIndVendedor(String idVendedor, String idPeriodo) {
        this.cekIndVendedorPK = new CekIndVendedorPK(idVendedor, idPeriodo);
    }

    public CekIndVendedorPK getCekIndVendedorPK() {
        return cekIndVendedorPK;
    }

    public void setCekIndVendedorPK(CekIndVendedorPK cekIndVendedorPK) {
        this.cekIndVendedorPK = cekIndVendedorPK;
    }

    public Double getIndivVentaNeta() {
        return indivVentaNeta;
    }

    public void setIndivVentaNeta(Double indivVentaNeta) {
        this.indivVentaNeta = indivVentaNeta;
    }

    public Double getIndivProyVenta() {
        return indivProyVenta;
    }

    public void setIndivProyVenta(Double indivProyVenta) {
        this.indivProyVenta = indivProyVenta;
    }

    public Long getIndivCumpVenta() {
        return indivCumpVenta;
    }

    public void setIndivCumpVenta(Long indivCumpVenta) {
        this.indivCumpVenta = indivCumpVenta;
    }

    public Long getIndivPenetracion() {
        return indivPenetracion;
    }

    public void setIndivPenetracion(Long indivPenetracion) {
        this.indivPenetracion = indivPenetracion;
    }

    public Long getIndivCobertura() {
        return indivCobertura;
    }

    public void setIndivCobertura(Long indivCobertura) {
        this.indivCobertura = indivCobertura;
    }

    public Short getIndivCliNuevos() {
        return indivCliNuevos;
    }

    public void setIndivCliNuevos(Short indivCliNuevos) {
        this.indivCliNuevos = indivCliNuevos;
    }

    public Short getIndivProyCnuevos() {
        return indivProyCnuevos;
    }

    public void setIndivProyCnuevos(Short indivProyCnuevos) {
        this.indivProyCnuevos = indivProyCnuevos;
    }

    public Long getIndivCumpCnuevos() {
        return indivCumpCnuevos;
    }

    public void setIndivCumpCnuevos(Long indivCumpCnuevos) {
        this.indivCumpCnuevos = indivCumpCnuevos;
    }

    public Short getIndivCliRecu() {
        return indivCliRecu;
    }

    public void setIndivCliRecu(Short indivCliRecu) {
        this.indivCliRecu = indivCliRecu;
    }

    public Short getIndivProyCrecup() {
        return indivProyCrecup;
    }

    public void setIndivProyCrecup(Short indivProyCrecup) {
        this.indivProyCrecup = indivProyCrecup;
    }

    public Long getIndivCumplCrecup() {
        return indivCumplCrecup;
    }

    public void setIndivCumplCrecup(Long indivCumplCrecup) {
        this.indivCumplCrecup = indivCumplCrecup;
    }

    public Short getIndivCliPerd() {
        return indivCliPerd;
    }

    public void setIndivCliPerd(Short indivCliPerd) {
        this.indivCliPerd = indivCliPerd;
    }

    public Short getIndivTotCartera() {
        return indivTotCartera;
    }

    public void setIndivTotCartera(Short indivTotCartera) {
        this.indivTotCartera = indivTotCartera;
    }

    public Integer getIndivTotCvendio() {
        return indivTotCvendio;
    }

    public void setIndivTotCvendio(Integer indivTotCvendio) {
        this.indivTotCvendio = indivTotCvendio;
    }

    public Integer getIndivTotPvendio() {
        return indivTotPvendio;
    }

    public void setIndivTotPvendio(Integer indivTotPvendio) {
        this.indivTotPvendio = indivTotPvendio;
    }

    public CekVendedor getCekVendedor() {
        return cekVendedor;
    }

    public void setCekVendedor(CekVendedor cekVendedor) {
        this.cekVendedor = cekVendedor;
    }

    public CekPeriodo getCekPeriodo() {
        return cekPeriodo;
    }

    public void setCekPeriodo(CekPeriodo cekPeriodo) {
        this.cekPeriodo = cekPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cekIndVendedorPK != null ? cekIndVendedorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CekIndVendedor)) {
            return false;
        }
        CekIndVendedor other = (CekIndVendedor) object;
        if ((this.cekIndVendedorPK == null && other.cekIndVendedorPK != null) || (this.cekIndVendedorPK != null && !this.cekIndVendedorPK.equals(other.cekIndVendedorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cek.sig.ventas.sv.entidades.CekIndVendedor[ cekIndVendedorPK=" + cekIndVendedorPK + " ]";
    }
}
