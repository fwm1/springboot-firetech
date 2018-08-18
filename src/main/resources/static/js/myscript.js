function changeRoute(obj) {
    $.ajax({
        type:'POST',
        url:'/route/change',
        data:$(obj).parent().serialize(),
        success:function(){
            alert("切换成功");
        }
    });
}
function addUser(obj) {
    $.ajax({
        type:'POST',
        url:'/user/addUser',
        data:$(obj).parent().prev().children().serialize(),
        success:function(){
            window.location.href="/admin";
        },error:function () {
            alert("添加失败");
        }
    });
    $('#myModal_user').modal('hide')
}
function deleteUser(obj) {
    var confirmed = confirm("是否删除该用户？");
    if(confirmed){
        var username = $(obj).parent().prev().prev().prev().html();
        $(obj).parents("tr").remove();
        $.ajax({
            type:'POST',
            url:'/user/delete?username='+username,
            success:function(){
                window.location.href="/admin";
            },error:function () {
                alert("删除失败");
            }
        });
    }
}
function deleteRoute(obj) {
    var confirmed = confirm("是否删除该线路？");
    if(confirmed){
        var route_name = $(obj).parent().parent().children('td').html();
        $(obj).parents("tr").remove();
        $.ajax({
            type:'POST',
            url:'/route/deleteRoute?route_name='+route_name,
            success:function(){

            },error:function () {
                alert("删除失败");
            }
        });
    }
}
function updateRoute(obj) {
    var a = $('#myModal_updateRoute');
    var td = $(obj).parent().parent().find('td');
    var route_name = td.eq(0).html();
    var route_ins = td.eq(1).html();
    var out_ip = td.eq(2).html();
    var info = td.eq(3).html();
    var typeid = td.eq(4).html();
    /* trim() 删除多余空白*/
    var script_id = td.eq(5).html().trim();
    a.modal();
    a.find('.modal-title').html(route_name);
    $('#route_name').val(route_name);
    $('#ipport').val(route_ins);
    $('#outip').val(out_ip);
    $('#info').val(info);
    $("#sip").children("option").each(function(){
        var temp_value = $(this).val();
        if(temp_value === script_id){
            $(this).attr("selected","selected");
        }
    });
    $("#type").children("option").each(function(){
        var temp_value = $(this).val();
        if(temp_value === typeid){
            $(this).attr("selected","selected");
        }
    });
}
function saveRoute(obj) {
    $.ajax({
        type:'POST',
        url:'/route/updateRoute',
        data:$(obj).parent().prev().children().serialize(),
        success:function(){
            window.location.href="/admin";
        },error:function () {
            alert("修改失败");
        }
    });
    $('#myModal_route').modal('hide');
}
function updateUser(obj) {
    var user_name = $(obj).parent().prev().prev().prev().html();
    var user_port = $(obj).parent().prev().prev().html();
    var a = $('#myModal_updateUser');
    a.modal();
    a.find('.modal-title').html(user_name);
    $('#username').val(user_name);
    $('#port').val(user_port);
}
function saveUser(obj) {
    $.ajax({
        type:'POST',
        url:'/user/updatePort',
        data:$(obj).parent().prev().children().serialize(),
        success:function(){
            window.location.href="/admin";
        },error:function () {
            alert("修改失败");
        }
    });
    $('#myModal_route').modal('hide');
}
function addRoute(obj) {
    $.ajax({
        type:'POST',
        url:'/route/addRoute',
        data:$(obj).parent().prev().children().serialize(),
        success:function(result){
            var json = JSON.parse(result);
            $('#route_table').append('<tr><td>'+json.routeName+'</td><td>'+json.routeInstruct+'</td><td>'
                +json.outIp+'</td><td>'+json.info+'</td><td>'+json.groupId+'</td><td>'+(json.scriptId===0?'无':json.groupId) +'</td>'
                +'<td class="center"><a class="btn btn-info" href="#" onclick="updateRoute(this)">' +
                '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>' +
                '<a class="btn btn-danger" href="#" onclick="deleteRoute(this)">' +
                '<span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>' +
                '</td></tr>');
        },error:function () {
            alert("添加失败");
        }
    });
    $('#myModal_route').modal('hide')
}
function addScript(obj) {
    $.ajax({
        type:'POST',
        url:'/script/add',
        data:$(obj).parent().prev().children().serialize(),
        success:function(result){
            var script = JSON.parse(result);
            $('#script_table').append('<tr><td>'+script.scriptId+'</td><td>'+script.content+'</td><td></td>'
                +'<td class="center"><a class="btn btn-info" href="#" onclick="updateScript(this)">' +
                '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>' +
                '<a class="btn btn-danger" href="#" onclick="deleteScript(this)">' +
                '<span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td><tr>');
        },error:function () {
            alert("添加失败");
        }
    });
    $('#myModal_script').modal('hide')
}
function deleteScript(obj) {
    var confirmed = confirm("是否删除该脚本？");
    if(confirmed){
        var script_id = $(obj).parent().prev().prev().prev().html();
        $(obj).parents("tr").remove();
        $.ajax({
            type:'POST',
            url:'/script/delete?script_id='+script_id,
            success:function(){

            },error:function () {
                alert("删除失败");
            }
        });
    }
}
function updateScript(obj) {
    var a = $(obj).parent().prev().prev().html();
    var b = $(obj).parent().prev().prev().prev().html();
    $('#myModal_updateScript').modal();
    $('#script_title').html(b);
    $('#script_id').val(b);
    $('#script_content').val(a);
}
function saveScript(obj) {
    $.ajax({
        type:'POST',
        url:'/script/update',
        data:$(obj).parent().prev().children().serialize(),
        success:function(){
            window.location.href="/admin";
        },error:function () {
            alert("修改失败");
        }
    });
    $('#myModal_route').modal('hide');
}
