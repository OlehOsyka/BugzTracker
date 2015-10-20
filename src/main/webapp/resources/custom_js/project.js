//function format(d) {
//    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
//        '<tr>' +
//        '<td>Name:</td>' +
//        '<td>' + d.name + '</td>' +
//        '</tr>' +
//        '<tr>' +
//        '<td>Date:</td>' +
//        '<td>' + d.date + '</td>' +
//        '</tr>' +
//        '<tr>' +
//        '<td>Description:</td>' +
//        '<td>' + d.description + '</td>' +
//        '</tr>' +
//        '</table>';
//}
var dt;
var projectId;
var isMyProject;
var checkedId;
var isChecked;
// Array to track the ids of the details displayed rows
var detailRows = [];

preLoad();

function preLoad() {
    projectId = Cookies.get('checkedId');
    Cookies.remove('checkedId');
    $.ajax({
        type: "GET",
        url: "/check/" + projectId,
        success: function (data) {
            isMyProject = data;
            if (!isMyProject) {
                $('#btn-my-issues').addClass('show-none');
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
        }
    });
}

$(document).ready(function () {

    $('#btn-my-issues').click(function () {
        dt.ajax.url("/project/" + projectId + "/issues?my=true").load();
    });

    $('#btn-issues').click(function () {
        dt.ajax.url("/project/" + projectId + "/issues?my=false").load();
    });

    $('#issuesTable').find('tbody').on('click', 'tr td.details-control', function () {
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

    $('#issuesTable').find('tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            $('#btn-delete').addClass('show-none');
            isChecked = false;
        }
        else {
            dt.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            checkedId = $(this).closest('tr').context.children[1].innerText;
            isChecked = true;
            $('#btn-delete').removeClass('show-none');
        }
    });

});