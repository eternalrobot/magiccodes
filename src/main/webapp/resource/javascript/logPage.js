function ClickSignIn() {
    var Username = $("#username").val();
    var Password = $("#password").val();
    var RememberMe = $("#rememberme").val();
    var tip = $("#tip");
    if (Username == "" || Password == "") {
        tip.html("User name or password cannot be empty!");
    }
    else {
        $.ajax({
            type: "POST",
            url: "/login",
            data:
            "username=" + Username +
            "&password=" + Password +
            "&remember-me=" + RememberMe
            ,
            success: function (res, status) {
                var win = window;
                while (win != win.top) {
                    win = win.top;
                }
                win.location.href = "/home";
            },
            error: function (res, status) {
                tip.html("ERROR Incorrect username or password");
            },
            complete: function () {
            }
        });
    }
}
