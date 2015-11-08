/**
 * Created by oleg
 * Date: 01.11.15
 * Time: 15:56
 */

var issueId = Cookies.get('checkedIssueId');
var preLoaded = preLoad();

function preLoad() {
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
    $('span#creator').text(issue.userCreator.fullName);
    $('span#version').text(issue.version);
    $('span#assignee').text(issue.assignee.fullName);

    $('div#files').empty();
    // if key exists -> show file list
    if (issue.hasOwnProperty('attachments') && !$.isEmptyObject(issue.attachments)) {
        $.each(issue.attachments, function (index, value) {
            var name = (/\/files\/\d+\/(.+)/gi).exec(value.attachmentPath)[1];
            var href = "/file/get/" + issueId + "/" + name;
            var deleteHref = "/file/remove/" + issueId + "/" + name;
            $('div#files').append('<a href="' + href + '" target="_blank">' + name + '</a>'
                + '<span class="remove-attachment" data-href="' + deleteHref + '">&times;</span><br/>');
        });
    }
    $('div#comments').text(issue.comments);

    var fileTable = $('#fileTable');
    fileTable.empty();
    fileTable.append('<div id="upload-0" class="input-group">' +
        '<span class="btn btn-sm btn-file">' +
        'Browse&hellip;' + '<input name="files[0]" type="file">' +
        '</span>' +
        '<span class="filename"></span>' +
        '<span data-id="0" class="remove-extra-file">' + '&times;' + '</span>' +
        '</div>'
    );
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

function initDownloadEvents() {

    // Delete attachment
    $('.remove-attachment').on('click', function () {
        var href = $(this).attr('data-href');
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

function workflow(data) {

    // For creating new upload buttons
    var uploadCounter = 0;

    $('#dropdown-issues').addClass('non-visible');
    $('#dropdown-projects').addClass('non-visible');
    $('#list-issues').removeClass('non-visible');

    renderPage(data);

    initUploadEvents();

    initDownloadEvents();

    // Append new input for uploading
    $('#addFile').unbind('click').click(function () {
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