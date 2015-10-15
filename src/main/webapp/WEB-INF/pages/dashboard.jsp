<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <%--<link href="<c:url value="/resources/css/bootstrap-table.css"/>" rel="stylesheet"/>--%>
    <link href="<c:url value="/resources/custom_css/dashboard.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-editable.css"/>" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/dt-1.10.9,b-1.0.3,b-colvis-1.0.3,cr-1.2.0,fc-3.1.0,fh-3.0.0,r-1.0.7,sc-1.3.0,se-1.0.1/datatables.min.css"/>

    <t:header/>

    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="table-panel">
                <%--<table class="bootstrap-table"--%>
                       <%--id="table"--%>
                       <%--data-search="true"--%>
                       <%--data-show-refresh="true"--%>
                       <%--data-show-toggle="true"--%>
                       <%--data-detail-view="true"--%>
                       <%--data-detail-formatter="detailFormatter"--%>
                       <%--data-id-field="id">--%>
                <%--</table>--%>
                    <table id="example" class="display" cellspacing="0" width="100%">
                    </table>

            </div>
        </div>
    </div>

</t:template>

<script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/dt-1.10.9,b-1.0.3,b-colvis-1.0.3,cr-1.2.0,fc-3.1.0,fh-3.0.0,r-1.0.7,sc-1.3.0,se-1.0.1/datatables.min.js"></script>

<%--<script src="<c:url value="/resources/js/bootstrap-table.js"/>"></script>--%>
<%--<script src="<c:url value="/resources/js/bootstrap-table-multiple-sort.js"/>"></script>--%>
<%--<script src="<c:url value="/resources/js/bootstrap-table-multiple-search.js"/>"></script>--%>
<script src="<c:url value="/resources/js/bootstrap-editable.js"/>"></script>
<%--<script src="<c:url value="/resources/js/bootstrap-table-editable.js"/>"></script>--%>
<script src="<c:url value="/resources/custom_js/dashboard.js"/>"></script>

