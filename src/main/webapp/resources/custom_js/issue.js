// inline mode for x-editable
$.fn.editable.defaults.mode = 'inline';
var issueId = Cookies.get('checkedIssueId');

function wrapText(txt) {
    if (!jQuery.isEmptyObject(txt)) {
        var index = 30;
        while (index < txt.length) {
            txt = [txt.slice(index - 30, index), '<br/>', txt.slice(index)].join('');
            index = index + 30;
        }
    }
    return txt;
}

function preLoad() {
    return $.ajax({
        type: "GET",
        url: "/issue/" + issueId
    });
}

function renderPage(issue) {
    $('span#name').html(wrapText(issue.name));
    $('span#date').html(wrapText(issue.date));
    $('span#lastUpdate').html(wrapText(issue.lastUpdate));
    $('span#status').html(wrapText(issue.status));
    $('span#priority').html(wrapText(issue.priority));
    $('span#desc').html(wrapText(issue.description));
    $('span#category').html(wrapText(issue.category));
    $('span#creator').html(wrapText(issue.userCreator.fullName));
    $('span#version').html(wrapText(issue.version));
    $('span#assignee').html(wrapText(issue.assignee.fullName));
    $('span#name').text(issue.name);
    $('span#date').text(issue.date);
    $('span#lastUpdate').text(issue.lastUpdate == null ? '-' : issue.lastUpdate);
    $('span#status').text(issue.status);
    $('span#priority').text(issue.priority);
    $('span#desc').text(issue.description == "" || jQuery.isEmptyObject(issue.description) ? '-' : issue.description);
    $('span#category').text(issue.category);
    $('span#creator').text(issue.userCreator.fullName);
    $('span#version').text(issue.version);
    $('span#assignee').text(issue.assignee.fullName);

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
                '<a href="' + href + '" target="_blank">' + wrapText(name) + '</a>' +
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
            commentsField.append('<div class="comment alert alert-dismissible" id="comment-' + comment.id + '">' +
                '<span class="comment-util">' + comment.sender.fullName + '</span>' +
                '<span style="float: right">' + date.toLocaleDateString() + date.toLocaleTimeString() + '</span>' +
                '<hr/>' +
                '<span class="comments" data-id="' + comment.id + '">' + wrapText(comment.comment) + '</span>' +
                '<span class="remove-comment close" data-id="' + comment.id + '"> &times;</span><br/>' +
                '</div>');
        });
    }
    commentsField.append('<div id="text-comment">' +
        '<textarea id="comment" class="form-control" placeholder="Add comment..."></textarea>' +
        '<br/>' +
        '<button id="addComment" type="button" class="btn btn-primary btn-sm">Add comment</button>' +
        '</div>');
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
            data: {
                comment: comment,
                issueId: issueId
            },
            success: function () {
                // refresh page
                preLoad().done(function (issue) {
                    workflow(issue);
                });
            }
        });
    });

    //Update
    $('.comments').editable({
        emptytext: 'Input text!',
        type: 'text',
        url: function (params) {
            var comment = params.value;
            var commentId = $(this).attr('data-id');
            $.ajax({
                type: "POST",
                url: "/comment/update",
                data: {
                    comment: comment,
                    commentId: commentId,
                    issueId: issueId
                },
                success: function () {
                    // refresh page
                    preLoad().done(function (issue) {
                        workflow(issue);
                    });
                }
            });
        },
        title: 'Enter new comment message'
    });

    // Validate
    $('.comments').editable('option', 'validate', function (v) {
        if (!v) return 'Comment is required!';
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
            }
        });
    });
}

$.when(preLoad()).done(function (issue) {
    workflow(issue);
});