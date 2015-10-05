function Validation() {

}

Validation.validEmail = function (email) {
    var error = "";
    if (!email) {
        $('#email').addClass('border-error');
        error += "Email is required! ";
    } else {
        var valid_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        if (!email.match(valid_email)) {
            $('#email').addClass('border-error');
            error += "Email is invalid! ";
        } else {
            $('#email').addClass('border-success');
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
          $('#email').addClass('border-error');
          error += "Please, use email 50 symbols length! ";
      } else {
          $('#email').addClass('border-success');
      }
    }
    return error;
};

Validation.validFullNameRegistration = function (fullName) {
    var error = "";
    if (!fullName) {
        $('#full_name').addClass('border-error');
        error += "Full name is required! ";
    } else {
        if(fullName.length > 100) {
            $('#full_name').addClass('border-error');
            error += "Please, shorten your full name to 100 symbols! ";
        } else {
            $('#full_name').addClass('border-success');
        }
    }
    return error;
};

Validation.validPassword = function (password) {
    var error = "";
    if (!password) {
        $('#password').addClass('border-error');
        error += "Password is required! ";
    } else {
        $('#password').addClass('border-success');
    }
    return error;
};



