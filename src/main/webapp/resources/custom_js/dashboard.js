function format(d) {
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
        '<tr>' +
        '<td>Project name:</td>' +
        '<td>' + d.name + '</td>' +
        '</tr>' +
        '<tr>' +
        '<td>Participants:</td>' +
        '<td>' + participantsFormatter(d.participants) + '</td>' +
        '</tr>' +
        '<tr>' +
        '<td>Date of creation:</td>' +
        '<td>' + d.date + '</td>' +
        '</tr>' +
        '<tr>' +
        '<td>Description:</td>' +
        '<td>' + dataFormatter(d.description) + '</td>' +
        '</tr>' +
        '</table>';
}

function participantsFormatter(data) {
    if (data == null || jQuery.isEmptyObject(data)) {
        return "-";
    }
    var participantsStr = "";
    $.each(data, function (i, item) {
        participantsStr += item.fullName + " | ";
    });
    return participantsStr;
}

function dataFormatter(data) {
    if (data == null || jQuery.isEmptyObject(data)) {
        return "-";
    }
    return data;
}

$(document).ready(function () {

    var dt = $('#example').DataTable({
        ajax: {
            contentType: "application/json",
            dataType: 'json',
            url: "/projects?my=true",
            type: "get",
            dataSrc: ''
        },
        columns: [
            {
                "class": "details-control",
                "orderable": false,
                "data": null,
                "defaultContent": ""
            },
            {
                title: "ID",
                data: "id"
            },
            {
                title: "Name",
                data: "name"
            },
            {
                title: "Participants",
                data: "participants",
                render: function participantsFormatter(data) {
                    if (data == null || jQuery.isEmptyObject(data)) {
                        return "-";
                    }
                    var participantsStr = "";
                    $.each(data, function (i, item) {
                        participantsStr += item.fullName + " | ";
                    });
                    if (participantsStr.length < 15) {
                        return participantsStr;
                    }
                    var desc = participantsStr.substring(0, 15);
                    return desc.concat('...');
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
            },
            {
                title: "Date of creation",
                data: "date"
            }
        ],
        paging: false,
        scrollY: 370
    });

    $('#btn-cancel, #btn-close').click(function () {
        cleanModal();
    });

    function cleanModal() {
        $('#desc').val('');
        $('#name').val('');
        $('#user').val('');
        $('#users-list').empty();
        $('#invalid-project-edit').addClass('non-visible');
        $('#invalid-project-edit').empty();
        $('#form-group-name, #form-group-desc, #form-group-users').removeClass('has-error has-success');
    }

    //what btn was pushed last
    var lastBtn = "btn-my-proj";
    //id of checked tr
    var checkedId;
    var isChecked;
    // Array to track the ids of the details displayed rows
    var detailRows = [];
    var tempUserTypeaheadList;

    $('#btn-my-proj').click(function () {
        dt.ajax.url('/projects?my=true').load();
        lastBtn = $(this).attr('id');
    });

    $('#btn-proj').click(function () {
        dt.ajax.url('/projects?my=false').load();
        lastBtn = $(this).attr('id');
    });

    $('#example').find('tbody').on('click', 'tr td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = dt.row(tr);
        var idx = $.inArray(tr.attr('id'), detailRows);

        if (row.child.isShown()) {
            tr.removeClass('details');
            row.child.hide();

            // Remove from the 'open' array
            detailRows.splice(idx, 1);
        }
        else {
            tr.addClass('details');
            row.child(format(row.data())).show();

            // Add to the 'open' array
            if (idx === -1) {
                detailRows.push(tr.attr('id'));
            }
        }
    });

    document.oncontextmenu = function () {
        return false;
    };

    $(document).mousedown(function (e) {
        if (e.button == 2 && $(e.target).is('td')) {
            checkedId = e.target.parentElement.cells[1].innerText;
            Cookies.set('checkedId', checkedId);
            window.location.href = '/project';
            return false;
        }
        if (e.button == 2 && $(e.target).is('span') && $('.label-info').size() > 1) {
            $(e.target).remove();
            return false;
        }
        return true;
    });


    $('#example').find('tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            isChecked = false;
        }
        else {
            dt.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            isChecked = true;
            checkedId = $(this).closest('tr').context.children[1].innerText;
        }
    });

    $('#btn-save').click(function () {
        var name = $.trim($('#name').val());
        var desc = $.trim($('#desc').val());
        var users = [];
        $('#users-list').children('span').each(function () {
            users.push({id: $(this).data('id')});
        });
        if (validate(name)) {
            var project = {
                "id": checkedId,
                "name": name,
                "description": desc,
                "participants": users
            };
            var url;
            if ($('#modalEdit').find('#modal-name').text() == 'Add') {
                url = '/project';
            }
            else {
                url = '/project/update';
            }
            $.ajax({
                type: "POST",
                url: url,
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                data: JSON.stringify(project),
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

                    $('#invalid-project-edit').removeClass('non-visible');
                    $('#invalid-project-edit').text(errorText);
                }
            });
        }
});

function validate(name) {
    var error = "";
    error += Validation.validProjectName(name);
    error += Validation.validParticipants();
    error += Validation.validDescription();
    if (error) {
        $('#invalid-project-edit').removeClass('non-visible');
        $('#invalid-project-edit').text(error);
        return false;
    } else {
        $('#invalid-project-edit').addClass('non-visible');
        return true;
    }
}


$('#btn-edit').click(function () {
    $('#modalEdit').modal('show');
});

$('#modalEdit').on('show.bs.modal', function (event) {
    var modal = $(this);
    if (isChecked) {
        modal.find('#modal-name').text("Edit");
        $.ajax({
            url: "/project/" + checkedId,
            success: function (data) {
                modal.find('#name').val(data.name);
                modal.find('#desc').val(data.description);
                modal.find('#users-list').empty();
                $.each(data.participants, function (i, item) {
                    modal.find('#users-list').append(
                        '<span class="label label-info margin" data-id="' + item.id + '">' + item.fullName + '</span>'
                    );
                });
            }
        });
    } else {
        modal.find('#modal-name').text("Add");
    }
});

// On each draw, loop over the `detailRows` array and show any child rows
dt.on('draw', function () {
    $.each(detailRows, function (i, id) {
        $('#' + id + ' td.details-control').trigger('click');
    });
});

$('#user').typeahead({
    source: function (query, process) {
        return $.ajax({
            url: "/users/",
            data: {query: query},
            dataType: 'json',
            success: function (result) {

                tempUserTypeaheadList = result;

                var existingUsers = [];
                $('#users-list').children('span').each(function () {
                    existingUsers.push($(this).text());
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
        var item = $.grep(tempUserTypeaheadList, function (e) {
            return e.fullName == name;
        });
        if (item.length == 0) {
            // return empty string to clear input
            return '';
        } else {
            $('#users-list').append(
                '<span class="label label-info margin" data-id="' + item[0].id + '">' + item[0].fullName + '</span>'
            );
            // return empty string to clear input
            return '';
        }
    }
});

})
;

