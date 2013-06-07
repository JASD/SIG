<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Expires" content="-1" />
        <%
            request.setAttribute(org.zkoss.zk.ui.sys.Attributes.NO_CACHE, Boolean.TRUE);
        %>
        <title>SIG para control de ventas Cek centroamérica, El Salvador</title>
    </head>
    <body> 
        <z:page>
            <form id="f_login" name="login" action="j_spring_security_check" method="POST"
                  xmlns:html="native">
                <z:grid>
                    <z:rows>
                        <z:row>User: <z:textbox id="u" name="j_username"/></z:row>
                        <z:row>Password: <z:textbox id="p" type="password" name="j_password"/></z:row>
                        <z:row spans="2">
                            <z:hbox>
                                <z:button type="submit" label="Acceder" />
                                <z:label id="error"/>
                            </z:hbox>
                        </z:row>
                    </z:rows>
                </z:grid>
                <z:zscript > 
                    String parameter = Executions.getCurrent().getParameter("error");
                    if(parameter!=null){
                    if(parameter.equals("1")){
                    error.setValue("Usuario o Contraseña Incorrecto");
                    }
                    }
                </z:zscript>
            </form>
        </z:page>
    </body>
</html>
