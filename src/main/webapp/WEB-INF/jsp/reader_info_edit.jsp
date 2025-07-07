<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        }

        .form-container {
            background-color: rgba(255, 255, 255, 0.95);
            border-radius: 8px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.15);
            padding: 30px;
            margin: auto;
            width: 98%;
            max-width: 600px;
            min-width: 350px;
        }

        .table th, .table td {
            vertical-align: middle!important;
        }

        .btn-default {
            padding: 8px 20px;
            font-size: 14px;
        }

        .input-group {
            margin-bottom: 15px;
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

<div class="form-container">
    <div class="text-center mb-3">
        <h2 class="text-primary">信息修改</h2>
    </div>

    <form action="reader_edit_do_r" method="post" id="edit">
        <div class="input-group">
            <span class="input-group-addon">读者证号</span>
            <input type="text" readonly="readonly" class="form-control" name="readerId" id="readerId" value="${readerinfo.readerId}">
        </div>
        <div class="input-group">
            <span class="input-group-addon">姓名</span>
            <input type="text" class="form-control" name="name" id="name" value="${readerinfo.name}" >
        </div>
        <div class="input-group">
            <span class="input-group-addon">性别</span>
            <input type="text" class="form-control" name="sex" id="sex"  value="${readerinfo.sex}" >
        </div>
        <div class="input-group">
            <span class="input-group-addon">生日</span>
            <input type="text" class="form-control" name="birth" id="birth"  value="<fmt:formatDate value="${readerinfo.birth}" pattern="yyyy年MM月dd日"/>">
        </div>
        <div class="input-group">
            <span class="input-group-addon">地址</span>
            <input type="text" class="form-control" name="address" id="address"  value="${readerinfo.address}" >
        </div>
        <div class="input-group">
            <span class="input-group-addon">电话</span>
            <input type="text" class="form-control" name="phone" id="phone"  value="${readerinfo.phone}" >
        </div>
        <br/>
        <div class="text-center">
            <input type="submit" value="确定" class="btn btn-default">
            <a href="reader_info" class="btn btn-default" style="margin-left: 15px;">返回</a>
        </div>
    </form>

    <script>
        $(function() {
            $('#edit').submit(function() {
                if ($('#name').val() === '' || $('#sex').val() === '' || $('#birth').val() === '' || $('#address').val() === '' || $('#phone').val() === '') {
                    alert('请填入完整信息！');
                    return false;
                }
            });
        });
    </script>
</div>
</body>
</html>
