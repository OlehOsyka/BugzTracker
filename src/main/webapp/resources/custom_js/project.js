var dt;
var projectId;
var isMyProject;
var checkedId;
var isChecked;
var lastBtn = "btn-my-issues";
var tempUserTypeaheadList = [];

preLoad();

function preLoad() {
    projectId = Cookies.get('checkedId');
    //Cookies.remove('checkedId');
    $.ajax({
        type: "GET",
        url: "/check/" + projectId,
        success: function (data) {
            renderTable(data);
        }
    });
}

function renderTable(data) {
    isMyProject = data;
    if (!isMyProject) {
        $('#btn-my-issues').addClass('show-none');
        $('#btn-issues').addClass('active');
    } else {
        $('#btn-my-issues').addClass('active');
    }
    dt = $('#issuesTable').DataTable({
        ajax: {
            contentType: "application/json",
            dataType: 'json',
            url: "/project/" + projectId + "/issues?my=" + isMyProject,
            type: "get",
            dataSrc: ''
        },
        columns: [
            {
                title: "ID",
                data: "id"
            },
            {
                title: "Name",
                data: "name"
            },
            {
                title: "Category",
                data: "category"
            },
            {
                title: "Priority",
                data: "priority"
            },
            {
                title: "Creator",
                data: "userCreator.fullName"
            },
            {
                title: "Status",
                data: "status"
            },
            {
                title: "Date of creation",
                data: "date"
            },
            {
                title: "Last update",
                data: "lastUpdate",
                render: function dateFormatter(data) {
                    if (data == null) {
                        return "-";
                    }
                    return data;
                }
            },
            {
                title: "Description",
                data: "description",
                render: function descriptionFormatter(data) {
                    if (data == null) {
                        return "-";
                    }
                    if (data.length < 15) {
                        return data;
                    }
                    var desc = data.substring(0, 15);
                    return desc.concat('...');
                }
            }
        ],
        paging: false,
        scrollY: 385
    });

}

