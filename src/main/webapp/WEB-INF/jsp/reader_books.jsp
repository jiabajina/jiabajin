<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            overflow: hidden;
        }
        .main-card {
            background-color: rgba(255,255,255,0.95);
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.15);
            padding: 35px 30px 30px 30px;
            margin: 50px auto;
            max-width: 1600px;
            width: 100%;
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
        .btn-xs {
            border-radius: 12px;
            padding: 3px 14px;
        }
        .content-wrapper {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: auto;
            padding: 20px;
            overflow: hidden;
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
        <div class="alert-container" style="position: fixed; top: 70px; left: 50%; transform: translateX(-50%); z-index: 9999; width: 90%; max-width: 500px;">
            <c:if test="${!empty succ}">
                <div class="alert alert-success alert-dismissable" id="success-alert" style="display: none; padding: 15px 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1);">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${succ}
                </div>
                <script type="text/javascript">
                    $(function() {
                        $("#success-alert").fadeIn(300);
                        setTimeout(function(){
                            $("#success-alert").fadeOut(1000);
                        }, 3000);
                    });
                </script>
            </c:if>
            <c:if test="${!empty error}">
                <div class="alert alert-danger alert-dismissable" style="display: none; padding: 15px 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1);">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${error}
                </div>
                <script type="text/javascript">
                    $(function() {
                        $(".alert-danger").fadeIn(300);
                        setTimeout(function(){
                            $(".alert-danger").fadeOut(1000);
                        }, 3000);
                    });
                </script>
            </c:if>
        </div>
        <div class="main-card">
            <div class="main-title">全部图书</div>
            <!-- 查询表单 -->
            <form method="post" action="reader_querybook_do" class="form-inline search-bar" id="searchform">
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
            <!-- 提示消息 -->
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
                        <th>借还</th>
                        <th>详情</th>
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
                            
                            <c:set var="flag" value="false"/>
                            <c:forEach var="lend" items="${myLendList}">
                                <c:if test="${lend eq book.bookId}">
                                    <c:set var="flag" value="true"/>
                                </c:if>
                            </c:forEach>
                            <c:if test="${flag}">
                                <td><a href="returnbook?bookId=<c:out value="${book.bookId}"/>">
                                    <button type="button" class="btn btn-danger btn-xs">归还</button>
                                </a></td>
                            </c:if>
                            <c:if test="${not flag}">
                                <c:if test="${book.number>0}">
                                    <td><a href="lendbook?bookId=<c:out value="${book.bookId}"/>">
                                        <button type="button" class="btn btn-primary btn-xs">借阅</button>
                                    </a></td>
                                </c:if>
                                <c:if test="${book.number==0}">
                                    <td>
                                        <button type="button" class="btn btn-default btn-xs" disabled="disabled">已空</button>
                                    </td>
                                </c:if>
                            </c:if>
                            <td><a href="reader_book_detail?bookId=<c:out value="${book.bookId}"/>">
                                <button type="button" class="btn btn-success btn-xs">详情</button>
                            </a></td>
                        </tr>
                        <c:set var="index" value="${index+1}" />
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- 分页控件 -->
            <c:if test="${not empty books && totalPages > 0}">
                <div style="display: flex; align-items: center; justify-content: center;">
                    <ul class="pagination">
                        <li class="${currentPage == 1 ? 'disabled' : ''}">
                            <a href="<c:choose><c:when test='${currentPage == 1 || totalPages == 1}'>#</c:when><c:otherwise>reader_books?currentPage=1&amp;searchWord=${param.searchWord != null ? param.searchWord : searchWord}</c:otherwise></c:choose>" aria-label="First">
                                <span aria-hidden="true">首页</span>
                            </a>
                        </li>
                        <li class="${currentPage == 1 ? 'disabled' : ''}">
                            <a href="<c:choose><c:when test='${currentPage == 1 || totalPages == 1}'>#</c:when><c:otherwise>reader_books?currentPage=${currentPage-1}&amp;searchWord=${param.searchWord != null ? param.searchWord : searchWord}</c:otherwise></c:choose>" aria-label="Previous">
                                <span aria-hidden="true">上一页</span>
                            </a>
                        </li>
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="${currentPage == i ? 'active' : ''}"><a href="reader_books?currentPage=${i}&amp;searchWord=${param.searchWord != null ? param.searchWord : searchWord}">${i}</a></li>
                        </c:forEach>
                        <li class="${currentPage == totalPages ? 'disabled' : ''}">
                            <a href="<c:choose><c:when test='${currentPage == totalPages || totalPages == 1}'>#</c:when><c:otherwise>reader_books?currentPage=${currentPage+1}&amp;searchWord=${param.searchWord != null ? param.searchWord : searchWord}</c:otherwise></c:choose>" aria-label="Next">
                                <span aria-hidden="true">下一页</span>
                            </a>
                        </li>
                        <li class="${currentPage == totalPages ? 'disabled' : ''}">
                            <a href="<c:choose><c:when test='${currentPage == totalPages || totalPages == 1}'>#</c:when><c:otherwise>reader_books?currentPage=${totalPages}&amp;searchWord=${param.searchWord != null ? param.searchWord : searchWord}</c:otherwise></c:choose>" aria-label="Last">
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
