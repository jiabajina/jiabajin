<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>读者信息</title>
    <link rel="shortcut icon"  href="img/library.ico" />
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <style>
        .bg-overlay {
            background-image: url('/static/img/u1.jpg');
            background-repeat: no-repeat;
            background-size: 100% 100%;
            background-attachment: fixed;
            min-height: 100vh;
            position: relative;
        }
        .bg-overlay::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(255, 255, 255, 0.85);
        }
        .content-container {
            position: relative;
            z-index: 1;
        }
        .reader-card {
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            padding: 30px;
        }
        .countdown-text {
            font-size: 18px;
            color: #337ab7;
            font-weight: bold;
        }
    </style>
</head>
<body class="bg-overlay">
<div id="header"></div>

<div class="content-container">
    <c:if test="${!empty info}">
        <script>alert("${info}");window.location.href="allreaders"</script>
    </c:if>

    <div style="position: relative; top: 50px; max-width: 800px; margin: 0 auto;">
        <c:if test="${!empty succ}">
            <div class="alert alert-success alert-dismissable text-center" id="success-alert">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                ${succ}
            </div>
            <script type="text/javascript">
                setTimeout(function() {
                    window.location.href = "/index";
                }, 5000);
            </script>
        </c:if>
        <c:if test="${!empty error}">
            <div class="alert alert-danger alert-dismissable text-center" id="error-alert">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                ${error}
            </div>
            <script type="text/javascript">
                setTimeout(function() {
                    window.location.href = "/login";
                }, 5000);
            </script>
        </c:if>
        <div class="panel panel-default" style="margin-top:40px;">
            <div class="panel-heading text-center">
                <p><em id="num">5</em> 秒后，自动跳转主页（请记住自己的读者号）</p>
            </div>
            <div class="panel-body">
                <table class="table table-hover" style="margin:0 auto;max-width:400px;">
                    <thead>
                    <tr>
                        <th>读者号</th>
                        <th>姓名</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${not empty reader && not empty reader.readerId}">
                            <tr>
                                <td><c:out value="${reader.readerId}"/></td>
                                <td><c:out value="${reader.name}"/></td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="2" style="text-align:center; color:red;">${error != null ? error : '注册失败，未获取到读者信息'}</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
        <c:if test="${not empty succ}">
        <script>
            var i = 5;
            function djs() {
                document.getElementById("num").innerText = i--;
                if(i >= 0) setTimeout(djs, 1000);
            }
            djs();
        </script>
        </c:if>
    </div>
</div>

</body>
</html>