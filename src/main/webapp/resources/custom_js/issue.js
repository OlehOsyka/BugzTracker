// inline mode for x-editable
$.fn.editable.defaults.mode = 'inline';
var issueId = Cookies.get('checkedIssueId');

function preLoad() {
    return $.ajax({
        type: "GET",
        url: "/issue/" + issueId
    });
}

function renderPage(issue) {
    $('span#name').text((issue.name));
    $('span#date').text((issue.date));
    $('span#lastUpdate').text((issue.lastUpdate == null ? '-' : issue.lastUpdate));
    $('span#status').text((issue.status));
    $('span#priority').text((issue.priority));
    $('span#desc').text((issue.description == "" || jQuery.isEmptyObject(issue.description) ? '-' : issue.description));
    $('span#category').text((issue.category));
    $('span#creator').text((issue.userCreator.fullName));
    $('span#version').text((issue.version));
    $('span#assignee').text((issue.assignee.fullName));

    // Show attachments
    $('div#files').empty();
    // if key exists -> show file list
    if (issue.hasOwnProperty('attachments') && !$.isEmptyObject(issue.attachments)) {
        $.each(issue.attachments, function (index, value) {
            var name = (/\/files\/-?\d+\/(.+)/gi).exec(value.attachmentPath)[1];
            var href = "/file/get/" + issueId + "/" + name;
            var deleteHref = "/file/remove/" + issueId + "/" + name;
            $('div#files').append(
                '<div class="attachment-table">' +
                '<a href="' + href + '" target="_blank">' + (name) + '</a>' +
                '<span class="remove-attachment close" data-href="' + deleteHref + '">  &times;</span>' +
                '</div>');
        });
    } else {
        $('div#files').append('<span>' + '-' + '</span>');
    }

    // init upload buttons
    var fileTable = $('#fileTable');
    fileTable.empty();
    fileTable.append('<div id="upload-0" class="input-group">' +
        '<span class="btn btn-sm btn-file">' +
        'Browse&hellip;' + '<input name="files[0]" type="file">' +
        '</span>' +
        '<span class="filename"></span>' +
        '<span data-id="0" class="remove-extra-file close">' + '    &times;' + '</span>' +
        '</div>'
    );

    // show comments
    var commentsField = $('div#comments');
    commentsField.empty();
    if (issue.hasOwnProperty('comments') && !$.isEmptyObject(issue.comments)) {
        $.each(issue.comments, function (i, comment) {
            var date = new Date(comment.date);
            var updateDate = !jQuery.isNumeric(comment.updateDate) ? '' : '(' + new Date(comment.updateDate).toLocaleDateString() + ' ' + new Date(comment.updateDate).toLocaleTimeString() + ')';
            commentsField.append('<div class="comment alert alert-dismissible" id="comment-' + comment.id + '">' +
                '<span class="comment-util">' + comment.sender.fullName + '</span>' +
                '<span style="float: right"><b>' + date.toLocaleDateString() + ' ' + date.toLocaleTimeString() + '</b> ' + updateDate + '</span>' +
                '<hr/>' +
                '<span class="comments hyphenate" data-id="' + comment.id + '">' + comment.comment + '</span>' +
                '<span class="remove-comment close" data-id="' + comment.id + '"> &times;</span><br/>' +
                '</div>');
        });
    }
    commentsField.append('<div id="text-comment">' +
        '<textarea id="comment" class="form-control" placeholder="Add comment..."></textarea>' +
        '<br/>' +
        '<button id="addComment" type="button" class="btn btn-primary btn-sm">Add comment</button>' +
        '</div>');

    $('#error').addClass('non-visible');
    Hyphenator.run();
}

