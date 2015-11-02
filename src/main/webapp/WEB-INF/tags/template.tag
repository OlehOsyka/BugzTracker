<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet"/>
    <link href="//fonts.googleapis.com/css?family=Quicksand" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/resources/custom_css/general.css"/>" rel="stylesheet"/>

    <script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

    <%--for js + css in head tag--%>
    <jsp:invoke fragment="header"/>

    <title>Bugz Tracker</title>
</head>
<body>
<div class="container">
    <jsp:doBody/>
</div>
<div class="footer">
    <div class="footer-text">
        <div class="panel-footer">
            Bugz Tracker, 2015
        </div>
    </div>
</div>
</body>
    <%--for js + css after body--%>
    <jsp:invoke fragment="footer"/>
</html>
