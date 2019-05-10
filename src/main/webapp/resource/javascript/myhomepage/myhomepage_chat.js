function homepageChat() {
    $("#homepagepanel").html(
        "<div class=\"row\">\n" +
        "                    <div class=\"col-md-2\">\n" +
        "                        <div class=\"card\" style=\"min-height: 500px\">\n" +
        "                            <div class=\"header\">\n" +
        "                                <h4 class=\"title text-center\">聊天列表</h4>\n" +
        "                            </div>\n" +
        "                            <div class=\"content\">\n" +
        "                                <div class=\"table-full-width table-tasks\">\n" +
        "                                    <table class=\"table\">\n" +
        "                                        <tbody id='chatfriends'>\n" +
        "                                        </tbody>\n" +
        "                                    </table>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                        <div class=\"card\">\n" +
        "                            <div class=\"content\">\n" +
        "                                <div class=\"row\">\n" +
        "                                    <div class=\"col-sm-7\">\n" +
        "                                        <input class=\"form-control\" type=\"text\" placeholder=\"Name\" id='friendname'/>\n" +
        "                                    </div>\n" +
        "                                    <div class=\"col-sm-5\">\n" +
        "                                        <button class=\"btn btn-primary btn-fill\" id='addfriendbutton'>添加</button>\n" +
        "                                    </div>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>"+
        "                    </div>\n" +
        "                    <div class=\"col-md-10\">\n" +
        "                        <div class=\"card card-chat\">\n" +
        "                            <div class=\"header\">\n" +
        "                                <h4 class=\"title\" id='chattitle'>聊天</h4>\n" +
        "                                <p class=\"category\" id='chatcategory'></p>\n" +
        "                            </div>\n" +
        "                            <div class=\"content\">\n" +
        "                                <ol class=\"chat\" id='chatcontent' style='min-height: 400px;'>\n" +
        "                                </ol>\n" +
        "                                <hr>\n" +
        "                                <div class=\"send-message\">\n" +
        "                                    <div class=\"avatar\">\n" +
        "                                        <img src=\"../assets/img/faces/avatar.png\" alt=\"crash\"/>\n" +
        "                                    </div>\n" +
        "                                    <input class=\"form-control textarea\" type=\"text\" placeholder=\"Type here!\" id='sendcontent'/>\n" +
        "                                    <div class=\"send-button\">\n" +
        "                                        <button class=\"btn btn-primary btn-fill\" id='sendbutton'>Send</button>\n" +
        "                                    </div>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>"
    );

    $("#addfriendbutton").on("click",function () {
        var name=$("#friendname").val();
        if(name!=""){
            stomp.send("/app/addfriend" ,{}, JSON.stringify({ 'message': name }));
        }
    });

    $("#sendbutton").on("click",function () {
        var sendcontent=$("#sendcontent").val();
        $("#sendcontent").val("");
        if(chataim!=""){
            stomp.send("/app/sendchat",{},JSON.stringify({'message':chataim+"|"+sendcontent}));
        }
    });

    socket=new SockJS(url);
    stomp = Stomp.over(socket);
    stomp.connect('guest', 'guest', function(frame) {
        console.log('*****  Connected  *****');
        stomp.subscribe("/user/queue/chatinfo", handleChatInfo);
        stomp.subscribe("/user/queue/chatfriend",handleChatFriend);
    });

    function handleChatFriend(message) {
        // console.log('Received: ', message);
        themessage=JSON.parse(message.body).message;
        if(themessage=="addfriend_true"){
            showNotice('top', 'center', "添加朋友成功", 'success');
        }else if(themessage=="addfriend_false"){
            showNotice('top', 'center', "添加朋友失败", 'warning');
        }else{
            var isAdd=true;
            for(var i=0;i<friends.length;i++){
                if(friends[i]==themessage){
                    isAdd=false;
                    break;
                }
            }
            if(isAdd===true){
                friends.push(themessage);
            }
        }
    }

    setInterval(function (args) {
        for(var i=friendsaim;i<friends.length;i++){
            $("#chatfriends").append("<tr>\n" +
                "<td class=\"text-center\" >" +
                "<a href='javascript:void(0);' onclick='clickfriendbutton(this.id)' id='"+friends[i]+"' class='text-warning'>"+friends[i]+"</a></td>\n" +
                "</tr>");
        }
        friendsaim=friends.length;
    },500);

    setInterval(function () {
        for(var i=chatmessagesaim;i<chatmessages.length;i++){
            var json = eval("("+chatmessages[i]+")");//将其转化为json对象
            if(json.ismereceive==0&&json.receiveuser==chataim){//我是发出者
                var isread="未读";
                if(json.isread==true){
                    isread="已读";
                }
                $("#chatcontent").append("" +
                    "                                    <li class=\"self\">\n" +
                    "                                        <div class=\"msg\">\n" +
                    "                                            <p>"+json.content+
                    "                                            </p>\n" +
                    "                                            <div class=\"card-footer\">\n" +
                    "                                                <h6>"+json.time.month+"."+json.time.date+" "+json.time.hours+":"+json.time.minutes+"</h6>\n" +
                    "                                            </div>\n" +
                    "                                        </div>\n" +
                    "                                        <div class=\"avatar\">\n" +
                    "                                            <img src=\"../assets/img/faces/avatar.png\" alt=\"crash\"/>\n" +
                    "                                        </div>\n" +
                    "                                    </li>" +
                    "");
            }else if(json.ismereceive==1&&json.senduser==chataim){//我是接收者
                var isread="未读";
                if(json.isread==true){/////////////////////已读未读交复杂 没实现
                    isread="已读";
                }
                $("#chatcontent").append(
                    "                                    <li class=\"other\">\n" +
                    "                                        <div class=\"avatar\">\n" +
                    "                                            <img src=\"../assets/img/faces/avatar.png\" alt=\"crash\"/>\n" +
                    "                                        </div>\n" +
                    "                                        <div class=\"msg\">\n" +
                    "                                            <p>\n" +json.content+
                    "                                            </p>\n" +
                    "                                            <div class=\"card-footer\">\n" +
                    "                                                <h6>"+json.time.month+"."+json.time.date+" "+json.time.hours+":"+json.time.minutes+"</h6>\n" +
                    "                                            </div>\n" +
                    "                                        </div>\n" +
                    "                                    </li>"
                )
            }
        }
        chatmessagesaim=chatmessages.length;
    },500);

    function handleChatInfo(message) {
        console.log('Received: ', message);
        themessage=JSON.parse(message.body).message;
        // var json = eval("("+themessage+")");//将其转化为json对象
        var isAdd=true;
        for(var i=0;i<chatmessages.length;i++){
            if(chatmessages[i]==themessage){
                isAdd=false;
                break;
            }
        }
        if(isAdd===true){
            chatmessages.push(themessage);
        }
    }
}

function clickfriendbutton(id) {
    chataim=id;//将聊天对象设置为id
    chatmessages=[];
    chatmessagesaim=0;
    $("#chatcontent").html("");
    $("#chatcategory").html("和"+id+"的聊天");//设置频道名
}