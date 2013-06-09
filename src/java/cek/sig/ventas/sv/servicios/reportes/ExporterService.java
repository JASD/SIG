/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.servicios.reportes;

import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

import org.springframework.stereotype.Service;

/**
 *
 * @author Antonio
 */
@Service
public class ExporterService {

    public static final String MEDIA_TYPE_EXCEL = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String MEDIA_TYPE_PDF = "application/pdf";
    public static final String MEDIA_TYPE_WORD = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    public static final String EXTENSION_TYPE_EXCEL = ".xlsx";
    public static final String EXTENSION_TYPE_PDF = ".pdf";
    public static final String EXTENSION_TYPE_WORD = ".docx";

    public HttpServletResponse export(String type,
            String fileName,
            JasperPrint jp,
            HttpServletResponse response,
            ByteArrayOutputStream baos) {

        if (type.equalsIgnoreCase(EXTENSION_TYPE_EXCEL)) {
            // Export to output stream
            exportXlsx(jp, baos);

            // Set our response properties
            // Here you can declare a custom filename
            //String fileName = "UserReport.xls";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName + EXTENSION_TYPE_EXCEL);

            // Set content type
            response.setContentType(MEDIA_TYPE_EXCEL);
            response.setContentLength(baos.size());

            return response;
        }

        if (type.equalsIgnoreCase(EXTENSION_TYPE_PDF)) {
            // Export to output stream
            exportPdf(jp, baos);

            // Set our response properties
            // Here you can declare a custom filename
            //String fileName = "UserReport.pdf";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName + EXTENSION_TYPE_PDF);

            // Set content type
            response.setContentType(MEDIA_TYPE_PDF);
            response.setContentLength(baos.size());

            return response;

        }

        if (type.equalsIgnoreCase(EXTENSION_TYPE_WORD)) {
            // Export to output stream
            exportWord(jp, baos);

            // Set our response properties
            // Here you can declare a custom filename
            //String fileName = "UserReport.pdf";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName + EXTENSION_TYPE_WORD);

            // Set content type
            response.setContentType(MEDIA_TYPE_WORD);
            response.setContentLength(baos.size());

            return response;

        }

        throw new RuntimeException("No type set for type " + type);
    }

    private void exportXlsx(JasperPrint jp, ByteArrayOutputStream baos) {
        // Create a JRXlsExporter instance
        JRXlsxExporter exporter = new JRXlsxExporter();

        // Here we assign the parameters jp and baos to the exporter
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

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

    private void exportPdf(JasperPrint jp, ByteArrayOutputStream baos) {
        // Create a JRPdfExporter instance
        JRPdfExporter exporter = new JRPdfExporter();

        // Here we assign the parameters jp and baos to the exporter
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        try {
            exporter.exportReport();

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    private void exportWord(JasperPrint jp, ByteArrayOutputStream baos) {
        // Create a JRDocxExporter instance
        JRDocxExporter exporter = new JRDocxExporter();

        // Here we assign the parameters jp and baos to the exporter
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        try {
            exporter.exportReport();

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
