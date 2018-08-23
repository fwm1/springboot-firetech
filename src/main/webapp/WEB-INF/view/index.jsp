<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 万民
  Date: 2018/7/26
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>FireTech</title>
</head>

<body style="background: lightgray">
<%--只能静态导入,动态带入会报错no such dispacher--%>
<%@include file="head.html"%>
<!-- start: Content -->
<div id="content" class="span10" style="background: white">
    <div class="row-fluid">
        <ul id="myTab" class="nav nav-tabs">
            <li class="active">
                <a href="#route" data-toggle="tab">
                    路由
                </a>
            </li>
            <li><a href="#user" data-toggle="tab">信息</a></li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="box span12 tab-pane fade in active" id="route">
            <div class="box-content">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Routes</th>
                        <th>Out Ip</th>
                        <th>Info</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="route" items="${page.list}">
                        <tr style="text-align: left">
                            <td>
                                <form method="post">
                                    <input type="hidden" name="username" value="${pageContext.request.remoteUser}">
                                    <input type="hidden" name="route" value="${route.routeName}">
                                    <input type="hidden" name="info" value="${user.info}">
                                    ${route.routeName}
                                </form>
                            </td>
                            <td>${route.outIp}</td>
                            <td>${route.info}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${route.routeName == user.currentRoute}">
                                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <button class="btn btn-info" onclick="changeRoute(this)">
                                    <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
                                </button>
                                <button class="btn btn-danger" href="#">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div><!--/span-->
            <div class="box span12 tab-pane fade" id="user">
                ${user.info}
            </div>
        </div>
    </div><!--/row-->
</div>
<!-- end: Content -->

<script>
    function changeRoute(obj) {
        $.ajax({
            type:'POST',
            url:'/route/change',
            data:$(obj).parent().parent().children().children().serialize(),
            success:function(){
                alert("已切换线路");
                window.location.href="/index";
            },error:function () {
                alert("切换失败");
            }
        });
    }
</script>
<footer style="position: absolute;bottom: 5px;width: 100%">
    <img src="/static/img/logo_firetech.svg">
</footer>
</body>

</html>
