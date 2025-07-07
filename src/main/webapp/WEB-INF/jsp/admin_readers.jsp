<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type"content="text/html;charset=utf-8"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>全部读者</title>
    <link rel="shortcut icon"  href="img/library.ico" />
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <script>
        $(function () {
            $('#header').load('admin_header');
        })
    </script>
    <style>
        body {
            background: url('/static/img/u1.jpg') no-repeat center center fixed;
            background-size: 100% 100%;
        }
        .content-wrapper {
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
        }
        .container {
            max-width: 1300px;
            min-width: 350px;
            width: 99vw;
        }
        .main-card {
            background-color: rgba(255,255,255,0.95);
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.15);
            padding: 25px 20px 20px 20px;
            margin-top: 140px;
            margin-bottom: 20px;
        }
        .main-title {
            text-align: center;
            color: #337ab7;
            font-size: 2.2rem;
            font-weight: 600;
            margin-bottom: 18px;
        }
        .search-bar {
            margin-bottom: 18px;
            text-align: center;
        }
        .search-bar .form-control {
            width: 220px;
            display: inline-block;
        }
        .search-bar .btn {
            border-radius: 20px;
            padding: 7px 28px;
            font-size: 15px;
        }
        .table-container {
            margin-bottom: 10px;
        }
        .pagination {
            margin: 0;
        }
        .pagination > li > a, .pagination > li > span {
            border-radius: 50px !important;
            margin: 0 2px;
        }
        .btn-xs {
            border-radius: 12px;
            padding: 3px 14px;
        }
        @media (max-width: 600px) {
            .main-card {padding: 10px 2px 8px 2px;}
        }
    </style>
</head>
<body>
<div id="header"></div>
<div class="content-wrapper">
    <div class="container">
        <div class="main-card">
            <div class="main-title">全部读者</div>
            <!-- 查询表单 -->
            <form method="get" action="allreaders" class="form-inline search-bar">
                <div class="form-group">
                    <input type="text" name="name" value="${name}" class="form-control" placeholder="按姓名查询">
                </div>
                <button type="submit" class="btn btn-primary">查询</button>
            </form>
            <div class="table-container">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>读者号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>生日</th>
                        <th>地址</th>
                        <th>电话</th>
                        <th>编辑</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="pageSize" value="10" />
                    <c:set var="index" value="${(currentPage < 1 ? 0 : currentPage - 1) * pageSize + 1}" />
                    <c:forEach items="${readers}" var="reader">
                        <tr>
                            <td>${index}</td>
                            <td><c:out value="${reader.readerId}"></c:out></td>
                            <td><c:out value="${reader.name}"></c:out></td>
                            <td><c:out value="${reader.sex}"></c:out></td>
                            <td><fmt:formatDate value="${reader.birth}" pattern="yyyy年MM月dd日"/></td>
                            <td><c:out value="${reader.address}"></c:out></td>
                            <td><c:out value="${reader.phone}"></c:out></td>
                            <td><a href="reader_edit?readerId=<c:out value='${reader.readerId}'/>"><button type="button" class="btn btn-info btn-xs">编辑</button></a></td>
                            <td><a href="reader_delete?readerId=<c:out value='${reader.readerId}'/>"><button type="button" class="btn btn-danger btn-xs">删除</button></a></td>
                        </tr>
                        <c:set var="index" value="${index+1}" />
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:if test="${not empty readers && totalPages > 0}">
                <div style="display: flex; align-items: center; justify-content: center;">
                    <ul class="pagination">
                        <li class="${currentPage == 1 ? 'disabled' : ''}">
                            <a href="<c:choose><c:when test='${currentPage == 1 || totalPages == 1}'>#</c:when><c:otherwise>allreaders?page=1&amp;name=${name}</c:otherwise></c:choose>" aria-label="First">
                                <span aria-hidden="true">首页</span>
                            </a>
                        </li>
                        <li class="${currentPage == 1 ? 'disabled' : ''}">
                            <a href="<c:choose><c:when test='${currentPage == 1 || totalPages == 1}'>#</c:when><c:otherwise>allreaders?page=${currentPage-1}&amp;name=${name}</c:otherwise></c:choose>" aria-label="Previous">
                                <span aria-hidden="true">上一页</span>
                            </a>
                        </li>
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="${currentPage == i ? 'active' : ''}"><a href="allreaders?page=${i}&amp;name=${name}">${i}</a></li>
                        </c:forEach>
                        <li class="${currentPage == totalPages ? 'disabled' : ''}">
                            <a href="<c:choose><c:when test='${currentPage == totalPages || totalPages == 1}'>#</c:when><c:otherwise>allreaders?page=${currentPage+1}&amp;name=${name}</c:otherwise></c:choose>" aria-label="Next">
                                <span aria-hidden="true">下一页</span>
                            </a>
                        </li>
                        <li class="${currentPage == totalPages ? 'disabled' : ''}">
                            <a href="<c:choose><c:when test='${currentPage == totalPages || totalPages == 1}'>#</c:when><c:otherwise>allreaders?page=${totalPages}&amp;name=${name}</c:otherwise></c:choose>" aria-label="Last">
                                <span aria-hidden="true">末页</span>
                            </a>
                        </li>
                    </ul>
                    <span style="margin-left:16px;">共${totalPages}页</span>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
