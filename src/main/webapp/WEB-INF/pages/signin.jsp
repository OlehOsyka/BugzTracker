<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value="/resources/custom_css/login.css"/>" rel="stylesheet"/>

<t:template>

    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please, sign in</h3>
                </div>
                <div class="panel-body">
                    <form role="form">
                        <fieldset>
                            <p id="invalid_login" class="error-text"></p>
                            <div class="form-group">
                                <input class="form-control" placeholder="E-mail" id="email" type="email" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Password" id="password" type="password">
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="Remember Me" id="remember">Remember Me
                                </label>
                            </div>
                            <button type="submit" class="btn btn-lg btn-success btn-block" id="sign_in_btn">Sign in
                            </button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <input type="hidden" value="${pageContext.request.contextPath}" id="contex-path">

</t:template>

<script src="<c:url value="/resources/custom_js/login.js"/>"></script>