function initAttachmentEvents() {
    // Add filename near browse button to see attachment name
    $('.btn-file :file').unbind('click').on('change', function () {
        var file = $(this);
        var label = file.val().replace(/\\/g, '/').replace(/.*\//, '');
        var input = $(this).parents('.input-group').find('.filename');
        input.text(label);
    });

    // Delete field for extra attachment
    $('.remove-extra-file').unbind('click').on('click', function () {
        var id = $(this).attr('data-id');
        var selector = '#upload-' + id;
        $(selector).remove();
    });

    // Delete attachment
    $('.remove-attachment').unbind('click').on('click', function () {
        var href = $(this).attr('data-href');
        $('#btn-del-att').attr('data-href', href);
        $('#modalDeleteAtt').modal('show');
    });

    $('#btn-del-att').unbind('click').on('click', function () {
        var href = $(this).attr('data-href');
        $('#modalDeleteAtt').modal('hide');
        $.ajax({
            type: "GET",
            url: href,
            success: function () {
                // refresh page
                preLoad().done(function (issue) {
                    workflow(issue);
                });
            }
        });
    });
}

function initCommentEvents() {
    // Add comment
    $('#addComment').unbind('click').on('click', function () {
        var comment = $('#comment').val();
        $.ajax({
            type: "POST",
            url: "/comment/add",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            data: JSON.stringify({
                comment: comment,
                issueByIssueId: {
                    id: issueId
                }
            }),
            success: function () {
                // refresh page
                preLoad().done(function (issue) {
                    workflow(issue);
                });
            },
            error: function (data) {
                if (data.hasOwnProperty('responseJSON') && !$.isEmptyObject(data.responseJSON)) {
                    var error = data.responseJSON;
                    var errorText = error.error;
                    $('#error').removeClass('non-visible').text(errorText);
                } else {
                    $('#error').text(data);
                }
            }
        });
    });

    //Update
    $('.comments').editable({
        type: 'text',
        url: function (params) {
            var comment = params.value;
            var commentId = $(this).attr('data-id');
            $.ajax({
                type: "POST",
                url: "/comment/update",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                data: JSON.stringify({
                    comment: comment,
                    id: commentId,
                    issueByIssueId: {
                        id: issueId
                    }
                }),
                success: function () {
                    // refresh page
                    preLoad().done(function (issue) {
                        workflow(issue);
                    });
                },
                error: function (data) {
                    if (data.hasOwnProperty('responseJSON') && !$.isEmptyObject(data.responseJSON)) {
                        var error = data.responseJSON;
                        var errorText = error.error;
                        $('#error').removeClass('non-visible').text(errorText);
                    } else {
                        $('#error').text(data);
                    }
                }
            });
        }
    });

    // Validate
    $('.comments').editable('option', 'validate', function (v) {
        if (!$.trim(v)) return 'Comment is required!';
        else if ($.trim(v).length > 500) return 'Please, shorten the name of issue. Not more than 500 symbols is possible!';
    });

    // Delete comment
    $('.remove-comment').unbind('click').on('click', function () {
        var commentId = $(this).attr('data-id');
        $('#btn-del-com').attr('data-id', commentId);
        $('#modalDeleteCom').modal('show');
    });

    $('#btn-del-com').unbind('click').on('click', function () {
        var commentId = $(this).attr('data-id');
        $('#modalDeleteCom').modal('hide');
        $.ajax({
            type: "GET",
            url: "/comment/delete/" + issueId + "/" + commentId,
            success: function () {
                // refresh page
                preLoad().done(function (issue) {
                    workflow(issue);
                });
            }
        });
    });

}

function workflow(data) {

    // For creating new upload buttons
    var uploadCounter = 0;

    $('#dropdown-issues').addClass('non-visible');
    $('#dropdown-projects').addClass('non-visible');
    $('#list-issues').removeClass('non-visible');

    renderPage(data);

    initAttachmentEvents();

    initCommentEvents();

    // Append new input for uploading
    $('#addFile').unbind('click').on('click', function () {
        var fileTable = $('#fileTable');
        uploadCounter = uploadCounter + 1;
        fileTable.append(
            '<div id="upload-' + uploadCounter + '" class="input-group">' +
            '<span class="btn btn-sm btn-file">   ' +
            '   Browse&hellip; <input name="files[' + uploadCounter + ']" type="file">' +
            '</span>' +
            '<span class="filename"></span>' +
            '<span data-id="' + uploadCounter + '" class="remove-extra-file close">   &times;</span>' +
            '</div>');
        initAttachmentEvents();
    });

    // Get all files and upload using AJAX
    $('#uploadFile').unbind('click').on('click', function () {
        var formData = new FormData();
        $('input[type=file]').each(function (i, file) {
            formData.append('files[]', file.files[0]);
        });
        $.ajax({
            type: "POST",
            url: '/file/save/' + issueId,
            cache: false,
            contentType: false,
            processData: false,
            data: formData,
            success: function () {
                // refresh page
                preLoad().done(function (issue) {
                    workflow(issue);
                });
            }, error: function (data) {
                if (data.hasOwnProperty('responseJSON') && !$.isEmptyObject(data.responseJSON)) {
                    var error = data.responseJSON;
                    var errorText = error.error;
                    $('#error').removeClass('non-visible').text(errorText);
                } else {
                    $('#error').text(data);
                }
            }
        });
    });
}

$.when(preLoad()).done(function (issue) {
    workflow(issue);
});