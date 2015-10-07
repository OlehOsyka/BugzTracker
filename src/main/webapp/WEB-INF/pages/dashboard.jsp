<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value="/resources/css/bootstrap-table.css"/>" rel="stylesheet"/>
<link href="<c:url value="/resources/custom_css/dashboard.css"/>" rel="stylesheet"/>

<t:template>
    <t:header/>

    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="table-panel">
                <table class="bootstrap-table"
                       id="table"
                       data-search="true"
                       data-show-refresh="true"
                       data-show-toggle="true"
                       data-detail-view="true"
                       data-detail-formatter="detailFormatter"
                       data-id-field="id">
                </table>
            </div>
        </div>
    </div>

</t:template>

<script src="<c:url value="/resources/js/bootstrap-table.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap-table-multiple-sort.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap-table-multiple-search.js"/>"></script>
<script src="<c:url value="/resources/custom_js/dashboard.js"/>"></script>

