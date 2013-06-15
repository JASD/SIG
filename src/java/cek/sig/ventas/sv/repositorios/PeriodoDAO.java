/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.repositorios;

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
public class PeriodoDAO extends AbstractDAO<CekPeriodo> {

    @Autowired
    private SessionFactory sessionFactory;

    public PeriodoDAO() {
        super(CekPeriodo.class);
    }

    @Override
    public void create(CekPeriodo entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(CekPeriodo entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CekPeriodo entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CekPeriodo find(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Transactional(readOnly = true)
    @Override
    public List<CekPeriodo> findAll() {
        return (List<CekPeriodo>) sessionFactory.getCurrentSession().getNamedQuery("CekPeriodo.findAll").list();
    }

    //@Transactional(readOnly = true)
    @Override
    public List<CekPeriodo> executeNamedQuery(String NamedQuery) {
        return (List<CekPeriodo>) sessionFactory.getCurrentSession().getNamedQuery(NamedQuery).list();
    }
    
    
    public CekPeriodo obtenerUltimo(){
        return (CekPeriodo) sessionFactory.getCurrentSession()
                .getNamedQuery("CekPeriodo.findUltimo").uniqueResult();
    }
    
    public List<Integer> obtenerAnios(){
        Query q = sessionFactory.getCurrentSession()
                .getNamedQuery("CekPeriodo.findAnios").setMaxResults(10);
        List<Integer> anios = q.list();
        return anios;
    }
    
    public List<Integer> obtenerMeses(String anio){
        Query q = sessionFactory.getCurrentSession()
                .getNamedQuery("CekPeriodo.findMeses");
        q.setInteger("anio", Integer.valueOf(anio));
        List<Integer> meses = q.list();
        return meses;
    }
    
    
}
