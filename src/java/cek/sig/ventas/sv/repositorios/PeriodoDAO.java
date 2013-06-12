/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.repositorios;

import cek.sig.ventas.sv.entidades.CekPeriodo;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    @Override
    public List<CekPeriodo> findAll() {
        return (List<CekPeriodo>) sessionFactory.getCurrentSession().getNamedQuery("CekPeriodo.findAll").list();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CekPeriodo> executeNamedQuery(String NamedQuery) {
        return (List<CekPeriodo>) sessionFactory.getCurrentSession().getNamedQuery(NamedQuery).list();
    }
}
