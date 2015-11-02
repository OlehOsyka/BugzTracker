var dt;
var projectId;
var isMyProject;
var checkedId;
var isChecked;
var lastBtn = "btn-my-issues";
var tempUserTypeaheadList = [];

var preLoaded = preLoad();

function preLoad() {
    projectId = Cookies.get('checkedId');
    //Cookies.remove('checkedId');
    return $.ajax({
        type: "GET",
        url: "/check/" + projectId
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
                data: "id",
                class: 'text-center'
            },
            {
                title: "Name",
                data: "name",
                render: function nameFormatter(data) {
                    if (data.length < 7) {
                        return data;
                    }
                    var desc = data.substring(0, 7);
                    return desc.concat('...');
                },
                class: 'text-center'
            },
            {
                title: "Category",
                data: "category",
                class: 'text-center'
            },
            {
                title: "Priority",
                data: "priority",
                class: 'text-center',
                render: function priorityFormatter(data) {
                    if (data == 'CRITICAL') {
                        return '<div class="color-red">'+data+'</div>';
                    } else if(data == 'BLOCKER') {
                        return '<div class="color-orange">'+data+'</div>';
                    } else if(data == 'MAJOR') {
                        return '<div class="color-yellow">'+data+'</div>';
                    } else if(data == 'TRIVIAL') {
                        return '<div class="color-cream">'+data+'</div>';
                    } else {
                        return '<div class="color-grey">'+data+'</div>';
                    }
                }
            },
            {
                title: "Status",
                data: "status",
                class: 'text-center'
            },
            {
                title: "Creator",
                data: "userCreator.fullName",
                render: function creatorFormatter(data) {
                    if (data.length < 10) {
                        return data;
                    }
                    var desc = data.substring(0, 10);
                    return desc.concat('...');
                },
                class: 'text-center'
            },
            {
                title: "Assignee",
                data: "assignee.fullName",
                render: function assigneeFormatter(data) {
                    if (data.length < 10) {
                        return data;
                    }
                    var desc = data.substring(0, 10);
                    return desc.concat('...');
                },
                class: 'text-center'
            },
            {
                title: "Last update",
                data: "lastUpdate",
                render: function dateFormatter(data) {
                    if (data == null) {
                        return "-";
                    }
                    return data;
                },
                class: 'text-center'
            }
        ],
        paging: false,
        scrollY: 375
    });

}

function loadProjectName() {
    $.ajax({
        url: "/project/" + projectId,
        success: function (data) {
            $('#project-name').text(data.name);
        }
    });
}

$.when(preLoaded).done(function (data) {

    renderTable(data);

    loadProjectName();

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

    //to do
    function validate(name, version, assignee) {
        var error = "";
        error += Validation.validProjectName(name);
        error += Validation.validAssignee(assignee);
        error += Validation.validVersion(version);
        error += Validation.validDescription();
        error += Validation.validStatusPriorityCategory();
        if (error) {
            $('#invalid-issue-edit').removeClass('non-visible');
            $('#invalid-issue-edit').text(error);
            return false;
        } else {
            $('#invalid-issue-edit').addClass('non-visible');
            return true;
        }
    }

    $('#btn-save').click(function () {
        var name = $.trim($('#name').val());
        var desc = $.trim($('#desc').val());
        var version = $.trim($('#version').val());
        var status = $('#status').val();
        var priority = $('#priority').val();
        var category = $('#category').val();
        var assignee = {
            id: $('#assignee-field').children('span').data('id')
        };
        var project = {
            id: projectId
        };
        if (validate(name, version, assignee)) {
            var issue;
            var url;
            if ($('#modalEdit').find('#modal-name').text() == 'Add') {
                url = '/issue';
                issue = {
                    "id": checkedId,
                    "name": name,
                    "description": desc,
                    "priority": priority,
                    "project": project,
                    "version": version,
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
                    "priority": priority,
                    "version": version,
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
                    $('#invalid-issue-edit').removeClass('non-visible');
                    $('#invalid-issue-edit').text(errorText);
                }
            });
        }
    });

    $('#btn-delete').click(function () {
        $('#modalDelete').modal('show');
    });

    $('#btn-delete-yes').click(function () {
        $.ajax({
            type: "POST",
            url: 'issue/delete/' + checkedId,
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
            if ($('#assignee-field').text() == "") {
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
        $('#form-group-status').addClass('show-none');
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
        isChecked = false;
        lastBtn = $(this).attr('id');
    });

    $('#btn-issues').click(function () {
        dt.ajax.url("/project/" + projectId + "/issues?my=false").load();
        $('#btn-issues').addClass('active');
        isChecked = false;
        $('#btn-my-issues').removeClass('active');
        lastBtn = $(this).attr('id');
    });

    $('#issuesTable').find('tbody').on('click', 'tr', function () {
        if (this.children[0].className != 'dataTables_empty') {
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
        }

    });

    $("#assignee-field").on('click', 'button.close', function (e) {
        $(e.currentTarget.parentElement).remove();
    });

    $(document).mousedown(function (e) {
        if (e.button == 2 && $(e.target).is('td')) {
            checkedId = e.target.parentElement.cells[0].innerText;
            Cookies.set('checkedId', checkedId);
            window.location.href = '/issue';
            return false;
        }
        return true;
    });

});
