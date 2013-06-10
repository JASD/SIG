/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.controladores.util;

import java.io.File;
import java.util.HashMap;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author Antonio
 */
public class JasperExporter {

    public static final String MEDIA_TYPE_EXCEL = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String MEDIA_TYPE_PDF = "application/pdf";
    public static final String MEDIA_TYPE_WORD = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    public static final String EXTENSION_TYPE_EXCEL = ".xlsx";
    public static final String EXTENSION_TYPE_PDF = ".pdf";
    public static final String EXTENSION_TYPE_WORD = ".docx";

    public static void export(String jasperPath,
            HashMap params,
            JRBeanCollectionDataSource dataSource,
            String type,
            File file) {

        JasperPrint jp;
        try {
            jp = JasperFillManager.fillReport(jasperPath, params, dataSource);
        } catch (JRException ex) {
            throw new RuntimeException(ex);
        }
        if (type.equalsIgnoreCase(EXTENSION_TYPE_EXCEL)) {
            exportXlsx(jp, file);
        }

        if (type.equalsIgnoreCase(EXTENSION_TYPE_PDF)) {
            exportPdf(jp, file);
        }

        if (type.equalsIgnoreCase(EXTENSION_TYPE_WORD)) {
            exportWord(jp, file);
        }
    }

    private static void exportXlsx(JasperPrint jp, File file) {
        // Create a JRXlsExporter instance
        JRXlsxExporter exporter = new JRXlsxExporter();

        // Here we assign the parameters jp and baos to the exporter
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, file);

        // Excel specific parameters
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_CELL_BACKGROUND, Boolean.TRUE);

        try {
            exporter.exportReport();

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    private static void exportPdf(JasperPrint jp, File file) {
        // Create a JRPdfExporter instance
        JRPdfExporter exporter = new JRPdfExporter();

        // Here we assign the parameters jp and baos to the exporter
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, file);

        try {
            exporter.exportReport();

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    private static void exportWord(JasperPrint jp, File file) {
        // Create a JRDocxExporter instance
        JRDocxExporter exporter = new JRDocxExporter();

        // Here we assign the parameters jp and baos to the exporter
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, file);

        try {
            exporter.exportReport();

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
