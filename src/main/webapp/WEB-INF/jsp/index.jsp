<%@ page contentType="text/html;charset=UTF-8"  language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书馆首页</title>
    <!-- <link rel="shortcut icon"  href="/img/library.ico" /> -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <script src="js/js.cookie.js"></script>
    <style>
        body {
            background: url('/static/img/timg.jpg') no-repeat center center fixed;
            background-size: 100% 100%;
        }
        .content-wrapper {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
        }
        .main-card {
            padding: 40px 40px 30px 40px;
            background: #fff;
            border-radius: 12px;
            box-shadow: 0 4px 24px rgba(0,0,0,0.12);
            font-size: 22px;
        }
        .main-title {
            font-size: 32px;
            margin-bottom: 32px;
            text-align: center;
        }
        .form-group label {
            font-size: 20px;
        }
        .form-control {
            font-size: 20px;
            height: 48px;
            padding: 10px 16px;
        }
        .btn {
            padding: 14px 0;
            font-size: 20px;
        }
        @media (max-width: 600px) {
            .main-card {padding: 16px 2px 12px 2px; font-size: 16px;}
            .container {max-width: 98vw;}
            .main-title {font-size: 22px;}
            .form-group label, .form-control, .btn {font-size: 16px;}
        }
    </style>
</head>
<body>
<div class="content-wrapper">
    <div class="container">
        <div class="main-card">
            <div class="main-title">图书馆登录</div>
            <form id="loginform">
                <div class="form-group">
                    <label for="id">账号</label>
                    <input type="text" class="form-control" id="id" placeholder="请输入账号">
                </div>
                <div class="form-group">
                    <label for="passwd">密码</label>
                    <input type="password" class="form-control" id="passwd" placeholder="请输入密码">
                </div>
                <button id="loginButton" type="button" class="btn btn-primary btn-block">登录</button><br>
                <button id="registeredButton" type="button" class="btn btn-primary btn-block">注册</button>
            </form>
        </div>
    </div>
</div>
<script>

    $("#id").keyup(
        function () {
            if(isNaN($("#id").val())){
                $("#info").text("提示:账号只能为数字");
            }
            else {
                $("#info").text("");
            }
        }
    )
    // 登录逻辑
    $("#loginButton").click(function () {
        var id =$("#id").val();
        var passwd=$("#passwd").val();
        var remember = false; // 记住密码功能已移除
        if (id == '') {
            alert("提示:账号不能为空");
        }
        else if( passwd ==''){
            alert("提示:密码不能为空");
        }
        else if(isNaN( id )){
            alert("提示:账号必须为数字");
        }

        else {
            $.ajax({
                type: "POST",
                url: "/api/loginCheck",
                data: {
                    id:id ,
                    passwd: passwd
                },
                dataType: "json",
                success: function(data) {
                    var code = data.stateCode.trim();
                    if (code === "0") {
                        alert("提示:账号或密码错误！");
                    } else if (code === "1") {
                        window.location.href = "/admin_main";
                    } else if (code === "2") {
                        // 记住密码功能已移除
                        window.location.href = "/reader_main";
                    } else {
                        // 处理所有未覆盖的状态码，弹窗显示后端返回的msg
                        alert("���示:" + (data.msg || "未知错误"));
                    }
                }
            });
        }
    })
    $("#registeredButton").click(function () {
        window.location.href="/reader1_add";
    })

</script>
</body>
</html>
