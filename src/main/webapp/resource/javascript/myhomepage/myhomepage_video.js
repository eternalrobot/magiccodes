// var player = videojs('homepagevideo',{
//     muted: true,
//     controls : true/false,
//     height:"500px",
//     width:"auto",
//     loop : false
// });
//
function homepageVideo() {
    $("#homepagepanel").html(
        "                <div class=\"row\">\n" +
        "                    <div class=\"col-md-6 col-md-push-3\">\n" +
        "                        <fieldset>\n" +
        "                            <div class=\"form-group\">\n" +
        "                                <div>\n" +
        "                                    <input type=\"text\" placeholder=\"请搜索你想看的电影\" id=\"Input\" class=\"form-control\">\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </fieldset>\n" +
        "                        <button class='btn' id='searchmoviebutton' style='display:none;'>搜索</button>"+
        "                    </div>\n" +
        "                </div>"+
        "                <div class=\"row\" id='movielistdiv' style='display: none'>\n" +
        "                    <div class=\"col-md-12\">\n" +
        "                        <div class=\"card\">\n" +
        "                            <div class=\"content\">\n" +
        "                                <h4 class=\"title\">电影列表</h4>\n" +
        "                                <div class=\"table-responsive\">\n" +
        "                                    <table class=\"table table-hover\">\n" +
        "                                        <thead class=\"text-primary\">\n" +
        "                                        <tr>\n" +
        "                                            <th>电影名</th>\n" +
        "                                            <th>国家</th>\n" +
        "                                            <th>评分</th>\n" +
        "                                            <th>播放量</th>\n" +
        "                                        </tr>\n" +
        "                                        </thead>\n" +
        "                                        <tbody id='moviedatabody'>\n" +
        "                                        </tbody>\n" +
        "                                    </table>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>"+
        "                <div class=\"row\" id='videodiv' style='display:none'>\n" +
        "                    <div class=\"col-md-12\">\n" +
        "                        <div class=\"card\" style=\"height: 500px\" id='videocard'>\n" +
        "                            <div class=\"content\">\n" +
        "                                <video id=\"homepagevideo\" controls=\"true\" style='width= 100%; height=100%; object-fit: fill'></video>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>"
    );

    setInterval(function () {
        if($("#Input").val()!=""){
            $("#searchmoviebutton").css('display','block');
        }else{
            $("#searchmoviebutton").css('display','none');
        }
    },2000);

    $("#searchmoviebutton").on("click",function () {
        var moviename=$("#Input").val();
        $.ajax({
            url:"/utils/getmoviename",
            type:"POST",
            data:{
                "moviename":moviename
            },
            success:function (data) {
                if(data.success==true){
                    var result=JSON.parse(data.result);
                    $("#movielistdiv").css('display','block');//点击搜索的时候 成功时 电影列表显示
                    $("#videodiv").css('display','none');//点击搜索的时候 成功时 电影屏幕关闭
                    $("#moviedatabody").html("");
                    for(var i=0;i<result.length;i++){
                        $("#moviedatabody").append(
                            "<tr>\n" +
                            "    <td>"+result[i].name+"</td>\n" +
                            "    <td>"+result[i].thecountry+"</td>\n" +
                            "    <td>"+result[i].lovescore+"</td>\n" +
                            "    <td class='text-primary'>"+result[i].playnumber+"</td>\n" +
                            "    <td style='display: none'>"+result[i].filelocation+"</td>"+
                            "</tr>"
                        );
                    }
                    $("#moviedatabody tr").on("click",function () {//对每一行做事件
                        var td = $( this ).find( "td" );//获得这一行的对象
                        swal({
                            title: '你想观看'+td[0].innerText+"吗？",
                            type: 'info',
                            showCancelButton: true,
                            confirmButtonClass: 'btn btn-success',
                            cancelButtonClass: 'btn btn-info',
                            confirmButtonText: '播放',
                            cancelButtonText:'取消',
                            buttonsStyling: false
                        }).then(function() {
                            $("#homepagevideo").attr("src","/videos/"+td[4].innerHTML);
                            $("#movielistdiv").css('display','none');//开始播放时 电影列表关闭
                            swal({
                                title: '点击下面开始播放',
                                type: 'success',
                                confirmButtonClass: "btn btn-success",
                                buttonsStyling: false
                            }).then(function (value) {
                                $("#videodiv").css('display','block');//开始播放时 电影屏幕打开
                            });
                        },function(dismiss){});
                    });
                    showNotice("top","center","您的电影找到了","success");
                }else{
                    showNotice("top","center","没有找到您需要的电影","warning");
                }
            }
        })
    });

    setInterval(function () {
        $("#videocard").height($("#homepagevideo").height()+25);
        $("#videocard").width($("#homepagevideo").width()+25);
    },500);

}