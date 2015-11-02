/**
 * Created by oleg
 * Date: 01.11.15
 * Time: 15:56
 */

var preLoaded = preLoad();

function preLoad() {
    var issueId = Cookies.get('checkedIssueId');
    return $.ajax({
        type: "GET",
        url: "/issue/" + issueId
    });
}

function renderPage(issue) {
    $('span#name').text(issue.name);
    $('span#date').text(issue.date);
    $('span#lastUpdate').text(issue.lastUpdate);
    $('span#status').text(issue.status);
    $('span#priority').text(issue.priority);
    $('span#desc').text(issue.description);
    $('span#category').text(issue.category);
    $('span#creator').text(issue.userCreator);
    $('span#version').text(issue.version);
    $('span#assignee').text(issue.assignee.fullName);

    // if key exists -> show file list
    if (issue.hasOwnProperty('attachments') && !$.isEmptyObject(issue.attachments)) {
        $.each(issue.attachments, function (index, value) {
            var href = (/\/resources\/.+/gi).exec(value.attachmentPath)[0];
            var name = (/\/files\/\d+\/(.+)/gi).exec(value.attachmentPath)[1];
            $('div#files').append('<a href="' + href + '" target="_blank">' + name + '</a><br/>');
        });
    }
    $('div#comments').text(issue.comments);
}

function initUploadEvents() {
    // Add filename near browse button to see attachment name
    $('.btn-file :file').on('change', function () {
        var file = $(this);
        var label = file.val().replace(/\\/g, '/').replace(/.*\//, '');
        var input = $(this).parents('.input-group').find('.filename');
        input.text(label);
    });

    // Delete attachment
    $('.remove-extra-file').on('click', function () {
        var id = $(this).attr('data-id');
        var selector = '#upload-' + id;
        $(selector).remove();
    });
}

$.when(preLoaded).done(function (data) {

    // For creating new upload buttons
    var uploadCounter = 0;

    renderPage(data);

    initUploadEvents();

    // Append new input for uploading
    $('#addFile').click(function () {
        var fileTable = $('#fileTable');
        uploadCounter = uploadCounter + 1;
        fileTable.append(
            '<div id="upload-' + uploadCounter + '" class="input-group">' +
            '<span class="btn btn-sm btn-file">   ' +
            '   Browse&hellip; <input name="files[' + uploadCounter + ']" type="file">' +
            '</span>' +
            '<span class="filename"></span>' +
            '<span data-id="' + uploadCounter + '" class="remove-extra-file">&times;</span>' +
            '</div>');
        initUploadEvents();
    });

    // Get all files and upload using AJAX
    $('#uploadFile').on('click', function () {
        var issueId = Cookies.get('checkedId');
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
                preLoaded
            }
        });
    });
});