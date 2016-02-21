$(document).ready(function () {

    $('#sign_in_btn').click(function () {
        this.blur();

        var email = $.trim($('#email').val());
        var password = $.trim($('#password').val());

        if (validate(email, password)) {
            var credentials = {
                "email": email,
                "password": password,
                "fullName": "default",
                "isRemember": $('#remember').is(":checked")
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
                    var errorText = error.error;
                    $('#password').val('');
                    $('#invalid_login').removeClass('non-visible').text(errorText);
                }
            });
        }
        return false;
    });

    $('body').keypress(function (eventCode) {
        if (eventCode.keyCode == 13) {
            $('#sign_in_btn').click();
        }
    });

    function validate(email, password) {
        var error = "";
        error += Validation.validEmail(email);
        error += Validation.validPassword(password);
        if (error) {
            $('#invalid_login').removeClass('non-visible').text(error);
            return false;
        } else {
            $('#invalid_login').addClass('non-visible');
            return true;
        }
    }

});


