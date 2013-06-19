/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.repositorios;

import cek.sig.ventas.sv.entidades.CekClasificacion;
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
public class ClasificacionDAO extends AbstractDAO<CekClasificacion> {

    @Autowired
    private SessionFactory sessionFactory;

    public ClasificacionDAO() {
        super(CekClasificacion.class);
    }

    @Override
    public void create(CekClasificacion entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(CekClasificacion entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CekClasificacion entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CekClasificacion find(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CekClasificacion> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CekClasificacion> executeNamedQuery(String NamedQuery) {
       return sessionFactory.getCurrentSession().getNamedQuery(NamedQuery).list();
    }
    
    public List<CekClasificacion> obtenerCategorias(){
        Query q = sessionFactory.getCurrentSession().getNamedQuery("CekClasificacion.findAll");
        return q.list();
    }

}
