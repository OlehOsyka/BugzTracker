<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="header">
        <link href="<c:url value="/resources/custom_css/general.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/resources/custom_css/issue.css"/>" rel="stylesheet"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <script src="<c:url value="/resources/js/js.cookie.js"/>"></script>
        <script src="<c:url value="/resources/custom_js/issue.js"/>"></script>
    </jsp:attribute>

    <jsp:body>
        <t:navbar/>

        <div class="col-md-12">
            <div class="table-panel">
                <h4 class="modal-title  btn-distance-project" id="project-name"></h4>

                <div class="modal-body">
                    <fieldset>
                        <div class="form-group col-md-12">
                            <div class="alert alert-danger non-visible text-center" id="invalid-issue-edit"
                                 role="alert"></div>
                        </div>
                        <div class="form-group col-md-2" id="form-group-name">
                            <label class="control-label" for="name">Name:</label>
                            <span id="name"/>
                        </div>
                        <div class="form-group col-md-2" id="form-group-namedate">
                            <label class="control-label" for="date">Date:</label>
                            <span id="date"/>
                        </div>
                        <div class="form-group col-md-2" id="form-group-last-update">
                            <label class="control-label" for="lastUpdate">Last update:</label>
                            <span id="lastUpdate"/>
                        </div>
                        <div class="form-group col-md-2" id="form-group-status">
                            <label class="control-label" for="status">Status:</label>
                            <span id="status"/>
                        </div>
                        <div class="form-group col-md-2" id="form-group-category">
                            <label class="control-label" for="category">Category:</label>
                            <span id="category"/>
                        </div>
                        <div class="form-group col-md-2" id="form-group-priority">
                            <label class="control-label" for="priority">Priority:</label>
                            <span id="priority"/>
                        </div>
                        <div class="form-group col-md-8" id="form-group-assignee">
                            <label class="control-label" for="assignee">Assignee:</label>
                            <span id="assignee"/>
                        </div>
                        <div class="form-group col-md-4" id="form-group-version">
                            <label class="control-label" for="version">Version:</label>
                            <span id="version"/>
                        </div>
                        <div class="form-group col-md-12" id="form-group-desc">
                            <label class="control-label" for="desc">Description:</label>
                            <span id="desc"/>
                        </div>
                        <div class="form-group col-md-3" id="form-group-upload">
                            <label class="control-label">Attachments:</label>
                            <div id="fileTable" class="btn-group">
                                <div id="upload-0" class="input-group">
                                        <span class="btn btn-sm btn-file">
                                            Browse&hellip; <input name="files[0]" type="file">
                                        </span>
                                    <span class="filename"></span>
                                    <span data-id="0" class="remove-extra-file">&times;</span>
                                </div>
                            </div>
                            <br/>
                            <button id="addFile" type="button" class="btn btn-sm">Add file</button>
                            <button id="uploadFile" type="button" class="btn btn-sm">Upload</button>
                        </div>
                    </fieldset>

                </div>
            </div>
        </div>
    </jsp:body>
</t:template>

