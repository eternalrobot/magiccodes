function homepagePython() {
    $("#homepagepanel").html("<div class=\"row\">\n" +
        "                    <div class=\"card col-sm-12\" style=\"height: auto\">\n" +
        "                        <div class=\"content\">\n" +
        "                            <h4 class=\"title\">Python编辑器</h4>\n" +
        "                            <div class=\"row\">\n" +
        "                                <div class=\"col-sm-6\">\n" +
        "                                    <fieldset>\n" +
        "                                        <div class=\"col-sm-12\">\n" +
        "                                            <textarea class=\"form-control\" spellcheck='false' placeholder=\"请在这里输入您的代码\" rows=\"30\" id=\"pythontextareainput\"></textarea>\n" +
        "                                        </div>\n" +
        "                                    </fieldset>\n" +
        "                                </div>\n" +
        "                                <div class=\"col-sm-6\">\n" +
        "                                    <fieldset>\n" +
        "                                        <div class=\"col-sm-12\">\n" +
        "                                            <textarea class=\"form-control\" spellcheck='false' placeholder=\"这儿是您的输出\" rows=\"30\" id=\"pythontextareaoutput\"></textarea>\n" +
        "                                        </div>\n" +
        "                                    </fieldset>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                        <div class=\"footer\">\n" +
        "                            <div class=\"row\">\n" +
        "                                <div class=\"col-sm-3 col-sm-push-9\">\n" +
        "                                    <button class=\"btn btn-primary\" id=\"savepythoninput\">保存</button>\n" +
        "                                    <button class=\"btn btn-primary\" id=\"goonpython\">运行</button>\n" +
        "                                    <button class='btn btn-primary' id='stoppython'>停止运行</button>\n"+
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                        <br>\n" +
        "                    </div>\n" +
        "                </div>");
    $.ajax({
        url: "/utils/getpythondata",
        type: "GET",
        success: function (data) {
            if (data.success == true) {
                $("#pythontextareainput").val(data.pythondata);
            } else {
            }
        },
        error: function () {
            showNotice('top', 'center', "加载代码失败", 'warning')
        }
    });

    $("#pythontextareainput").keydown(function (e) {
        if (e.which == 9) {// 判断所按是否tab
            $("#pythontextareainput").val($("#pythontextareainput").val() + "\t");//是按下tab则在后面加一个/t
            return false;// 取消默认的行为
        }
    });

    $("#savepythoninput").on('click', function () {
        savedata = $("#pythontextareainput").val();
        var formData = new FormData();
        formData.append("savedata", savedata);
        sendFormData("/utils/savepythondata", formData, "POST", 'top', 'center', "保存成功", 'success',
            'top', 'center', "保存失败", 'warning');
    });

    $("#goonpython").on("click", function () {
        $("#goonpython").attr('disabled', true);
        $("#pythontextareaoutput").val("");
        stomp.send("/app/pythongoon", {}, JSON.stringify({ 'message': 'python go on' }));
    });

    $("#stoppython").on("click",function () {
        $("#stoppython").attr('disabled', true);
        stomp.send("/app/pythonstop", {}, JSON.stringify({ 'message': 'python stop' }));
    });

    socket=new SockJS(url);
    stomp = Stomp.over(socket);
    stomp.connect('guest', 'guest', function(frame) {
        console.log('*****  Connected  *****');
        stomp.subscribe("/user/queue/pythonoutinfo", handlePythonOutInfo);
    });
    function handlePythonOutInfo(message) {
        console.log('Received: ', message);
        themessage=JSON.parse(message.body).message;
        if(themessage=="@GoOn@"){
            showNotice('top', 'center', "代码开始运行", 'info')
        }else if(themessage=="@Complete@"){
            showNotice('top', 'center', "代码运行结束", 'success');
            $("#goonpython").attr('disabled', false);
        }else if(themessage=="@StopSuccess@"){
            showNotice('top', 'center', "停止代码运行成功", 'success');
            $("#stoppython").attr('disabled', false);
        }else if(themessage=="@StopFail@"){
            showNotice('top', 'center', "代码没有在运行", 'warning');
            $("#stoppython").attr('disabled', false);
        } else{
            $("#pythontextareaoutput").val($("#pythontextareaoutput").val()+"\n"+themessage);
        }
    }
}
