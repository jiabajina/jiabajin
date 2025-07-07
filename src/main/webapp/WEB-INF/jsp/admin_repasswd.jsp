<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>密码修改</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        /* 确保html和body占满整个视口 */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }

        body {
            background: url('/static/img/school.jpg') no-repeat center center fixed;
            background-size: cover;
            display: flex;
            flex-direction: column;
        }

        /* 内容包装器 - 使用flex布局确保垂直和水平居中 */
        .content-wrapper {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        /* 主卡片样式 */
        .main-card {
            background-color: rgba(255,255,255,0.95);
            border-radius: 20px;
            box-shadow: 0 8px 30px rgba(0,0,0,0.15);
            padding: 50px 40px;
            width: 100%;
            max-width: 500px; /* 调整最大宽度 */
        }

        /* 标题样式 */
        .main-title {
            text-align: center;
            color: #337ab7;
            font-size: 2.5rem;
            font-weight: 600;
            margin-bottom: 35px;
        }

        /* 输入框样式 */
        .form-control {
            height: 54px;
            font-size: 1.2rem;
            border-radius: 12px;
            padding: 10px 15px;
            margin-bottom: 20px; /* 增加输入框间距 */
        }

        /* 按钮样式 */
        .btn-primary {
            border-radius: 25px;
            padding: 12px 45px;
            font-size: 1.2rem;
            background-color: #337ab7;
            border-color: #337ab7;
        }

        .btn-primary:hover {
            background-color: #286090;
            border-color: #204d74;
        }

        /* 提示消息样式 */
        .alert-message {
            position: fixed;
            top: 20px;
            left: 50%;
            transform: translateX(-50%);
            z-index: 9999;
            padding: 15px 20px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            display: none;
        }
    </style>
    <script>
        $(function () {
            $('#header').load('admin_header');
        })
    </script>
</head>
<body>
<!-- 顶部导航 -->
<div id="header"></div>

<!-- 主要内容区 -->
<div class="content-wrapper">
    <div class="main-card">
        <!-- 提示消息 -->
        <div style="position: relative; margin-bottom: 30px;">
            <c:if test="${!empty succ}">
                <div id="success-alert" class="alert alert-success alert-dismissable alert-message">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        ${succ}
                </div>
                <script>
                    $(function() {
                        if ($("#success-alert").text().trim() !== "") {
                            $("#success-alert").fadeIn(300);
                            setTimeout(function() {
                                $("#success-alert").fadeOut(1000);
                            }, 2000);
                        }
                    });
                </script>
            </c:if>
            <c:if test="${!empty error}">
                <div id="error-alert" class="alert alert-danger alert-dismissable alert-message">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        ${error}
                </div>
                <script>
                    $(function() {
                        if ($("#error-alert").text().trim() !== "") {
                            $("#error-alert").fadeIn(300);
                            setTimeout(function() {
                                $("#error-alert").fadeOut(1000);
                            }, 3000);
                        }
                    });
                </script>
            </c:if>
        </div>

        <!-- 密码修改表单 -->
        <div class="main-title">密码修改</div>

        <form method="post" action="admin_repasswd_do" id="repasswd">
            <input type="password" id="oldPasswd" name="oldPasswd" placeholder="输入旧密码" class="form-control">
            <input type="password" id="newPasswd" name="newPasswd" placeholder="输入新密码" class="form-control">
            <input type="password" id="reNewPasswd" name="reNewPasswd" placeholder="再次输入新密码" class="form-control">
            <em id="tishi" class="text-danger mt-2 d-block"></em>

            <div class="text-center mt-5">
                <button type="submit" class="btn btn-primary">提交</button>
            </div>
        </form>
    </div>
</div>

<script>
    // 密码校验逻辑
    $(document).keyup(function () {
        if ($("#newPasswd").val() != $("#reNewPasswd").val() &&
            $("#newPasswd").val() != "" &&
            $("#reNewPasswd").val() != "" &&
            $("#newPasswd").val().length == $("#reNewPasswd").val().length) {
            $("#tishi").text("两次输入的新密码不同，请检查");
        } else {
            $("#tishi").text("");
        }
    });

    // 表单提交校验
    $("#repasswd").submit(function () {
        if ($("#oldPasswd").val() == '' ||
            $("#newPasswd").val() == '' ||
            $("#reNewPasswd").val() == '') {
            $("#tishi").text("请填写完毕后提交");
            return false;
        } else if ($("#newPasswd").val() != $("#reNewPasswd").val()) {
            $("#tishi").text("两次输入的新密码不同，请检查");
            return false;
        }
    });
</script>
</body>
</html>