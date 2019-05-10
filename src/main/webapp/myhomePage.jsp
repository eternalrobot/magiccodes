<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!doctype html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>我的主页</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' name='viewport'>
    <meta name="viewport" content="width=device-width">


    <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="assets/img/favicon.png">

    <!-- Bootstrap core CSS     -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">

    <!--  Material Dashboard CSS    -->
    <link href="assets/css/amaze.css" rel="stylesheet">

    <!--     Fonts and icons     -->
    <link href="assets/css/font-awesome.min.css" rel="stylesheet">
    <link href="assets/css/font-muli.css" rel='stylesheet' type='text/css'>
    <link href="assets/css/themify-icons.css" rel="stylesheet">

    <link href="assets/vendors/sweetalert/css/sweetalert2.min.css" rel="Stylesheet">

    <!--chessboard css-->
    <link href="resource/chessboardjs-0.3.0/css/chessboard-0.3.0.min.css" rel="Stylesheet">

    <!--播放器-->
    <link href="resource/css/video-js.min.css" rel="stylesheet">
</head>
<body>
<div class="wrapper">
    <div class="sidebar" data-background-color="white" data-active-color="danger">
        <div class="logo">
            <a href="#" class="simple-text">
                ${username}
            </a>
        </div>
        <div class="logo logo-mini">
            <a href="#" class="simple-text">
                ${ufc}
            </a>
        </div>
        <div class="sidebar-wrapper">
            <ul class="nav">
                <li id="MyHomeTitle">
                    <a href="#">
                        <i class="ti-panel"></i>
                        <p>主页</p>
                    </a>
                </li>
                <li id="ChatTitle">
                    <a href="#">
                        <i class="ti-bell"></i>
                        <p>聊天</p>
                    </a>
                </li>
                <li id="VideoTitle">
                    <a href="#">
                        <i class="ti-video-clapper"></i>
                        <p>电影</p>
                    </a>
                </li>
                <li id="SmallGameTitle">
                    <a data-toggle="collapse" href="#SmallGamePanel" class="collapsed" aria-expanded="false">
                        <i class="ti-bar-chart-alt"></i>
                        <p>小游戏
                            <b class="caret"></b>
                        </p>
                    </a>
                    <div class="collapse" id="SmallGamePanel" role="navigation" aria-expanded="false"
                         style="height: 0px;">
                        <ul class="nav">
                            <li id="ChessTitle">
                                <a href="#">国际象棋</a>
                            </li>
                        </ul>
                    </div>
                </li>
                <li id="LanguageEditTitle">
                    <a data-toggle="collapse" href="#LanguageEditPanel" class="collapsed" aria-expanded="false">
                        <i class="ti-pencil"></i>
                        <p>高级语言编辑器
                            <b class="caret"></b>
                        </p>
                    </a>
                    <div class="collapse" id="LanguageEditPanel" role="navigation" aria-expanded="false"
                         style="height: 0px;">
                        <ul class="nav">
                            <li id="PythonTitle">
                                <a href="#">Python</a>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="main-panel">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-minimize">
                    <button id="minimizeSidebar" class="btn btn-round btn-white btn-fill btn-just-icon">
                        <i class="ti-arrow-left"></i>
                    </button>
                </div>
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#" id="mainTitle"> Home </a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="Notifications">
                                <i class="ti-bell"></i>
                                <span class="notification" style="display: none"></span>
                                <p class="hidden-lg hidden-md">Notifications</p>
                            </a>
                        </li>
                        <li>
                            <a href="#pablo" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="ti-layout-grid3-alt"></i>
                                <p class="hidden-lg hidden-md">Apps</p>
                            </a>
                        </li>
                        <li>
                            <a href="#pablo" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="ti-user"></i>
                                <p class="hidden-lg hidden-md">Profile</p>
                            </a>
                        </li>
                        <li>
                            <a href="#pablo" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="ti-settings"></i>
                                <p class="hidden-lg hidden-md">Settings</p>
                            </a>
                        </li>
                        <li class="separator hidden-lg hidden-md"></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="content">
            <div class="container-fluid" id="homepagepanel">
            </div>
        </div>
        <footer class="footer">
            <div class="container-fluid">
                <nav class="pull-left">
                    <ul>
                        <li>
                            <a href="/home">
                                网站主页
                            </a>
                        </li>
                    </ul>
                </nav>
                <p class="copyright pull-right">
                    &copy;魔法代码
                    <script>
                        document.write(new Date().getFullYear())
                    </script>
                </p>
            </div>
        </footer>
    </div>
