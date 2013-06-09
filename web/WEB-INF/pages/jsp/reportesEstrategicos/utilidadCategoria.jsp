<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <z:init class="org.zkoss.zk.ui.util.Composition" arg0="/WEB-INF/pages/zul/plantilla.zul" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Expires" content="-1" />
        <%
            request.setAttribute(org.zkoss.zk.ui.sys.Attributes.NO_CACHE, Boolean.TRUE);
        %>
        <title>Reporte de utilidades por categoria</title>
    </head>
    <body> 
        <z:page>
            <z:window self="@{define(left)}" title="Reporte de Utilidades por Categoria">

                <table style="margin:20px 20px 10px 10px">
                    <tr>
                        <td>
                            <z:label value="Mes: "/>
                        </td>
                        <td>
                            <z:combobox>
                                <z:comboitem label="1"/>
                                <z:comboitem label="2"/>
                                <z:comboitem label="3"/>
                                <z:comboitem label="4"/>
                                <z:comboitem label="5"/>
                                <z:comboitem label="6"/>
                                <z:comboitem label="7"/>
                                <z:comboitem label="8"/>
                                <z:comboitem label="9"/>
                                <z:comboitem label="10"/>
                                <z:comboitem label="11"/>
                                <z:comboitem label="12"/>
                            </z:combobox>
                        </td>
                        <td>
                            <z:label value="Año: "/>
                        </td>
                        <td>
                            <z:combobox>
                                <z:comboitem label="2001"/>
                                <z:comboitem label="2002"/>
                                <z:comboitem label="2003"/>
                                <z:comboitem label="2004"/>
                                <z:comboitem label="2005"/>
                                <z:comboitem label="2006"/>
                                <z:comboitem label="2007"/>
                                <z:comboitem label="2008"/>
                                <z:comboitem label="2009"/>
                                <z:comboitem label="2010"/>
                                <z:comboitem label="2011"/>
                                <z:comboitem label="2012"/>
                                <z:comboitem label="2013"/>
                            </z:combobox>    
                        </td>
                        <td>
                            <img style="cursor: help" 
                                 alt="ayuda"
                                 src="images/botonAyuda.gif"
                                 title="Es el período de meses anteriores a incluir en el reporte">
                            <br>
                        </td>
                        <td style="width: 100%">
                            <z:button style="margin:20px 20px 10px 10px;float:right"
                                      label="Generar Reporte"/>
                            <z:combobox style="margin:20px 20px 10px 10px;float:right">
                                <z:comboitem label="Archivo de excel"/>
                                <z:comboitem label="Archivo de word"/>
                                <z:comboitem label="Archivo PDF"/>
                                <z:comboitem label="Archivo de texto"/>
                            </z:combobox>
                            <z:label style="margin:20px 20px 10px 10px;clear:left;float:right"
                                     value="Tipo de reporte: "/>
                        </td>
                    </tr>
                </table>

                <z:grid style="clear:right;float:left; margin:10px">

                    <z:auxhead>
                        <z:auxheader style="text-align:center" 
                                     colspan="10"  label="UTILIDADES POR CATEGORIA"/>
                    </z:auxhead>

                    <z:auxhead>
                        <z:auxheader style="text-align:center"  label=" " colspan="1" />
                        <z:auxheader style="text-align:center"  label="Ventas" colspan="3"/>
                        <z:auxheader style="text-align:center"  label="Costo Ventas" colspan="2"/>
                        <z:auxheader style="text-align:center"  label="Gastos Directos" colspan="2"/>
                        <z:auxheader style="text-align:center"  label="Utilidad" colspan="2"/>
                    </z:auxhead>


                    <z:columns>
                        <z:column hflex="15">Categoria</z:column>
                        <z:column hflex="6">Brutas</z:column>
                        <z:column hflex="6">Descuento</z:column>
                        <z:column hflex="6">Neta</z:column>
                        <z:column hflex="6">Monto</z:column>
                        <z:column hflex="3">%</z:column>
                        <z:column hflex="6">Monto</z:column>
                        <z:column hflex="3">%</z:column>
                        <z:column hflex="6">Monto</z:column>
                        <z:column hflex="3">%</z:column>
                    </z:columns>
                    <z:rows>
                        <z:row>
                            <z:label value="Limpieza" />
                            <z:label value="100" />
                            <z:label value="20" />
                            <z:label value="80" />
                            <z:label value="20" />
                            <z:label value="0.2" />
                            <z:label value="10" />
                            <z:label value="0.1" />
                            <z:label value="50" />
                            <z:label value="0.5" />

                        </z:row>
                    </z:rows>
                </z:grid>
            </z:window>
            <z:div self="@{define(right)}" width="100%">

            </z:div> 
        </z:page>
    </body>
</html>
