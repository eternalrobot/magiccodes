var timer = setInterval(function(){
    CheckRegisterInput();
},1500);

function CheckRegisterInput() {
    var flag=true;
    var flagemail=true;
    var flagusername=true;
    var username=$("#username").val();
    var password=$("#password").val();
    var passwordconfirm=$("#passwordconfirm").val();
    var email=$("#email").val();

    //当为空时没有提示
    if(username==""){
        $("#tipfornickname").html("");
        flag=false;
    }
    if(email==""){
        $("#tipforemail").html("");
        flag=false;
    }
    if(password==""){
        $("#tipforpassword").html("");
        flag=false;
    }
    if(passwordconfirm==""){
        $("#tipforpasswordconfirm").html("");
        flag=false;
    }

    //对昵称的检测
    if(username!=""&&(username.length>15||username.length<2)){
        $("#tipfornickname").html("昵称（名字也行）长度不要超过15，也不要短于2哦");
        username="";//让后面检测相同不会去检测
        flag=false;
        flagusername=false;
    }else if(checksIsIllegal(username)){
        $("#tipfornickname").html("不能包含非法字符，朋友");
        username="";
        flag=false;
        flagusername=false;
    }

    //对邮箱的检测
    if(email!=""&&!checksIsEmail(email)){
        $("#tipforemail").html("请输入正确的邮箱哦");
        email="";
        flag=false;
        flagemail=false;
    }

    //对用户名和邮箱的检测
    if(username!=""||email!=""){
        $.ajax({
            url:"/register/checkthesame",
            type:"POST",
            data:{
                "username":username,
                "email":email
            },
            success:function (data) {
                if(data.username==true){
                    $("#tipfornickname").html("同样的昵称已经存在了");
                    flag=false;
                }else{
                    if(flagusername==true){
                        $("#tipfornickname").html("");
                    }
                }
                if(data.email==true){
                    $("#tipforemail").html("有一样的邮箱存在");
                    flag=false;
                }
                else {
                    if(flagemail==true){
                        $("#tipforemail").html("");
                    }
                }
            }
        })
    }

    //对密码的检测
    if(password!=""&&(password.length<6||password.length>15)){
        $("#tipforpassword").html("密码长度不要小于6个也不要多于15个");
        flag=false;
        password="";//让后面的检测不同密码不会去检测
    }
    else{
        $("#tipforpassword").html("");
    }

    //对重复输入密码的检测
    if(password!=""&&passwordconfirm!=""&&password!=passwordconfirm){
        $("#tipforpasswordconfirm").html("重复写的密码要与原来的一样！");
        flag=false;
    }else{
        $("#tipforpasswordconfirm").html("");
    }

    return flag;
}

function finalsubmit() {
    if(CheckRegisterInput()!=true){
        return;
    }
    console.log("begin register");
    var username=$("#username").val();
    var password=$("#password").val();
    var email=$("#email").val();
    var token=$("#accesstoken").val();

    $.ajax({
        url:"/register/submit",
        data:{
            "username":username,
            "email":email,
            "password":password,
            "token":token
        },
        type:"POST",
        success:function (data) {
            if(data.success==true){
                var win = window;
                while (win != win.top) {
                    win = win.top;
                }
                swal({
                    title: '注册成功',
                    buttonsStyling: false,
                    confirmButtonClass: "btn btn-success",
                    timer: 10000,
                    html:
                    '点击下面按钮到<a href="/login">登录页</a>'
                }).then(function (value) {
                    win.location.href = "/login";
                });
            }else if(data.token==false){
                showNotice("top","center","token错误！","warning");
            }
        }
    })
}