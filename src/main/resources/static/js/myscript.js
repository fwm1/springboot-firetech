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
        success:function(result){
            window.location.href="/admin";
        },error:function (result) {
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
            success:function(result){
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
        success:function(result){
            window.location.href="/admin";
        },error:function (result) {
            alert("添加失败");
        }
    });
    $('#myModal_route').modal('hide')
}
function deleteRoute(obj) {
    var confirmed = confirm("是否删除该线路？");
    if(confirmed){
        var route_name = $(obj).parent().prev().prev().prev().html();
        $(obj).parents("tr").remove();
        $.ajax({
            type:'POST',
            url:'/route/deleteRoute?route_name='+route_name,
            success:function(result){
                window.location.href="/admin";
            },error:function () {
                alert("删除失败");
            }
        });
    }
}