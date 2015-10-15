function format(d) {
    return 'Project name: ' + d.name + '<br>' +
        'Details: ' + d.description + '<br>' +
        'Date: ' + d.date;
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
                data: "name",
                render: function edit(data) {
                    return '<a class data-type="text" data-pk="1" data-name="name" data-original-title="Enter project name">' + data + '</a>';
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

    $('#btn-my-proj').click(function () {
        dt.ajax.url('/projects?my=true').load();
    });

    $('#btn-proj').click(function () {
        dt.ajax.url('/projects?my=false').load();
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

    // On each draw, loop over the `detailRows` array and show any child rows
    dt.on('draw', function () {
        $.each(detailRows, function (i, id) {
            $('#' + id + ' td.details-control').trigger('click');
        });
    });

});

