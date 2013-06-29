/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.servicios;


import cek.sig.ventas.sv.entidades.CekUsuario;
import cek.sig.ventas.sv.repositorios.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Antonio
*/ 
@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioDAO.find(username);
    }
    
    @Transactional
    public void registrarUsuario(String usuario,String pass,int tipoUsuario){
        CekUsuario nuevo=new CekUsuario();
        nuevo.setContrasenaUsuario(pass);
        nuevo.setNombreUsuario(usuario);
        nuevo.setEstaHabilitadoUsuario(true);
        
        
        usuarioDAO.create(null);
    }
}
