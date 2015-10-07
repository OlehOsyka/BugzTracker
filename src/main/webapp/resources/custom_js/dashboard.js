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
        return;
    }
    var desc = data.substring(0, 25);
    return desc.concat('...');
}

$(document).ready(function () {

    $('#table').bootstrapTable({
        url: '/projects',
        columns: [{
            field: 'id',
            title: 'ID',
            align: 'center',
            valign: 'middle'
            //sortable: true
        }, {
            field: 'name',
            title: 'Name',
            align: 'center',
            valign: 'middle'
            //sortable: true,
            //editable: {
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
            formatter: descriptionFormatter
            //sortable: true,
            // editable: true
        }, {
            field: 'date',
            title: 'Date of creation',
            align: 'center',
            valign: 'middle'
            //sortable: true
        }]
    });

});
