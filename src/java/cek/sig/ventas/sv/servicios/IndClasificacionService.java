/* * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.servicios;

import cek.sig.ventas.sv.entidades.CekIndClasificacion;
import cek.sig.ventas.sv.entidades.reportes.CRVendedor;
import cek.sig.ventas.sv.entidades.CekIndVendedor;
import cek.sig.ventas.sv.entidades.CekPeriodo;
import cek.sig.ventas.sv.entidades.CekVendedor;
import cek.sig.ventas.sv.entidades.reportes.CNVendedor;
import cek.sig.ventas.sv.entidades.reportes.VPPTOCategoria;
import cek.sig.ventas.sv.entidades.reportes.VVendedor;
import cek.sig.ventas.sv.repositorios.CekIndClasificacionDAO;
import cek.sig.ventas.sv.repositorios.CekVendedorDAO;
import cek.sig.ventas.sv.repositorios.PeriodoDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 *
 * @author Antonio
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IndClasificacionService {

    @Autowired
    private CekIndClasificacionDAO indClasificacionDAO;
    private List<CekIndClasificacion> records = new ArrayList<CekIndClasificacion>();

    public List<VPPTOCategoria> getCuentasRecuperadas() {
        records = indClasificacionDAO.executeNamedQuery("CekIndClasificacion.cuentasRecuperadasUltimo");
        List<VPPTOCategoria> dtos = new ArrayList<VPPTOCategoria>();

        // Map records
        for (CekIndClasificacion ind : records) {
            VPPTOCategoria vpc = new VPPTOCategoria();
            vpc.setCategoria(ind.getCekClasificacion().getClasNombre());
            vpc.setVentas(ind.getIndcVentaNeta().floatValue());
            vpc.setPresupuesto(ind.getIndcPpto().floatValue());
            vpc.setVariacion(ind.getIndcVarPpto().floatValue());
            vpc.setVentasAcum(-1000F);
            vpc.setPresupuestoAcum(-1000F);
            vpc.setVariacionAcum(-1000F);
            dtos.add(vpc);
        }

        return dtos;
    }

    public String getPeriodo() {
        if (!records.isEmpty()) {
            CekPeriodo periodo = records.get(0).getCekPeriodo();
            Calendar c = new GregorianCalendar();
            c.set(GregorianCalendar.MONTH, periodo.getPeriMes() - 1);
            c.set(GregorianCalendar.YEAR, periodo.getPeriAnio());
            c.set(GregorianCalendar.DAY_OF_MONTH, 1);
            SimpleDateFormat bartDateFormat = new SimpleDateFormat("MMMM yyyy");
            return bartDateFormat.format(c.getTime());
        }
        return "Ahora";
    }
    /**
     *
     * public JRDataSource getDataSource() { List<CekIndVendedor> records =
     * indVendedorDAO.findAll(); List<CRVendedor> dtos = new
     * ArrayList<CRVendedor>();
     *
     * // Map records for (CekIndVendedor ind : records) { CRVendedor cre = new
     * CRVendedor();
     * cre.setNombreEmpleado(ind.getCekVendedor().getVendNombre());
     * cre.setProyectadoEmpleado(ind.getIndivProyCrecup().toString());
     * cre.setRecuperadoEmpleado(ind.getIndivCliRecu().toString());
     * cre.setCumplimientoEmpleado(ind.getIndivCumplCrecup().toString());
     * dtos.add(cre); } // Return wrapped collection return new
     * JRBeanCollectionDataSource(dtos); }*
     */
}
