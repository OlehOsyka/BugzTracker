<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>

     <jsp:attribute name="header">
        <link href="<c:url value="/resources/css/morris.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/resources/js/morris.min.js"/>" rel="stylesheet"/>
    </jsp:attribute>

    <jsp:body>

        <t:navbar-new/>

        <div style="padding: 2%">

            <ul class="nav nav-pills" id="project-names">
                <%--<li class="active"><a href="#project1-pills" data-toggle="tab"></a></li>--%>
                <%--<li><a href="#project2-pills" data-toggle="tab">Project2</a></li>--%>

                <a data-toggle="modal" data-target="#create-issue-modal" class="btn btn-success pull-right">Create issue</a>
            </ul>

            <div class="tab-content">

                <div class="tab-pane fade in active" id="project1">

                    <br/>

                    <div class="row">
                        <div class="col-lg-3 col-md-6">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-xs-3">
                                            <i class="fa fa-envelope fa-3x"></i>
                                        </div>
                                        <div class="col-xs-9 text-right">
                                            <div class="huge">36</div>
                                            <div>OPENED</div>
                                        </div>
                                    </div>
                                </div>
                                <a href="#">
                                    <div class="panel-footer">
                                        <span class="pull-left">View Details</span>
                                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                        <div class="clearfix"></div>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-xs-3">
                                            <i class="fa fa-tasks fa-3x"></i>
                                        </div>
                                        <div class="col-xs-9 text-right">
                                            <div class="huge">49</div>
                                            <div>IN PROGRESS</div>
                                        </div>
                                    </div>
                                </div>
                                <a href="#">
                                    <div class="panel-footer">
                                        <span class="pull-left">View Details</span>
                                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                        <div class="clearfix"></div>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-xs-3">
                                            <i class="fa fa-wrench fa-3x"></i>
                                        </div>
                                        <div class="col-xs-9 text-right">
                                            <div class="huge">124</div>
                                            <div>RESOLVED</div>
                                        </div>
                                    </div>
                                </div>
                                <a href="#">
                                    <div class="panel-footer">
                                        <span class="pull-left">View Details</span>
                                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                        <div class="clearfix"></div>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-xs-3">
                                            <i class="fa fa-check-square-o fa-3x"></i>
                                        </div>
                                        <div class="col-xs-9 text-right">
                                            <div class="huge">149</div>
                                            <div>CLOSED</div>
                                        </div>
                                    </div>
                                </div>
                                <a href="#">
                                    <div class="panel-footer">
                                        <span class="pull-left">View Details</span>
                                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                        <div class="clearfix"></div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="row">

                        <div class="col-lg-8">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <i class="fa fa-bar-chart-o fa-fw"></i> Burndown Chart
                                    <div class="pull-right">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default btn-xs dropdown-toggle"
                                                    data-toggle="dropdown">
                                                Actions
                                                <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu pull-right" role="menu">
                                                <li><a href="#">Action</a>
                                                </li>
                                                <li><a href="#">Another action</a>
                                                </li>
                                                <li><a href="#">Something else here</a>
                                                </li>
                                                <li class="divider"></li>
                                                <li><a href="#">Separated link</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.panel-heading -->
                                <div class="panel-body">
                                    <div id="morris-area-chart"
                                         style="position: relative; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
                                        <svg height="357" version="1.1" width="603" xmlns="http://www.w3.org/2000/svg"
                                             xmlns:xlink="http://www.w3.org/1999/xlink"
                                             style="overflow: hidden; position: relative;">
                                            <desc style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Created with
                                                Raphaël 2.1.2
                                            </desc>
                                            <defs style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></defs>
                                            <text x="49.203125" y="323" text-anchor="end" font-family="sans-serif"
                                                  font-size="12px" stroke="none" fill="#888888"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: end; font-family: sans-serif; font-size: 12px; font-weight: normal;"
                                                  font-weight="normal">
                                                <tspan style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);" dy="4.25">
                                                    0
                                                </tspan>
                                            </text>
                                            <path fill="none" stroke="#aaaaaa" d="M61.703125,323H578" stroke-width="0.5"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                                            <text x="49.203125" y="248.5" text-anchor="end" font-family="sans-serif"
                                                  font-size="12px" stroke="none" fill="#888888"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: end; font-family: sans-serif; font-size: 12px; font-weight: normal;"
                                                  font-weight="normal">
                                                <tspan style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);" dy="4.25">
                                                    7,500
                                                </tspan>
                                            </text>
                                            <path fill="none" stroke="#aaaaaa" d="M61.703125,248.5H578"
                                                  stroke-width="0.5"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                                            <text x="49.203125" y="174" text-anchor="end" font-family="sans-serif"
                                                  font-size="12px" stroke="none" fill="#888888"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: end; font-family: sans-serif; font-size: 12px; font-weight: normal;"
                                                  font-weight="normal">
                                                <tspan style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);" dy="4.25">
                                                    15,000
                                                </tspan>
                                            </text>
                                            <path fill="none" stroke="#aaaaaa" d="M61.703125,174H578" stroke-width="0.5"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                                            <text x="49.203125" y="99.5" text-anchor="end" font-family="sans-serif"
                                                  font-size="12px" stroke="none" fill="#888888"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: end; font-family: sans-serif; font-size: 12px; font-weight: normal;"
                                                  font-weight="normal">
                                                <tspan style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);" dy="4.25">
                                                    22,500
                                                </tspan>
                                            </text>
                                            <path fill="none" stroke="#aaaaaa" d="M61.703125,99.5H578"
                                                  stroke-width="0.5"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                                            <text x="49.203125" y="25" text-anchor="end" font-family="sans-serif"
                                                  font-size="12px" stroke="none" fill="#888888"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: end; font-family: sans-serif; font-size: 12px; font-weight: normal;"
                                                  font-weight="normal">
                                                <tspan style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);" dy="4.25">
                                                    30,000
                                                </tspan>
                                            </text>
                                            <path fill="none" stroke="#aaaaaa" d="M61.703125,25H578" stroke-width="0.5"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                                            <text x="482.6663610386816" y="335.5" text-anchor="middle"
                                                  font-family="sans-serif" font-size="12px" stroke="none" fill="#888888"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-family: sans-serif; font-size: 12px; font-weight: normal;"
                                                  font-weight="normal" transform="matrix(1,0,0,1,0,7)">
                                                <tspan style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);" dy="4.25">
                                                    2012
                                                </tspan>
                                            </text>
                                            <text x="253.67741744088906" y="335.5" text-anchor="middle"
                                                  font-family="sans-serif" font-size="12px" stroke="none" fill="#888888"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-family: sans-serif; font-size: 12px; font-weight: normal;"
                                                  font-weight="normal" transform="matrix(1,0,0,1,0,7)">
                                                <tspan style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);" dy="4.25">
                                                    2011
                                                </tspan>
                                            </text>
                                            <path fill="#7cb47c" stroke="none"
                                                  d="M61.703125,270.2242C76.12603020891348,264.76086666666663,104.97184062674042,253.4220639750849,119.3947458356539,248.37086666666664C133.82418611715863,243.31738064175158,162.68306668016808,236.72031483420594,177.1125069616728,229.80546666666666C191.39164057357854,222.96264816753927,219.94990779739,195.3820058112773,234.22904140929575,193.34019999999998C248.34479820641994,191.3217558112773,276.57631180066835,212.07944811846423,290.6920685977925,213.56446666666665C305.11497380670596,215.08179811846423,333.960784224533,204.33911226123067,348.3836894334464,205.3496C362.8131297149512,206.360545594564,391.67201027796057,239.58761570680625,406.1014505594653,221.65019999999998C420.3805841713711,203.8996323734729,448.9388513951825,71.25654810406654,463.21798500708826,62.59766666666667C477.4905835464027,53.94274810406654,506.03578062503163,139.69876123889773,520.3083791643461,152.39499999999998C534.7312843732595,165.22494457223107,563.5770947910866,161.62554999999998,578,164.70239999999998L578,323L61.703125,323Z"
                                                  fill-opacity="1"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); fill-opacity: 1;"></path>
                                            <path fill="none" stroke="#4da74d"
                                                  d="M61.703125,270.2242C76.12603020891348,264.76086666666663,104.97184062674042,253.4220639750849,119.3947458356539,248.37086666666664C133.82418611715863,243.31738064175158,162.68306668016808,236.72031483420594,177.1125069616728,229.80546666666666C191.39164057357854,222.96264816753927,219.94990779739,195.3820058112773,234.22904140929575,193.34019999999998C248.34479820641994,191.3217558112773,276.57631180066835,212.07944811846423,290.6920685977925,213.56446666666665C305.11497380670596,215.08179811846423,333.960784224533,204.33911226123067,348.3836894334464,205.3496C362.8131297149512,206.360545594564,391.67201027796057,239.58761570680625,406.1014505594653,221.65019999999998C420.3805841713711,203.8996323734729,448.9388513951825,71.25654810406654,463.21798500708826,62.59766666666667C477.4905835464027,53.94274810406654,506.03578062503163,139.69876123889773,520.3083791643461,152.39499999999998C534.7312843732595,165.22494457223107,563.5770947910866,161.62554999999998,578,164.70239999999998"
                                                  stroke-width="3"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                                            <circle cx="61.703125" cy="270.2242" r="2" fill="#4da74d" stroke="#ffffff"
                                                    stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="119.3947458356539" cy="248.37086666666664" r="2" fill="#4da74d"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="177.1125069616728" cy="229.80546666666666" r="2" fill="#4da74d"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="234.22904140929575" cy="193.34019999999998" r="2" fill="#4da74d"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="290.6920685977925" cy="213.56446666666665" r="2" fill="#4da74d"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="348.3836894334464" cy="205.3496" r="2" fill="#4da74d"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="406.1014505594653" cy="221.65019999999998" r="2" fill="#4da74d"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="463.21798500708826" cy="62.59766666666667" r="2" fill="#4da74d"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="520.3083791643461" cy="152.39499999999998" r="2" fill="#4da74d"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="578" cy="164.70239999999998" r="2" fill="#4da74d"
                                                    stroke="#ffffff"
                                                    stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <path fill="#a7b3bc" stroke="none"
                                                  d="M61.703125,296.51773333333335C76.12603020891348,290.54283333333336,104.97184062674042,277.8505729143073,119.3947458356539,272.61813333333333C133.82418611715863,267.38332291430726,162.68306668016808,257.5095333333333,177.1125069616728,254.64873333333333C191.39164057357854,251.81773333333334,219.94990779739,252.15249181051016,234.22904140929575,249.85093333333333C248.34479820641994,247.57570847717682,276.57631180066835,239.5339599725212,290.6920685977925,236.3416C305.11497380670596,233.07977663918786,333.960784224533,223.89888898829747,348.3836894334464,224.0342C362.8131297149512,224.1695723216308,391.67201027796057,251.25028516579405,406.1014505594653,237.42433333333332C420.3805841713711,223.7424018324607,448.9388513951825,122.11508972304873,463.21798500708826,114.00266666666664C477.4905835464027,105.8939563897154,506.03578062503163,164.02335226599862,520.3083791643461,172.53979999999999C534.7312843732595,181.14593559933195,563.5770947910866,180.00469999999999,578,182.493L578,323L61.703125,323Z"
                                                  fill-opacity="1"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); fill-opacity: 1;"></path>
                                            <path fill="none" stroke="#7a92a3"
                                                  d="M61.703125,296.51773333333335C76.12603020891348,290.54283333333336,104.97184062674042,277.8505729143073,119.3947458356539,272.61813333333333C133.82418611715863,267.38332291430726,162.68306668016808,257.5095333333333,177.1125069616728,254.64873333333333C191.39164057357854,251.81773333333334,219.94990779739,252.15249181051016,234.22904140929575,249.85093333333333C248.34479820641994,247.57570847717682,276.57631180066835,239.5339599725212,290.6920685977925,236.3416C305.11497380670596,233.07977663918786,333.960784224533,223.89888898829747,348.3836894334464,224.0342C362.8131297149512,224.1695723216308,391.67201027796057,251.25028516579405,406.1014505594653,237.42433333333332C420.3805841713711,223.7424018324607,448.9388513951825,122.11508972304873,463.21798500708826,114.00266666666664C477.4905835464027,105.8939563897154,506.03578062503163,164.02335226599862,520.3083791643461,172.53979999999999C534.7312843732595,181.14593559933195,563.5770947910866,180.00469999999999,578,182.493"
                                                  stroke-width="3"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                                            <circle cx="61.703125" cy="296.51773333333335" r="2" fill="#7a92a3"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="119.3947458356539" cy="272.61813333333333" r="2" fill="#7a92a3"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="177.1125069616728" cy="254.64873333333333" r="2" fill="#7a92a3"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="234.22904140929575" cy="249.85093333333333" r="2" fill="#7a92a3"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="290.6920685977925" cy="236.3416" r="2" fill="#7a92a3"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="348.3836894334464" cy="224.0342" r="2" fill="#7a92a3"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="406.1014505594653" cy="237.42433333333332" r="2" fill="#7a92a3"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="463.21798500708826" cy="114.00266666666664" r="2" fill="#7a92a3"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="520.3083791643461" cy="172.53979999999999" r="2" fill="#7a92a3"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="578" cy="182.493" r="2" fill="#7a92a3" stroke="#ffffff"
                                                    stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <path fill="#2577b5" stroke="none"
                                                  d="M61.703125,296.51773333333335C76.12603020891348,296.2396,104.97184062674042,298.1933516723292,119.3947458356539,295.4052C133.82418611715863,292.6157850056625,162.68306668016808,275.44190436300175,177.1125069616728,274.2074666666667C191.39164057357854,272.9858876963351,219.94990779739,287.9513764096663,234.22904140929575,285.58113333333336C248.34479820641994,283.2380097429996,276.57631180066835,257.69146095717883,290.6920685977925,255.35399999999998C305.11497380670596,252.9656776238455,333.960784224533,264.2076429973575,348.3836894334464,266.678C362.8131297149512,269.14947633069085,391.67201027796057,286.8578527050611,406.1014505594653,275.1213333333333C420.3805841713711,263.5070693717277,448.9388513951825,180.56139239719235,463.21798500708826,173.27486666666667C477.4905835464027,165.99167573052569,506.03578062503163,208.6397502922645,520.3083791643461,216.84246666666667C534.7312843732595,225.13156695893116,563.5770947910866,233.64221666666666,578,239.24213333333333L578,323L61.703125,323Z"
                                                  fill-opacity="1"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); fill-opacity: 1;"></path>
                                            <path fill="none" stroke="#0b62a4"
                                                  d="M61.703125,296.51773333333335C76.12603020891348,296.2396,104.97184062674042,298.1933516723292,119.3947458356539,295.4052C133.82418611715863,292.6157850056625,162.68306668016808,275.44190436300175,177.1125069616728,274.2074666666667C191.39164057357854,272.9858876963351,219.94990779739,287.9513764096663,234.22904140929575,285.58113333333336C248.34479820641994,283.2380097429996,276.57631180066835,257.69146095717883,290.6920685977925,255.35399999999998C305.11497380670596,252.9656776238455,333.960784224533,264.2076429973575,348.3836894334464,266.678C362.8131297149512,269.14947633069085,391.67201027796057,286.8578527050611,406.1014505594653,275.1213333333333C420.3805841713711,263.5070693717277,448.9388513951825,180.56139239719235,463.21798500708826,173.27486666666667C477.4905835464027,165.99167573052569,506.03578062503163,208.6397502922645,520.3083791643461,216.84246666666667C534.7312843732595,225.13156695893116,563.5770947910866,233.64221666666666,578,239.24213333333333"
                                                  stroke-width="3"
                                                  style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                                            <circle cx="61.703125" cy="296.51773333333335" r="2" fill="#0b62a4"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="119.3947458356539" cy="295.4052" r="2" fill="#0b62a4"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="177.1125069616728" cy="274.2074666666667" r="2" fill="#0b62a4"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="234.22904140929575" cy="285.58113333333336" r="2" fill="#0b62a4"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="290.6920685977925" cy="255.35399999999998" r="2" fill="#0b62a4"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="348.3836894334464" cy="266.678" r="2" fill="#0b62a4"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="406.1014505594653" cy="275.1213333333333" r="2" fill="#0b62a4"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="463.21798500708826" cy="173.27486666666667" r="2" fill="#0b62a4"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="520.3083791643461" cy="216.84246666666667" r="2" fill="#0b62a4"
                                                    stroke="#ffffff" stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                            <circle cx="578" cy="239.24213333333333" r="2" fill="#0b62a4"
                                                    stroke="#ffffff"
                                                    stroke-width="1"
                                                    style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                                        </svg>
                                        <div class="morris-hover morris-default-style"
                                             style="left: 4.70312px; top: 192px; display: none;">
                                            <div class="morris-hover-row-label">2010 Q1</div>
                                            <div class="morris-hover-point" style="color: #0b62a4">
                                                iPhone:
                                                2,666
                                            </div>
                                            <div class="morris-hover-point" style="color: #7A92A3">
                                                iPad:
                                                -
                                            </div>
                                            <div class="morris-hover-point" style="color: #4da74d">
                                                iPod Touch:
                                                2,647
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="col-lg-4">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <i class="fa fa-list-alt fa-fw"></i> My in progress tasks
                                </div>
                                <div class="panel-body">
                                    <div class="list-group">
                                        <a href="#" class="list-group-item">
                                            New Comment <span style="color: brown">BLOCKER</span>
                                                <%--<span class="pull-right text-muted small"><em>4 minutes ago</em>--%>
                                                <%--</span>--%>
                                        </a>
                                        <a href="#" class="list-group-item">
                                            3 New Followers <span style="color: lightpink">MINOR</span>
                                                <%--<span class="pull-right text-muted small"><em>12 minutes ago</em>--%>
                                                <%--</span>--%>
                                        </a>
                                        <a href="#" class="list-group-item">
                                            Server Rebooted <span style="color: gold">TRIVIAL</span>
                                                <%--<span class="pull-right text-muted small"><em>11:32 AM</em>--%>
                                                <%--</span>--%>
                                        </a>
                                        <a href="#" class="list-group-item">
                                            Server Crashed! <span style="color: navy">MAJOR</span>
                                                <%--<span class="pull-right text-muted small"><em>11:13 AM</em>--%>
                                                <%--</span>--%>
                                        </a>
                                        <a href="#" class="list-group-item">
                                            Server Not Responding <span style="color: brown">BLOCKER</span>
                                                <%--<span class="pull-right text-muted small"><em>10:57 AM</em>--%>
                                                <%--</span>--%>
                                        </a>
                                        <a href="#" class="list-group-item">
                                            New Order Placed <span style="color: lightpink">MINOR</span>
                                                <%--<span class="pull-right text-muted small"><em>9:49 AM</em>--%>
                                                <%--</span>--%>
                                        </a>
                                        <a href="#" class="list-group-item">
                                            Payment Received <span style="color: red">CRITICAL</span>
                                                <%--<span class="pull-right text-muted small"><em>Yesterday</em>--%>
                                                <%--</span>--%>
                                        </a>
                                    </div>
                                    <a href="#" class="btn btn-default btn-block">View all tasks</a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <i class="fa fa-male fa-fw"></i>Participants
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Full name</th>
                                        <th>Role</th>
                                        <th>Email</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>Mark Otto</td>
                                        <td>QA</td>
                                        <td>mark@company.com</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>Jacob Tronton</td>
                                        <td>Java Developer</td>
                                        <td>jacob@company.com</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <i class="fa fa-pencil-square-o fa-fw"></i>Description
                        </div>
                        <div class="panel-body">
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tincidunt est vitae
                                ultrices accumsan. Aliquam ornare lacus adipiscing, posuere lectus et, fringilla
                                augue.</p>
                        </div>
                        <div class="panel-footer">
                            Team Lead: Oleh Osyka
                        </div>
                    </div>

                    <div class="tab-pane fade" id="project2">


                    </div>
                </div>
            </div>
        </div>

    </jsp:body>

</t:template>



