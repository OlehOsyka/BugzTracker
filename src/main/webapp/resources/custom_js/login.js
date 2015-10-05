$(document).ready(function () {

    $('#sign_in_btn').click(function () {
        this.blur();

        var context_path = $('#context_path').val();
        var email = $('#email').val();
        var password = $('#password').val();

        if (validate(email, password)) {
            var credentials = {
                "email": email,
                "password": password,
                "remember": $('#rememeber').is(":checked")
            };
            $.ajax({
                type: 'POST',
                url: context_path + '/login',
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
                    $('#invalid_login').removeClass('non-visible');
                    $('#invalid_login').text(error.error);
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
            $('#invalid_login').removeClass('non-visible');
            $('#invalid_login').text(error);
            return false;
        } else {
            $('#invalid_login').addClass('non-visible');
            return true;
        }
    }

});


