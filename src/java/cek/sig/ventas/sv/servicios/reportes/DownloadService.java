/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.servicios.reportes;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Antonio
 */
@Service
public class DownloadService {

    @Autowired
    private ExporterService exporter;
    @Autowired
    private TokenService tokenService;

    public void download(String jasperPath, HashMap params, String type, String fileName,
            JRDataSource dataSource, String token, HttpServletResponse response) {

        try {
            // 1. Create the JasperPrint object
            // Make sure to pass the JasperReport, report parameters, and data source
            JasperPrint jp = JasperFillManager.fillReport(jasperPath, params, dataSource);

            // 2. Create an output byte stream where data will be written
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // 3. Export report
            exporter.export(type, fileName, jp, response, baos);

            // 4. Write to reponse stream
            write(token, response, baos);

        } catch (JRException jre) {
            //logger.error("Unable to process download");
            throw new RuntimeException(jre);
        }
    }

    /**
     * Writes the report to the output stream
     */
    private void write(String token, HttpServletResponse response,
            ByteArrayOutputStream baos) {

        try {
            //logger.debug(baos.size());

            // Retrieve output stream
            ServletOutputStream outputStream = response.getOutputStream();
            // Write to output stream
            baos.writeTo(outputStream);
            // Flush the stream
            outputStream.flush();
            // Remove download token
            tokenService.remove(token);

        } catch (Exception e) {
            //logger.error("Unable to write report to the output stream");
            throw new RuntimeException(e);
        }
    }
}
