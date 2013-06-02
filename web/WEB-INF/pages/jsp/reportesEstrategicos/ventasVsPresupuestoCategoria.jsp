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
        <title>Reporte de Ventas contra presupuesto</title>
    </head>
    <body> 
        <z:page>
            <z:window self="@{define(left)}" title="Reporte de Ventas contra Presupuesto">

                <z:label style="margin:20px 20px 10px 10px" 
                         value="Período: "/>
                <z:datebox id="createdDatebox1" value="${win$composer.inventoryItem.createdDate}"
                           format="dd/MM/yyyy" constraint="no empty" />
                <z:label style="margin:20px 20px 10px 10px" 
                         value="- "/>
                <z:datebox id="createdDatebox2" value="${win$composer.inventoryItem.createdDate}"
                           format="dd/MM/yyyy" constraint="no empty" />


                <img style="cursor: help" 
                     alt="ayuda"
                     src="images/botonAyuda.gif"
                     title="Es el período de meses anteriores a incluir en el reporte">

                <z:button style="margin:20px 20px 10px 10px;clear:left;float:right"
                          label="Generar Reporte"/>
                <z:grid style="clear:right;float:left; margin:10px">

                    <z:auxhead>
                        <z:auxheader style="text-align:center" 
                                     colspan="10"  label="VENTAS CONTRA PRESUPUESTO"/>
                    </z:auxhead>                     

                    <z:columns>
                        <z:column hflex="15">Categoria</z:column>
                        <z:column hflex="6">ventas</z:column>
                        <z:column hflex="6">Presupuesto</z:column>
                        <z:column hflex="4">Variacion %</z:column>
                        <z:column hflex="6">Ventas Acumuladas</z:column>
                        <z:column hflex="6">Presupuesto Acumulado</z:column>
                        <z:column hflex="4">Variacion Acumulada %</z:column>

                    </z:columns>
                    <z:rows>
                        <z:row>
                            <z:label value="Limpieza" />
                            <z:label value="100" />
                            <z:label value="100" />
                            <z:label value="0.0" />
                            <z:label value="20" />
                            <z:label value="20" />
                            <z:label value="10" />
                            <z:label value="0.0" />                                                       
                        </z:row>
                    </z:rows>
                </z:grid>
            </z:window>
            <z:div self="@{define(right)}" width="100%">

            </z:div> 
        </z:page>
    </body>
</html>
