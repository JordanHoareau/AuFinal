<%-- 
    Document   : adminlogin
    Created on : 12 mai 2016, 15:46:57
    Author     : norabbit
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Admin Authentification</title> 
    </head> 
    <body> 
        <h1>Login Page</h1> 
    <center> 
        <h2>Signup Details</h2> 
        <form action="LoginCheck.jsp" method="post"> 
            <br/>Username:<input type="text" name="username"> 
            <br/>Password:<input type="password" name="password"> 
            <br/><input type="submit" value="Submit"> 
        </form> 
    </center> 
</body> 
</html>
