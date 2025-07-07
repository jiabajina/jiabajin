<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>全部图书信息</title>

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
            margin-top: 140px;
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
            width: 280px; /* 增大搜索框宽度 */
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
        .btn-xs {
            border-radius: 12px;
            padding: 3px 14px;
        }

        /* 新增：优化页面宽度的响应式设置 */
        .content-wrapper {
            display: flex;
            justify-content: center;
            min-height: 100vh;
            padding: 0 20px; /* 两侧留白，避免边缘贴边 */
        }
        .container {
            max-width: 1600px; /* 增大容器最大宽度 */
            width: 100%;
        }
    </style>
    <script>
        $(function () {
            $('#header').load('admin_header');
        })
    </script>
</head>
<body>
<div id="header"></div>
<div class="content-wrapper">
    <div class="container">
        <div class="main-card">
            <div class="main-title">全部图书</div>
            <!-- 查询表单 -->
            <form method="post" action="querybook" class="form-inline search-bar" id="searchform">
                <input type="hidden" name="currentPage" value="1">
                <div class="form-group">
                    <input type="text" placeholder="输入图书名" class="form-control" id="search" name="searchWord" value="${param.searchWord != null ? param.searchWord : searchWord}">
                </div>
                <button type="submit" class="btn btn-primary">搜索</button>
            </form>
            <script>
                $("#searchform").submit(function () {
                    var val = $("#search").val();
                    if (val == '') {
                        alert("请输入关键字");
                        return false;
                    }
                })
            </script>
            <div class="table-container">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>书名</th>
                        <th>作者</th>
                        <th>出版社</th>
                        <th>ISBN</th>
                        <th>价格</th>
                        <th>剩余数量</th>
                        <th>详情</th>
                        <th>编辑</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="pageSize" value="10" />
                    <c:set var="index" value="${(currentPage < 1 ? 0 : currentPage - 1) * pageSize + 1}" />
                    <c:forEach items="${books}" var="book">
                        <tr>
                            <td>${index}</td>
                            <td><c:out value="${book.name}"/></td>
                            <td><c:out value="${book.author}"/></td>
                            <td><c:out value="${book.publish}"/></td>
                            <td><c:out value="${book.isbn}"/></td>
                            <td><c:out value="${book.price}"/></td>
                            <td><c:out value="${book.number}"/></td>
                            <td><a href="admin_book_detail?bookId=${book.bookId}"><button type="button" class="btn btn-info btn-xs">详情</button></a></td>
                            <td><a href="updatebook?bookId=${book.bookId}&currentPage=${currentPage}"><button type="button" class="btn btn-warning btn-xs">编辑</button></a></td>
                            <td><a href="deletebook?bookId=<c:out value='${book.bookId}'/>&currentPage=${currentPage}"><button type="button" class="btn btn-danger btn-xs">删除</button></a></td>
                        </tr>
                        <c:set var="index" value="${index+1}" />
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- 分页控件：仅在有数据时显示 -->
            <c:if test="${not empty books && totalPages > 0}">
                <div style="display: flex; align-items: center; justify-content: center;">
                    <ul class="pagination">
                        <li class="${currentPage == 1 ? 'disabled' : ''}">
                            <a href="<c:choose><c:when test='${currentPage == 1 || totalPages == 1}'>#</c:when><c:otherwise>admin_books?currentPage=1&amp;searchWord=${param.searchWord != null ? param.searchWord : searchWord}</c:otherwise></c:choose>" aria-label="First">
                                <span aria-hidden="true">首页</span>
                            </a>
                        </li>
                        <li class="${currentPage == 1 ? 'disabled' : ''}">
                            <a href="<c:choose><c:when test='${currentPage == 1 || totalPages == 1}'>#</c:when><c:otherwise>admin_books?currentPage=${currentPage-1}&amp;searchWord=${param.searchWord != null ? param.searchWord : searchWord}</c:otherwise></c:choose>" aria-label="Previous">
                                <span aria-hidden="true">上一页</span>
                            </a>
                        </li>
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="${currentPage == i ? 'active' : ''}"><a href="admin_books?currentPage=${i}&amp;searchWord=${param.searchWord != null ? param.searchWord : searchWord}">${i}</a></li>
                        </c:forEach>
                        <li class="${currentPage == totalPages ? 'disabled' : ''}">
                            <a href="<c:choose><c:when test='${currentPage == totalPages || totalPages == 1}'>#</c:when><c:otherwise>admin_books?currentPage=${currentPage+1}&amp;searchWord=${param.searchWord != null ? param.searchWord : searchWord}</c:otherwise></c:choose>" aria-label="Next">
                                <span aria-hidden="true">下一页</span>
                            </a>
                        </li>
                        <li class="${currentPage == totalPages ? 'disabled' : ''}">
                            <a href="<c:choose><c:when test='${currentPage == totalPages || totalPages == 1}'>#</c:when><c:otherwise>admin_books?currentPage=${totalPages}&amp;searchWord=${param.searchWord != null ? param.searchWord : searchWord}</c:otherwise></c:choose>" aria-label="Last">
                                <span aria-hidden="true">末页</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>