</div>

<!--   Core JS Files   -->
<script src="assets/vendors/jquery-3.1.1.min.js" type="text/javascript"></script>
<script src="assets/vendors/jquery-ui.min.js" type="text/javascript"></script>
<script src="assets/vendors/bootstrap.min.js" type="text/javascript"></script>
<script src="assets/vendors/material.min.js" type="text/javascript"></script>
<script src="assets/vendors/perfect-scrollbar.jquery.min.js" type="text/javascript"></script>
<!-- Forms Validations Plugin -->
<script src="assets/vendors/jquery.validate.min.js"></script>
<!--  Plugin for Date Time Picker and Full Calendar Plugin-->
<script src="assets/vendors/moment.min.js"></script>
<!--  Charts Plugin -->
<script src="assets/vendors/charts/flot/jquery.flot.js"></script>
<script src="assets/vendors/charts/flot/jquery.flot.resize.js"></script>
<script src="assets/vendors/charts/flot/jquery.flot.pie.js"></script>
<script src="assets/vendors/charts/flot/jquery.flot.stack.js"></script>
<script src="assets/vendors/charts/flot/jquery.flot.categories.js"></script>
<script src="assets/vendors/charts/chartjs/Chart.min.js" type="text/javascript"></script>

<!--  Plugin for the Wizard -->
<script src="assets/vendors/jquery.bootstrap-wizard.js"></script>
<!--  Notifications Plugin    -->
<script src="assets/vendors/bootstrap-notify.js"></script>
<!-- DateTimePicker Plugin -->
<script src="assets/vendors/bootstrap-datetimepicker.js"></script>
<!-- Vector Map plugin -->
<script src="assets/vendors/jquery-jvectormap.js"></script>
<!-- Sliders Plugin -->
<script src="assets/vendors/nouislider.min.js"></script>
<!--  Google Maps Plugin    -->
<script src="http://ditu.google.cn/maps/api/js?key=AIzaSyAurmSUEQDwY86-wOG3kCp855tCI8lHL-I"></script>
<!-- Select Plugin -->
<script src="assets/vendors/jquery.select-bootstrap.js"></script>

<!--  Checkbox, Radio, Switch and Tags Input Plugins -->
<script src="assets/js/bootstrap-checkbox-radio-switch-tags.js"></script>

<!-- Circle Percentage-chart -->
<script src="assets/js/jquery.easypiechart.min.js"></script>

<!--  DataTables.net Plugin    -->
<script src="assets/vendors/jquery.datatables.js"></script>
<!-- Sweet Alert 2 plugin -->
<script src="assets/vendors/sweetalert/js/sweetalert2.min.js"></script>
<!--	Plugin for Fileupload, full documentation here: http://www.jasny.net/bootstrap/javascript/#fileinput -->
<script src="assets/vendors/jasny-bootstrap.min.js"></script>
<!--  Full Calendar Plugin    -->
<script src="assets/vendors/fullcalendar.min.js"></script>
<!-- TagsInput Plugin -->
<script src="assets/vendors/jquery.tagsinput.js"></script>
<!-- Material Dashboard javascript methods -->
<script src="assets/js/amaze.js"></script>

<script src="assets/js/charts/flot-charts.js"></script>
<script src="assets/js/charts/chartjs-charts.js"></script>

<!--播放器-->
<script src="resource/javascript/utils/video.min.js"></script>

<!--chessboard js-->
<script src="resource/chessboardjs-0.3.0/js/chessboard-0.3.0.js"></script>
<!--chess js-->
<script src="resource/javascript/utils/chess.js"></script>

<!--使用websocket及stomp的需要的包-->
<script src="resource/javascript/utils/sockjs.min.js"></script>
<script src="resource/javascript/utils/stomp.min.js"></script>

<!--Vue.js-->
<script src="resource/javascript/utils/vue.js"></script>


<!--This Page Sepcial JS-->
<script src="resource/javascript/utils/myUtils.js"></script>
<script src="resource/javascript/myhomepage/myhomepage_chess.js"></script>
<script src="resource/javascript/myhomepage/myhomepage_python.js"></script>
<script src="resource/javascript/myhomepage/myhomepage_chat.js"></script>
<script src="resource/javascript/myhomepage/myhomepage_video.js"></script>
<script src="resource/javascript/myhomepage/myhomePage.js"></script>

</body>
</html>