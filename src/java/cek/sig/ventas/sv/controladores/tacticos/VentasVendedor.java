/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.controladores.tacticos;

import cek.sig.ventas.sv.controladores.util.JasperExporter;
import cek.sig.ventas.sv.entidades.reportes.VVendedor;
import cek.sig.ventas.sv.servicios.IndVendedorService;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;

/**
 *
 * @author antonio
 */
@Controller
public class VentasVendedor extends SelectorComposer<Component> {

    private static final String JASPER_PATH = "/WEB-INF/jaspers/venta-por-vendedor.jasper";
    @Wire
    private Combobox formatos;
    @Wire
    private Grid vvGrid;
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
    private List<VVendedor> vvList;

    @RequestMapping(value = "/ventasVendedor")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mv = new ModelAndView("reportesTacticos/ventasVendedor");
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
        vvList = indVendedorService.getVentas();
        vvGrid.setModel(new ListModelList<VVendedor>(vvList));

    }

    @Listen("onClick = #downloadButton")
    public void generarReporte() throws JRException, IOException {
        if (formatos.getSelectedIndex() == -1) {
            Clients.showNotification("Debe Seleccionar un formato", Clients.NOTIFICATION_TYPE_INFO,
                    formatos, "top_center", 2000);
        } else {
            Execution exec = Executions.getCurrent();
            HttpServletRequest request = (HttpServletRequest) exec.getNativeRequest();
            String realPath = request.getServletContext().getRealPath(JASPER_PATH);
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("periodo1", mes1.getValue());
            params.put("periodo2", mes2.getValue());
            params.put("periodo3", mes3.getValue());
            params.put("periodo4", mes4.getValue());
            params.put("periodo5", mes5.getValue());
            params.put("periodo6", mes6.getValue());
            String format;
            String type;
            switch (formatos.getSelectedIndex()) {
                case 0:
                    format = JasperExporter.EXTENSION_TYPE_EXCEL;
                    type = JasperExporter.MEDIA_TYPE_EXCEL;
                    params.put("mostrar", Boolean.valueOf(false));
                    break;
                case 1:
                    format = JasperExporter.EXTENSION_TYPE_WORD;
                    type = JasperExporter.MEDIA_TYPE_WORD;
                    params.put("mostrar", Boolean.valueOf(true));
                    break;
                case 2:
                    format = JasperExporter.EXTENSION_TYPE_PDF;
                    type = JasperExporter.MEDIA_TYPE_PDF;
                    params.put("mostrar", Boolean.valueOf(true));
                    break;
                default:
                    format = JasperExporter.EXTENSION_TYPE_PDF;
                    type = JasperExporter.MEDIA_TYPE_PDF;
                    params.put("mostrar", Boolean.valueOf(true));
                    break;
            }
            File report = File.createTempFile("VentasVendedores", format);
            JasperExporter.export(realPath, params, new JRBeanCollectionDataSource(vvList),
                    format, report);
            Filedownload.save(report, type);
        }
    }
}
