var login = function () {

    $('#sign_in_btn').click(function () {
        this.blur();
        $('#invalid_login').empty();
        var credentials = {
            "email": $('#email').val(),
            "password": $('#password').val(),
            "remember": $('#rememeber').is(":checked")
        };
        $.ajax({
            type: 'POST',
            url: '/login',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            data: JSON.stringify(credentials),
            success: function (data) {
                window.location.href = data.redirect;
            },
            error: function (data) {
                var error = data.responseJSON;
                $('#invalid_login').text(error.error);
                $('#password').val('');
            }
        });
        return false;
    });

    $('body').keypress(function (eventCode) {
        if (eventCode.keyCode == 13) {
            $('#sign_in_btn').click();
        }
    });
};

$(document).ready(login);

