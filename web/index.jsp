<%@ page import="com.ucp.jsfbeans.ExampleBean" %><%--
  Created by IntelliJ IDEA.
  User: mathieu.hannoun
  Date: 19/12/2018
  Time: 09:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ExampleBean b = new ExampleBean();
%>
<html>
<head>
    <title>PAGE1</title>
</head>
<body>
<p>Whoa guys so hard, project already set up for you </p>
<jsp:useBean id="test" class="com.ucp.jsfbeans.ExampleBean"/>
<jsp:getProperty name="test" property="message"/>
<jsp:setProperty name="test" property="message" value="Hello JSP..."/>
<p>Got message....</p>
<p>
    <%=b.sayHello("sandwiches")%>
</p>
<form action="bonjour.jsp">
    First name:<br>
    <input type="text" name="firstname" value="Mickey"><br>
    Last name:<br>
    <input type="text" name="lastname" value="Mouse"><br><br>
    <input type="submit" value="Submit">
</form>

</body>
</html>
