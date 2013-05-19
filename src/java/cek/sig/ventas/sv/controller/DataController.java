/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.controller;

import cek.sig.ventas.sv.model.Purchase;
import cek.sig.ventas.sv.model.PurchaseData;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.ListModelList;

/**
 *
 * @author antonio
 */
@Controller
public class DataController extends SelectorComposer<Component>{

     private static final long serialVersionUID = 1L;
     
    private PurchaseData data = new PurchaseData();
    private ListModelList<String> availableItems;

    public DataController() {
         availableItems = new ListModelList<String>(data.getAvailableItems());
    }

    @RequestMapping(value = "/datos")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //logger.info("Returning hello view");
        PurchaseData pd = new PurchaseData();
        ModelAndView mv = new ModelAndView("datos");
        mv.addObject("purchases", pd.getAllPurchases());

        return mv;
    }

    public ListModelList<Purchase> getAllPurchases() {
        return new ListModelList<Purchase>(data.getAllPurchases());
    }
     
    public ListModelList<String> getAvailableItems() {
        return availableItems;
    }
}
