<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="header">
        <link href="<c:url value="/resources/css/bootstrap-editable.css"/>" rel="stylesheet"/>
        <%--<link href="<c:url value="/resources/custom_css/general.css"/>" rel="stylesheet"/>--%>
        <%--<link href="<c:url value="/resources/custom_css/issue.css"/>" rel="stylesheet"/>--%>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <script src="<c:url value="/resources/js/bootstrap-editable.min.js"/>"></script>
        <script src="<c:url value="/resources/js/js.cookie.js"/>"></script>
        <script src="<c:url value="/resources/js/Hyphenator.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/patterns/en-us.js"/>" type="text/javascript"></script>
        <%--<script src="<c:url value="/resources/custom_js/issue.js"/>"></script>--%>
    </jsp:attribute>

    <jsp:body>
        <t:navbar-new/>
        <div style="padding: 2%">

            <div class="col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <i class="fa fa-tag fa-fw"></i>Issue1
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <th>Version</th>
                                    <td>2.0</td>
                                </tr>
                                <tr>
                                    <th>Date of creation</th>
                                    <td>28.10.2015 12.00.34</td>
                                </tr>
                                <tr>
                                    <th>Date of last update</th>
                                    <td>-</td>
                                </tr>
                                <tr>
                                    <th>Category</th>
                                    <td>BUG</td>
                                </tr>
                                <tr>
                                    <th>Status</th>
                                    <td>IN PROGRESS</td>
                                </tr>
                                <tr>
                                    <th>Priority</th>
                                    <td>CRITICAL</td>
                                </tr>
                                <tr>
                                    <th>Creator</th>
                                    <td>Oleh Osyka</td>
                                </tr>
                                <tr>
                                    <th>Assignee</th>
                                    <td>Yuliia Vovk</td>
                                </tr>
                                <tr>
                                    <th>Description</th>
                                    <td>Lorem ipsum dolor sit amet,
                                        consectetur adipiscing elit.
                                        Vestibulum tincidunt est vitae ultrices accumsan.
                                        Aliquam ornare lacus adipiscing, posuere lectus et,
                                        fringilla augue.
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="chat-panel panel panel-primary">
                    <div class="panel-heading">
                        <i class="fa fa-comments fa-fw"></i>
                        Comments
                        <div class="btn-group pull-right">
                            <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <i class="fa fa-chevron-down"></i>
                            </button>
                            <ul class="dropdown-menu slidedown">
                                <li>
                                    <a href="#">
                                        <i class="fa fa-refresh fa-fw"></i> Refresh
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="panel-body">
                        <ul class="chat">
                            <li class="clearfix">
                                <div class="header">
                                    <strong class="pull-left primary-font">Jack Sparrow</strong>
                                    <small class="pull-right text-muted">
                                        <i class="fa fa-clock-o fa-fw"></i> 12.12.2016 12:00:23
                                    </small>
                                </div>
                                <div style="padding-top: 6%">
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
                                    dolor, quis ullamcorper ligula soda.
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="chat-body clearfix">
                                    <div class="header">
                                        <small class="pull-right text-muted">
                                            <i class="fa fa-clock-o fa-fw"></i> 12.12.2016 12:00:23
                                        </small>
                                        <strong class="pull-left primary-font">Bhaumik Patel</strong>
                                    </div>
                                    <div style="padding-top: 6%">
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
                                        dolor, quis ullamcorper ligula sodales.
                                    </div>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="chat-body clearfix">
                                    <div class="header">
                                        <strong class="pull-left primary-font">Jack Sparrow</strong>
                                        <small class="pull-right text-muted">
                                            <i class="fa fa-clock-o fa-fw"></i> 12.12.2016 12:00:23
                                        </small>
                                    </div>
                                    <div style="padding-top: 6%">
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
                                        dolor, quis ullamcorper ligula sodales.
                                    </div>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="chat-body clearfix">
                                    <div class="header">
                                        <small class="pull-right text-muted">
                                            <i class="fa fa-clock-o fa-fw"></i> 12.12.2016 12:00:23
                                        </small>
                                        <strong class="pull-left primary-font">Bhaumik Patel</strong>
                                    </div>
                                    <div style="padding-top: 6%">
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
                                        dolor, quis ullamcorper ligula sodales.
                                    </div>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="chat-body clearfix">
                                    <div class="header">
                                        <small class="pull-right text-muted">
                                            <i class="fa fa-clock-o fa-fw"></i> 12.12.2016 12:00:23
                                        </small>
                                        <strong class="pull-left primary-font">Bhaumik Patel</strong>
                                    </div>
                                    <div style="padding-top: 6%">
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
                                        dolor, quis ullamcorper ligula sodales.
                                    </div>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="chat-body clearfix">
                                    <div class="header">
                                        <small class="pull-right text-muted">
                                            <i class="fa fa-clock-o fa-fw"></i> 12.12.2016 12:00:23
                                        </small>
                                        <strong class="pull-left primary-font">Bhaumik Patel</strong>
                                    </div>
                                    <div style="padding-top: 6%">
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
                                        dolor, quis ullamcorper ligula sodales.
                                    </div>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="chat-body clearfix">
                                    <div class="header">
                                        <small class="pull-right text-muted">
                                            <i class="fa fa-clock-o fa-fw"></i> 12.12.2016 12:00:23
                                        </small>
                                        <strong class="pull-left primary-font">Bhaumik Patel</strong>
                                    </div>
                                    <div style="padding-top: 6%">
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
                                        dolor, quis ullamcorper ligula sodales.
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="panel-footer">
                        <div class="input-group">
                            <input id="btn-input" type="text" class="form-control input-sm" placeholder="Type your message here...">
                                <span class="input-group-btn">
                                    <button class="btn btn-success btn-sm" id="btn-chat">
                                        Send
                                    </button>
                                </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="issue-div col-md-4" id="form-group-upload">
                        <label class="control-label">New attachments:</label>
                        <div id="fileTable" class="btn-group">
                        </div>
                        <br/>
                        <button id="addFile" type="button" class="btn btn-primary btn-sm">Add file</button>
                        <button id="uploadFile" type="button" class="btn btn-primary btn-sm">Upload</button>
                    </div>
                    <div class="issue-div col-md-2" id="form-group-uploaded">
                        <label class="control-label">Attached files:</label>
                        <div id="files" class="btn-group"></div>
                    </div>
                </div>
            </div>
        </div>

        <%--Delete attachment--%>
        <div class="modal fade" id="modalDeleteAtt" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" id="btn-close"
                                aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title></title>" id="modal-name"></h4>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to delete attachment?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" id="btn-del-att">Delete</button>
                        <button type="button" class="btn btn-default" id="btn-cancel-att"
                                data-dismiss="modal">Cancel
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
                        <button type="button" class="close" data-dismiss="modal" id="btn-close-com"
                                aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title></title>" id="modal-name-com"></h4>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to delete comment?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" id="btn-del-com">Delete</button>
                        <button type="button" class="btn btn-default" id="btn-cancel-com"
                                data-dismiss="modal">Cancel
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>


