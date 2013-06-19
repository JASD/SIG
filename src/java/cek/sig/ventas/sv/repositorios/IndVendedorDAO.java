/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.repositorios;

import cek.sig.ventas.sv.entidades.CekIndVendedor;
import cek.sig.ventas.sv.entidades.CekPeriodo;
import cek.sig.ventas.sv.entidades.CekVendedor;
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
public class IndVendedorDAO extends AbstractDAO<CekIndVendedor> {

    @Autowired
    private SessionFactory sessionFactory;

    public IndVendedorDAO() {
        super(CekIndVendedor.class);
    }

    @Override
    public void create(CekIndVendedor entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(CekIndVendedor entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CekIndVendedor entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CekIndVendedor find(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Transactional(readOnly = true)
    @Override
    public List<CekIndVendedor> findAll() {
        return (List<CekIndVendedor>) sessionFactory.getCurrentSession().getNamedQuery("CekIndVendedor.findAll").list();
    }

    //@Transactional(readOnly = true)
    @Override
    public List<CekIndVendedor> executeNamedQuery(String NamedQuery) {
        return (List<CekIndVendedor>) sessionFactory.getCurrentSession().getNamedQuery(NamedQuery).list();
    }
    
    /**
     * Busca los indicadores para un periodo dado
     * @param anio
     * @param mes
     * @return 
     */
    public List<CekIndVendedor> findByPeriodo(String anio, int mes) {
        Query q = sessionFactory.getCurrentSession().getNamedQuery("CekIndVendedor.findByPeriodo");
        q.setInteger("anio", Integer.valueOf(anio));
        q.setInteger("mes", mes);
        return (List<CekIndVendedor>) q.list();

    }
    
    
    

    
    public CekIndVendedor obtenerPorPeriodoVendedor(CekPeriodo p, CekVendedor v){
        Query q = sessionFactory.getCurrentSession()
                .getNamedQuery("CekIndVendedor.findByVendedorPeriodo");
        q.setParameter("periodo", p);
        q.setParameter("vendedor", v);
        return (CekIndVendedor)q.uniqueResult();
        
    }
}
