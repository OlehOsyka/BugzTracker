function format(d) {
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
        '<tr>' +
        '<td>Project name:</td>' +
        '<td>' + d.name + '</td>' +
        '</tr>' +
        '<tr>' +
        '<td>Date:</td>' +
        '<td>' + d.date + '</td>' +
        '</tr>' +
        '<tr>' +
        '<td>Description:</td>' +
        '<td>' + d.description + '</td>' +
        '</tr>' +
        '</table>';
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
        scrollY: 360
    });

    //what btn was pushed last
    var lastBtn = "btn-my-proj";

    $('#btn-my-proj').click(function () {
        dt.ajax.url('/projects?my=true').load();
        lastBtn = $(this).attr('id');
    });

    $('#btn-proj').click(function () {
        dt.ajax.url('/projects?my=false').load();
        lastBtn = $(this).attr('id');
    });

    // Array to track the ids of the details displayed rows
    var detailRows = [];

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
        if (e.button == 2 && $(e.target).is('span')) {
            $(e.target).remove();
            return false;
        }
        return true;
    });

    //id of checked tr
    var checkedId;

    $('#example').find('tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            $('#btn-edit').addClass('show-none');
        }
        else {
            dt.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            checkedId = $(this).closest('tr').context.children[1].innerText;
            $('#btn-edit').removeClass('show-none');
        }
    });

    $('#btn-save').click(function () {
        var name = $.trim($('#name').val());
        var desc = $.trim($('#desc').val());
        var users = [];
        $('#users-list').children('span').each(function () {
            users.push({id: $(this).data('id')});
        });
        var project = {
            "id": checkedId,
            "name": name,
            "description": desc,
            "participants": users
        };
        $.ajax({
            type: "POST",
            url: '/project/update',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            data: JSON.stringify(project),
            success: function () {
                $('#desc').val('');
                $('#name').val('');
                $('#user').val('');
                $('#users-list').empty();
                $('#modalEdit').modal('hide');
                $('#' + lastBtn).click();
            }
        });
    });

    $('#btn-edit').click(function () {
        $('#modalEdit').modal('show');
    });

    $('#btn-add').click(function () {
        $('#modalEdit').modal('show');
    });

    $('#modalEdit').on('show.bs.modal', function (event) {
        var modal = $(this);
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

    });

// On each draw, loop over the `detailRows` array and show any child rows
    dt.on('draw', function () {
        $.each(detailRows, function (i, id) {
            $('#' + id + ' td.details-control').trigger('click');
        });
    });

    var tempUserTypeaheadList;

    $('#user').typeahead({
        source: function (query, process) {
            return $.ajax({
                url: "/users/",
                type: 'post',
                data: {query: query},
                dataType: 'json',
                success: function (result) {

                    tempUserTypeaheadList = result;

                    var existingUsers = [];
                    $('#users-list').children('span').each(function () {
                        existingUsers.push($(this).text());
                    });

                    var resultList = result.map(function (item) {
                        if (existingUsers.indexOf(item.fullName) == -1) {
                            return item.fullName;
                        }
                    });

                    return process(resultList);

                }
            });
        },
        //},
        //
        //matcher: function (obj) {
        //    var item = JSON.parse(obj);
        //    return ~item.name.toLowerCase().indexOf(this.query.toLowerCase())
        //},
        //
        //sorter: function (items) {
        //    var beginswith = [], caseSensitive = [], caseInsensitive = [], item;
        //    while (aItem = items.shift()) {
        //        var item = JSON.parse(aItem);
        //        if (!item.name.toLowerCase().indexOf(this.query.toLowerCase())) beginswith.push(JSON.stringify(item));
        //        else if (~item.name.indexOf(this.query)) caseSensitive.push(JSON.stringify(item));
        //        else caseInsensitive.push(JSON.stringify(item));
        //    }
        //
        //    return beginswith.concat(caseSensitive, caseInsensitive)
        //
        //},

        //
        //highlighter: function (obj) {
        //    var item = JSON.parse(obj);
        //    var query = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, '\\$&')
        //    return item.name.replace(new RegExp('(' + query + ')', 'ig'), function ($1, match) {
        //        return '<strong>' + match + '</strong>'
        //    })
        //},

        updater: function (name) {
            var item = $.grep(tempUserTypeaheadList, function (e) {
                return e.fullName == name;
            });
            if (item.length == 0) {
                return name;
            } else {
                $('#users-list').append(
                    '<span class="label label-info margin" data-id="' + item[0].id + '">' + item[0].fullName + '</span>'
                );
                return name;
            }
        }
    });

});

