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
    <title>Home</title>
    <meta charset="UTF-8">
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
    <link href="css/util.css" rel="stylesheet" type="text/css">
    <link href="css/main.css" rel="stylesheet" type="text/css">
    <!--===============================================================================================-->
</head>
<body>
<div class="container-contact100">
    <div class="wrap-contact100">
        <form action="result.jsp" method="post" class="contact100-form validate-form">
                    <span class="contact100-form-title">
                        Choose your criterias
                    </span>

            <div class="wrap-contact100-form-range">
                <span class="label-input100">Journey duration</span>

                <div class="contact100-form-range-value">
                    <span id="nbjours">1</span> <span id="jourjours">days</span>
                    <input name="from-value" id="sliderDay" value="7" type="range">
                </div>

                <div class="contact100-form-range-bar">
                    <div class="barbar" id="filter-bar"></div>
                </div>
            </div>


            <div class="wrap-contact100-form-range">
                <span class="label-input100">Wanted comfort</span>

                <div class="contact100-form-range-value">
                    <span id="comfort">1</span> <span id="etoileetoiles">stars</span>
                    <input name="comfort-from-value" id="sliderComfort" value="3" type="range">
                </div>

                <div class="contact100-form-range-bar ">
                    <div class="barbar" id="comfort-bar"></div>
                </div>
            </div>


            <div class="wrap-input100 validate-input bg1" data-validate="Please enter some keywords">
                <span class="label-input100">Keywords</span>
                <input class="input100" name="keywords" placeholder="Enter space-separated keywords" type="text">
            </div>

            <div class="wrap-input100 validate-input bg1 rs1-wrap-input100" data-validate="Please enter your budget">
                <span class="label-input100">Budget</span>
                <input class="input100" name="budget" placeholder="Enter your budget" type="text">
            </div>

            <div class="wrap-input100 validate-input bg1 rs1-wrap-input100"
                 data-validate="Please enter the free days amount">
                <span class="label-input100">Free days</span>
                <input class="input100" name="free-day" placeholder="Free days amount" type="text">
            </div>
            <div class="wrap-contact100-form-range">
                <span class="label-input100">Average activities per day</span>

                <div class="contact100-form-range-value">
                    <span id="activities">1</span> <span id="activiteactivites">activities</span>
                    <input name="activities-from-value" id="sliderActivities" type="range" value="2">
                </div>

                <div class="contact100-form-range-bar">
                    <div class="barbar" id="activities-bar"></div>
                </div>
            </div>

            <div class="container-contact100-form-btn">
                <button class="contact100-form-btn">
                            <span>
                                Submit
                                <i aria-hidden="true" class="fa fa-long-arrow-right m-l-7"></i>
                            </span>
                </button>
            </div>
        </form>
    </div>
</div>


<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<script>
    $(".js-select2").each(function () {
        $(this).select2({
            minimumResultsForSearch: 20,
            dropdownParent: $(this).next('.dropDownSelect2')
        });


        $(".js-select2").each(function () {
            $(this).on('select2:close', function (e) {
                if ($(this).val() == "Please chooses") {
                    $('.js-show-service').slideUp();
                } else {
                    $('.js-show-service').slideUp();
                    $('.js-show-service').slideDown();
                }
            });
        });
    })
</script>
<!--===============================================================================================-->
<script src="vendor/daterangepicker/moment.min.js"></script>
<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="vendor/noui/nouislider.min.js"></script>
<script>
    var filterBar = document.getElementById('filter-bar');
    var comfortBar = document.getElementById('comfort-bar');
    var activitiesBar = document.getElementById('activities-bar');


    noUiSlider.create(filterBar, {
        start: 7,
        connect: [true, false],
        range: {
            'min': 1,
            'max': 14
        }
    });

    noUiSlider.create(comfortBar, {
        start: 3,
        connect: [true, false],
        range: {
            'min': 1,
            'max': 5
        }
    });

    noUiSlider.create(activitiesBar, {
        start: 2,
        connect: [true, false],
        range: {
            'min': 1,
            'max': 4
        }
    });

    var skipValues3 = [
        document.getElementById('nbjours')
    ];

    filterBar.noUiSlider.on('update', function (values, handle) {
        skipValues3[handle].innerHTML = Math.round(values[handle]);
        document.getElementById('sliderDay').setAttribute('value', values[handle])
        $('.contact100-form-range-value input[name="from-value"]').val($('#nbjours').html());
        if (Math.round(values[handle]) == 1)
            document.getElementById('jourjours').innerHTML = "day";
        else
            document.getElementById('jourjours').innerHTML = "days";


    });

    var skipValues = [
        document.getElementById('comfort')
    ];

    comfortBar.noUiSlider.on('update', function (values, handle) {
        document.getElementById('sliderComfort').setAttribute('value', values[handle])
        skipValues[handle].innerHTML = Math.round(values[handle]);
        $('.contact100-form-range-value input[name="from-value"]').val($('#comfort').html());
        if (Math.round(values[handle]) == 1)
            document.getElementById('etoileetoiles').innerHTML = "star";
        else
            document.getElementById('etoileetoiles').innerHTML = "stars";

    });

    var skipValues2 = [
        document.getElementById('activities')
    ];

    activitiesBar.noUiSlider.on('update', function (values, handle) {
        skipValues2[handle].innerHTML = Math.round(values[handle]);
        document.getElementById('sliderActivities').setAttribute('value', values[handle])
        $('.contact100-form-range-value input[name="from-value"]').val($('#activities').html());
        if (Math.round(values[handle]) == 1)
            document.getElementById('activiteactivites').innerHTML = "activity";
        else
            document.getElementById('activiteactivites').innerHTML = "activities";

    });
</script>
<!--===============================================================================================-->
<script src="js/main.js"></script>
</body>
</html>
