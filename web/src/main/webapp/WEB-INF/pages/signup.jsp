<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:template>
    <jsp:attribute name="header">
        <link href="<c:url value="/resources/custom_css/signin-signup.css"/>" rel="stylesheet"/>
    </jsp:attribute>

    <jsp:attribute name="footer">
        <script src="<c:url value="/resources/custom_js/signup.js"/>"></script>
        <script src="<c:url value="/resources/custom_js/validation.js"/>"></script>
    </jsp:attribute>

    <jsp:body>
        <body style="background: url('/bugztracker/resources/images/macbook-6.jpg') center center no-repeat fixed; opacity: 0.9; background-size: cover;">
        <div class="login-panel">
            <div class="col-md-4">
                <div class="alert alert-danger non-visible alert-danger-position" id="invalid_signup"
                     role="alert"></div>
                <div class="alert alert-success non-visible alert-danger-position" id="valid_signup"
                     role="alert"></div>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Sign up</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form">
                            <fieldset>
                                <div class="form-group" id="form_group_full_name">
                                    <label class="control-label" for="full_name">Full name:</label>
                                    <input class="form-control" placeholder="Full name" id="full_name" type="text"
                                           autofocus>
                                </div>
                                <div class="form-group" id="form_group_email">
                                    <label class="control-label" for="email">E-mail:</label>
                                    <input class="form-control" placeholder="E-mail" id="email" type="text">
                                </div>
                                <div class="form-group" id="form_group_password">
                                    <label class="control-label" for="password">Password:</label>
                                    <input class="form-control" placeholder="Password" id="password" type="password">
                                </div>
                                <button type="submit" class="btn btn-lg btn-primary btn-block" id="sign_up_btn">Sign up
                                </button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-md-8" style="color: white">
                <h3>Join Bugztracker and simplify development as never before</h3>
            </div>
        </div>
    </jsp:body>

</t:template>