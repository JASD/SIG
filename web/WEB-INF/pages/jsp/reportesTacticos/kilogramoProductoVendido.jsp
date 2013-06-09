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
        <title>Reporte de kilogramos de productos vendidos por categoría</title>
    </head>
    <body> 
        <z:page>
            <z:window self="@{define(left)}" title='Kilogramos de productos vendidos por categoría'>
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
                    <tr>
                        <td>
                            <z:label value="Categoría: "/>
                        </td>
                        <td>
                            <z:combobox>
                                <z:comboitem label="Limpieza"/>
                                <z:comboitem label="Hogar"/>
                            </z:combobox>
                        </td>
                        <td>
                            <img style="cursor: help" 
                                 alt="ayuda"
                                 src="images/botonAyuda.gif"
                                 title="Es el período de meses anteriores a incluir en el reporte">
                        </td>
                        <td>

                        </td>
                    </tr>
                </table>
                <z:grid style="clear:right;float:left; margin:10px">
                    <z:auxhead>
                        <z:auxheader style="text-align:center"
                                     colspan="4" label='Reporte de kilogramos de productos vendidos por categoría'/>
                    </z:auxhead>
                    <z:columns>
                        <z:column hflex="20">Producto</z:column>
                        <z:column hflex="6">proyeccion en kilogramos</z:column>
                        <z:column hflex="6">kilogramos vendidos productos</z:column>
                        <z:column hflex="6">cumplimiento %</z:column>
                    </z:columns>
                    <z:rows>
                        <z:row>
                            <z:label value="Limpieza" />
                            <z:label value="100" />
                            <z:label value="90" />
                            <z:label value="90%" />
                        </z:row>
                    </z:rows>
                </z:grid>
            </z:window>
            <z:div self="@{define(right)}" width="100%">

            </z:div> 
        </z:page>
    </body>
</html>
