$(function () {

})
layui.use('layer',function(){
    var href = window.location.href.split("/");
    getUser(href[href.length-1]);
    getUserNewArticle(href[href.length-1]);
    getBlogId(href[href.length-1]);
})
function getUser(id){
    $.ajax({
        url: "http://localhost:8080/user/users/"+id,
        type: "GET",
        dataType: "json",
        async: false,
        success: function (result) {
            console.log(result)
            var code = result.code;
            if(code != '0001'){
                layer.msg(result.msg, {icon: 5, time: 1000,shift : 6})
                return;
            }
            var content = result.content;
            var html = '<img src="'+content.headurl+'" alt="'+content.nickname+'" class="img-circle">' +
                '                <div class="caption">' +
                '                    <h4 style="margin-bottom: 10px;text-align: center">'+content.nickname+'</h4>' +
                '                    <div class="row">' +
                '                        <div class="col-sm-4 col-md-4" style="text-align: center;"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;'+content.goodSum+'</div>' +
                '                        <div class="col-sm-4 col-md-4" style="text-align: center;"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>&nbsp;'+content.viewSum+'</div>' +
                '                        <div class="col-sm-4 col-md-4" style="text-align: center;"><span class="glyphicon glyphicon-comment" aria-hidden="true"></span>&nbsp;'+content.commentSum+'</div>' +
                '                    </div>' +
                '                </div>';
            $("#user-info").append(html)

        },
        error: function (request) {
            alert("Connection error");
        }
    })
}
function getUserNewArticle(userId){
    $.ajax({
        url: "http://localhost:8080/article/recent/"+userId,
        type: "GET",
        dataType: "json",
        async: false,
        success: function (result) {
            console.log(result)
            var code = result.code;
            if(code != '0001'){
                layer.msg(result.msg, {icon: 5, time: 1000,shift : 6})
                return;
            }
            var content = result.content.content;

            for(var i = 0;i<content.length;i++){
                var html = '<li class="list-group-item"><a href="/details/'+content[i].id+"/"+userId+'">'+content[i].title+'</a></li>';
                $("#article-new").append(html)
            }
        },
        error: function (request) {
            alert("Connection error");
        }
    })
}
function getBlogId(userId){
    $.ajax({
        url: "http://localhost:8080/blog/getByUserId?userId="+userId,
        type: "GET",
        dataType: "json",
        async: false,
        success: function (result) {
            console.log(result)
            var code = result.code;
            if(code != '0001'){
                layer.msg(result.msg, {icon: 5, time: 1000,shift : 6})
                return;
            }
            getAricleSort(result.content.id);
        },
        error: function (request) {
            alert("Connection error");
        }
    })
}

function getAricleSort(blogId){
    $.ajax({
        url: "http://localhost:8080/sort/sorts?blogId="+blogId,
        type: "GET",
        dataType: "json",
        async: false,
        success: function (result) {
            console.log(result)
            var code = result.code;
            if(code != '0001'){
                layer.msg(result.msg, {icon: 5, time: 1000,shift : 6})
                return;
            }
            var content = result.content;

            for(var i = 0;i<content.length;i++){
                var html = '<li class="list-group-item"><a href="">'+content[i].sortName+'</a></li>';
                $("#article-sort").append(html)
            }
        },
        error: function (request) {
            alert("Connection error");
        }
    })
}