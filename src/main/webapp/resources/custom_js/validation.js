function Validation() {

}

Validation.validEmail = function (email) {
    var error = "";
    if (!email) {
        $('#form_group_email').removeClass('has-success');
        $('#form_group_email').addClass("has-error");
        error += "Email is required! ";
    } else {
        var valid_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        if (!email.match(valid_email)) {
            $('#form_group_email').removeClass('has-success');
            $('#form_group_email').addClass("has-error");
            error += "Email is invalid! ";
        } else {
            $('#form_group_email').removeClass('has-error');
            $('#form_group_email').addClass('has-success');
        }
    }
    return error;
};

Validation.validEmailRegistration = function (email) {
    var error = Validation.validEmail(email);
    if (error) {
        return error;
    } else {
      if(email.length > 50) {
          $('#form_group_email').removeClass('has-success');
          $('#form_group_email').addClass('has-error');
          error += "Please, use email 50 symbols length! ";
      } else {
          $('#form_group_email').removeClass('has-error');
          $('#form_group_email').addClass('has-success');
      }
    }
    return error;
};

Validation.validFullNameRegistration = function (fullName) {
    var error = "";
    if (!fullName) {
        $('#form_group_full_name').removeClass('has-success');
        $('#form_group_full_name').addClass('has-error');
        error += "Full name is required! ";
    } else {
        if(fullName.length > 100) {
            $('#form_group_full_name').removeClass('has-success');
            $('#form_group_full_name').addClass('has-error');
            error += "Please, shorten your full name to 100 symbols! ";
        } else {
            $('#form_group_full_name').removeClass('has-error');
            $('#form_group_full_name').addClass('has-success');
        }
    }
    return error;
};

Validation.validPassword = function (password) {
    var error = "";
    if (!password) {
        $('#form_group_password').removeClass('has-success');
        $('#form_group_password').addClass('has-error');
        error += "Password is required! ";
    } else {
        $('#form_group_password').removeClass('has-error');
        $('#form_group_password').addClass('has-success');
    }
    return error;
};



