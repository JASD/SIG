/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.repositorios;

import cek.sig.ventas.sv.entidades.CekVendedor;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Antonio
 */
@Repository
public class CekVendedorDAO extends AbstractDAO<CekVendedor> {

    @Autowired
    private SessionFactory sessionFactory;

    public CekVendedorDAO() {
        super(CekVendedor.class);
    }

    @Override
    public void create(CekVendedor entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(CekVendedor entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CekVendedor entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CekVendedor find(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Transactional(readOnly = true)
    @Override
    public List<CekVendedor> findAll() {
        return (List<CekVendedor>) sessionFactory.getCurrentSession().getNamedQuery("CekVendedor.findAll").list();
    }

    //@Transactional(readOnly = true)
    @Override
    public List<CekVendedor> executeNamedQuery(String NamedQuery) {
        return (List<CekVendedor>) sessionFactory.getCurrentSession().getNamedQuery(NamedQuery).list();
    }
}
