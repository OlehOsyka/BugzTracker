<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value="/resources/custom_css/login.css"/>" rel="stylesheet"/>

<t:template>

    <input type="hidden" value="${pageContext.request.contextPath}" id="context_path">

    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="alert alert-danger non-visible alert-danger-position" id="invalid_signup" role="alert"></div>
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please, Sign up</h3>
                </div>
                <div class="panel-body">
                    <form role="form">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="Full name" id="full_name" type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="E-mail" id="email" type="text">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Password" id="password" type="password">
                            </div>
                            <button type="submit" class="btn btn-lg btn-success btn-block" id="sign_up_btn">Sign up
                            </button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>

</t:template>

<script src="<c:url value="/resources/custom_js/signup.js"/>"></script>
<script src="<c:url value="/resources/custom_js/validation.js"/>"></script>
