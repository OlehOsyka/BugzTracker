function detailFormatter(index, row) {
    var html = [];
    $.each(row, function (key, value) {
        if (value == null) {
            value = "-";
        }
        html.push('<p><b>' + key.toUpperCase() + ':</b> ' + value + '</p>');
    });
    return html.join('');
}

function descriptionFormatter(data) {
    if (data == null) {
        return "-";
    }
    var desc = data.substring(0, 15);
    return desc.concat('...');
}

$(document).ready(function () {

    $('#table').bootstrapTable({
        url: '/projects?name=',
        columns: [{
            field: 'id',
            title: 'ID',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'name',
            title: 'Name',
            align: 'center',
            valign: 'middle',
            sortable: true,
            editable: true
            //    type: 'text',
            //    title: 'Item Price',
            //    validate: function (value) {
            //        value = $.trim(value);
            //        if (!value) {
            //            return 'Name is required';
            //        }
            //        return '';
            //    }
            //}
        }, {
            field: 'description',
            title: 'Description',
            align: 'center',
            valign: 'middle',
            formatter: descriptionFormatter,
            sortable: true,
            editable: true
        }, {
            field: 'date',
            title: 'Date of creation',
            align: 'center',
            valign: 'middle',
            sortable: true
        }]
    }).on('search.bs.table', function (e, text) {
        $.ajax({
            url: '/projects?search='+text
            //headers: {
            //    "Accept": "application/json",
            //    "Content-Type": "application/json"
            //}
        });
    }).on('click-row.bs.table', function (e, row, $element) {

    }).on('sort.bs.table', function (e, name, order) {
        $.ajax({
            url: '/projects?name='+name+'&order='+order,
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        });
    });

});

