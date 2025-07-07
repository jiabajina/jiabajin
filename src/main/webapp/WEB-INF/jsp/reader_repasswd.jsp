<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${readercard.name}的主页</title>
    <link rel="shortcut icon" href="img/library.ico" />
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        body {
            background: url('../../static/img/book.jpg') no-repeat center center;
            background-size: cover;
            background-attachment: fixed;
            overflow-y: auto;
            margin: 0;
            padding: 0;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            font-size: 1.1em;
        }

        .form-container {
            background-color: rgba(255, 255, 255, 0.95);
            border-radius: 8px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.15);
            padding: 40px;
            margin: auto;
            width: 98%;
            max-width: 800px;
            min-width: 350px;
        }

        .main-title {
            text-align: center;
            color: #337ab7;
            margin-bottom: 30px;
            font-size: 28px;
        }

        .input-group {
            margin-bottom: 20px;
        }

        .input-group-addon {
            font-size: 16px;
            padding: 12px 15px;
        }

        .form-control {
            font-size: 16px;
            padding: 12px 15px;
            height: auto;
        }

        .btn-default {
            padding: 10px 24px;
            font-size: 16px;
            margin-top: 10px;
        }

        .alert-container {
            position: fixed;
            top: 20px;
            left: 50%;
            transform: translateX(-50%);
            z-index: 1050;
            width: 90%;
            max-width: 500px;
        }

        @media (max-width: 600px) {
            .form-container {
                padding: 15px;
            }
        }
    </style>
    <script>
        $(function () {
            $('#header').load('reader_header');
        })
    </script>
</head>
<body>
<div id="header" style="padding-bottom: 20px;"></div>

<div class="alert-container">
    <c:if test="${!empty succ}">
        <div class="alert alert-success alert-dismissable" id="success-alert">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            ${succ}
        </div>
        <script type="text/javascript">
            setTimeout(function(){
                $("#success-alert").alert('close');
            }, 2000);
        </script>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger alert-dismissable">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            ${error}
        </div>
    </c:if>
</div>

<div class="form-container">
    <div class="main-title">密码修改</div>
    <form method="post" action="reader_repasswd_do" id="repasswd">
        <div class="input-group">
            <span class="input-group-addon">旧密码</span>
            <input type="password" id="oldPasswd" name="oldPasswd" placeholder="输入旧密码" class="form-control">
        </div>
        <div class="input-group">
            <span class="input-group-addon">新密码</span>
            <input type="password" id="newPasswd" name="newPasswd" placeholder="输入新密码" class="form-control">
        </div>
        <div class="input-group">
            <span class="input-group-addon">确认新密码</span>
            <input type="password" id="reNewPasswd" name="reNewPasswd" placeholder="再次输入新密码" class="form-control">
        </div>
        <em id="tishi" style="color: red"></em>
        <div class="text-center" style="margin-top: 20px;">
            <input type="submit" value="提交" class="btn btn-default">
        </div>
    </form>
</div>

<script>
    $(document).keyup(function () {
        if ($("#newPasswd").val() != $("#reNewPasswd").val() && $("#newPasswd").val() != "" && $("#reNewPasswd").val() != "" && $("#newPasswd").val().length == $("#reNewPasswd").val().length) {
            $("#tishi").text("提示:两次输入的新密码不同，请检查!");
        } else {
            $("#tishi").text("");
        }
    });

    $("#repasswd").submit(function () {
        if ($("#oldPasswd").val() == '' || $("#newPasswd").val() == '' || $("#reNewPasswd").val() == '') {
            $("#tishi").text("提示:请填写完整!");
            return false;
        } else if ($("#newPasswd").val() != $("#reNewPasswd").val()) {
            $("#tishi").text("提示:两次输入的新密码不同，请检查!");
            return false;
        }
    });
</script>
</body>
</html>
