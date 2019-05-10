<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" >
    <title>注册</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' name='viewport' >
    <meta name="viewport" content="width=device-width" >


    <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png" >
    <link rel="icon" type="image/png" href="assets/img/favicon.png" >

    <!-- Bootstrap core CSS     -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" >

    <!--  Material Dashboard CSS    -->
    <link href="assets/css/amaze.css" rel="stylesheet" >

    <!--     Fonts and icons     -->
    <link href="assets/css/font-awesome.min.css" rel="stylesheet">
    <link href="assets/css/font-muli.css" rel='stylesheet' type='text/css'>
    <link href="assets/css/themify-icons.css" rel="stylesheet">

    <link href="assets/vendors/sweetalert/css/sweetalert2.min.css" rel="Stylesheet" >
</head>
<body>
<nav class="navbar navbar-transparent navbar-absolute">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle navbar-toggle-black" data-toggle="collapse" data-target="#navigation-example-2">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar "></span>
                <span class="icon-bar "></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="login.html" class="btn">
                        直接登陆
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="wrapper wrapper-full-page">
    <div class="register-page">
        <!--   you can change the color of the filter page using: data-color="blue | azure | green | orange | red | purple" -->
        <div class="content">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <div class="header-text">
                            <h2>世家网站</h2>
                            <h4>欢迎加入世家网站大家庭</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="col-md-4 col-md-offset-2">
                        <div class="media">
                            <div class="media-left">
                                <div class="icon icon-danger">
                                    <i class="ti ti-user"></i>
                                </div>
                            </div>
                            <div class="media-body">
                                <h5>免费账户</h5>
                                本网站对所有授权用户，免费注册，享受网站服务
                            </div>
                        </div>
                        <div class="media">
                            <div class="media-left">
                                <div class="icon icon-warning">
                                    <i class="ti-bar-chart-alt"></i>
                                </div>
                            </div>
                            <div class="media-body">
                                <h5>口令注册</h5>
                                通过口令注册网站，是本网站保护网站内用户的方式
                            </div>
                        </div>
                        <div class="media">
                            <div class="media-left">
                                <div class="icon icon-info">
                                    <i class="ti-headphone"></i>
                                </div>
                            </div>
                            <div class="media-body">
                                <h5>多种功能</h5>
                                网站提供象棋游戏，python编辑器，聊天等多种功能
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <form method="#" action="#">
                            <div class="card card-plain">
                                <div class="content">
                                    <div class="form-group">
                                        <input type="text" placeholder="您的名字" class="form-control" id="username">
                                        <span id="tipfornickname" style="color: black;"></span>
                                    </div>
                                    <div class="form-group">
                                        <input type="email" placeholder="邮箱" class="form-control" id="email">
                                        <span id="tipforemail" style="color: black;"></span>
                                    </div>
                                    <div class="form-group">
                                        <input type="password" placeholder="密码" class="form-control" id="password">
                                        <span id="tipforpassword" style="color: black;"></span>
                                    </div>
                                    <div class="form-group">
                                        <input type="password" placeholder="再写一次密码" class="form-control" id="passwordconfirm">
                                        <span id="tipforpasswordconfirm" style="color: black;"></span>
                                    </div>
                                    <div class="form-group">
                                        <input type="accesstoken" placeholder="网站的token，您注册的令牌" class="form-control" id="accesstoken">
                                        <span id="tipforaccesstoken" style="color: black;"></span>
                                    </div>
                                </div>
                                <div class="footer text-center">
                                    <button type="button" class="btn btn-fill btn-danger btn-wd" onclick="finalsubmit()">Create Free Account</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <footer class="footer footer-transparent">
            <div class="container-fluid">
                <div class="copyright text-center">
                    &copy; <script>document.write(new Date().getFullYear())</script> 世家网站</a>
                </div>
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

<!--Vue.js-->
<script src="resource/javascript/utils/vue.js"></script>

<!--This Page Sepcial JS-->
<script src="resource/javascript/registerPage.js"></script>
<script src="resource/javascript/utils/myUtils.js"></script>

</body>
</html>
