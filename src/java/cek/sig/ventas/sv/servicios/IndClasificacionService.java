/* * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.servicios;

import cek.sig.ventas.sv.controladores.util.Auxiliares;
import cek.sig.ventas.sv.controladores.util.Mes;
import cek.sig.ventas.sv.entidades.CekClasificacion;
import cek.sig.ventas.sv.entidades.CekIndClasificacion;
import cek.sig.ventas.sv.entidades.CekPeriodo;
import cek.sig.ventas.sv.entidades.reportes.CRVendedor;
import cek.sig.ventas.sv.entidades.reportes.UCategoria;
import cek.sig.ventas.sv.entidades.reportes.VCategoria;
import cek.sig.ventas.sv.entidades.reportes.VKCategoria;
import cek.sig.ventas.sv.entidades.reportes.VPPTOCategoria;
import cek.sig.ventas.sv.repositorios.ClasificacionDAO;
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
    @Autowired
    private ClasificacionDAO clasificacionDAO;
    //private List<CekIndClasificacion> records = new ArrayList<CekIndClasificacion>();
    //prueba de paco
            /*@Transactional(readOnly = true)
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
     }*/ /**
     * Obtengo las ventas para los ultimos 6 meses
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<VCategoria> getVentas() {

        //Buscar todas las categorias
        List<CekClasificacion> categorias = clasificacionDAO
                .executeNamedQuery("CekClasificacion.findAll");

        //Instancerar la nueva lista
        List<VCategoria> ventas = new ArrayList<VCategoria>();

        // Y Por cada categoria
        for (CekClasificacion c : categorias) {

            VCategoria vcategoria = null;
            int contador = 1;
            boolean entro = false;

            //Para los ultimos 6 meses
            for (CekPeriodo p : ultimos6) {

                //consultar si tiene indices para ese periodo
                CekIndClasificacion indv = indClasificacionDAO.obtenerPorPeriodoClasificacion(p, c);
                if (indv != null) {
                    //si tiene
                    if (!entro) {
                        //si ya entro antes no se vuelve a crear instancia
                        //ni se pone de nuevo el nombre
                        vcategoria = new VCategoria();
                        vcategoria.setCategoria(indv.getCekClasificacion().getClasNombre());
                        entro = true;
                    }

                    if (contador == 1 && entro) {
                        //Es el primer mes
                        vcategoria.setMes1(indv.getIndcVentaNeta().floatValue());
                    }
                    if (contador == 2 && entro) {
                        //Es el segundo mes
                        vcategoria.setMes2(indv.getIndcVentaNeta().floatValue());
                    }
                    if (contador == 3 && entro) {
                        //Es el tercer mes
                        vcategoria.setMes3(indv.getIndcVentaNeta().floatValue());
                    }
                    if (contador == 4 && entro) {
                        //Es el cuarto mes
                        vcategoria.setMes4(indv.getIndcVentaNeta().floatValue());
                    }
                    if (contador == 5 && entro) {
                        //Es el quinto mes mes
                        vcategoria.setMes5(indv.getIndcVentaNeta().floatValue());
                    }
                    if (contador == 6 && entro) {
                        //Es el sexto mes
                        vcategoria.setMes6(indv.getIndcVentaNeta().floatValue());
                    }
                }
                contador++;
            }
            if (entro) {
                //agregar a la lista
                ventas.add(vcategoria);
            }
        }
        return ventas;

    }

    @Transactional(readOnly = true)
    public List<UCategoria> getUtilidad() {

        //Hago la consulta a la base
        List<CekIndClasificacion> records = indClasificacionDAO.executeNamedQuery("CekIndClasificacion.ultimo");
        List<UCategoria> dtos = new ArrayList<UCategoria>();

        /**
         * Mapeo los datos obtenidos para solo obtener los que se muestran en el
         * reporte especifico, para ello se debe crear una nueva entidad en
         * cek.sig.ventas.sv.entidades.reportes con los campos q tendra el
         * reporte y que implemente la interfaz Serializable
         */
        for (CekIndClasificacion indu : records) {
            UCategoria uc = new UCategoria();
            uc.setCategoria(indu.getCekClasificacion().getClasNombre());
            uc.setvBrutas(indu.getIndcVentaBruta());
            uc.setDescuento(indu.getIndcTotDesc());
            uc.setvNetas(indu.getIndcVentaNeta());
            uc.setCosto(indu.getIndcCostoVenta());
            uc.setCostoPorc(uc.getCosto() / uc.getvNetas());
            uc.setGastos(indu.getIndcGastosInd());
            uc.setGastosPorc(uc.getGastos() / uc.getvNetas());
            uc.setUtilidad(indu.getIndcUtilidad());
            uc.setUtilidadPorc(uc.getUtilidad() / uc.getvNetas());
            dtos.add(uc);
        }

        return dtos;
    }

    @Transactional(readOnly = true)
    public List<UCategoria> getUtilidad(String anio, int mes) {

        List<CekIndClasificacion> records = indClasificacionDAO.findByPeriodo(anio, mes);
        List<UCategoria> dtos = new ArrayList<UCategoria>();
        for (CekIndClasificacion indu : records) {
            UCategoria uc = new UCategoria();
            uc.setCategoria(indu.getCekClasificacion().getClasNombre());
            uc.setvBrutas(indu.getIndcVentaBruta());
            uc.setDescuento(indu.getIndcTotDesc());
            uc.setvNetas(indu.getIndcVentaNeta());
            uc.setCosto(indu.getIndcCostoVenta());
            uc.setCostoPorc(uc.getCosto() / uc.getvNetas());
            uc.setGastos(indu.getIndcGastosInd().floatValue());
            uc.setGastosPorc(uc.getGastos() / uc.getvNetas());
            uc.setUtilidad(indu.getIndcUtilidad());
            uc.setUtilidadPorc(uc.getUtilidad() / uc.getvNetas());
            dtos.add(uc);
        }
        return dtos;

    }

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

    @Transactional(readOnly = true)
    public List<VPPTOCategoria> getClasificacion() {
        List<CekIndClasificacion> records = indClasificacionDAO.executeNamedQuery("CekIndClasificacion.ultimo");
        List<VPPTOCategoria> dtos = new ArrayList<VPPTOCategoria>();

        for (CekIndClasificacion ind : records) {
            VPPTOCategoria cne = new VPPTOCategoria();
            cne.setCategoria(ind.getCekClasificacion().getClasNombre());
            cne.setVentas(ind.getIndcVentaNeta());
            cne.setPresupuesto(ind.getIndcPpto());
            cne.setVariacion(ind.getIndcVarPpto());
            dtos.add(cne);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public List<VPPTOCategoria> getClasificacion(String anio, int mes) {

        List<CekIndClasificacion> records = indClasificacionDAO.findByPeriodo(anio, mes);
        List<VPPTOCategoria> dtos = new ArrayList<VPPTOCategoria>();
        for (CekIndClasificacion ind : records) {
            VPPTOCategoria cne = new VPPTOCategoria();
            cne.setCategoria(ind.getCekClasificacion().getClasNombre());
            cne.setVentas(ind.getIndcVentaNeta());
            cne.setPresupuesto(ind.getIndcPpto());
            cne.setVariacion(ind.getIndcVarPpto());
            dtos.add(cne);
        }
        return dtos;

    }
    
    @Transactional(readOnly = true)
    public List<VKCategoria> getKg() {

        //Hago la consulta a la base
        List<CekIndClasificacion> records = indClasificacionDAO.executeNamedQuery("CekIndClasificacion.ultimo");
        List<VKCategoria> dtos = new ArrayList<VKCategoria>();

        /**
         * Mapeo los datos obtenidos para solo obtener los que se muestran en el
         * reporte especifico, para ello se debe crear una nueva entidad en
         * cek.sig.ventas.sv.entidades.reportes con los campos q tendra el
         * reporte y que implemente la interfaz Serializable
         */
        Float totKg = Float.valueOf(0);
        for (CekIndClasificacion indc : records) {
            VKCategoria kvc = new VKCategoria();
            kvc.setCategoria(indc.getCekClasificacion().getClasNombre());
            kvc.setKilogramos(indc.getIndcKg());
            totKg = totKg + kvc.getKilogramos();
            dtos.add(kvc);
        }
        
        for(VKCategoria vkc: dtos){
            vkc.setPorcentaje(vkc.getKilogramos() / totKg);
        }

        return dtos;
    }
     
     @Transactional(readOnly = true)
    public List<VKCategoria> getKg(String anio, int mes) {

        //Hago la consulta a la base
             
        List<CekIndClasificacion> records = indClasificacionDAO.findByPeriodo(anio, mes);
        List<VKCategoria> dtos = new ArrayList<VKCategoria>();

        /**
         * Mapeo los datos obtenidos para solo obtener los que se muestran en el
         * reporte especifico, para ello se debe crear una nueva entidad en
         * cek.sig.ventas.sv.entidades.reportes con los campos q tendra el
         * reporte y que implemente la interfaz Serializable
         */
         Float totKg = Float.valueOf(0);
        for (CekIndClasificacion indc : records) {
            VKCategoria kvc = new VKCategoria();
            kvc.setCategoria(indc.getCekClasificacion().getClasNombre());
            kvc.setKilogramos(indc.getIndcKg());
             totKg = totKg + kvc.getKilogramos();
            //kvc.setPorcentaje(indc.getIndcVarPpto().floatValue());
            dtos.add(kvc);
        }
        
         for(VKCategoria vkc: dtos){
            vkc.setPorcentaje(vkc.getKilogramos() / totKg);
        }

        return dtos;
    } 
   
    @Transactional(readOnly = true)
    public List<CekClasificacion> obtenerCatgorias() {
        return clasificacionDAO.obtenerCategorias();

    }


}
