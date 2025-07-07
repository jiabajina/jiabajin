<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>${readercard.name}的主页</title>

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
    <script type="text/javascript">
        setTimeout(function(){
            $("#success-alert").alert('close');
        }, 2000);
    </script>
</head>
<body>
<div id="header" style="padding-bottom: 20px;"></div>

<c:if test="${!empty succ}">
    <div class="alert-container">
        <div class="alert alert-success alert-dismissable" id="success-alert">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            ${succ}
        </div>
    </div>
</c:if>
<c:if test="${!empty error}">
    <div class="alert-container">
        <div class="alert alert-danger alert-dismissable">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            ${error}
        </div>
    </div>
</c:if>

<div class="form-container">
    <div class="text-center mb-3">
        <h2 class="text-primary">我的信息</h2>
    </div>

    <table class="table table-hover">
        <tbody>
        <tr>
            <th width="20%">读者证号</th>
            <td>${readerinfo.readerId}</td>
        </tr>
        <tr>
            <th>姓名</th>
            <td>${readerinfo.name}</td>
        </tr>
        <tr>
            <th>性别</th>
            <td>${readerinfo.sex}</td>
        </tr>
        <tr>
            <th>生日</th>
            <td><fmt:formatDate value="${readerinfo.birth}" pattern="yyyy年MM月dd日"/></td>
        </tr>
        <tr>
            <th>地址</th>
            <td>${readerinfo.address}</td>
        </tr>
        <tr>
            <th>电话</th>
            <td>${readerinfo.phone}</td>
        </tr>
        </tbody>
    </table>

    <div class="text-center mt-4">
        <a class="btn btn-default" href="reader_info_edit" role="button">修改</a>
    </div>
</div>
</body>
</html>
