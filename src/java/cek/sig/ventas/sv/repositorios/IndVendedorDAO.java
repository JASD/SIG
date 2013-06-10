/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.repositorios;

import cek.sig.ventas.sv.entidades.CekIndVendedor;
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

    @Transactional(readOnly = true)
    @Override
    public List<CekIndVendedor> findAll() {
        return (List<CekIndVendedor>) sessionFactory.getCurrentSession().getNamedQuery("CekIndVendedor.findAll").list();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CekIndVendedor> executeNamedQuery(String NamedQuery) {
        return (List<CekIndVendedor>) sessionFactory.getCurrentSession().getNamedQuery(NamedQuery).list();
    }
}
