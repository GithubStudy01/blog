var saveType;

$(function () {

})
layui.use('layer',function(){
    getArticleListByType();

    $("#article-type li").click(function(){
        var type = $(this).attr("articleType");
        saveType = type;
        getArticleListByType(type);
    })

    $(".deleteArticle").on("click",function(){
        console.log($(this).text())
        var id = $(this).parent().attr("article-id");
        deleteArticle(id)
    })

})
function getArticleListByType(type){
    $.ajax({
        url: "http://localhost:8080/article/user",
        type: "GET",
        dataType: "json",
        data:{
            "type":type
        },
        async: false,
        success: function (result) {
            console.log(result)
            var code = result.code;
            if(code != '0001'){
                layer.msg(result.content, {icon: 5, time: 1000,shift : 6})
                return;
            }
            var result = result.content;
            var content = result.content;

            var html = "";
            for(var i = 0 ;i< content.length;i++){
                html +='        <div class="list-group-item">\n' +
                    '            <h3 class="list-group-item-heading"><a style="text-decoration:none">'+content[i].title+'</a></h3>\n' +
                    '            <div class="article-list">\n' +
                    '                <div class="article-attribute">\n' +
                    '                    <span style="">'+content[i].createtime+'</span>\n' +
                    '                    <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>' +content[i].goodTimes+
                    '                    <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>' +content[i].viewTimes+
                    '                    <span class="glyphicon glyphicon-comment" aria-hidden="true"></span>' +content[i].commentTimes+
                    '                </div>\n' +
                    '                <div class="article-option" article-id="'+content[i].id+'">\n' +
                    '                    <a href=""><span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>顶置</a>\n' +
                    '                    <a href=""><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>公开</a>\n' +
                    '                    <a href=""><span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>隐藏</a>\n' +
                    '                    <a href="/edit/'+content[i].id+'" target="_blank"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span>编辑</a>\n' +
                    '                    <a href="javascritp:void(0)" class="deleteArticle"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除</a>\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '        </div>';
            }
            $("#article-show").empty();
            $("#article-show").append(html);
            //分页
            paging(result);
        },
        error: function (request) {
            var code = request.responseJSON.code;
            if(code == "0004"){
                layer.confirm('您还未登陆，现在去登陆？', {
                    btn: ['确定','取消'] //按钮
                }, function(){
                    layer.closeAll('dialog');
                    window.location.href="/logoreg";
                });
                return;
            }
            layer.msg(request.responseJSON.msg, {icon: 5, time: 1000,shift : 6})
        }
    })
}

function deleteArticle(id){
    $.ajax({
        url: "http://localhost:8080/article/delete/"+id,
        type: "DELETE",
        dataType: "json",
        async: false,
        success: function (result) {
            console.log(result)
            var code = result.code;
            if(code != '0001'){
                layer.msg(result.content, {icon: 5, time: 1000,shift : 6})
                return;
            }
            layer.msg("删除成功！", {icon: 6, time: 1000})
            getArticleListByType(saveType);
        },
        error: function (request) {
            var code = request.responseJSON.code;
            if(code == "0004"){
                layer.confirm('您还未登陆，现在去登陆？', {
                    btn: ['确定','取消'] //按钮
                }, function(){
                    layer.closeAll('dialog');
                    window.location.href="/logoreg";
                });
                return;
            }
            layer.msg(request.responseJSON.msg, {icon: 5, time: 1000,shift : 6})
        }
    })
}

function overhead(articleId,overhead){
    $.ajax({
        url: "http://localhost:8080/article/changeOverhead",
        type: "PUT",
        dataType: "json",
        data:{
          "article":articleId,
          "overhead":overhead
        },
        async: false,
        success: function (result) {
            console.log(result)
            var code = result.code;
            if(code != '0001'){
                layer.msg(result.content, {icon: 5, time: 1000,shift : 6})
                return;
            }
            layer.msg("操作成功！", {icon: 6, time: 1000})
            getArticleListByType(saveType);
        },
        error: function (request) {
            var code = request.responseJSON.code;
            if(code == "0004"){
                layer.confirm('您还未登陆，现在去登陆？', {
                    btn: ['确定','取消'] //按钮
                }, function(){
                    layer.closeAll('dialog');
                    window.location.href="/logoreg";
                });
                return;
            }
            layer.msg(request.responseJSON.msg, {icon: 5, time: 1000,shift : 6})
        }
    })
}

function changeType(articleId,type){
    $.ajax({
        url: "http://localhost:8080/article/changeType",
        type: "PUT",
        dataType: "json",
        data:{
            "article":articleId,
            "type":type
        },
        async: false,
        success: function (result) {
            console.log(result)
            var code = result.code;
            if(code != '0001'){
                layer.msg(result.content, {icon: 5, time: 1000,shift : 6})
                return;
            }
            layer.msg("操作成功！", {icon: 6, time: 1000})
            getArticleListByType(saveType);
        },
        error: function (request) {
            var code = request.responseJSON.code;
            if(code == "0004"){
                layer.confirm('您还未登陆，现在去登陆？', {
                    btn: ['确定','取消'] //按钮
                }, function(){
                    layer.closeAll('dialog');
                    window.location.href="/logoreg";
                });
                return;
            }
            layer.msg(request.responseJSON.msg, {icon: 5, time: 1000,shift : 6})
        }
    })
}