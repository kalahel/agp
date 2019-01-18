<%@ page import="com.ucp.PropositionEngine.PropositionCriterias" %>
<%@ page import="com.ucp.PropositionEngine.PropositionEngine" %>
<%@ page import="com.ucp.business.data.Model.Stay" %>
<%--
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

    String hotel = "";
    int price = 0;
    int comfortEval = 0;

    String dayHtml = "";


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


    PropositionCriterias prop = PropositionCriterias.builder()
            .averageActivitiesPerDay(activityInt)
            .chillDays(freeInt)
            .comfort(comfort)
            .criterias(keyWords)
            .maxBudget(budgetInt)
            .stayDuration(stayInt)
            .build();
    try {
        PropositionEngine.setProposition(prop);
    } catch (Exception e) {
        System.err.println("__" + prop.getClass().getName() + "__");
    }

    try {
        Stay stay = PropositionEngine.computeProposition();

        hotel = stay.getHotel().getBeach().getName();
        price = (int) Math.round(stay.getPrice());
        comfortEval = (int) Math.round(stay.getComfort() / 40);
        dayHtml = "<ul>";
        for (int index = 0; index < stay.getDaysList().size(); index++) {
            dayHtml += "<li>" + "<b>" + "Day : " + (index + 1) + "</b><ul>";
            if (stay.getDaysList().get(index).getExcursion().getTouristicSites().size() > 1)
                for (int index_2 = 0; index_2 < stay.getDaysList().get(index).getExcursion().getTouristicSites().size(); index_2++) {
                    dayHtml += "<li>" + stay.getDaysList().get(index).getExcursion().getTouristicSites().get(index_2).getDescription() + "</li>";
                }
            else
                dayHtml += "<li style=\"color:rgb(0, 173, 95);\">Chill day</li>";
            dayHtml += "</ul></li>";
        }
        dayHtml = dayHtml + "</ul>";
        System.err.println("__" + stay.getClass().getName() + stay.toString() + "__ END");
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
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <!--===============================================================================================-->
    <link href="css/util.css" rel="stylesheet" type="text/css">

    <meta content="width=device-width, initial-scale=1" name="viewport">
    <!--===============================================================================================-->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!--===============================================================================================-->
    <link href="fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!--===============================================================================================-->
    <link href="fonts/iconic/css/material-design-iconic-font.min.css" rel="stylesheet" type="text/css">
    <!--===============================================================================================-->
    <link href="vendor/animate/animate.css" rel="stylesheet" type="text/css">
    <!--===============================================================================================-->
    <link href="vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" type="text/css">
    <!--===============================================================================================-->
    <link href="vendor/animsition/css/animsition.min.css" rel="stylesheet" type="text/css">
    <!--===============================================================================================-->
    <link href="vendor/select2/select2.min.css" rel="stylesheet" type="text/css">
    <!--===============================================================================================-->
    <link href="vendor/daterangepicker/daterangepicker.css" rel="stylesheet" type="text/css">
    <!--===============================================================================================-->
    <link href="vendor/noui/nouislider.min.css" rel="stylesheet" type="text/css">
    <!--===============================================================================================-->
    <link href="./css/main.css" rel="stylesheet" type="text/css">

    <link href="./css/result.css" rel="stylesheet" type="text/css">
    <!--============================================================
  ===============================================================================================-->
</head>
<body>
<div class="container-contact100">
    <div class="wrap-contact100">
        <div class="stay">
            <fieldset>
                <legend>Stay</legend>
                <div class="contact100-form-range-value">
                    <div class="elements">
                        <p><b>Hotel is at </b> : <%=hotel%>
                        </p>
                        <p><b>Price </b> : <%=price%> €
                        </p>
                        <p><b>Comfort </b> : <%=comfortEval%> stars
                        </p>
                    </div>
                </div>
                <div class="stays">
                    <p><b>Excursions</b></p>
                    <p><%=dayHtml%>
                    </p>

                </div>
            </fieldset>
        </div>
    </div>
</div>


</body>
</html>
