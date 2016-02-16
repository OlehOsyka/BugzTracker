<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:body>
        <t:navbar-new/>

        <div style="padding: 2%">
            <div class="row">
                <div class="col-md-3">
                    <ul class="nav nav-pills nav-stacked">
                        <h5>Created:</h5> <br/>
                        <li><a href="#file1.java" data-toggle="tab">File1.java</a></li>
                        <li><a href="#file2.java" data-toggle="tab">File2.java</a></li>
                        <h5>Modified:</h5> <br/>
                        <li><a href="#file3.java" data-toggle="tab">File3.java</a></li>
                        <li><a href="#file4.java" data-toggle="tab">File4.java</a></li>
                        <h5>Removed:</h5> <br/>
                        <li><a href="#file5.java" data-toggle="tab">File5.java</a></li>
                    </ul>
                </div>
                <div class="col-md-9">
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="file1.java">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <i class="fa fa-pencil-square-o fa-fw"></i>File1.java
                                </div>
                                <div class="panel-body">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tincidunt est
                                        vitae
                                        ultrices accumsan. Aliquam ornare lacus adipiscing, posuere lectus et, fringilla
                                        augue.</p>
                                </div>
                                    <%--<div class="panel-footer">--%>
                                    <%--Team Lead: Oleh Osyka--%>
                                    <%--</div>--%>
                            </div>
                        </div>
                        <div class="tab-pane fade in active" id="file2.java">

                        </div>
                    </div>
                </div>
            </div>
            <br/>
            <div class="chat-panel panel panel-primary">
                <div class="panel-heading">
                    <i class="fa fa-comments fa-fw"></i>
                    Comments
                    <div class="btn-group pull-right">
                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"
                                aria-expanded="false">
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
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <ul class="chat">
                        <li class="clearfix">
                            <div class="header">
                                <strong class="pull-left primary-font">Jack Sparrow</strong>
                                <small class="pull-right text-muted">
                                    <i class="fa fa-clock-o fa-fw"></i> 12.12.2016 12:00:23
                                </small>
                            </div>
                            <div style="padding-top: 3%">
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
                                <div style="padding-top: 3%">
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
                                <div style="padding-top: 3%">
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
                                <div style="padding-top: 3%">
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
                                <div style="padding-top: 3%">
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
                                <div style="padding-top: 3%">
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
                                <div style="padding-top: 3%">
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
                                    dolor, quis ullamcorper ligula sodales.
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <!-- /.panel-body -->
                <div class="panel-footer">
                    <div class="input-group">
                        <input id="btn-input" type="text" class="form-control input-sm"
                               placeholder="Type your message here...">
                                <span class="input-group-btn">
                                    <button class="btn btn-success btn-sm" id="btn-chat">
                                        Send
                                    </button>
                                </span>
                    </div>
                </div>
                <!-- /.panel-footer -->
            </div>
        </div>
    </jsp:body>
</t:template>
