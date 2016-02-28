var preLoaded = preLoad();

function preLoad() {
    var userEmail = $("#user-session").val();
    $.ajax({
        url: "/" + userEmail + "/projects",
        success: function (data) {
            var projectArr = data.responseJSON;
            $.each(projectArr.projectNames, function (i, pr) {
                $("#project-names").append(
                    '<li><a href="#' + pr + '" data-toggle="tab">pr</a></li>'
                );
            });
            //$('#tab-' + projectArr[0]).click();
        }
    });
}

$.when(preLoaded).done(function (data) {

    $.ajax({
        url: "/" + +"/issues/count",
        success: function (data) {
            var projectArr = data.responseJSON;
            $.each(projectArr.projectNames, function (i, pr) {
                $("#project-names").append(
                    '<li><a href="#' + pr + '" data-toggle="tab">pr</a></li>'
                );
            });
            //$('#tab-' + projectArr[0]).click();
        }
    });


    $('#project-names').children('input').each(function () {
        var id = this.id.substring(1);
        $("#content").append(
            '<div class="tab-pane fade in active" id="' + id + '">' +
            '<br/>' +
            '<div class="row">'+
            '<div class="col-lg-3 col-md-6">'+
            '<div class="panel panel-primary">'+
            '<div class="panel-heading">'+
            '<div class="row">'+
            '<div class="col-xs-3">'+
            '<i class="fa fa-envelope fa-3x"></i>'+
            '</div>'+
            '<div class="col-xs-9 text-right">'+
            '<div class="huge">36</div>'+
            '<div>OPENED</div>'+
            '</div>'+
            '</div>'+
            '</div>'+
            '<a href="#">'+
            '<div class="panel-footer">'+
            '<span class="pull-left">View Details</span>'+
            '<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>'+
            '<div class="clearfix"></div>'+
            '</div>'+
            '</a>'+
            '</div>'+
            '</div>'+
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
            '</div>'
        );
    });

});

