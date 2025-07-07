<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>我的借还</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        body {
            background: url('/static/img/school.jpg') no-repeat center center fixed;
            background-size: 100% 100%;
        }
        .main-card {
            background-color: rgba(255,255,255,0.95);
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.15);
            padding: 35px 30px 30px 30px;
            margin-top: 40px;
            margin-bottom: 40px;
        }
        .main-title {
            text-align: center;
            color: #337ab7;
            font-size: 2.2rem;
            font-weight: 600;
            margin-bottom: 18px;
        }
        .search-bar {
            margin-bottom: 25px;
            text-align: center;
        }
        .search-bar .form-control {
            width: 280px;
            display: inline-block;
        }
        .search-bar .btn {
            border-radius: 20px;
            padding: 7px 28px;
            font-size: 15px;
        }
        .table-container {
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
            margin-bottom: 20px;
        }
        .pagination {
            margin: 0;
        }
        .pagination > li > a, .pagination > li > span {
            border-radius: 50px !important;
            margin: 0 2px;
        }
        .content-wrapper {
            display: flex;
            justify-content: center;
            min-height: 100vh;
            padding: 0 20px;
        }
        .container {
            max-width: 1600px;
            width: 100%;
        }
    </style>
    <script>
        $(function () {
            $('#header').load('reader_header');
        })
    </script>
</head>
<body>
<div id="header"></div>
<div class="content-wrapper">
    <div class="container">
        <div class="main-card">
<div id="header"></div>
<div style="position: relative;top: 10%">
    <c:if test="${!empty succ}">
        <div class="alert alert-success alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
                ${succ}
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
                ${error}
        </div>
    </c:if>
</div>

<div class="main-title">我的借还日志</div>
<div class="table-container">
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>图书号</th>
                <th>借出日期</th>
                <th>归还日期</th>
                <th>状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="alog">
                <tr>
                    <td><c:out value="${alog.bookId}"></c:out></td>
                    <td><fmt:formatDate value="${alog.lendDate}" pattern="yyyy-MM-dd" timeZone="GMT+8"/></td>
                    <td><fmt:formatDate value="${alog.backDate}" pattern="yyyy-MM-dd" timeZone="GMT+8"/></td>
                    <c:if test="${empty alog.backDate}">
                        <td>未还</td>
                    </c:if>
                    <c:if test="${!empty alog.backDate}">
                        <td>已还</td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
            <!-- 分页控件 -->
            <c:if test="${not empty list && totalPages > 0}">
                <div style="display: flex; align-items: center; justify-content: center; margin-top: 20px;">
                    <ul class="pagination">
                        <li class="${currentPage == 1 ? 'disabled' : ''}">
                            <a href="<c:choose><c:when test='${currentPage == 1 || totalPages == 1}'>#</c:when><c:otherwise>mylend?currentPage=1</c:otherwise></c:choose>" aria-label="First">
                                <span aria-hidden="true">首页</span>
                            </a>
                        </li>
                        <li class="${currentPage == 1 ? 'disabled' : ''}">
                            <a href="<c:choose><c:when test='${currentPage == 1 || totalPages == 1}'>#</c:when><c:otherwise>mylend?currentPage=${currentPage-1}</c:otherwise></c:choose>" aria-label="Previous">
                                <span aria-hidden="true">上一页</span>
                            </a>
                        </li>
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="${currentPage == i ? 'active' : ''}"><a href="mylend?currentPage=${i}">${i}</a></li>
                        </c:forEach>
                        <li class="${currentPage == totalPages ? 'disabled' : ''}">
                            <a href="<c:choose><c:when test='${currentPage == totalPages || totalPages == 1}'>#</c:when><c:otherwise>mylend?currentPage=${currentPage+1}</c:otherwise></c:choose>" aria-label="Next">
                                <span aria-hidden="true">下一页</span>
                            </a>
                        </li>
                        <li class="${currentPage == totalPages ? 'disabled' : ''}">
                            <a href="<c:choose><c:when test='${currentPage == totalPages || totalPages == 1}'>#</c:when><c:otherwise>mylend?currentPage=${totalPages}</c:otherwise></c:choose>" aria-label="Last">
                                <span aria-hidden="true">末页</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
</div>

<!-- 删除多余的header -->
<script>
    $(function () {
        $('#header').load('reader_header');
    })
</script>
    </div>
</div>
</body>
</html>
