/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.controladores;

import cek.sig.ventas.sv.entidades.CekUsuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Row;

/**
 *
 * @author Antonio
 */
@Controller
public class VerUsuariosController extends SelectorComposer<Component> {

    @Wire
    private Grid userGrid;

    @RequestMapping(value = "/usuarios")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mv = new ModelAndView("admin/usuarios");
        return mv;
    }

    @Listen("onCheck = checkbox")
    public void deshabilitarUsuario(Event e) {
        Row row = (Row) e.getTarget().getParent();
        int position = row.getIndex();
        CekUsuario u = (CekUsuario) userGrid.getModel().getElementAt(position);
        String message = null;
        String type = null;
        try {
            //borrar
            message = "Usuario Deshabilitado Correctamente";
            type = Clients.NOTIFICATION_TYPE_INFO;
        } catch (Exception ex) {
            message = ex.getMessage();
            type = Clients.NOTIFICATION_TYPE_ERROR;
        } finally {
            Clients.showNotification(message, type, this.getSelf(), "top_center", 2000, true);
        }
    }
}
