function showNotice(topbutton,centerleftright,message,thetype) {
    type = ['','info','success','warning','danger','rose','primary'];
    var color =2;
    for(var i=0;i<type.length;i++){
        if(type[i]==thetype){
            color=i;
            break;
        }
    }
    $.notify({
        icon: "notifications",
        message: message

    },{
        type: type[color],
        timer: 3000,
        placement: {
            from: topbutton,
            align: centerleftright
        }
    });
}

function sleep(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime)
            return;
    }
}

/**验证输入框是否非法字符  是非法字符 则返回true**/
function checksIsIllegal(newName){
    var regEn = /[`!@#$%^&*()_+<>?:"{},.\/;'[\]]/im,
        regCn = /[·！#￥（——）：；“”‘、，|《。》？、【】[\]]/im;
    if(regEn.test(newName) || regCn.test(newName)) {
        return true;
    }
    return false;
}

function checksIsEmail(newEmail) {
    var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    isok= reg.test(newEmail);
    if (!isok) {
        // alert("邮箱格式不正确，请重新输入！");
        return false;
    }
    return true;
}

function sendFormData(url,formdata,type,topbutton,centerleftright,message,thetype,topbuttonfalse,centerleftrightfalse,messagefalse,thetypefalse){
    $.ajax({
        url:url,
        data:formdata,
        type:type,
        async: true,
        cache: false,
        contentType: false,
        processData: false,
        success:function (data) {
            if(data.success==true){
                showNotice(topbutton,centerleftright,message,thetype)
            }else {
                showNotice(topbuttonfalse,centerleftrightfalse,messagefalse,thetypefalse)
            }
        },
        error:function () {
            showNotice(topbuttonfalse,centerleftrightfalse,messagefalse,thetypefalse)
        }
    });
}

function jiamiRSA(minwen) {
    var publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNGInJJ/hChVSWCExya7qLOsXfxTJmVjkTmPge89BonUPH9U5Ad40kVLlTQEdSV8/xLXAt7SEIhwEzccwI+///Id5/pDPgVu862akOrCN4ynwmo/dOnD5xgzqnZPUzkcmd9vR1337YhGZsZbstvTXSg64HrFP/Y91C/RFKboHdowIDAQAB";
    var encrypt=new JSEncrypt();
    encrypt.setPublicKey(publicKey);
    return encrypt.encrypt(minwen);
}

function jiemiRSA(miwen){
    var privateKey="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAI0Yickn+EKFVJYITHJruos6xd/FMmZWOROY+B7z0GidQ8f1TkB3jSRUuVNAR1JXz/EtcC3tIQiHATNxzAj7//8h3n+kM+BW7zrZqQ6sI3jKfCaj906cPnGDOqdk9TORyZ329HXfftiEZmxluy29NdKDrgesU/9j3UL9EUpugd2jAgMBAAECgYAsVTl16wkmXZ5mRdU/XnoOpTjnRp6/aPgmIDpV/2XMeR2DBqC/wNCoSbaxYdEHHnBH528H/yoR89l8wJTLfeLBW/hE8Qwzm4DaBXuVVCc1btns88ij/k/DNRsRucK8szXeNm1tB0SNkUvVS5qDOrnnJ3M5wGdJaEqp6wWfggDhAQJBAPZhMsWtZRebv0Tn4zYGCAfQbXtlMK/JRL9XKDS0kDsneuznuzwu05TgvMo0WbuY0mYZ9S51emgqGFP0UFFGHZkCQQCSmu1Gisy0oP1kWWbwfTJs8paThBiqX0aQMWKvGl3YF+ANSVq0M1DY7aKV9aMCPuRAQk3r2tEr/CPTr6SvoMKbAkBT91Y1Vl2wiQzLb/fYTn/oIdleB2NrVT2PtCVoqOzKrC7RlDwUS6UiBOFyTBsUuwVhdJFBjxnza/00h3vj5vBpAkBq9q538X05rNw0AyagADhyVOyX5KE6bRlGy0F9EHe32nOWPhb3sxkhrQAa+YVddhWC7N19X8GNngFlyZkWquxRAkAaoXZpTlcUCDXruwJUtW1jStKmDd2BUH3LX+kU2hSqd9Hx6v4ij/X14MlCkC6YxYB8slIuBf78hJk54zJISuIB";
    var encrypt=new JSEncrypt();
    encrypt.setPrivateKey(privateKey);
    return encrypt.decrypt(miwen);
}

//调用弹出框必须的
$(function () { $("[data-toggle='popover']").popover(); });
//调用提示工具必须的
$(function () { $("[data-toggle='tooltip']").tooltip(); });
