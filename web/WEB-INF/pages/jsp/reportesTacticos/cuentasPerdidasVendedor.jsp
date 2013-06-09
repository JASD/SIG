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
        <title>Reporte de cuentas perdidas por vendedor</title>
    </head>
    <body> 
        <z:page>
            <z:window self="@{define(left)}" title='Kilogramos de productos vendidos por categoría'>
                <z:label style="margin:20px 20px 10px 10px" 
                         value="Período a analizar: "/>
                <z:combobox style="margin:20px">
                    <z:comboitem label="1 mes"/>
                    <z:comboitem label="2 meses"/>
                    <z:comboitem label="3 meses"/>
                    <z:comboitem label="4 meses"/>
                    <z:comboitem label="5 meses"/>
                    <z:comboitem label="6 meses"/>
                </z:combobox>

                <img style="cursor: help" 
                     alt="ayuda"
                     src="images/botonAyuda.gif"
                     title="Es el período de meses anteriores a incluir en el reporte">

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

                <z:grid style="clear:right;float:left; margin:10px">
                    <z:auxhead>
                        <z:auxheader style="text-align:center"
                                     colspan="4" label='Reporte de cuentas perdidas por vendedor'/>
                    </z:auxhead>
                    <z:columns>
                        <z:column hflex="20">vendedor</z:column>
                        <z:column hflex="6">cuentas perdidas</z:column>
                        <z:column hflex="6">porcentaje de cartera</z:column>
                        <z:column hflex="6">comentarios</z:column>
                    </z:columns>
                    <z:rows>
                        <z:row>
                            <z:label value="Venito Camelas" />
                            <z:label value="10" />
                            <z:label value="9%" />
                            <z:label value="Por incapacidad" />
                        </z:row>
                    </z:rows>
                </z:grid>
            </z:window>
            <z:div self="@{define(right)}" width="100%">

            </z:div> 
        </z:page>
    </body>
</html>
