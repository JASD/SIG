/* * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.servicios;

import cek.sig.ventas.sv.controladores.Auxiliares;
import cek.sig.ventas.sv.entidades.reportes.CRVendedor;
import cek.sig.ventas.sv.entidades.CekIndVendedor;
import cek.sig.ventas.sv.entidades.CekPeriodo;
import cek.sig.ventas.sv.entidades.CekVendedor;
import cek.sig.ventas.sv.entidades.reportes.CNVendedor;
import cek.sig.ventas.sv.entidades.reportes.VVendedor;
import cek.sig.ventas.sv.repositorios.CekVendedorDAO;
import cek.sig.ventas.sv.repositorios.IndVendedorDAO;
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
public class IndVendedorService {

    @Autowired
    private IndVendedorDAO indVendedorDAO;
    private CekVendedorDAO vendedorDAO;
    private List<CekIndVendedor> records = new ArrayList<CekIndVendedor>();
    private PeriodoDAO periodoDAO;

    public List<CRVendedor> getCuentasRecuperadas() {
        records = indVendedorDAO.executeNamedQuery("CekIndVendedor.cuentasRecuperadasUltimo");
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

    public List<CNVendedor> getCuentasNuevas() {
        records = indVendedorDAO.executeNamedQuery("CekIndVendedor.Ultimo");
        List<CNVendedor> dtos = new ArrayList<CNVendedor>();

        // Map records
        for (CekIndVendedor ind : records) {
            CNVendedor cne = new CNVendedor();
            cne.setVendedor(ind.getCekVendedor().getVendNombre());
            cne.setProyectado(ind.getIndivProyCnuevos().floatValue());
            cne.setCuentasNuevas(ind.getIndivCliNuevos().floatValue());
            cne.setCumplimiento(ind.getIndivCumpCnuevos().floatValue());
            dtos.add(cne);
        }

        return dtos;
    }

    public List<VVendedor> getVentasVendedor() {
        List<VVendedor> dtos = new ArrayList<VVendedor>();
        try {
            //obtener todos los vendedores
            List<CekVendedor> vendedores = vendedorDAO.findAll();
            //Obtener todos los períodos en orden descendente
            List<CekPeriodo> periodosDesc = periodoDAO.executeNamedQuery("CekPeriodos.periodosDesc");

            // Analizar vendedores
            for (CekVendedor vendedorActual : vendedores) {
                //variable para enviar los reportes al jasper
                VVendedor entityReporteVendedor = new VVendedor();

                //establece el nombre del vendedor actual
                entityReporteVendedor.setVendedor(vendedorActual.getVendNombre());
                //obtiene la lista de las actividades del vendedor
                List<CekIndVendedor> indVendedorActual = vendedorActual.getCekIndVendedorList();

                //almacena las posiciones de los indices en los que se encuentra un período especifico
                int indicePeriodo[] = new int[6];

                //busca los indices en los que se encuentran los períodos del mayor al menor, sino lo encuentra devuelve -1
                for (int x = 0; x < 6; x++) {
                    indicePeriodo[x] = periodosDesc.size() > x ? vendedorActual.getCekIndVendedorList().indexOf(periodosDesc.get(x)) : -1;
                }

                //establece los valores de las ventas netas para cada mes. si no se encontró valor en el período,
                //asigna cero a ese mes
                entityReporteVendedor.setMes1(indicePeriodo[0] > 0 ? indVendedorActual.get(indicePeriodo[0]).getIndivVentaNeta().floatValue() : 0);
                entityReporteVendedor.setMes2(indicePeriodo[1] > 0 ? indVendedorActual.get(indicePeriodo[1]).getIndivVentaNeta().floatValue() : 0);
                entityReporteVendedor.setMes3(indicePeriodo[2] > 0 ? indVendedorActual.get(indicePeriodo[2]).getIndivVentaNeta().floatValue() : 0);
                entityReporteVendedor.setMes4(indicePeriodo[3] > 0 ? indVendedorActual.get(indicePeriodo[3]).getIndivVentaNeta().floatValue() : 0);
                entityReporteVendedor.setMes5(indicePeriodo[4] > 0 ? indVendedorActual.get(indicePeriodo[4]).getIndivVentaNeta().floatValue() : 0);
                entityReporteVendedor.setMes6(indicePeriodo[5] > 0 ? indVendedorActual.get(indicePeriodo[5]).getIndivVentaNeta().floatValue() : 0);

                //agrega el registro a la lista para tabular
                dtos.add(entityReporteVendedor);
            }
        } catch (Exception e) {
            System.out.print("ERROR");
            System.out.print(e.getStackTrace());
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
