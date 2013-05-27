/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.controller.reportesTacticos;

import cek.sig.ventas.sv.controller.reportesEstrategicos.*;
import cek.sig.ventas.sv.controller.*;
import cek.sig.ventas.sv.model.Car;
import cek.sig.ventas.sv.model.CarService;
import cek.sig.ventas.sv.model.CarServiceImpl;
import java.io.IOException;
import java.util.List;
import java.util.Set;
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
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.ext.Selectable;

/**
 *
 * @author antonio
 */
@Controller
public class VentasCategoria extends SelectorComposer<Component>{
    
     @RequestMapping(value = "/ventasCategoria")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //logger.info("Returning hello view");
        //PurchaseData pd = new PurchaseData();
        ModelAndView mv = new ModelAndView("reportesTacticos/ventasCategoria");
        //mv.addObject("purchases", pd.getAllPurchases());

        return mv;
    }
}
