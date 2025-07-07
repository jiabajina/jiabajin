<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>编辑《 ${detail.name}》</title>
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
<body background="../../static/img/book.jpg" style="background-repeat:no-repeat; background-size:100% 100%; background-attachment: fixed;">
<div id="header" style="padding-bottom: 70px"></div>
<div style="display: flex; justify-content: center; align-items: flex-start; min-height: 100vh;">
    <div class="container" style="max-width: 800px; margin-top: 30px;">
        <div class="row">
            <div class="col-12 form-container" style="padding:18px 32px 10px 32px;">
                <div class="text-center mb-4">
                    <h2 class="text-primary">编辑《${detail.name}》</h2>
                </div>
                <form action="book_edit_do?bookId=${detail.bookId}" method="post" id="addbook">
                    <div class="form-group">
                        <label for="name">书名</label>
                        <input type="text" class="form-control" name="name" id="name" value="${detail.name}">
                    </div>
                    <div class="form-group">
                        <label for="author">作者</label>
                        <input type="text" class="form-control" name="author" id="author" value="${detail.author}">
                    </div>
                    <div class="form-group">
                        <label for="publish">出版社</label>
                        <input type="text" class="form-control" name="publish" id="publish" value="${detail.publish}">
                    </div>
                    <div class="form-group">
                        <label for="isbn">ISBN</label>
                        <input type="text" class="form-control" name="isbn" id="isbn" value="${detail.isbn}">
                    </div>
                    <div class="form-group" style="margin-bottom:10px;">
                        <label for="introduction">简介</label>
                        <textarea class="form-control" rows="4" style="resize:none;width:100%;min-width:300px;max-width:100%;height:80px;min-height:80px;max-height:80px;" name="introduction" id="introduction">${detail.introduction}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="language">语言</label>
                        <input type="text" class="form-control" name="language" id="language" value="${detail.language}">
                    </div>
                    <div class="form-group">
                        <label for="price">价格</label>
                        <input type="text" class="form-control" name="price" id="price" value="${detail.price}">
                    </div>
                    <div class="form-group">
                        <label for="pubstr">出版日期</label>
                        <input type="date" class="form-control" name="pubstr" id="pubstr" value="<fmt:formatDate value='${detail.pubdate}' pattern='yyyy-MM-dd'/>">
                    </div>
                    <div class="form-group">
                        <label for="classId">分类号</label>
                        <select class="form-control" name="classId" id="classId" onchange="showClassName()">
                            <c:forEach var="cls" items="${classList}">
                                <option value="${cls.classId}" ${cls.classId==detail.classId?'selected':''}>${cls.classId}（${cls.className}）</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="number">数量</label>
                        <input type="text" class="form-control" name="number" id="number" value="${detail.number}">
                    </div>
                    <div style="text-align:center;margin-top:20px;">
                        <input type="submit" value="确定" class="btn btn-success" style="margin-right:20px;">
                        <button type="button" class="btn btn-default" onclick="window.history.back();">返回上一页</button>
                    </div>
                    <script>
                        var classMap = {};
                        <c:forEach var="cls" items="${classList}">
                        classMap['${cls.classId}'] = '${cls.className}';
                        </c:forEach>
                        function showClassName() {
                            var cid = document.getElementById('classId').value;
                            document.getElementById('classNameShow').innerText = classMap[cid] ? '分类名称：' + classMap[cid] : '';
                        }
                        $(function(){ showClassName(); });
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
