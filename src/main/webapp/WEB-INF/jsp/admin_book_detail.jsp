<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>《 ${detail.name}》</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        body {
            background: url('../../static/img/book.jpg') no-repeat center center;
            background-size: cover;
            background-attachment: fixed;
            overflow-y: auto; /* 实现外部滚动条 */
        }

        .form-container {
            background-color: rgba(255, 255, 255, 0.95);
            border-radius: 8px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.15);
            padding: 30px;
            margin-top: 80px;
            /* 删除max-height和overflow-y，避免内部滚动条 */
        }

        .table th, .table td {
            vertical-align: middle!important;
        }

        .btn-default {
            padding: 10px 30px;
            font-size: 16px;
        }

        /* 小屏幕适配 */
        @media (max-width: 600px) {
            .form-container {
                padding: 15px;
                max-height: calc(100vh - 80px);
            }
            .btn-default {
                padding: 8px 15px;
                font-size: 14px;
            }
        }
    </style>
    <script>
        $(function () {
            $('#header').load('admin_header');
        })
    </script>
</head>
<body background="../../static/img/book.jpg" style="background-repeat:no-repeat; background-size:100% 100%; background-attachment: fixed; overflow-y: auto;">
<div id="header" style="padding-bottom: 20px;"></div>

<div class="container" style="max-width: 1000px; min-width: 350px; width: 98%; margin-top: 20px;">
    <div class="row">
        <div class="col-12 form-container">
            <div class="text-center mb-3">
                <h2 class="text-primary">《${detail.name}》</h2>
                <p class="text-muted">图书详细信息</p>
            </div>

            <table class="table table-hover">
                <tbody>
                <tr>
                    <th width="15%">书名</th>
                    <td>${detail.name}</td>
                </tr>
                <tr>
                    <th>作者</th>
                    <td>${detail.author}</td>
                </tr>
                <tr>
                    <th>出版社</th>
                    <td>${detail.publish}</td>
                </tr>
                <tr>
                    <th>ISBN</th>
                    <td>${detail.isbn}</td>
                </tr>
                <tr>
                    <th>简介</th>
                    <td style="word-break:break-all; max-height: 150px; overflow-y: auto;">${detail.introduction}</td>
                </tr>
                <tr>
                    <th>语言</th>
                    <td>${detail.language}</td>
                </tr>
                <tr>
                    <th>价格</th>
                    <td>${detail.price}</td>
                </tr>
                <tr>
                    <th>出版日期</th>
                    <td>
                        <c:choose>
                            <c:when test="${not empty detail.pubdate}">
                                <fmt:formatDate value="${detail.pubdate}" pattern="yyyy年MM月dd日"/>
                            </c:when>
                            <c:otherwise>--</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <th>分类号</th>
                    <td>${detail.classId}（${className}）</td>
                </tr>
                <tr>
                    <th>数量</th>
                    <td>${detail.number}</td>
                </tr>
                </tbody>
            </table>

            <div class="text-center mt-4">
                <a href="admin_books" class="btn btn-default">返回图书列表</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>