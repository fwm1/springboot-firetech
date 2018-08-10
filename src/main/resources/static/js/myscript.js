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
function addRoute(obj) {
    $.ajax({
        type:'POST',
        url:'/route/addRoute',
        data:$(obj).parent().prev().children().serialize(),
        success:function(){
            window.location.href="/admin";
        },error:function () {
            alert("添加失败");
        }
    });
    $('#myModal_route').modal('hide')
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
                window.location.href="/admin";
            },error:function () {
                alert("删除失败");
            }
        });
    }
}
function addScript(obj) {
    $.ajax({
        type:'POST',
        url:'/script/add',
        data:$(obj).parent().prev().children().serialize(),
        success:function(){
            window.location.href="/admin";
        },error:function () {
            alert("添加失败");
        }
    });
    $('#myModal_route').modal('hide')
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
                window.location.href="/admin";
            },error:function () {
                alert("删除失败");
            }
        });
    }
}
function updateRoute(obj) {
    var a = $('#myModal_updateScript');
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

