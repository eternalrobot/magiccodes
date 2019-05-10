var stomp;//设置为全局的 方便断开连接
var url="http://"+window.location.host+"/websocket";
var socket;

var friends=[];
var friendsaim=0;
var chataim="";
var chatmessages=[];
var chatmessagesaim=0;


function initHomePage() {
    closeTitleActiveAndStompAndClear();
    $("#MyHomeTitle").attr("class", "active");
    $("#mainTitle").html("主页");
    homepage();
}

initHomePage();//测试后需重新启动

function closeTitleActiveAndStompAndClear() {
    $("#MyHomeTitle").removeClass("active");
    $("#SmallGameTitle").removeClass("active");
    $("#ChessTitle").removeClass("active");
    $("#LanguageEditTitle").removeClass("active");
    $("#PythonTitle").removeClass("active");
    $("#ChatTitle").removeClass("active");
    $("#VideoTitle").removeClass("active");
    try{
        stomp.disconnect(function () {
            console.log("断开连接");
        });
    }catch (e) {
        console.log("断开连接失败");
    }
    friends=[];
    friendsaim=0;
    chataim="";
    chatmessages=[];
    chatmessagesaim=0;
}

$("#VideoTitle").on("click",function () {
    closeTitleActiveAndStompAndClear();
    $("#VideoTitle").addClass("active");
    $("#mainTitle").html("视频播放");
    homepageVideo();
});

$("#ChessTitle").on("click", function () {
    closeTitleActiveAndStompAndClear();
    $("#SmallGameTitle").addClass("active");
    $("#ChessTitle").addClass("active");
    $("#mainTitle").html("国际象棋游戏");
    homepageChess();
});

$("#ChatTitle").on("click",function () {
    closeTitleActiveAndStompAndClear();
    $("#ChatTitle").addClass("active");
    $("#mainTitle").html("聊天");
    homepageChat();
});

$("#MyHomeTitle").on("click", function () {
    initHomePage();
});

$("#PythonTitle").on("click", function () {
    closeTitleActiveAndStompAndClear();
    $("#LanguageEditTitle").attr("class", "active");
    $("#PythonTitle").attr("class", "active");
    $("#mainTitle").html("Python");
    homepagePython();
});

$("#Notifications").on("click",function () {
    closeTitleActiveAndStompAndClear();
    $("#mainTitle").html("聊天");
    homepageChat();
});

function homepage() {
    $("#homepagepanel").html("");
}