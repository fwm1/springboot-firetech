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
<body style="background: lightgray;">
<%@include file="head.html"%>
<!-- start: Content -->
<div id="content" class="span10" style="background: white;" >
    <div class="row-fluid">
        <ul id="myTab" class="nav nav-tabs">
            <li class="active"><a href="#user" data-toggle="tab" id="a-user">用户</a></li>
            <li><a href="#route" data-toggle="tab" id="a-route">路由</a></li>
            <li><a href="#diy-script" data-toggle="tab" id="a-script">脚本</a></li>
            <li><a href="#admin" data-toggle="tab" id="a-info">信息</a></li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="box span12 tab-pane fade in active container-fluid" id="user">
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
                                        <select name="route" style="border-radius:10px">
                                            <c:forEach var="route" items="${user.routeList}">
                                                <c:choose>
                                                    <c:when test="${user.currentRoute eq route.routeName}">
                                                        <option selected="selected">${route.routeName}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option>${route.routeName}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                        <input class="btn btn-success btn-sm" type="button" value="select" style="border-radius: 10px" onclick="changeRoute(this)">
                                    </form>
                                </td>
                                <td class="center">
                                    <a class="btn btn-info" href="#" onclick="updateUser(this)">
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
            <div class="box span12 tab-pane fade container-fluid" id="route">
                <div class="box-header" data-original-title="">
                    <h2><span class="break"></span></h2>
                    <div class="box-icon">
                        <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModal_route" style="float: left">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
                        </button>
                    </div>
                </div>
                <div class="box-content">
                    <table class="table table-striped table-hover" id="route_table">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>IP:Port</th>
                            <th>Out IP</th>
                            <th>Info</th>
                            <th>Type</th>
                            <th>Script</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="route" items="${page_route.list}">
                            <tr>
                                <td >${route.routeName}</td>
                                <td>${route.routeInstruct}</td>
                                <td>${route.outIp}</td>
                                <td>${route.info}</td>
                                <td>${route.groupId}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${route.scriptId == 0}">
                                            无
                                        </c:when>
                                        <c:otherwise>
                                            ${route.scriptId}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="center">
                                    <a class="btn btn-info" href="#" onclick="updateRoute(this)">
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
            <div class="box span12 tab-pane fade container-fluid" id="admin">
                <div class="box-content">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-6 col-sm-offset-3 form-box">
                                <form action="/user/update">
                                    <input type="hidden" name="old_name" value="${pageContext.request.remoteUser}">
                                    <div class="form-group">
                                        <label for="new_name">Name</label>
                                        <input type="text" class="form-control" id="new_name" name="new_name" value="${pageContext.request.remoteUser}">
                                    </div>
                                    <div class="form-group">
                                        <label for="password">New Password</label>
                                        <input type="text" class="form-control" id="password" placeholder="New Password" name="password">
                                    </div>
                                    <button type="submit" class="btn btn-default">Submit</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="box span12 tab-pane fade container-fluid" id="diy-script">
                <div class="box-header">
                    <h2><span class="break"></span></h2>
                    <div class="box-icon">
                        <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModal_script" style="float: left">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
                        </button>
                    </div>
                </div>
                <div class="box-content">
                    <table class="table table-striped table-hover" id="script_table">
                        <colgroup>
                            <col style="width:10%">
                            <col style="width:25%">
                            <col style="width:30%">
                            <col style="width:5%">
                        </colgroup>
                        <thead>
                        <tr >
                            <th>No.</th>
                            <th class="text-center">Script</th>
                            <th class="text-center">Routes</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="script" items="${scriptList}">
                            <tr>
                                <td>${script.scriptId}</td>
                                <td>${script.content}</td>
                                <td>
                                    <ul class="list-unstyled">
                                        <c:forEach items="${script.routeList}" var="route">
                                            <li>
                                                <dl class="dl-horizontal">
                                                    <dt>${route.routeName}</dt>
                                                    <dd>${route.routeInstruct} ==> ${route.outIp} & ${route.info}</dd>
                                                </dl>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </td>
                                <td class="center">
                                    <a class="btn btn-info" href="#" onclick="updateScript(this)">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                    </a>
                                    <a class="btn btn-danger" href="#" onclick="deleteScript(this)">
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
    </div>
</div st>
<!-- end: Content -->
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
                <form method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Route name..." name="route_name">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="eg:192.168.1.1:8080" name="route_instruct">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Out IP" name="route_out_ip">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Info..." name="route_info">
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
<div class="modal fade" id="myModal_script" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新脚本...</h4>
            </div>
            <div class="modal-body">
                <form method="post">
                    <textarea placeholder="$1:客户端口  $2:服务ip  $3:服务端口  $4:出口ip" name="content"  class="form-control input-lg" rows="5"></textarea>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="addScript(this)">Add</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal_updateRoute" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <form method="post">
                    <input id="route_name" type="hidden" name="route_name">
                    <div class="form-group">
                        <label for="ipport">IP:Port</label>
                        <input type="text" class="form-control" id="ipport" name="route_instruct">
                    </div>
                    <div class="form-group">
                        <label for="outip">Out IP</label>
                        <input type="text" class="form-control" id="outip" name="route_out_ip">
                    </div>
                    <div class="form-group">
                        <label for="info">Info</label>
                        <input type="text" class="form-control" id="info" name="route_info">
                    </div>
                    <div class="form-group">
                        <label for="sip">Script</label>
                        <select id="sip" name="script_id">
                            <c:forEach var="script" items="${scriptList}">
                                <option>${script.scriptId}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="type">Type</label>
                        <select id="type" name="group_id">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="saveRoute(this)">Update</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal_updateUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <form method="post">
                    <input type="hidden" name="username" id="username">
                    <div class="form-group">
                        <label for="port">Port</label>
                        <input type="text" class="form-control" id="port" name="user_port">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="saveUser(this)">Update</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal_updateScript" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="script_title"></h4>
            </div>
            <div class="modal-body">
                <form method="post">
                    <input name="script_id" type="hidden" id="script_id">
                    <textarea name="content"  class="form-control input-lg" rows="5" id="script_content"></textarea>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="saveScript(this)">Update</button>
            </div>
        </div>
    </div>
</div>
<footer style="position: absolute;bottom: 10px;width: 100%">
    <img src="/static/img/logo_firetech.svg">
</footer>
</body>
</html>
