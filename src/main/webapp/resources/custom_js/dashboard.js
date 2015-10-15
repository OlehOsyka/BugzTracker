function format(d) {
    return 'Project name: ' + d.name + '<br>' +
        'Details: ' + d.description + '<br>' +
        'Date:' + d.date;
}

$(document).ready(function () {

    var dt = $('#example').DataTable({
        ajax: {
            contentType: "application/json",
            dataType: 'json',
            url: "/projects?order=asc&name=",
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
                    if (data.length < 15) {
                        return data;
                    }
                    if (data == null) {
                        return "-";
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
        scrollY: 400
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


    //$('#table').bootstrapTable({
    //    url: '/projects?name=',
    //    columns: [{
    //        field: 'id',
    //        title: 'ID',
    //        align: 'center',
    //        valign: 'middle',
    //        sortable: true
    //    }, {
    //        field: 'name',
    //        title: 'Name',
    //        align: 'center',
    //        valign: 'middle',
    //        sortable: true,
    //        editable: true
    //        //    type: 'text',
    //        //    title: 'Item Price',
    //        //    validate: function (value) {
    //        //        value = $.trim(value);
    //        //        if (!value) {
    //        //            return 'Name is required';
    //        //        }
    //        //        return '';
    //        //    }
    //        //}
    //    }, {
    //        field: 'description',
    //        title: 'Description',
    //        align: 'center',
    //        valign: 'middle',
    //        formatter: descriptionFormatter,
    //        sortable: true,
    //        editable: true
    //    }, {
    //        field: 'date',
    //        title: 'Date of creation',
    //        align: 'center',
    //        valign: 'middle',
    //        sortable: true
    //    }]
    //}).on('search.bs.table', function (e, text) {
    //    $.ajax({
    //        url: '/projects?search='+text
    //        //headers: {
    //        //    "Accept": "application/json",
    //        //    "Content-Type": "application/json"
    //        //}
    //    });
    //}).on('click-row.bs.table', function (e, row, $element) {
    //
    //}).on('sort.bs.table', function (e, name, order) {
    //    $.ajax({
    //        url: '/projects?name='+name+'&order='+order,
    //        headers: {
    //            "Accept": "application/json",
    //            "Content-Type": "application/json"
    //        }
    //    });
    //});

});

