<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <link href="<c:url value="/resources/custom_css/dashboard.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-editable.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/dataTables.foundation.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/dataTables.bootstrap.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/dataTables.jqueryui.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/jquery.dataTables.min.css"/>" rel="stylesheet"/>

    <t:header/>

    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="table-panel">
                <div class="btn-group btn-distance" role="group">
                    <button type="button" class="btn btn-default" id="btn-issues">All issues</button>
                    <button type="button" class="btn btn-default" id="btn-my-issues">My issues</button>
                </div>

                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-default" id="btn-edit">Edit</button>
                    <button type="button" class="btn btn-default show-none" id="btn-delete">Delete</button>
                </div>

                <table id="issuesTable" class="display" cellspacing="0" width="100%"></table>
            </div>
        </div>
    </div>

</t:template>

<script src="<c:url value="/resources/js/js.cookie.js"/>"></script>
<script src="<c:url value="/resources/js/dataTables.foundation.min.js"/>"></script>
<script src="<c:url value="/resources/js/dataTables.jqueryui.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.dataTables.min.js"/>"></script>
<script src="<c:url value="/resources/js/dataTables.bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap-editable.js"/>"></script>
<script src="<c:url value="/resources/custom_js/project.js"/>"></script>

