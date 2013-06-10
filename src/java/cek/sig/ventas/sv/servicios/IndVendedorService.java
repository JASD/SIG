/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.servicios;

import cek.sig.ventas.sv.entidades.reportes.CRVendedor;
import cek.sig.ventas.sv.entidades.CekIndVendedor;
import cek.sig.ventas.sv.repositorios.IndVendedorDAO;
import java.util.ArrayList;
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
public class IndVendedorService {

    @Autowired
    private IndVendedorDAO indVendedorDAO;

    public List<CRVendedor> getCuentasRecuperadas() {
        List<CekIndVendedor> records = indVendedorDAO.executeNamedQuery("CekIndVendedor.cuentasRecuperadasUltimo");
        List<CRVendedor> dtos = new ArrayList<CRVendedor>();

        // Map records
        for (CekIndVendedor ind : records) {
            CRVendedor cre = new CRVendedor();
            cre.setNombreVendedor(ind.getCekVendedor().getVendNombre());
            cre.setProyectadoVendedor(ind.getIndivProyCrecup().floatValue());
            cre.setRecuperadoVendedor(ind.getIndivCliRecu().floatValue());
            cre.setCumplimientoVendedor(ind.getIndivCumplCrecup().floatValue());
            dtos.add(cre);
        }

        return dtos;
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
     * JRBeanCollectionDataSource(dtos);
    }*
     */
}
