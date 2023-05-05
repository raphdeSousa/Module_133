<%-- 
    Document   : erreur
    Created on : 24 mars 2023, 16:10:27
    Author     : GfellerM01
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.BeanInfo"%>
<%@page import="beans.BeanErro"%>
<jsp:useBean id="Error"  scope="session" class="beans.BeanErro"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Vous n'êtes pas logguer</h1>
        <p>
            Votre Host est <%=Error.getHost()%> <br>
            Votre message est <%=Error.getMsg()%><br>
        </p>
    </body>
</html>
