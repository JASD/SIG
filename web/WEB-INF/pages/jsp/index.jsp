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
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <z:page>
            <z:tabbox id="tb" height="300px"> 
                <z:tabs id="tabs">
                    <z:tab id="A" label="Tab A" />
                    <z:tab id="B" label="Tab B" />
                    <z:tab id="C" label="Tab C" />
                    <z:tab id="D" label="Tab D" />
                    <z:tab id="E" label="Tab E" />
                </z:tabs>
                <z:tabpanels>
                    <z:tabpanel>This is panel A</z:tabpanel>
                    <z:tabpanel>This is panel B</z:tabpanel>
                    <z:tabpanel>This is panel C</z:tabpanel>
                    <z:tabpanel>This is panel D</z:tabpanel>
                    <z:tabpanel>This is panel E</z:tabpanel>
                </z:tabpanels>
            </z:tabbox>
            <img src="http://www.cwv.com.ve/wp-content/uploads/2013/01/java.jpg">
        </z:page>
        Holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa k ase xD

    </body>
</html>
