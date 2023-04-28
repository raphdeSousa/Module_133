<%-- 
    Document   : pageAutorise
    Created on : 24 mars 2023, 16:12:31
    Author     : GfellerM01
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="beans.BeanInfo"%>
<%@ page import="beans.BeanErro"%>
<jsp:useBean id="Info"  scope="session" class="beans.BeanInfo"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login réussi, voici vos informations !</h1>
        <p>
            Votre nom est <%=Info.getUsername()%> <br>
            Votre password est <%=Info.getPassword()%><br>
        </p>
    </body>
</html>
