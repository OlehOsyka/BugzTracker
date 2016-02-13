<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand brand-title" href="<c:url value="/dashboard"/>">Bugz Tracker</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="non-visible" id="list-issues"><a href="<c:url value="/project"/>">Issues</a></li>
                <li class="dropdown" id="dropdown-projects">
                    <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Projects<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a id="btn-proj">All projects</a></li>
                        <li><a id="btn-my-proj">My projects</a></li>
                    </ul>
                </li>
                <li class="dropdown non-visible" id="dropdown-issues">
                    <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false" id="project-name"><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a id="btn-issues">All issues</a></li>
                        <li><a id="btn-my-issues">My issues</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">${user.fullName}<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="/logout"/>">Logout</a></li>
                    </ul>
            </ul>
        </div>
    </div>
</nav>