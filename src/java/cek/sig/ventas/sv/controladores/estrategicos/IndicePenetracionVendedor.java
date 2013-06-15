/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.controladores.estrategicos;

import cek.sig.ventas.sv.entidades.reportes.IPVendedor;
import cek.sig.ventas.sv.servicios.IndVendedorService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;

/**
 *
 * @author antonio
 */
@Controller
public class IndicePenetracionVendedor extends SelectorComposer<Component> {

    @Wire
    private Combobox formatos;
    @Wire
    private Grid indPen;
    @Wire
    private Label mes1;
    @Wire
    private Label mes2;
    @Wire
    private Label mes3;
    @Wire
    private Label mes4;
    @Wire
    private Label mes5;
    @Wire
    private Label mes6;
    @WireVariable
    private IndVendedorService indVendedorService;
    private List<IPVendedor> ipvList;

    @RequestMapping(value = "/indicePenetracionVendedor")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mv = new ModelAndView("reportesEstrategicos/indicePenetracionVendedor");

        return mv;
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        //Obtengo los encabezados para la tabla
        //Aqui ya deja guardada la lista de los ultimos periodos en el service
        List<String> periodos = indVendedorService.getUltimos6Periodos();
        int contador = 1;
        for (String p : periodos) {
            if (contador == 1) {
                mes1.setValue(p);
            }
            if (contador == 2) {
                mes2.setValue(p);
            }
            if (contador == 3) {
                mes3.setValue(p);
            }
            if (contador == 4) {
                mes4.setValue(p);
            }
            if (contador == 5) {
                mes5.setValue(p);
            }
            if (contador == 6) {
                mes6.setValue(p);
            }
            contador++;
        }

        //Se llena la tabla
        ipvList = indVendedorService.getIndicePenetracion();
        indPen.setModel(new ListModelList<IPVendedor>(ipvList));

    }
}
