var preLoaded = preLoad();

function preLoad() {
    //var userEmail = $("#user-session").val();
    $.ajax({
        url: "/user/projects",
        success: function (data) {
            var projectArr = data.responseJSON;
            $.each(projectArr.projects, function (i, pr) {
                $("#project-names").append(
                    '<li id="li-'+pr+'"><a href="#tab-' + pr + '" data-toggle="tab">pr</a></li>'
                );
            });
            $('#li-' + projectArr[0]).click();
        }
    });
}

$.when(preLoaded).done(function (data) {



});

