function format(d) {
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
        '<tr>' +
        '<td>Project ID:</td>' +
        '<td>' + d.id + '</td>' +
        '</tr>' +
        '<tr>' +
        '<tr>' +
        '<td>Project name:</td>' +
        '<td>' + d.name + '</td>' +
        '</tr>' +
        '<tr>' +
        '<tr>' +
        '<td>Project owner:</td>' +
        '<td>' + d.userOwner.fullName + '</td>' +
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
        if (data.length - 1 == i) {
            participantsStr += item.fullName;
        } else {
            participantsStr += item.fullName + " | ";
        }
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

    $('#dropdown-projects').removeClass('non-visible');
    $('#list-issues').addClass('non-visible');

    var dt = $('#example').DataTable({
        "bInfo" : false,
        ajax: {
            contentType: "application/json",
            dataType: 'json',
            url: "/projects?my=true",
            type: "get",
            dataSrc: ''
        },
        columns: [
            {
                class: "details-control",
                data: null,
                defaultContent: "",
                orderable: false
            },
            {
                title: "ID",
                data: "id",
                class: 'text-center'
            },
            {
                title: "Name",
                data: "name",
                render: function nameFormatter(data) {
                    if (data.length < 15) {
                        return data;
                    }
                    var desc = data.substring(0, 15);
                    return desc.concat('...');
                },
                class: 'text-center'
            },
            {
                title: "Description",
                data: "description",
                render: function descriptionFormatter(data) {
                    if (data == null || jQuery.isEmptyObject(data)) {
                        return "-";
                    }
                    if (data.length < 15) {
                        return data;
                    }
                    var desc = data.substring(0, 15);
                    return desc.concat('...');
                },
                class: 'text-center'
            },
            {
                title: "Owner",
                data: "userOwner.fullName",
                render: function ownerFormatter(data) {
                    if (data.length < 15) {
                        return data;
                    }
                    var desc = data.substring(0, 15);
                    return desc.concat('...');
                },
                class: 'text-center'
            },
            {
                title: "Date of creation",
                data: "date",
                class: 'text-center'
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
                        if (data.length - 1 == i) {
                            participantsStr += item.fullName;
                        } else {
                            participantsStr += item.fullName + " | ";
                        }
                    });
                    if (participantsStr.length < 15) {
                        return participantsStr;
                    }
                    var desc = participantsStr.substring(0, 15);
                    return desc.concat('...');
                },
                class: 'text-center'
            }
        ],
        paging: false,
        scrollY: 385
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

    // remove all text inside element without deleting child elements
    $('.dataTables_filter label').get(0).childNodes[0].nodeValue = '';
    // add placeholder to searchbox
    $('.dataTables_filter label input').attr('placeholder','Search');

    //$('<div class="btn-group" role="group">' +
    //    '<button type="button" class="btn btn-primary" id="btn-proj">All projects</button>' +
    //    '<button type="button" class="btn btn-primary active" id="btn-my-proj">My projects</button>' +
    $('<button type="button" class="btn btn-primary" id="btn-edit">Add</button>').appendTo('div.dataTables_filter');

    //what btn was pushed last
    var lastBtn = "btn-my-proj";
    //id of checked tr
    var checkedId;
    var isChecked;
    // Array to track the ids of the details displayed rows
    var detailRows = [];
    var tempUserTypeaheadList;

    $('#btn-my-proj, #btn-dashboard').click(function () {
        dt.ajax.url('/projects?my=true').load();
        $('#dropdown-issues').addClass('non-visible');
        //$('#btn-my-proj').addClass('active');
        isChecked = false;
        $('#btn-edit').text('Add');
        //$('#btn-proj').removeClass('active');
        lastBtn = $(this).attr('id');
    });

    $('#btn-proj').click(function () {
        dt.ajax.url('/projects?my=false').load();
        $('#dropdown-issues').addClass('non-visible');
        //$('#btn-proj').addClass('active');
        isChecked = false;
        $('#btn-edit').text('Add');
        //$('#btn-my-proj').removeClass('active');
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
        return true;
    });


    $('#example').find('tbody').on('click', 'tr', function () {
        if (this.children[0].className != 'dataTables_empty') {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
                isChecked = false;
                $('#btn-edit').text('Add');
            }
            else {
                dt.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
                isChecked = true;
                $('#btn-edit').text('Edit');
                checkedId = $(this).closest('tr').context.children[1].innerText;
            }
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

    $("#users-list").on('click', 'button.close', function (e) {
        //if ($('#modalEdit').find('#modal-name').text() == 'Add') {
        //    $(e.currentTarget.parentElement).remove();
        //} else {
        //    var userName = $('#current-user').val();
        //    var text = e.currentTarget.parentElement.innerText;
        //if (text.substring(0, text.length - 1) != userName) {
        $(e.currentTarget.parentElement).remove();
        //   }
        //}
    });


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
                    $.each(data.participants, function (i, item) {
                        modal.find('#users-list').append(
                            '<span class="label label-info display-user-label" data-id="' + item.id + '">' + item.fullName +
                            '<button type="button" class="close margin-close-btn">' +
                            '<span aria-hidden="true">&times;</span></button></span>'
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
                url: "/users",
                data: {query: query},
                dataType: 'json',
                success: function (result) {

                    tempUserTypeaheadList = result;

                    var existingUsers = [];
                    $('#users-list').children('span').each(function () {
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
            var item = $.grep(tempUserTypeaheadList, function (e) {
                return e.fullName == name;
            });
            if (item.length == 0) {
                // return empty string to clear input
                return '';
            } else {
                $('#users-list').append(
                    '<span class="label label-info display-user-label" data-id="' + item[0].id + '">' + item[0].fullName +
                    '<button type="button" class="close margin-close-btn">' +
                    '<span aria-hidden="true">&times;</span></button></span>'
                );
                // return empty string to clear input
                return '';
            }
        }
    });

});

function wrapText(txt) {
    var index = 30;
    while (index < txt.length) {
        txt = [txt.slice(index - 30, index), '<br/>', txt.slice(index)].join('');
        index = index + 30;
    }
    return txt;
}

