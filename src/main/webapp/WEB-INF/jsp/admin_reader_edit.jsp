<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>编辑读者信息《 ${readerInfo.readerId}》</title>
    <link rel="shortcut icon"  href="img/library.ico" />
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        .form-container {
            background-color: rgba(255, 255, 255, 0.95);
            border-radius: 8px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.15);
            padding: 30px 30px 10px 30px;
            margin-bottom: 50px;
        }
        .form-group { margin-bottom: 20px; }
        .form-control:focus {
            border-color: #337ab7;
            box-shadow: 0 0 0 0.2rem rgba(51, 122, 183, 0.25);
        }
        .btn-success { padding: 10px 30px; font-size: 16px; }
        .btn-default { padding: 10px 30px; font-size: 16px; }
        @media (max-width: 600px) {
            .form-container {padding: 10px 2px 5px 2px;}
            .btn-success, .btn-default {padding: 8px 10px; font-size: 14px;}
        }
    </style>
    <script>
        $(function () {
            $('#header').load('admin_header');
        })
    </script>
</head>
<body background="img/book.jpg" style="background-repeat:no-repeat; background-size:100% 100%; background-attachment: fixed; overflow: hidden;">
<div id="header" style="padding-bottom: 70px"></div>
<div style="display: flex; justify-content: center; align-items: flex-start; min-height: 100vh;">
    <div class="container" style="max-width: 800px; margin-top: 30px;">
        <div class="row justify-content-center">
            <div class="col-12 form-container" style="padding:18px 32px 10px 32px; margin: 0 auto;">
                <div class="text-center mb-4">
                    <h2 class="text-primary">编辑读者《${readerInfo.readerId}》信息</h2>
                </div>
                <form action="reader_edit_do?readerId=${readerInfo.readerId}" method="post" id="readeredit">
                    <div class="form-group">
                        <label for="name">姓名</label>
                        <input type="text" class="form-control" name="name" id="name" value="${readerInfo.name}">
                    </div>
                    <div class="form-group">
                        <label for="sex">性别</label>
                        <input type="text" class="form-control" name="sex" id="sex" value="${readerInfo.sex}">
                    </div>
                    <div class="form-group">
                        <label for="birth">生日</label>
                        <input type="date" class="form-control" name="birth" id="birth" value="<fmt:formatDate value='${readerInfo.birth}' pattern='yyyy-MM-dd'/>">
                    </div>
                    <div class="form-group">
                        <label for="address">地址</label>
                        <input type="text" class="form-control" name="address" id="address" value="${readerInfo.address}">
                    </div>
                    <div class="form-group">
                        <label for="phone">电话</label>
                        <input type="text" class="form-control" name="phone" id="phone" value="${readerInfo.phone}">
                    </div>
                    <div style="text-align:center;margin-top:20px;">
                        <input type="submit" value="确定" class="btn btn-success" style="margin-right:20px;">
                        <button type="button" class="btn btn-default" onclick="window.history.back();">返回上一页</button>
                    </div>
                    <script>
                        $("#readeredit").submit(function () {
                            if ($("#name").val() == '' || $("#sex").val() == '' || $("#birth").val() == '' || $("#address").val() == '' || $("#phone").val() == '') {
                                alert("请填入完整读者信息！");
                                return false;
                            }
                        })
                    </script>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>