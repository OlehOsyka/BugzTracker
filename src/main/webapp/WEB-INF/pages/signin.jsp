<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value="/resources/custom_css/signin-signup.css"/>" rel="stylesheet"/>

<t:template>

    <input type="hidden" value="${pageContext.request.contextPath}" id="context_path">

    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="alert alert-danger non-visible alert-danger-position" id="invalid_login" role="alert"></div>
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please, Sign in or <a href="<c:url value="/signup"/>">Sign up</a></h3>
                </div>
                <div class="panel-body">
                    <form role="form">
                        <fieldset>
                            <div class="form-group" id="form_group_email">
                                <label class="control-label" for="email">E-mail:</label>
                                <input class="form-control" placeholder="E-mail" id="email" type="text" autofocus>
                            </div>
                            <div class="form-group" id="form_group_password">
                                <label class="control-label" for="password">Password:</label>
                                <input class="form-control" placeholder="Password" id="password" type="password">
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="Remember Me" id="remember">Remember Me
                                </label>
                            </div>
                            <button type="submit" class="btn btn-lg btn-default btn-block" id="sign_in_btn">Sign in
                            </button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>

</t:template>

<script src="<c:url value="/resources/custom_js/signin.js"/>"></script>
<script src="<c:url value="/resources/custom_js/validation.js"/>"></script>

