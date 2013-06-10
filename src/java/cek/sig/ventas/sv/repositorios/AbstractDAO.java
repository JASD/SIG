/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.repositorios;

import java.util.List;

/**
 *
 * @author Antonio
 */
public abstract class AbstractDAO<T> {

    private Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public abstract void create(T entity);

    public abstract void edit(T entity);

    public abstract void delete(T entity);

    public abstract T find(Object id);
    
    public abstract List<T> findAll();
    
    public abstract List<T> executeNamedQuery(String NamedQuery);
}
