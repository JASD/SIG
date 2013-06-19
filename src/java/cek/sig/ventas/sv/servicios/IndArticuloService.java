/* * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.servicios;

import cek.sig.ventas.sv.controladores.util.Auxiliares;
import cek.sig.ventas.sv.controladores.util.Mes;
import cek.sig.ventas.sv.entidades.CekClasificacion;
import cek.sig.ventas.sv.entidades.CekIndArticulo;
import cek.sig.ventas.sv.entidades.CekIndArticulo;
import cek.sig.ventas.sv.entidades.CekIndClasificacion;
import cek.sig.ventas.sv.entidades.CekIndVendedor;
import cek.sig.ventas.sv.entidades.CekPeriodo;
import cek.sig.ventas.sv.entidades.reportes.CRVendedor;
import cek.sig.ventas.sv.entidades.reportes.KPCategoria;
import cek.sig.ventas.sv.entidades.reportes.UCategoria;
import cek.sig.ventas.sv.entidades.reportes.VCategoria;
import cek.sig.ventas.sv.entidades.reportes.VKCategoria;
import cek.sig.ventas.sv.entidades.reportes.VPPTOCategoria;
import cek.sig.ventas.sv.repositorios.ClasificacionDAO;
import cek.sig.ventas.sv.repositorios.IndArticuloDAO;
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
 * @author Ever
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IndArticuloService {

    @Autowired
    private PeriodoDAO periodoDAO;
    private List<CekPeriodo> ultimos6;
    @Autowired
    private IndArticuloDAO indArticuloDAO;
    @Autowired
    private ClasificacionDAO clasificacionDAO;

     
     @Transactional(readOnly = true)
    public List<KPCategoria> getCategorias(String anio, int mes, String categoria) {

        //Hago la consulta a la base
             
        List<CekIndArticulo> records = indArticuloDAO.findByPeriodoCategoria(anio, mes,categoria);
        List<KPCategoria> dtos = new ArrayList<KPCategoria>();

       
        for (CekIndArticulo indc : records) {
            KPCategoria kvc = new KPCategoria();
            kvc.setProducto(indc.getCekArticulo().getArtiNombre());
            kvc.setProyKg(indc.getIndaProyKg().floatValue());
            kvc.setKgVproductos(indc.getIndaKg().floatValue());
            kvc.setCumplimiento(indc.getIndaCumpProy().floatValue());
            
            
            dtos.add(kvc);
        }

        return dtos;
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
     * Obtiene los aÃ±os que se pueden elegir en el combobox
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
     * Obtiene los meses que esten registrados dado un aÃ±o seleccionado
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
