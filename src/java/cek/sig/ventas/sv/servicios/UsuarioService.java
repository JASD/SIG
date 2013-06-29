/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.servicios;

import cek.sig.ventas.sv.entidades.CekRol;
import cek.sig.ventas.sv.entidades.CekUsuario;
import cek.sig.ventas.sv.repositorios.RolesDAO;
import cek.sig.ventas.sv.repositorios.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;
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
    private RolesDAO rolesDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioDAO.find(username);
    }

    @Transactional(readOnly = true)
    public void registrarUsuario(String usuario, String pass, short tipoUsuario) {
        CekUsuario nuevo = new CekUsuario();
        nuevo.setContrasenaUsuario(pass);
        nuevo.setNombreUsuario(usuario);
        nuevo.setEstaHabilitadoUsuario(true);

        List<CekUsuario> nuevos = new ArrayList<CekUsuario>();
        nuevos.add(nuevo);

        CekRol rol = new CekRol();
        rol.setNumeroRol(tipoUsuario);
        List<CekRol> roles = new ArrayList<CekRol>();
        rol.setCekUsuarioList(nuevos);

        nuevo.setCekRolList(roles);
        usuarioDAO.create(nuevo);
    }

    @Transactional(readOnly = true)
    public boolean verificarUsuario(String usuario) {

        if (usuarioDAO.existeUsuario(usuario)) {
            return true;
        } else {
            return false;
        }
    }
}
