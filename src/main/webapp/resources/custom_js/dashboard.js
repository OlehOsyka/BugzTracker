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
        scrollY: 370
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
        var project = {
            "id": checkedId,
            "name": name,
            "description": desc
        };
        $.ajax({
            type: "POST",
            url: 'project/update',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            data: JSON.stringify(project),
            success: function () {
                $('#desc').val('');
                $('#name').val('');
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
        $('#desc').val('');
        $('#name').val('');
    });

    $('#modalEdit').on('show.bs.modal', function (event) {
        var modal = $(this);
        $.ajax({
            url: "project/" + checkedId,
            success: function (data) {
                modal.find('#name').val(data.name);
                modal.find('#desc').val(data.description);
            }
        });

    });

    // On each draw, loop over the `detailRows` array and show any child rows
    dt.on('draw', function () {
        $.each(detailRows, function (i, id) {
            $('#' + id + ' td.details-control').trigger('click');
        });
    });

});

