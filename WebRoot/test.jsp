<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>My JSP 'test.jsp' starting page</title>
  </head>
  
  <body>
    <%
    	Date date=new Date();
    	out.write(date.toLocaleString());
     %>
  </body>
</html>
