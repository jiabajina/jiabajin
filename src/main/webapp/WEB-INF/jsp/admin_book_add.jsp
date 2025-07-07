<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书信息添加</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        .form-group {
            margin-bottom: 0;
        }
    </style>
    <script>
        $(function () {
            $('#header').load('admin_header');
        })
    </script>
</head>
<body background="../../static/img/book.jpg" style="background-repeat:no-repeat; background-size:100% 100%; background-attachment: fixed;">
<div id="header" style="padding-bottom: 70px"></div>
<div style="display: flex; justify-content: center; align-items: flex-start; min-height: 100vh;">
    <div class="container" style="max-width: 800px; margin-top: 30px;">
        <div class="row">
            <div class="col-12 form-container" style="background-color: rgba(255, 255, 255, 0.95); border-radius: 8px; box-shadow: 0 4px 20px rgba(0, 0, 0, 0.15); padding:18px 32px 10px 32px; margin-bottom: 50px;">
                <div class="text-center mb-4">
                    <h2 class="text-primary">添加图书信息</h2>
                </div>
                <form action="book_add_do" method="post" id="addbook">
                    <div class="form-group">
                        <label for="name">书名</label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="请输入书名">
                    </div>
                    <div class="form-group">
                        <label for="author">作者</label>
                        <input type="text" class="form-control" name="author" id="author" placeholder="请输入作者">
                    </div>
                    <div class="form-group">
                        <label for="publish">出版社</label>
                        <input type="text" class="form-control" name="publish" id="publish" placeholder="请输入出版社">
                    </div>
                    <div class="form-group">
                        <label for="isbn">ISBN</label>
                        <input type="text" class="form-control" name="isbn" id="isbn" placeholder="请输入ISBN">
                    </div>
                    <div class="form-group" style="margin-bottom:10px;">
                        <label for="introduction">简介</label>
                        <textarea class="form-control" rows="4" style="resize:none;width:100%;min-width:300px;max-width:100%;height:80px;min-height:80px;max-height:80px;" name="introduction" id="introduction"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="language">语言</label>
                        <input type="text" class="form-control" name="language" id="language" placeholder="请输入语言">
                    </div>
                    <div class="form-group">
                        <label for="price">价格</label>
                        <input type="text" class="form-control" name="price" id="price" placeholder="请输入价格">
                    </div>
                    <div class="form-group">
                        <label for="pubstr">出版日期</label>
                        <input type="date" class="form-control" name="pubstr" id="pubstr" placeholder="请输入出版日期">
                    </div>
                    <div class="form-group">
                        <label for="classId">分类号</label>
                        <select name="classId" id="classId" class="form-control" required>
                            <option value="">--请选择分类--</option>
                            <c:if test="${empty classList}">
                                <option value="" disabled>暂无分类数据，请先添加分类</option>
                            </c:if>
                            <c:forEach items="${classList}" var="cls">
                                <option value="${cls.classId}">${cls.classId}（${cls.className}）</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="number">数量</label>
                        <input type="text" class="form-control" name="number" id="number" placeholder="请输入图书数量">
                    </div>
                    <div style="text-align:center;margin-top:20px;">
                        <input type="submit" value="添加" class="btn btn-success btn-sm" style="margin-right:20px;">
                        <button type="button" class="btn btn-default" onclick="window.history.back();">返回上一页</button>
                    </div>
                    <script>
                        $("#addbook").submit(function () {
                            if ($("#name").val() == '' || $("#author").val() == '' || $("#publish").val() == '' || $("#isbn").val() == '' || $("#introduction").val() == '' || $("#language").val() == '' || $("#price").val() == '' || $("#pubstr").val() == '' || $("#classId").val() == '' || $("#number").val() == '') {
                                alert("请填入完整图书信息！");
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
