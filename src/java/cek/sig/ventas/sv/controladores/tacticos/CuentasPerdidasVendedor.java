/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.controladores.tacticos;

import cek.sig.ventas.sv.entidades.reportes.CPVendedor;
import cek.sig.ventas.sv.servicios.IndVendedorService;
import cek.sig.ventas.sv.controladores.util.JasperExporter;
import cek.sig.ventas.sv.controladores.util.Mes;
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
import org.zkoss.zul.Button;
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
public class CuentasPerdidasVendedor extends SelectorComposer<Component> {

    private static final String JASPER_PATH = "/WEB-INF/jaspers/cuentas_perdidas_vendedor.jasper";
    @Wire
    private Combobox anios;
    @Wire
    private Combobox meses;    
    @Wire
    private Combobox formatos;
    @Wire
    private Grid cpvGrid;
    @Wire
    private Label periodoSeleccionado;
    @Wire
    private Button downloadButton;
    @WireVariable
    private IndVendedorService indVendedorService;
    private List<CPVendedor> cpvList;
    private String periodo;

    @RequestMapping(value = "/cuentasPerdidasVendedor")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        
        return new ModelAndView("reportesTacticos/cuentasPerdidasVendedor");
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        //cpvList = cuentasPerdidasVendedorService.getCuentasPerdidas();
        cpvList = indVendedorService.getCuentasPerdidas();
        cpvGrid.setModel(new ListModelList<CPVendedor>(cpvList));
        periodo = indVendedorService.getPeriodo().toUpperCase();
         //Cargar los aÃ±os distintos que hay en la base (solo obtiene maximo 5)
        anios.setModel(new ListModelList<String>(
                indVendedorService.obtenerAnios()));
            periodoSeleccionado.setValue("PerÃ­odo mostrado: ".concat(periodo));
        if(cpvList.isEmpty()){
            downloadButton.setDisabled(true);
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
            File report = File.createTempFile("CuentasPerdidasVendedores", format);
            JasperExporter.export(realPath, params, new JRBeanCollectionDataSource(cpvList),
                    format, report);
            Filedownload.save(report, type);
        }
    }
    
    /**
     * Carga los meses despues de que el usuario seleccioo un aÃ±o
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
        cpvList = indVendedorService.getCuentasPerdidas(anio,
                mes.getNumero());
        cpvGrid.setModel(new ListModelList<CPVendedor>(cpvList));
        periodo = mes.getMes() + " " + String.valueOf(anio);
        periodoSeleccionado.setValue("PerÃ­odo mostrado: ".concat(periodo));
        downloadButton.setDisabled(false);
        if(cpvList.isEmpty()){
            downloadButton.setDisabled(true);
        }
    }
}
