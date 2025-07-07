<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<html>
<head>
    <title>添加读者</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        .form-container {
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 8px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.15);
            padding: 30px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-control:focus {
            border-color: #337ab7;
            box-shadow: 0 0 0 0.2rem rgba(51, 122, 183, 0.25);
        }
        .btn-success {
            padding: 10px 30px;
            font-size: 16px;
        }
    </style>
</head>
<body background="img/school.jpg" style="background-repeat: no-repeat; background-size: 100% 100%; background-attachment: fixed;">

<div id="header"></div>
<div style="display: flex; justify-content: center; align-items: flex-start; min-height: 100vh;">
    <div class="container" style="max-width: 500px; margin-top: 60px;">
        <div class="row">
            <div class="col-12 form-container">
                <div class="text-center mb-4">
                    <h2 class="text-primary">添加读者</h2>
                    <p class="text-muted">请填写以下信息完成读者注册</p>
                </div>

                <form action="reader_add_do1" method="post" id="readeredit">
                    <div class="form-group">
                        <label for="password">密码</label>
                        <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
                    </div>

                    <div class="form-group">
                        <label for="name">姓名</label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="请输入姓名">
                    </div>

                    <div class="form-group">
                        <label for="sex">性别</label>
                        <select class="form-control" name="sex" id="sex">
                            <option value="">请选择性别</option>
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="birth">生日</label>
                        <input type="date" class="form-control" name="birth" id="birth">
                    </div>

                    <div class="form-group">
                        <label for="address">地址</label>
                        <input type="text" class="form-control" name="address" id="address" placeholder="请输入地址">
                    </div>

                    <div class="form-group">
                        <label for="phone">电话</label>
                        <input type="text" class="form-control" name="phone" id="phone" placeholder="请输入电话号码">
                    </div>

                    <div class="text-center mt-5">
                        <button type="submit" class="btn btn-success mr-3" style="min-width:100px;">添加</button>
                        <button type="button" class="btn btn-success" style="min-width:100px;" onclick="window.location.href='/'">返回首页</button>
                    </div>
                    <div class="text-center mt-3">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $("#readeredit").submit(function() {
        if ($("#password").val() === '' || $("#name").val() === '' || $("#sex").val() === '' ||
            $("#birth").val() === '' || $("#address").val() === '' || $("#phone").val() === '') {
            alert("请填入完整读者信息！");
            return false;
        }
    });
</script>
</body>
</html>
