$(document).ready(function () {

    $('#sign_up_btn').click(function () {
        this.blur();

        var email = $.trim($('#email').val());
        var password = $.trim($('#password').val());
        var full_name = $.trim($('#full_name').val());

        if (validate(email, password, full_name)) {
            var credentials = {
                "email": email,
                "password": password,
                "fullName": full_name
            };
            $.ajax({
                type: 'POST',
                url: '/register',
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                data: JSON.stringify(credentials),
                success: function (data) {
                    $('#valid_signup').removeClass('non-visible');
                    $('#valid_signup').text(data.message);
                    $('#full_name').val('');
                    $('#email').val('');
                    $('#password').val('');
                },
                error: function (data) {
                    var error = data.responseJSON;
                    var errorText = error.error;
                    $('#invalid_signup').removeClass('non-visible');
                    $('#invalid_signup').text(errorText);
                }
            });
        }
        return false;
    });

    $('body').keypress(function (eventCode) {
        if (eventCode.keyCode == 13) {
            $('#sign_up_btn').click();
        }
    });

    function validate(email, password, fullName) {
        var error = "";
        error += Validation.validPassword(password, 6);
        error += Validation.validEmailRegistration(email);
        error += Validation.validFullNameRegistration(fullName);
        if (error) {
            $('#invalid_signup').removeClass('non-visible');
            $('#invalid_signup').text(error);
            return false;
        } else {
            $('#invalid_signup').addClass('non-visible');
            return true;
        }
    }

});


