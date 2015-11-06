<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
        <jsp:attribute name="header">
        <link href="<c:url value="/resources/custom_css/signin-signup.css"/>" rel="stylesheet"/>
    </jsp:attribute>

    <jsp:body>
        <div class="col-md-6 col-md-offset-3" style="margin-top: 20%">

            <div class="alert alert-danger alert-danger-position ${error eq null? 'non-visible':''}"
                 role="alert">${error}<a href="<c:url value="/signup"/>"> Sign up</a>!</div>
            <div class="alert alert-success alert-danger-position ${error eq null? '':'non-visible'}"
                 role="alert">${approve}<a href="<c:url value="/"/>"> Sign in</a>!</div>
        </div>
    </jsp:body>
</t:template>


