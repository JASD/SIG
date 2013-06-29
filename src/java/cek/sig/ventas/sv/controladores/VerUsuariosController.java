/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;

/**
 *
 * @author Antonio
 */
@Controller
public class VerUsuariosController extends SelectorComposer<Component> {

    @RequestMapping(value = "/usuarios")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mv = new ModelAndView("admin/usuarios");
        return mv;
    }
}
