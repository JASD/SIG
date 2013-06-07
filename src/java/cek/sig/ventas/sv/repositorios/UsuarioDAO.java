/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.repositorios;

import cek.sig.ventas.sv.entidades.CekUsuario;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Antonio
 */
@Repository
public class UsuarioDAO extends AbstractDAO<CekUsuario> {

    @Autowired
    private SessionFactory sessionFactory;

    public UsuarioDAO() {
        super(CekUsuario.class);
    }

    @Override
    public void create(CekUsuario entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(CekUsuario entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CekUsuario entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional(readOnly = true)
    @Override
    public CekUsuario find(Object id) {
        return (CekUsuario) sessionFactory.getCurrentSession().get(CekUsuario.class, (String) id);
    }
}
