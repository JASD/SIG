/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.repositorios;

import cek.sig.ventas.sv.entidades.CekClasificacion;
import cek.sig.ventas.sv.entidades.CekIndClasificacion;
import cek.sig.ventas.sv.entidades.CekPeriodo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Antonio
 */
@Repository
public class IndClasificacionDAO extends AbstractDAO<CekIndClasificacion> {

    @Autowired
    private SessionFactory sessionFactory;

    public IndClasificacionDAO() {
        super(CekIndClasificacion.class);
    }

    @Override
    public void create(CekIndClasificacion entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(CekIndClasificacion entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CekIndClasificacion entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CekIndClasificacion find(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Transactional(readOnly = true)
    @Override
    public List<CekIndClasificacion> findAll() {
        return (List<CekIndClasificacion>) sessionFactory.getCurrentSession().getNamedQuery("CekIndClasificacion.findAll").list();
    }

    //@Transactional(readOnly = true)
    @Override
    public List<CekIndClasificacion> executeNamedQuery(String NamedQuery) {
        return (List<CekIndClasificacion>) sessionFactory.getCurrentSession().getNamedQuery(NamedQuery).list();
    }

    public CekIndClasificacion obtenerPorPeriodoClasificacion(CekPeriodo p, CekClasificacion c) {
        Query q = sessionFactory.getCurrentSession()
                .getNamedQuery("CekIndClasificacion.findByClasificacionPeriodo");
        q.setParameter("periodo", p);
        q.setParameter("categoria", c);
        return (CekIndClasificacion) q.uniqueResult();

    }
    
     /**
     * Busca los indicadores para un periodo dado
     * @param anio
     * @param mes
     * @return 
     */
    public List<CekIndClasificacion> findByPeriodo(String anio, int mes) {
        Query q = sessionFactory.getCurrentSession().getNamedQuery("CekIndClasificacion.findByPeriodo");
        q.setInteger("anio", Integer.valueOf(anio));
        q.setInteger("mes", mes);
        return (List<CekIndClasificacion>) q.list();

    }
}
