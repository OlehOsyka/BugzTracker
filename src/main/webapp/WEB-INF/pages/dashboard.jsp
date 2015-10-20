<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <link href="<c:url value="/resources/custom_css/dashboard.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/custom_css/general.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-editable.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/dataTables.foundation.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/dataTables.bootstrap.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/dataTables.jqueryui.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/jquery.dataTables.min.css"/>" rel="stylesheet"/>

    <t:header/>

    <div class="row white-back">
        <div class="col-md-10 col-md-offset-1">
            <div class="table-panel">
                <div class="btn-group btn-distance" role="group">
                    <button type="button" class="btn btn-default" id="btn-proj">All projects</button>
                    <button type="button" class="btn btn-default" id="btn-my-proj">My projects</button>
                </div>

                <button type="button" class="btn btn-default" id="btn-edit">Edit</button>

                <table id="example" class="display" cellspacing="0" width="100%"></table>
            </div>
        </div>
    </div>

    <div class="modal fade" id="modalEdit" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" id="btn-close" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title></title>" id="modal-name"></h4>
                </div>
                <div class="modal-body">
                    <div class="alert alert-danger non-visible alert-danger-position" id="invalid-project-edit" role="alert"></div>
                    <fieldset>
                        <div class="form-group" id="form-group-name">
                            <label class="control-label" for="name">Name:</label>
                            <input class="form-control" placeholder="Name" id="name" type="text">
                        </div>
                        <div class="form-group" id="form-group-users">
                            <label class="control-label" for="user">Users:</label>
                            <input class="form-control" placeholder="Name" id="user" type="text">
                            <div id="users-list" class="padding-top"></div>
                        </div>
                        <div class="form-group" id="form-group-desc">
                            <label class="control-label" for="desc">Description:</label>
                            <textarea class="form-control" placeholder="Description" rows="4" id="desc"></textarea>
                        </div>

                    </fieldset>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="btn-save">Save</button>
                    <button type="button" class="btn btn-default" id="btn-cancel" data-dismiss="modal">Cancel</button>
                </div>
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
<script src="<c:url value="/resources/js/bootstrap3-typeahead.min.js"/>"></script>
<script src="<c:url value="/resources/custom_js/dashboard.js"/>"></script>
<script src="<c:url value="/resources/custom_js/validation.js"/>"></script>


