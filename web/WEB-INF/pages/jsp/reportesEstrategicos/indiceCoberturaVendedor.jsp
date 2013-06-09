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
        <title>Reporte de Indice de Cobertura por Vendedor</title>
    </head>
    <body> 
        <z:page>
            <z:window self="@{define(left)}" title="Reporte de Indice de Cobertura por Vendedor">
                
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
                                     colspan="7"  label="INDICE DE COBERTURA POR VENDEDOR"/>
                    </z:auxhead>
                    <z:auxhead>
                        <z:auxheader style="text-align:left"
                                     colspan="3" rowspan="2" label="Mes:"/>
                    </z:auxhead>
                    <z:auxhead>
                        <z:auxheader style="text-align:left"
                                     colspan="4" label="LINEA:"/>
                    </z:auxhead>
                    <z:auxhead>
                        <z:auxheader style="text-align:left"
                                     colspan="7" label="GRUPO:"/>
                    </z:auxhead>
                    <z:columns>
                        <z:column hflex="20">Vendedor</z:column>
                        <z:column hflex="6">mes 1</z:column>
                        <z:column hflex="6">mes 2</z:column>
                        <z:column hflex="6">mes 3</z:column>
                        <z:column hflex="6">mes 4</z:column>
                        <z:column hflex="6">mes 5</z:column>
                        <z:column hflex="6">mes 6</z:column>
                    </z:columns>
                    <z:rows>
                        <z:row>
                            <z:label value="Venito Camelas" />
                            <z:label value="0.3" />
                            <z:label value="0.3" />
                            <z:label value="0.3" />
                            <z:label value="0.3" />
                            <z:label value="0.3" />
                            <z:label value="0.3" />
                        </z:row>
                    </z:rows>
                </z:grid>
            </z:window>
            <z:div self="@{define(right)}" width="100%">

            </z:div> 
        </z:page>
    </body>
</html>
