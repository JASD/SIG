/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.controladores.estrategicos;

import cek.sig.ventas.sv.controladores.util.JasperExporter;
import cek.sig.ventas.sv.controladores.util.Mes;
import cek.sig.ventas.sv.entidades.reportes.VPPTOCategoria;
import cek.sig.ventas.sv.servicios.IndClasificacionService;
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
public class VentasVsPresupuestoCategoria extends SelectorComposer<Component> {
//////////////////falta modificar el jasper

    private static final String JASPER_PATH = "/WEB-INF/jaspers/ventas-contra-ppto.jasper";
    @Wire
    private Combobox anios;
    @Wire
    private Combobox meses;
    @Wire
    private Combobox formatos;
    @Wire
    private Grid vpcGrid;
    @Wire
    private Label periodoSeleccionado;
    @WireVariable
    private IndClasificacionService indClasificacionService;
    private List<VPPTOCategoria> vpcList;
    private String periodo;

    @RequestMapping(value = "/ventasVsPresupuestoCategoria")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //logger.info("Returning hello view");
        //PurchaseData pd = new PurchaseData();
        ModelAndView mv = new ModelAndView("reportesEstrategicos/ventasVsPresupuestoCategoria");
        //mv.addObject("purchases", pd.getAllPurchases());

        return mv;
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        vpcList = indClasificacionService.getClasificacion();
        vpcGrid.setModel(new ListModelList<VPPTOCategoria>(vpcList));
        periodo = indClasificacionService.getPeriodo().toUpperCase();
        //Cargar los años distintos que hay en la base (solo obtiene maximo 5)
        anios.setModel(new ListModelList<String>(
                indClasificacionService.obtenerAnios()));
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
            JasperExporter.export(realPath, params, new JRBeanCollectionDataSource(vpcList),
                    format, report);
            Filedownload.save(report, type);
        }
    }

    /**
     * Carga los meses despues de que el usuario seleccioo un año
     */
    @Listen("onSelect = #anios")
    public void cargarMeses() {
        String anioSeleccionado = anios.getSelectedItem().getValue();
        meses.setModel(new ListModelList<Mes>(
                indClasificacionService.obtenerMeses(anioSeleccionado)));
    }

    /**
     * Recargar los datos automaticamente despues de seleccionar el mes
     */
    @Listen("onSelect = #meses")
    public void recargarModelo() {
        String anio = anios.getSelectedItem().getValue();
        Mes mes = (Mes) meses.getSelectedItem().getValue();
        vpcList = indClasificacionService.getClasificacion(anio,
                mes.getNumero());
        vpcGrid.setModel(new ListModelList<VPPTOCategoria>(vpcList));
        periodo = mes.getMes() + " " + String.valueOf(anio);
        periodoSeleccionado.setValue("Período mostrado: ".concat(periodo));
    }
}
