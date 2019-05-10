function ModifyLogoruserLabel() {
    $.ajax({
        url:"/home/user/detials",
        type:"GET",
        success:function (data) {
            if(data.success==true){
                var jsonData = JSON.parse(data.list);
                console.log(jsonData);
                console.log(jsonData[0].username);
                $("#logoruser").html("<a href=\"/myhomepage\">欢迎 "+jsonData[0].username+"</a>");
                $("#logout").css("display","block");
                $("#thehomea").attr("href","/myhomepage")
            }
            else{
                $("#logoruser").html("<a href=\"/login\">log/register</a>");
            }
        }
    })
}

ModifyLogoruserLabel();