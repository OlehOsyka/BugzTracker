<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="header">
        <link href="<c:url value="/resources/custom_css/general.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/resources/custom_css/dashboard.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/resources/css/bootstrap-editable.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/resources/css/dataTables.foundation.min.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/resources/css/dataTables.bootstrap.min.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/resources/css/dataTables.jqueryui.min.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/resources/css/jquery.dataTables.min.css"/>" rel="stylesheet"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <script src="<c:url value="/resources/js/js.cookie.js"/>"></script>
        <script src="<c:url value="/resources/js/dataTables.foundation.min.js"/>"></script>
        <script src="<c:url value="/resources/js/dataTables.jqueryui.min.js"/>"></script>
        <script src="<c:url value="/resources/js/jquery.dataTables.min.js"/>"></script>
        <script src="<c:url value="/resources/js/dataTables.bootstrap.min.js"/>"></script>
        <script src="<c:url value="/resources/js/bootstrap-editable.js"/>"></script>
        <script src="<c:url value="/resources/js/bootstrap3-typeahead.min.js"/>"></script>
        <script src="<c:url value="/resources/custom_js/project.js"/>"></script>
        <script src="<c:url value="/resources/custom_js/validation.js"/>"></script>
    </jsp:attribute>

    <jsp:body>
        <t:navbar/>

        <div class="row">
            <div class="col-md-12">
                <div class="table-panel">
                        <%--<div class="btn-group btn-pro" role="group">--%>
                        <%--<button type="button" class="btn btn-primary" id="btn-issues">All issues</button>--%>
                        <%--<button type="button" class="btn btn-primary" id="btn-my-issues">My issues</button>--%>
                        <%--</div>--%>
                        <%--<h4 class="modal-title  btn-distance-project" id="project-name"></h4>--%>

                    <table id="issuesTable" class="table table-striped table-bordered"
                           data-order="[[ 1, &quot;asc&quot; ]]" cellspacing="0"
                           width="100%"></table>
                </div>
            </div>
        </div>
        <%--Modal Edit--%>
        <div class="modal fade" id="modalEdit" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" id="btn-close" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title></title>" id="modal-name"></h4>
                    </div>
                    <div class="modal-body">
                        <fieldset>
                            <div class="form-group col-md-12">
                                <div class="alert alert-danger non-visible text-center" id="invalid-issue-edit"
                                     role="alert"></div>
                            </div>
                            <div class="form-group col-md-12" id="form-group-name">
                                <label class="control-label" for="name">Name:</label>
                                <input class="form-control" placeholder="Name" id="name" type="text">
                            </div>
                            <div class="form-group col-md-6 show-none" id="form-group-status">
                                <label class="control-label" for="status">Status:</label>
                                <select id="status" class="form-control">
                                    <option>OPENED</option>
                                    <option>INPROGRESS</option>
                                    <option>RESOLVED</option>
                                    <option>CLOSED</option>
                                </select>
                            </div>
                            <div class="form-group col-md-6" id="form-group-category">
                                <label class="control-label" for="category">Category:</label>
                                <select id="category" class="form-control">
                                    <option>ISSUE</option>
                                    <option>BUG</option>
                                    <option>IMPROVEMENT</option>
                                </select>
                            </div>
                            <div class="form-group col-md-6" id="form-group-priority">
                                <label class="control-label" for="priority">Priority:</label>
                                <select id="priority" class="form-control">
                                    <option>MINOR</option>
                                    <option>TRIVIAL</option>
                                    <option>MAJOR</option>
                                    <option>BLOCKER</option>
                                    <option>CRITICAL</option>
                                </select>
                            </div>
                            <div class="form-group col-md-8" id="form-group-assignee">
                                <label class="control-label" for="assignee">Assignee:</label>
                                <input class="form-control" placeholder="Assignee" type="text" id="assignee">

                                <div id="assignee-field" class="padding-top"></div>
                            </div>
                            <div class="form-group col-md-4" id="form-group-version">
                                <label class="control-label" for="version">Version:</label>
                                <input class="form-control" placeholder="Version" type="number" id="version">
                            </div>
                            <div class="form-group col-md-12" id="form-group-desc">
                                <label class="control-label" for="desc">Description:</label>
                                <textarea class="form-control" placeholder="Description" rows="4" id="desc"></textarea>
                            </div>
                        </fieldset>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="btn-save">Save</button>
                        <button type="button" class="btn btn-default" id="btn-cancel" data-dismiss="modal">Cancel
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <%--Modal Delete--%>
        <div class="modal fade" id="modalDelete" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" id="btn-close-del"
                                aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Delete</h4>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to delete selected issue?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" id="btn-delete-yes">Delete</button>
                        <button type="button" class="btn btn-default" id="btn-cancel-sel" data-dismiss="modal">Cancel
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>

