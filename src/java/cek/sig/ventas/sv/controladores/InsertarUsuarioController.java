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
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

/**
 *
 * @author Antonio
 */
@Controller
public class InsertarUsuarioController extends SelectorComposer<Component>{
    
    @Wire
    private Combobox tipoUsuario;
    @Wire
    private Button registrarUsuario;
    @Wire
    private Textbox usuario;
    @Wire
    private Textbox pass;
    @Wire
    private Textbox pass2;
    
    //@WireVariable
    //private IndVendedorService indVendedorService;
    //private List<CVVendedor> cvvList;
    //private String periodo;

    @RequestMapping(value = "/registrarUsuario")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //logger.info("Returning hello view");
        //PurchaseData pd = new PurchaseData();
        ModelAndView mv = new ModelAndView("admin/crearUsuario");
        //mv.addObject("purchases", pd.getAllPurchases());

        return mv;
    }

    @Listen("onClick = #registrarUsuario")
    public void guardarUsuario() {
        if (tipoUsuario.getSelectedIndex() == -1) {
            Clients.showNotification("Debe Seleccionar un tipo de usuario", Clients.NOTIFICATION_TYPE_INFO,
                    tipoUsuario, "top_center", 2000);
        }
    }

    @Listen("onSelect = #tipoUsuario")
    public void seleccionarTipoUsuario() {

        Clients.showNotification("Debe Seleccionar un tipo de usuario" + tipoUsuario.getSelectedIndex(), Clients.NOTIFICATION_TYPE_INFO,
                tipoUsuario, "top_center", 2000);

    }
    
}
