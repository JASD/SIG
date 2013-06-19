/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.repositorios;

import cek.sig.ventas.sv.entidades.CekClasificacion;
import cek.sig.ventas.sv.entidades.CekPeriodo;
import cek.sig.ventas.sv.entidades.CekIndArticulo;
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
public class IndArticuloDAO extends AbstractDAO<CekIndArticulo> {

    @Autowired
    private SessionFactory sessionFactory;

    public IndArticuloDAO() {
        super(CekIndArticulo.class);
    }

    @Override
    public void create(CekIndArticulo entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(CekIndArticulo entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CekIndArticulo entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CekIndArticulo find(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Transactional(readOnly = true)
    @Override
    public List<CekIndArticulo> findAll() {
        return (List<CekIndArticulo>) sessionFactory.getCurrentSession().getNamedQuery("CekIndArticulo.findAll").list();
    }

    //@Transactional(readOnly = true)
    @Override
    public List<CekIndArticulo> executeNamedQuery(String NamedQuery) {
        return (List<CekIndArticulo>) sessionFactory.getCurrentSession().getNamedQuery(NamedQuery).list();
    }

    public CekIndArticulo obtenerPorPeriodoClasificacion(CekPeriodo p, CekClasificacion c) {
        Query q = sessionFactory.getCurrentSession()
                .getNamedQuery("CekIndArticulo.findByClasificacionPeriodo");
        q.setParameter("periodo", p);
        q.setParameter("categoria", c);
        return (CekIndArticulo) q.uniqueResult();

    }
    
     /**
     * Busca los indicadores para un periodo dado
     * @param anio
     * @param mes
     * @return 
     */
    public List<CekIndArticulo> findByPeriodoCategoria(String anio, int mes, 
            CekClasificacion categoria) {
        
        Query q = sessionFactory.getCurrentSession().getNamedQuery("CekIndArticulo.findByPeriodoCategoria");
        q.setInteger("anio", Integer.valueOf(anio));
        q.setInteger("mes", mes);
        q.setParameter("categoria", categoria);
        return (List<CekIndArticulo>) q.list();

    }
}
