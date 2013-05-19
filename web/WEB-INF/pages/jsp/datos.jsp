<%-- 
    Document   : datos
    Created on : 05-18-2013, 09:51:14 PM
    Author     : antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Expires" content="-1" />
        <%
            request.setAttribute(org.zkoss.zk.ui.sys.Attributes.NO_CACHE, Boolean.TRUE);
        %>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <z:page>
            <z:panel id="container" height="400px">
                <z:panelchildren>
                    <z:grid id="demoGrid" mold="paging" pagingPosition="both"

                            autopaging="true" vflex="true">
                        <z:columns>
                            <z:column width="80px" label="Order #" align="left" />
                            <z:column label="Item Purchased" align="center" />
                            <z:column label="Purchased Time" align="center" />
                            <z:column width="80px" label="Paid" align="center" />
                        </z:columns>

                        <z:rows>
                            <c:forEach items="${purchases}" var="each" >
                                <z:row>
                                    <z:label value="${each.id}" />
                                    <z:label value="${each.item}" />
                                    <z:timebox value="${each.time}"
                                               disabled="true" cols="12" format="a hh:mm:ss" mold="rounded" />
                                    <z:checkbox checked="${each.paid}" disabled="true"/>
                                </z:row>
                            </c:forEach>
                        </z:rows>
                    </z:grid>
                </z:panelchildren>
            </z:panel>
        </z:page>
    </body>
</html>
