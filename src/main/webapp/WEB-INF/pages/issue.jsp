<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="header">
        <link href="<c:url value="/resources/css/bootstrap-editable.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/resources/custom_css/general.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/resources/custom_css/issue.css"/>" rel="stylesheet"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <script src="<c:url value="/resources/js/bootstrap-editable.min.js"/>"></script>
        <script src="<c:url value="/resources/js/js.cookie.js"/>"></script>
        <script src="<c:url value="/resources/js/Hyphenator.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/patterns/en-us.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/custom_js/issue.js"/>"></script>
    </jsp:attribute>

    <jsp:body>
        <t:navbar/>

        <div class="col-md-12 padding-bottom">
            <div class="table-panel">
                <h4 class="modal-title  btn-distance-project" id="project-name"></h4>
                <div class="alert alert-danger non-visible text-center" id="error"></div>
                <div class="modal-body">
                    <fieldset>
                        <div class="row row-flex">
                            <div class="col-md-3 issue-div" id="form-group-name">
                                <label class="control-label" for="name">Name:</label>
                                <span id="name" class="hyphenate"/>
                            </div>
                            <div class="col-md-3 issue-div" id="form-group-namedate">
                                <label class="control-label" for="date">Date:</label>
                                <span id="date" class="hyphenate"/>
                            </div>
                            <div class="col-md-3 issue-div" id="form-group-last-update">
                                <label class="control-label" for="lastUpdate">Last update:</label>
                                <span id="lastUpdate" class="hyphenate"/>
                            </div>
                            <div class="col-md-3 issue-div" id="form-group-version">
                                <label class="control-label" for="version">Version:</label>
                                <span id="version" class="hyphenate"/>
                            </div>
                        </div>
                        <div class="row row-flex">
                            <div class="issue-div col-md-4" id="form-group-category">
                                <label class="control-label" for="category">Category:</label>
                                <span id="category" class="hyphenate"/>
                            </div>
                            <div class="issue-div col-md-4" id="form-group-status">
                                <label class="control-label" for="status">Status:</label>
                                <span id="status" class="hyphenate"/>
                            </div>
                            <div class="issue-div col-md-4" id="form-group-priority">
                                <label class="control-label" for="priority">Priority:</label>
                                <span id="priority" class="hyphenate"/>
                            </div>
                        </div>
                        <div class="row row-flex">
                            <div class="issue-div col-md-6" id="form-group-assignee">
                                <label class="control-label" for="assignee">Assignee:</label>
                                <span id="assignee" class="hyphenate"/>
                            </div>
                            <div class="issue-div col-md-6" id="form-group-creator">
                                <label class="control-label" for="creator">Creator:</label>
                                <span id="creator" class="hyphenate"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="issue-div" id="form-group-desc">
                                <label class="control-label" for="desc">Description:</label>
                                <span id="desc" class="hyphenate"/>
                            </div>
                        </div>
                        <div class="row row-flex">
                            <div class="issue-div col-md-6" id="form-group-upload">
                                <label class="control-label">New attachments:</label>

                                <div id="fileTable" class="btn-group">
                                </div>
                                <br/>
                                <button id="addFile" type="button" class="btn btn-primary btn-sm">Add file</button>
                                <button id="uploadFile" type="button" class="btn btn-primary btn-sm">Upload</button>
                            </div>
                            <div class="issue-div col-md-6" id="form-group-uploaded">
                                <label class="control-label">Attached files:</label>

                                <div id="files" class="btn-group"></div>
                            </div>
                        </div>
                        <div class="row">
                            <div id="form-group-comments" class="comment-div">
                                <label class="control-label">Comments:</label>

                                <div id="comments">
                                </div>
                            </div>
                        </div>
                    </fieldset>

                        <%--Delete attachment--%>
                    <div class="modal fade" id="modalDeleteAtt" tabindex="-1" role="dialog">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" id="btn-close" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title></title>" id="modal-name"></h4>
                                </div>
                                <div class="modal-body">
                                    Are you sure you want to delete attachment?
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" id="btn-del-att">Delete</button>
                                    <button type="button" class="btn btn-primary" id="btn-cancel-att" data-dismiss="modal">Cancel
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                        <%--Delete comment--%>
                    <div class="modal fade" id="modalDeleteCom" tabindex="-1" role="dialog">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" id="btn-close-com" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title></title>" id="modal-name-com"></h4>
                                </div>
                                <div class="modal-body">
                                    Are you sure you want to delete comment?
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" id="btn-del-com">Delete</button>
                                    <button type="button" class="btn btn-primary" id="btn-cancel-com" data-dismiss="modal">Cancel
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>

