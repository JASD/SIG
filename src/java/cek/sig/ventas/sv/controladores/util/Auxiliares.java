/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cek.sig.ventas.sv.controladores.util;

/**
 *
 * @author antonio
 */
public class Auxiliares {

    public static String getMes(int mes) {
        String r = "";
        switch (mes) {
            case 1:
                r = "enero";
                break;
            case 2:
                r = "febrero";
                break;
            case 3:
                r = "marzo";
                break;
            case 4:
                r = "abril";
                break;
            case 5:
                r = "mayo";
                break;
            case 6:
                r = "junio";
                break;
            case 7:
                r = "julio";
                break;
            case 8:
                r = "agosto";
                break;
            case 9:
                r = "septiembre";
                break;
            case 10:
                r = "octubre";
                break;
            case 11:
                r = "noviembre";
                break;
            case 12:
                r = "diciembre";
                break;
        }
        return r;
    }

    public String getConsultaIndVendedor(String idVendedor[], String periodos[]) {
        String consulta = "";
        Double aux1 = 0.0;
        Double aux2 = 0.0;
        int aux3 = 0, aux4 = 0, aux5 = 0, aux6 = 0, aux7 = 0, aux8 = 0, aux9 = 0, aux10 = 0;
        for (int i = 0; i < idVendedor.length; i++) {
            for (int j = 0; j < periodos.length; j++) {
                aux10 = (int) (2 + Math.random() * 10);
                aux9 = (int) (2 + Math.random() * 10);
                aux8 = (int) (10 + Math.random() * 30);
                consulta += "insert into cek_ind_vendedor values ('" + idVendedor[i] + "','" + periodos[j] + "',\n"
                        + (Math.rint((aux1 = (500 + Math.random() * 2000)) * 100) / 100) + ",\n"
                        + (Math.rint((aux2 = (500 + Math.random() * 2000)) * 100) / 100) + ",\n"
                        + (Math.rint((aux1 / aux2) * 100) / 100) + ",\n"
                        + (Math.rint(((aux10 + 0.0) / (aux8 + 0.0)) * 100) / 100) + ",\n"
                        + (Math.rint(((aux9 + 0.0) / (aux8 + 0.0)) * 100) / 100) + ",\n"
                        + (aux3 = (int) (4 + Math.random() * 5)) + ",\n"
                        + (aux4 = (int) (4 + Math.random() * 5)) + ",\n"
                        + (Math.rint(((aux3 + 0.0) / (aux4 + 0.0)) * 100) / 100) + ",\n"
                        + (aux5 = (int) (2 + Math.random() * 3)) + ",\n"
                        + (Math.rint((aux6 = (aux5 + (int) (Math.random() * 3))) * 100) / 100) + ",\n"
                        + (Math.rint(((aux5 + 0.0) / (aux6 + 0.0)) * 100) / 100) + ",\n"
                        + (aux7 = (int) (2 + Math.random() * 3)) + ",\n"
                        + aux8 + ",\n"
                        + aux9 + ",\n"
                        + aux10 + "); \n";
            }
        }
        return consulta;
    }

    public String getConsultaIndClasificacion(String idClasificacion[], String periodos[], String pais[]) {
        String consulta = "";
        Double aux1 = 0.0;
        Double aux2 = 0.0;
        double aux3 = 0, aux4 = 0, aux5 = 0, aux6 = 0;
        for (int i = 0; i < idClasificacion.length; i++) {
            for (int j = 0; j < periodos.length; j++) {
                aux6 = (500 + Math.random() * 2000);
                consulta += "insert into cek_ind_clasificacion values ('" + idClasificacion[i] + "','" + periodos[j] + "'," + pais[2] + "',\n"
                        + (Math.rint((aux1 = (500 + Math.random() * 2000)) * 100) / 100) + ",\n"
                        + (Math.rint((aux2 = (50 + Math.random() * 200)) * 100) / 100) + ",\n"
                        + (Math.rint((aux3 = (aux1 - aux2)) * 100) / 100) + ",\n"
                        + (Math.rint((aux4 = (50 + Math.random() * 500)) * 100) / 100) + ",\n"
                        + (Math.rint((aux5 = (50 + Math.random() * 500)) * 100) / 100) + ",\n"
                        + (Math.rint(((aux3 - aux4 - aux5)) * 100) / 100) + ",\n"
                        + (Math.rint(((500 + Math.random() * 2000)) * 100) / 100) + ",\n"
                        + (Math.rint(((aux3 / aux6)) * 100) / 100) + ",\n"
                        + (Math.rint((aux6 * 100)) / 100) + "); \n\n";
            }
        }
        return consulta;
    }

    public String getConsultaIndArticulo(String idArticulo[], String periodos[], String pais[]) {
        String consulta = "";
        Double aux1 = 0.0;
        Double aux2 = 0.0;
        for (int i = 0; i < idArticulo.length; i++) {
            for (int j = 0; j < periodos.length; j++) {
                consulta += "insert into cek_ind_articulo values ('" + pais[2] + "','" + periodos[j] + "'," + idArticulo[i] + "',\n"
                        + (Math.rint(((500 + Math.random() * 2000)) * 100) / 100) + ",\n"
                        + (Math.rint((aux1 = (50 + Math.random() * 200)) * 100) / 100) + ",\n"
                        + (Math.rint((aux2 = (50 + Math.random() * 200)) * 100) / 100) + ",\n"
                        + (Math.rint((aux1 / aux2) * 100) / 100) + "); \n\n";
            }
        }
        return consulta;
    }
}
