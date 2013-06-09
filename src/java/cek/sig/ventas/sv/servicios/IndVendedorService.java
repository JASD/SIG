/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.servicios;

import cek.sig.ventas.sv.entidades.reportes.CuentasRecuperadasEmpleado;
import cek.sig.ventas.sv.entidades.CekIndVendedor;
import cek.sig.ventas.sv.repositorios.IndVendedorDAO;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Antonio
 */
@Service
public class IndVendedorService {

    @Autowired
    private IndVendedorDAO indVendedorDAO;

    public JRDataSource getDataSource() {
        List<CekIndVendedor> records = indVendedorDAO.findAll();
        List<CuentasRecuperadasEmpleado> dtos = new ArrayList<CuentasRecuperadasEmpleado>();

        // Map records
        for (CekIndVendedor ind : records) {
            CuentasRecuperadasEmpleado cre = new CuentasRecuperadasEmpleado();
            cre.setNombreEmpleado(ind.getCekVendedor().getVendNombre());
            cre.setProyectadoEmpleado(ind.getIndivProyCrecup().toString());
            cre.setRecuperadoEmpleado(ind.getIndivCliRecu().toString());
            cre.setCumplimientoEmpleado(ind.getIndivCumplCrecup().toString());
            dtos.add(cre);
        }
        // Return wrapped collection
        return new JRBeanCollectionDataSource(dtos);
    }
}
