/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.controladores.tacticos;

import cek.sig.ventas.sv.controladores.util.JasperExporter;
import cek.sig.ventas.sv.entidades.reportes.CNVendedor;
import cek.sig.ventas.sv.controladores.util.Mes;
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
public class CuentasNuevasVendedor extends SelectorComposer<Component> {

    private static final String JASPER_PATH = "/WEB-INF/jaspers/cuentas-nuevas-por-vendedor.jasper";
    @Wire
    private Combobox anios;
    @Wire
    private Combobox meses;
    @Wire
    private Combobox formatos;
    @Wire
    private Grid cnvGrid;
    @Wire
    private Label periodoSeleccionado;
    @WireVariable
    private IndVendedorService indVendedorService;
    private List<CNVendedor> cnvList;
    private String periodo;

    @RequestMapping(value = "/cuentasNuevasVendedor")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mv = new ModelAndView("reportesTacticos/cuentasNuevasVendedor");
        return mv;
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        cnvList = indVendedorService.getCuentasNuevas();
        cnvGrid.setModel(new ListModelList<CNVendedor>(cnvList));
        periodo = indVendedorService.getPeriodo().toUpperCase();
        //Cargar los años distintos que hay en la base (solo obtiene maximo 5)
        anios.setModel(new ListModelList<String>(
                indVendedorService.obtenerAnios()));
            periodoSeleccionado.setValue("Período mostrado: ".concat(periodo));
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
            File report = File.createTempFile("CuentasNuevasVendedores", format);
            JasperExporter.export(realPath, params, new JRBeanCollectionDataSource(cnvList),
                    format, report);
            Filedownload.save(report, type);
        }
    }
    
     /**
     * Carga los meses despues de que el usuario seleccioo un año
     */
    @Listen("onSelect = #anios")
    public void cargarMeses(){
        String anioSeleccionado = anios.getSelectedItem().getValue();
        meses.setModel(new ListModelList<Mes>(
                indVendedorService.obtenerMeses(anioSeleccionado)));
    }
    
    /**
     * Recargar los datos automaticamente
     * despues de seleccionar el mes
     */
    @Listen("onSelect = #meses")
    public void recargarModelo() {
        String anio = anios.getSelectedItem().getValue();
        Mes mes = (Mes) meses.getSelectedItem().getValue();
        cnvList = indVendedorService.getCuentasNuevas(anio,
                mes.getNumero());
        cnvGrid.setModel(new ListModelList<CNVendedor>(cnvList));
        periodo = mes.getMes() + " " + String.valueOf(anio);
        periodoSeleccionado.setValue("Período mostrado: ".concat(periodo));
    }
}