$(document).ajaxStop(function () {

    $('#btn-edit').click(function () {
        $('#modalEdit').modal('show');
    });

    $('#modalEdit').on('show.bs.modal', function (event) {
        var modal = $(this);
        if (isChecked) {
            modal.find('#modal-name').text("Edit");
            $.ajax({
                url: "/issue/" + checkedId,
                success: function (data) {
                    modal.find('#name').val(data.name);
                    modal.find('#category').val(data.category);
                    modal.find('#form-group-status').removeClass('show-none');
                    modal.find('#status').val(data.status);
                    modal.find('#priority').val(data.priority);
                    modal.find('#version').val(data.version);
                    modal.find('#desc').val(data.description);
                    modal.find('#assignee-field').append(
                        '<span class="label label-info display-user-label" data-id="' + data.assignee.id + '">' + data.assignee.fullName +
                        '<button type="button" class="close margin-close-btn">' +
                        '<span aria-hidden="true">&times;</span></button></span>'
                    );
                }
            });
        } else {
            modal.find('#modal-name').text("Add");
        }
    });

    $('#btn-save').click(function () {
        var name = $.trim($('#name').val());
        var desc = $.trim($('#desc').val());
        var version = $.trim($('#version').val());
        var status = $('#status').val();
        var priority = $('#priority').val();
        var category = $('#category').val();
        var assignee = $('#assignee-field').children('span').data('id');
        //if (validate(name)) {
        var issue;
        var url;
        if ($('#modalEdit').find('#modal-name').text() == 'Add') {
            url = '/issue';
            issue = {
                "id": checkedId,
                "name": name,
                "description": desc,
                "project": projectId,
                "priority": priority,
                "category": category,
                "assignee": assignee
            };
        }
        else {
            url = '/issue/update';
            issue = {
                "id": checkedId,
                "name": name,
                "description": desc,
                "project": projectId,
                "priority": priority,
                "category": category,
                "status": status,
                "assignee": assignee
            };
        }
        $.ajax({
            type: "POST",
            url: url,
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            data: JSON.stringify(issue),
            success: function () {
                cleanModal();
                $('#modalEdit').modal('hide');
                $('#' + lastBtn).click();
            },
            error: function (data) {
                var error = data.responseJSON;
                var errorText = error.error;
                //if (errorText.indexOf("Name") >= 0 || errorText.indexOf("Not more") >= 0) {
                //    $('#form-group-name').removeClass('has-success');
                //    $('#form-group-name').addClass("has-error");
                //}
                //if (errorText.indexOf("At least") >= 0) {
                //    $('#form-group-users').removeClass('has-success');
                //    $('#form-group-users').addClass('has-error');
                //}
                //$('#form-group-desc').removeClass('has-error');
                //$('#form-group-desc').addClass('has-success');

                $('#invalid-issue-edit').removeClass('non-visible');
                $('#invalid-issue-edit').text(errorText);
            }
        });
        //}
    });

    $('#btn-delete').click(function () {
        $('#modalDelete').modal('show');
    });

    $('#btn-delete-yes').click(function () {
        $.ajax({
            type: "POST",
            url: 'issue/delete/'+checkedId,
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            success: function () {
                $('#modalDelete').modal('hide');
                $('#' + lastBtn).click();
            }
        });
    });

    $('#assignee').typeahead({
        source: function (query, process) {
            return $.ajax({
                url: "/users",
                data: {"query": query, "projectId": projectId},
                dataType: 'json',
                success: function (result) {

                    tempUserTypeaheadList = result;

                    var existingUsers = [];
                    $('#assignee-field').children('span').each(function () {
                        var name = $(this).text();
                        existingUsers.push(name.substring(0, name.length - 1));
                    });

                    var resultList = [];

                    jQuery.each(result, function (i, val) {
                        if ($.inArray(val.fullName, existingUsers) < 0) {
                            resultList.push(val.fullName);
                        }
                    });

                    return process(resultList);

                }
            });
        },

        updater: function (name) {
            if (jQuery.isEmptyObject($('#assignee-field'))) {
                var item = $.grep(tempUserTypeaheadList, function (e) {
                    return e.fullName == name;
                });
                if (item.length == 0) {
                    // return empty string to clear input
                    return '';
                } else {
                    $('#assignee-field').append(
                        '<span class="label label-info display-user-label" data-id="' + item[0].id + '">' + item[0].fullName +
                        '<button type="button" class="close margin-close-btn">' +
                        '<span aria-hidden="true">&times;</span></button></span>'
                    );
                    // return empty string to clear input
                    return '';
                }
            }
        }
    });

    $('#btn-cancel, #btn-close').click(function () {
        cleanModal();
    });

    function cleanModal() {
        $('#name').val('');
        $('#category').val('');
        $('#form-group-status').addClass('show-none');
        $('#priority').val('');
        $('#version').val('');
        $('#desc').val('');
        $('#assignee').val('');
        $('#assignee-field').empty();
        $('#invalid-issue-edit').addClass('non-visible');
        $('#invalid-issue-edit').empty();
        $('#form-group-name, #form-group-desc, #form-group-status, #form-group-category, #form-group-priority, #form-group-assignee, #form-group-version').removeClass('has-error has-success');
    }

    $('#btn-my-issues').click(function () {
        dt.ajax.url("/project/" + projectId + "/issues?my=true").load();
        $('#btn-my-issues').addClass('active');
        $('#btn-issues').removeClass('active');
        lastBtn = $(this).attr('id');
    });

    $('#btn-issues').click(function () {
        dt.ajax.url("/project/" + projectId + "/issues?my=false").load();
        $('#btn-issues').addClass('active');
        $('#btn-my-issues').removeClass('active');
        lastBtn = $(this).attr('id');
    });

    $('#issuesTable').find('tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            $('#btn-delete').addClass('show-none');
            isChecked = false;
        }
        else {
            dt.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            checkedId = $(this).closest('tr').context.children[0].innerText;
            isChecked = true;
            $('#btn-delete').removeClass('show-none');
        }
    });

});