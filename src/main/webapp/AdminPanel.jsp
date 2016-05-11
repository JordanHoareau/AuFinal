<%-- 
    Document   : AdminPanel
    Created on : 11 mai 2016, 16:42:02
    Author     : norabbit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html>
<f:view>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    <h:form>
        <h:commandLink action="#{Movie.findAll}" value="Show All Movie Items"/>        
    </h:form>    
    </body>
</html>
</f:view>
