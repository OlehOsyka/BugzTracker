$(document).ready(function () {

    $('#sign_up_btn').click(function () {
        this.blur();

        var context_path = $('#context_path').val();
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
                url: context_path + '/register',
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
                    if (errorText.indexOf("Email") >= 0) {
                        $('#form_group_password').removeClass('has-success');
                        $('#form_group_password').addClass('has-error');
                    }
                    $('#invalid_login').removeClass('non-visible');
                    $('#invalid_login').text(errorText);
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
        error += Validation.validPassword(password);
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


