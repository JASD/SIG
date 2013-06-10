/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.controladores.tacticos;

import cek.sig.ventas.sv.entidades.reportes.CRVendedor;
import cek.sig.ventas.sv.servicios.IndVendedorService;
import cek.sig.ventas.sv.controladores.util.JasperExporter;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class CuentasRecuperadasVendedor extends SelectorComposer<Component> {

    private static final String JASPER_PATH = "/WEB-INF/jaspers/cuentas_recuperadas_vendedor.jasper";
    @Wire
    private Combobox periodos;
    @Wire
    private Combobox formatos;
    @Wire
    private Grid crvGrid;
    @Wire
    private Label periodoSeleccionado;
    @WireVariable
    private IndVendedorService indVendedorService;
    private List<CRVendedor> crvList;
    private String periodo;

    @RequestMapping(value = "/cuentasRecuperadasVendedor")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        
        return new ModelAndView("reportesTacticos/cuentasRecuperadasVendedor");
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        crvList = indVendedorService.getCuentasRecuperadas();
        crvGrid.setModel(new ListModelList<CRVendedor>(crvList));
        periodo = indVendedorService.getPeriodo().toUpperCase();
        if (periodo != null) {
            periodoSeleccionado.setValue("Período mostrado: ".concat(periodo.toUpperCase()));
        }
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
            params.put("periodo", periodo);
            String format;
            String type;
            switch (formatos.getSelectedIndex()) {
                case 0:
                    format = JasperExporter.EXTENSION_TYPE_EXCEL;
                    type = JasperExporter.MEDIA_TYPE_EXCEL;
                    break;
                case 1:
                    format = JasperExporter.EXTENSION_TYPE_WORD;
                    type = JasperExporter.MEDIA_TYPE_WORD;
                    break;
                case 2:
                    format = JasperExporter.EXTENSION_TYPE_PDF;
                    type = JasperExporter.MEDIA_TYPE_PDF;
                    break;
                default:
                    format = JasperExporter.EXTENSION_TYPE_PDF;
                    type = JasperExporter.MEDIA_TYPE_PDF;
                    break;
            }
            File report = File.createTempFile("CuentasRecuperadasVendedores", format);
            JasperExporter.export(realPath, params, new JRBeanCollectionDataSource(crvList),
                    format, report);
            Filedownload.save(report, type);
        }
    }
}
