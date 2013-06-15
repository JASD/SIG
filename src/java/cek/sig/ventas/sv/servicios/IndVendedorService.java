/* * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.servicios;

import cek.sig.ventas.sv.controladores.util.Auxiliares;
import cek.sig.ventas.sv.entidades.reportes.CRVendedor;
import cek.sig.ventas.sv.entidades.CekIndVendedor;
import cek.sig.ventas.sv.entidades.CekPeriodo;
import cek.sig.ventas.sv.entidades.reportes.CNVendedor;
import cek.sig.ventas.sv.controladores.util.Mes;
import cek.sig.ventas.sv.entidades.CekVendedor;
import cek.sig.ventas.sv.entidades.reportes.IPVendedor;
import cek.sig.ventas.sv.entidades.reportes.VVendedor;
import cek.sig.ventas.sv.repositorios.VendedorDAO;
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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Antonio
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IndVendedorService {

    @Autowired
    private IndVendedorDAO indVendedorDAO;
    @Autowired
    private VendedorDAO vendedorDAO;
    @Autowired
    private PeriodoDAO periodoDAO;
    private List<CekPeriodo> ultimos6;

    /**
     * encontrar las cuentas recuperadas del ultimo periodo que existe en la
     * base de datos
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<CRVendedor> getCuentasRecuperadas() {

        //Hago la consulta a la base
        List<CekIndVendedor> records = indVendedorDAO.executeNamedQuery("CekIndVendedor.ultimo");
        List<CRVendedor> dtos = new ArrayList<CRVendedor>();

        /**
         * Mapeo los datos obtenidos para solo obtener los que se muestran en el
         * reporte especifico, para ello se debe crear una nueva entidad en
         * cek.sig.ventas.sv.entidades.reportes con los campos q tendra el
         * reporte y que implemente la interfaz Serializable
         */
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

    @Transactional(readOnly = true)
    public List<CRVendedor> getCuentasRecuperadas(String anio, int mes) {

        List<CekIndVendedor> records = indVendedorDAO.findByPeriodo(anio, mes);
        List<CRVendedor> dtos = new ArrayList<CRVendedor>();
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
     * Obtengo las cuentas nuevas por cada vendedor para el ultimo mes
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<CNVendedor> getCuentasNuevas() {
        List<CekIndVendedor> records = indVendedorDAO.executeNamedQuery("CekIndVendedor.ultimo");
        List<CNVendedor> dtos = new ArrayList<CNVendedor>();

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

    /**
     * Obtengo las cuentas nuevas para un periodo seleccionado
     *
     * @param anio
     * @param mes
     * @return
     */
    @Transactional(readOnly = true)
    public List<CNVendedor> getCuentasNuevas(String anio, int mes) {

        List<CekIndVendedor> records = indVendedorDAO.findByPeriodo(anio, mes);
        List<CNVendedor> dtos = new ArrayList<CNVendedor>();
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

    /**
     * Obtengo el indice de penetracion para los ultimos 6 meses
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<IPVendedor> getIndicePenetracion() {

        //Buscar todos los vendedores
        List<CekVendedor> vendedores = vendedorDAO
                .executeNamedQuery("CekVendedor.findAll");

        //Instancerar la nueva lista
        List<IPVendedor> indicesPenetracion = new ArrayList<IPVendedor>();

        // Y Por cada vendedor
        for (CekVendedor v : vendedores) {

            IPVendedor ipv = null;
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
                        ipv = new IPVendedor();
                        ipv.setVendedor(indv.getCekVendedor().getVendNombre());
                        entro = true;
                    }

                    if (contador == 1 && entro) {
                        //Es el primer mes
                        ipv.setMes1(indv.getIndivPenetracion().floatValue());
                    }
                    if (contador == 2 && entro) {
                        //Es el segundo mes
                        ipv.setMes2(indv.getIndivPenetracion().floatValue());
                    }
                    if (contador == 3 && entro) {
                        //Es el tercer mes
                        ipv.setMes3(indv.getIndivPenetracion().floatValue());
                    }
                    if (contador == 4 && entro) {
                        //Es el cuarto mes
                        ipv.setMes4(indv.getIndivPenetracion().floatValue());
                    }
                    if (contador == 5 && entro) {
                        //Es el quinto mes mes
                        ipv.setMes5(indv.getIndivPenetracion().floatValue());
                    }
                    if (contador == 6 && entro) {
                        //Es el sexto mes
                        ipv.setMes6(indv.getIndivPenetracion().floatValue());
                    }
                }
                contador++;
            }
            if (entro) {
                //agregar a la lista
                indicesPenetracion.add(ipv);
            }
        }
        return indicesPenetracion;

    }

    /**
     * Obtengo las ventas para los ultimos 6 meses
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<VVendedor> getVentas() {
        
        //Buscar todos los vendedores
        List<CekVendedor> vendedores = vendedorDAO
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

    }
    //Aqui empiezan los metodos que compartirian todos los services
    
    /**
     * Se obtienen los ultimos 6 periodos
     * @return 
     */
    @Transactional(readOnly = true)
    public List<String> getUltimos6Periodos() {
        ultimos6 = periodoDAO.obtenerUltimos6Meses();
        List<String> periodosGrid = new ArrayList<String>();
        for (CekPeriodo p : ultimos6) {
            String periodo = Auxiliares.getMes(p.getPeriMes()).toUpperCase()
                    .concat(" "+ String.valueOf(p.getPeriAnio()));
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
