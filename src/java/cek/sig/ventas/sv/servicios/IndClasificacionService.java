/* * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.servicios;

import cek.sig.ventas.sv.controladores.util.Auxiliares;
import cek.sig.ventas.sv.controladores.util.Mes;
import cek.sig.ventas.sv.entidades.CekClasificacion;
import cek.sig.ventas.sv.entidades.CekIndClasificacion;
import cek.sig.ventas.sv.entidades.CekPeriodo;
import cek.sig.ventas.sv.entidades.reportes.VPPTOCategoria;
import cek.sig.ventas.sv.repositorios.IndClasificacionDAO;
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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Antonio
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IndClasificacionService {

    @Autowired
    private PeriodoDAO periodoDAO;
    private List<CekPeriodo> ultimos6;
    @Autowired
    private IndClasificacionDAO indClasificacionDAO;
    private List<CekIndClasificacion> records = new ArrayList<CekIndClasificacion>();

    @Transactional(readOnly = true)
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
            dtos.add(vpc);
        }

        return dtos;
    }
    
    /**
     * Obtengo las ventas para los ultimos 6 meses
     *
     * @return
     */
    /*
    @Transactional(readOnly = true)
    public List<VVendedor> getVentas() {
        
        //Buscar todas las categorias
        List<CekClasificacion> categorias = indClasificacionDAO
                .executeNamedQuery("CekVendedor.findAll");

        //Instancerar la nueva lista
        List<VVendedor> ventas = new ArrayList<VVendedor>();

        // Y Por cada vendedor
        for (CekVendedor v : vendedores) {

            VVendedor vvendedor = null;
            int contador = 1;
            boolean entro = false;

            //Para los ultimos 6 meses
            for (CekPeriodo p : ultimos6) {
                
                //consultar si tiene indices para ese periodo
                CekIndVendedor indv = indVendedorDAO.obtenerPorPeriodoVendedor(p, v);
                if (indv != null) {
                    //si tiene
                    if (!entro) {
                        //si ya entro antes no se vuelve a crear instancia
                        //ni se pone de nuevo el nombre
                        vvendedor = new VVendedor();
                        vvendedor.setVendedor(indv.getCekVendedor().getVendNombre());
                        entro = true;
                    }

                    if (contador == 1 && entro) {
                        //Es el primer mes
                        vvendedor.setMes1(indv.getIndivVentaNeta().floatValue());
                    }
                    if (contador == 2 && entro) {
                        //Es el segundo mes
                        vvendedor.setMes2(indv.getIndivVentaNeta().floatValue());
                    }
                    if (contador == 3 && entro) {
                        //Es el tercer mes
                        vvendedor.setMes3(indv.getIndivVentaNeta().floatValue());
                    }
                    if (contador == 4 && entro) {
                        //Es el cuarto mes
                        vvendedor.setMes4(indv.getIndivVentaNeta().floatValue());
                    }
                    if (contador == 5 && entro) {
                        //Es el quinto mes mes
                        vvendedor.setMes5(indv.getIndivVentaNeta().floatValue());
                    }
                    if (contador == 6 && entro) {
                        //Es el sexto mes
                        vvendedor.setMes6(indv.getIndivVentaNeta().floatValue());
                    }
                }
                contador++;
            }
            if (entro) {
                //agregar a la lista
                ventas.add(vvendedor);
            }
        }
        return ventas;

    }*/

    /**
     * Se obtienen los ultimos 6 periodos
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<String> getUltimos6Periodos() {
        ultimos6 = periodoDAO.obtenerUltimos6Meses();
        List<String> periodosGrid = new ArrayList<String>();
        for (CekPeriodo p : ultimos6) {
            String periodo = Auxiliares.getMes(p.getPeriMes()).toUpperCase()
                    .concat(" " + String.valueOf(p.getPeriAnio()));
            periodosGrid.add(periodo);
        }
        return periodosGrid;
    }

    @Transactional(readOnly = true)
    public String getPeriodo() {
        CekPeriodo periodo = (CekPeriodo) periodoDAO.obtenerUltimo();
        Calendar c = new GregorianCalendar();
        c.set(GregorianCalendar.MONTH, periodo.getPeriMes() - 1);
        c.set(GregorianCalendar.YEAR, periodo.getPeriAnio());
        c.set(GregorianCalendar.DAY_OF_MONTH, 1);
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("MMMM yyyy");
        return bartDateFormat.format(c.getTime());
    }

    /**
     * Obtiene los años que se pueden elegir en el combobox
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<String> obtenerAnios() {

        List<Integer> periodos =
                periodoDAO.obtenerAnios();
        List<String> anios = new ArrayList<String>();
        for (Integer a : periodos) {
            anios.add(String.valueOf(a));
        }
        return anios;

    }

    /**
     * Obtiene los meses que esten registrados dado un año seleccionado
     *
     * @param anio
     * @return
     */
    @Transactional(readOnly = true)
    public List<Mes> obtenerMeses(String anio) {

        List<Integer> periodos =
                periodoDAO.obtenerMeses(anio);
        List<Mes> meses = new ArrayList<Mes>();
        for (Integer a : periodos) {
            Mes m = new Mes(Auxiliares.getMes(a).toUpperCase(), a);
            meses.add(m);
        }
        return meses;
    }
}
