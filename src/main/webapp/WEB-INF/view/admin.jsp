<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 万民
  Date: 2018/7/27
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/static/css/style-responsive.min.css"/>
    <script src="/static/js/myscript.js"></script>
</head>
<body>
<%@include file="head.html"%>
        <!-- start: Content -->
        <div id="content" class="span10">
            <div class="row-fluid">
                <ul id="myTab" class="nav nav-tabs">
                    <li class="active">
                        <a href="#user" data-toggle="tab">
                            用户
                        </a>
                    </li>
                    <li><a href="#route" data-toggle="tab">路由</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="box span12 tab-pane fade in active" id="user">
                        <div class="box-header" data-original-title="">
                            <h2><span class="break"></span></h2>
                            <div class="box-icon">
                                <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModal_user" style="float: left">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
                                </button>
                            </div>
                        </div>
                        <div class="box-content">
                            <table class="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th>Username</th>
                                    <th>IP:Port</th>
                                    <th>Routes</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="user" items="${page_user.list}">
                                    <tr>
                                        <td >${user.userName}</td>
                                        <td>${user.info}</td>
                                        <td>
                                            <form method="post">
                                                <input type="hidden" value="${user.userName}" name="username">
                                                <input type="hidden" value="${user.info}" name="info">
                                                <select name="route">
                                                    <c:forEach items="${user.routeList}" var="route">
                                                        <option>
                                                            ${route.routeName}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                                <input class="btn btn-success btn-sm" type="button" value="select" onclick="changeRoute(this)">
                                            </form>
                                        </td>
                                        <td class="center">
                                            <a class="btn btn-info" href="#">
                                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                            </a>
                                            <a class="btn btn-danger" href="#" onclick="deleteUser(this)">
                                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <nav aria-label="...">
                            <ul class="pager">
                                <li class="previous">
                                    <a href="?pageNum=${page_user.pageNum-1}">
                                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                    </a>
                                </li>
                                <li class="previous">
                                    <a href="?pageNum=${page_user.pageNum+1}">
                                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                    <div class="box span12 tab-pane fade" id="route">
                        <div class="box-header" data-original-title="">
                            <h2><span class="break"></span></h2>
                            <div class="box-icon">
                                <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModal_route" style="float: left">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
                                </button>
                            </div>
                        </div>
                        <div class="box-content">
                            <table class="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th>name</th>
                                    <th>IP:Port</th>
                                    <th>type</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="route" items="${page_route.list}">
                                    <tr>
                                        <td >${route.routeName}</td>
                                        <td>${route.routeInstruct}</td>
                                        <td>
                                            ${route.groupId}
                                        </td>
                                        <td class="center">
                                            <a class="btn btn-info" href="#">
                                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                            </a>
                                            <a class="btn btn-danger" href="#" onclick="deleteRoute(this)">
                                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
        <!-- end: Content -->

    </div>
</div>
<div class="modal fade" id="myModal_user" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新用户...</h4>
            </div>
            <div class="modal-body">
                <form method="post" id="form1">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Username..." name="username">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Password" name="password">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="IP:port" name="info">
                    </div>
                    <div class="form-group">
                        <select name="group" id="group1">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                        <label for="group1">type</label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="addUser(this)">Add</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal_route" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新线路...</h4>
            </div>
            <div class="modal-body">
                <form method="post" id="form2">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Route name..." name="route_name">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="eg:192.168.1.1:8080" name="route_instruct">
                    </div>
                    <div class="form-group">
                        <select name="group" id="group2">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                        <label for="group2">type</label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="addRoute(this)">Add</button>
            </div>
        </div>
    </div>
</div>
<script>

</script>
</body>

</html>
