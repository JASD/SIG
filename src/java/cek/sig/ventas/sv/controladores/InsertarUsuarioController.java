/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.controladores;

import cek.sig.ventas.sv.servicios.UsuarioService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.apache.commons.codec.digest.DigestUtils;
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
public class InsertarUsuarioController extends SelectorComposer<Component> {

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
    @WireVariable
    private UsuarioService usuarioService;
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

        int minUsuario = 6;
        int minPass = 6;
        if (tipoUsuario.getSelectedIndex() == -1) {
            Clients.showNotification("Debe Seleccionar un tipo de usuario", Clients.NOTIFICATION_TYPE_INFO,
                    tipoUsuario, "top_center", 2000);
        } else {

            if (usuario.getText().length() < minUsuario) {
                Clients.showNotification("El usuario debe tener almenos " + minUsuario + " caracteres", Clients.NOTIFICATION_TYPE_INFO,
                        usuario, "top_center", 2000);
            } else {
                if (pass.getText().length() < minPass) {
                    Clients.showNotification("El la contraseña debe tener almenos " + minUsuario + " caracteres", Clients.NOTIFICATION_TYPE_INFO,
                            pass, "top_center", 2000);
                } else {
                    if (!pass.getText().equals(pass2.getText())) {
                        Clients.showNotification("El la contraseñas no coinciden", Clients.NOTIFICATION_TYPE_INFO,
                                pass, "top_center", 2000);
                    } else {

                        short tipo = (short) (tipoUsuario.getSelectedIndex() + 1);
                        usuarioService.registrarUsuario(usuario.getText(), DigestUtils.sha256Hex(pass.getText()).toLowerCase(), tipo);
                    }
                }
            }
        }
    }
}
