<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    .app:hover{
        background:#9d9d9d;
    }
</style>

<nav class="navbar navbar-default" role="navigation" style="background-color:#fff">
    <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
            <a class="navbar-brand " href="reader_main"><p class="text-primary" style="font-family: 华文行楷; font-size: 200%; ">我的图书馆</p></a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
                <li class="app">
                    <a href="reader_books">
                        图书查询
                    </a>
                </li>
                <li class="app">
                    <a href="reader_info" >
                        个人信息
                    </a>
                </li>
                <li class="app" >
                    <a href="mylend" >
                        我的借还
                    </a>
                </li>
                <li class="app">
                    <a href="reader_repasswd" >
                        密码修改
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="reader_info">${readercard.name}, 已登录</a></li>
                <li><a href="login">退出</a></li>
            </ul>
        </div>
    </div>
</nav>
