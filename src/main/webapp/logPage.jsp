<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" >
    <title>登陆</title>
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
<nav class="navbar navbar-primary navbar-transparent navbar-absolute">
    <div class="container-fluid">
        <div class="navbar-header">
            <div class="navbar-header">
                <a class="navbar-brand" href="/home">魔法代码</a>
            </div>
        </div>
        <div>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        公用功能<b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/codesearch">代码搜索</a></li>
                        <li><a href="/descriptioncode">通过描述搜索</a></li>
                    </ul>
                </li>
                <li><a href="/login">登陆/注册</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="wrapper wrapper-full-page">
    <div class="full-page login-page"  data-color="green">
        <!--   you can change the color of the filter page using: data-color="blue | purple | green | orange | red | rose " -->
        <div class="content">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-sm-6 col-md-offset-4 col-sm-offset-3">
                        <form method="#" action="#">
                            <div class="card card-login card-hidden">
                                <div class="header text-center">
                                    <h3 class="title">登陆</h3>
                                </div>
                                <div class="content">
                                    <div class="social-line text-center">
                                        <h5>联系我们</h5>
                                        </br>
                                        <a href="#btn" class="btn btn-just-icon btn-default">
                                            <i class="fa fa-facebook-square"></i>
                                        </a>
                                        <a href="#pablo" class="btn btn-just-icon btn-default">
                                            <i class="fa fa-twitter"></i>
                                        </a>
                                        <a href="#eugen" class="btn btn-just-icon btn-default">
                                            <i class="fa fa-google-plus"></i>
                                        </a>
                                    </div>
                                    <span class="or-rule">或</span>
                                    <h5 class="text-center">世家网站账号</h5>
                                    <div class="form-group">
                                        <label>用户名/邮箱</label>
                                        <input type="text" placeholder="Username/Email" id="username" class="form-control input-no-border">
                                    </div>
                                    <div class="form-group">
                                        <label>密码</label>
                                        <input type="password" placeholder="Password" id="password" class="form-control input-no-border">
                                    </div>
                                    <span id="tip" style="color: black;"></span>
                                    <div class="form-group">
                                        <label>记住我</label>
                                        <input type="checkbox" id="rememberme"/>
                                    </div>
                                    <div class="form-group text-center">
                                        <button type="button" class="btn btn-rose btn-wd btn-fill" onclick="ClickSignIn()">Sign In</button>
                                    </div>
                                    <div class="form-group text-center">
                                        <p>新用户？&nbsp;&nbsp;
                                            <a href="/register">
                                                <i class="ti-id-badge"></i> 注册
                                            </a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <footer class="footer">
            <div class="container-fluid">
                <nav class="pull-left">
                    <ul>
                        <li>
                            <a href="#" id="thehomea">
                                我的主页
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

<!--Vue.js-->
<script src="resource/javascript/utils/vue.js"></script>

<!--This Page Sepcial JS-->
<script src="resource/javascript/logPage.js"></script>
<script src="resource/javascript/mainPage.js"></script>
<script src="resource/javascript/utils/myUtils.js"></script>

</body>
</html>
