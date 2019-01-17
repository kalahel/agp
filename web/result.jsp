<%@ page import="com.ucp.PropositionEngine.PropositionCriterias" %>
<%@ page import="com.ucp.PropositionEngine.PropositionEngine" %>
<%@ page import="com.ucp.business.data.Model.Stay" %><%--
  Created by IntelliJ IDEA.
  User: Cyril
  Date: 17/01/2019
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String activityFromValue = request.getParameter("activities-from-value");
    String freeDay = request.getParameter("free-day");
    String comfortFromValue = request.getParameter("comfort-from-value");
    String keyWords = request.getParameter("keywords");
    String budget = request.getParameter("budget");
    String stayDuration = request.getParameter("from-value");

    //int comfort = Integer.parseInt(comfortFromValue);
    int comfort = Math.round(Float.parseFloat(comfortFromValue));

    //int freeInt = Integer.parseInt(freeDay);
    int freeInt = Math.round(Float.parseFloat(freeDay));


    //int budgetInt = Integer.parseInt(budget);
    int budgetInt = Math.round(Float.parseFloat(budget));

    //int activityInt = Integer.parseInt(activityFromValue);
    int activityInt = Math.round(Float.parseFloat(activityFromValue));

    //int stayInt = Integer.parseInt(stayDuration);
    int stayInt = Math.round(Float.parseFloat(stayDuration));

    System.err.println(stayInt + " " + comfort + " " + freeInt + " " + activityInt + " " + budgetInt);


    PropositionCriterias prop = PropositionCriterias.builder()
            .averageActivitiesPerDay(activityInt)
            .chillDays(freeInt)
            .comfort(comfort)
            .criterias(keyWords)
            .maxBudget(budgetInt)
            .stayDuration(stayInt)
            .build();
    String test = prop.toString();
    try {
        PropositionEngine.setProposition(prop);
    } catch (Exception e) {
        System.err.println("BDUIEZDZQDPQZ");
    }

    try {
        Stay stay = PropositionEngine.computeProposition();
        System.err.println("CHOCO " + stay.toString() + " CHOCOLAT");
    } catch (NullPointerException e) {
        System.err.println("NPE : ComputeProposition");
    }
%>
<html>
<head>
    <title>Result</title>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <!--===============================================================================================-->
    <link href="css/util.css" rel="stylesheet" type="text/css">
    <link href="css/main.css" rel="stylesheet" type="text/css">
    <link href="css/result.css" rel="stylesheet" type="text/css">
    <!--===============================================================================================-->
</head>
<body>
<div class="container-contact100">
    <div class="wrap-contact100">
        <fieldset>
            <legend>Stay</legend>
            <div class="contact100-form-range-value">
                <ul>
                    <li><p>Hotel :<%=test%>;%></p></li>
                    <%--<li><p>Price :<%=stay.getPrice()%></p></li>
                    <li><p>Comfort  :<%=stay.getComfort()%>;%></p></li>--%>
                </ul>
            </div>
            <div class="stays">
                <p>Escursion's</p>
            </div>
        </fieldset>
    </div>
</div>

</body>
</html>
