/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.controladores.tacticos;

import cek.sig.ventas.sv.servicios.IndVendedorService;
import cek.sig.ventas.sv.servicios.reportes.DownloadService;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;

/**
 *
 * @author antonio
 */
@Controller
public class CuentasRecuperadasVendedor extends SelectorComposer<Component> {

    private static final String JASPER_PATH = "/WEB-INF/jaspers/cuentas_recuperadas_vendedor.jasper";
    @Autowired
    private IndVendedorService indVendedorService;
    @Autowired
    private DownloadService downloadService;

    @RequestMapping(value = "/cuentasRecuperadasVendedor")
    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        return new ModelAndView("reportesTacticos/cuentasRecuperadasVendedor");
    }

    @RequestMapping(value = "/cuentasRecuperadasVendedor/download")
    public void download(HttpServletRequest request,
            HttpServletResponse response) {

        //Aqui colocamos los parametros a enviar en el reporte
        HashMap<String, Object> params = new HashMap<String, Object>();
        //params.put("Title", "User Report");

        String realPath = request.getServletContext().getRealPath(JASPER_PATH);
        downloadService.download(realPath, params, ".docx", "word", 
                indVendedorService.getDataSource(), "crv", response);


    }
}
