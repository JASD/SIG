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
        <title>Reporte de ventas por vendedor</title>
    </head>
    <body> 
        <z:page>
            <z:window self="@{define(left)}" title="Ventas por vendedor">
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

                <z:button style="margin:20px 20px 10px 10px;clear:left;float:right"
                          label="Generar Reporte"/>
                <z:grid style="clear:right;float:left; margin:10px">
                    <z:auxhead>
                        <z:auxheader style="text-align:center"
                                     colspan="4" label="Reporte de ventas por vendedor"/>
                    </z:auxhead>
                    <z:columns>
                        <z:column hflex="20">categoría</z:column>
                        <z:column hflex="6">mes 1</z:column>
                        <z:column hflex="6">mes 2</z:column>
                        <z:column hflex="6">mes 4</z:column>
                        <z:column hflex="6">mes 5</z:column>
                        <z:column hflex="6">mes 6</z:column>
                    </z:columns>
                    <z:rows>
                        <z:row>
                            <z:label value="Limpieza" />
                            <z:label value="$91123.10" />
                            <z:label value="$29120.01" />
                            <z:label value="$20849.97" />
                            <z:label value="$91123.10" />
                            <z:label value="$29120.01" />
                        </z:row>
                    </z:rows>
                </z:grid>
            </z:window>
            <z:div self="@{define(right)}" width="100%">

            </z:div> 
        </z:page>
    </body>
</html>
