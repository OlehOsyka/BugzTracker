/**
 * Created by oleg on 31.10.15.
 */


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
            "data": null,
            "defaultContent": "",
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
        },
        {
            title: "Date of creation",
            data: "date",
            class: 'text-center'
        },
        {
            title: "Description",
            data: "description",
            orderable: false,
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
        }
    ],
    paging: false,
    scrollY: 375
});