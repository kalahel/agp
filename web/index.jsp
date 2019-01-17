<%@ page import="com.ucp.jsfbeans.ExampleBean" %><%--
  Created by IntelliJ IDEA.
  User: mathieu.hannoun
  Date: 19/12/2018
  Time: 09:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <p>Whoa guys so hard, project already set up for you </p>
  <jsp:useBean id = "test" class = "com.ucp.jsfbeans.ExampleBean" />
  <jsp:setProperty name = "test"  property = "message" value = "Hello JSP..." />
  <p>Got message....</p>
  <jsp:getProperty name = "test" property = "message" />

  </body>
</html>
