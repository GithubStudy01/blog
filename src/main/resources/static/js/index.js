var last = false;
var page = 0;
var size = 10;
$(function () {

    if(!last){
        getLeftData(page,size);
    }
    getHotArticle();
    getHotUser();
    getHotTag();

})
// 首页左侧按更新时间排序
function getLeftData(page,size){
    $.ajax({
        url: "http://localhost:8080/article/articles",
        type: "GET",
        data: {
            "page": page,
            "size": size
        },
        dataType: "json",
        async: false,
        success: function (result) {
            console.log(result)
            var code = result.code;
            if(code !='0001'){
                alert(result.msg)
                return;
            }
            var content = result.content;
            last = content.last;
            var resultContent = content.content;
            for(var i=0;i<resultContent.length;i++){
                var html = '<div class="list-group-item"><h3 class="list-group-item-heading">' +
                    '<a href="/details/'+resultContent[i].id+"/"+resultContent[i].user.id+'">'+resultContent[i].title+'</a></h3><p class="list-group-item-text">'+resultContent[i].content+'</p>' +
                    '<div class="row article-attribute"><div class="col-sm-8 col-md-9"><a href="/home/'+resultContent[i].id+'">' +
                    '<img src="'+resultContent[i].user.headurl+'" alt="'+resultContent[i].user.nickname+'" class="img-circle"></a>' +
                    '<span style="">'+resultContent[i].user.nickname+'</span><span style="">'+resultContent[i].createtime+'</span></div>' +
                    '<div class="col-sm-8 col-md-3"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>'+resultContent[i].goodTimes+
                    '<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>'+resultContent[i].viewTimes+
                    '<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>'+resultContent[i].commentTimes+
                    '</div></div></div>';
                $("#index-left .list-group").append(html);
            }
        },
        error: function (request) {
            alert("Connection error");
        }
    })
}


function getHotArticle(){
    $.ajax({
        url: "http://localhost:8080/article/hot",
        type: "GET",
        dataType: "json",
        async: false,
        success: function (result) {
            console.log(result)
            var code = result.code;
            if(code != '0001'){
                alert(result.msg)
                return;
            }
            var content = result.content.content;
            for(var i = 0;i<content.length;i++){
                var html = '<li class="list-group-item"><a href="/details/'+content[i].id+"/"+content[i].user.id+'">'+content[i].title+'</a></li>';
                $("#article-hot").append(html);
            }
        },
        error: function (request) {
            alert("Connection error");
        }
    })

}

function getHotUser(){
    $.ajax({
        url: "http://localhost:8080/user/hot",
        type: "GET",
        dataType: "json",
        async: false,
        success: function (result) {
            console.log(result)
            var code = result.code;
            if(code != '0001'){
                alert(result.msg)
                return;
            }
            var content = result.content.content;
            for(var i = 0;i<content.length;i++){
                var html = '<li class="list-group-item"><a href="/home/'+content[i].id+'">'+content[i].nickname+'</a></li>';
                $("#user-hot").append(html);
            }
        },
        error: function (request) {
            alert("Connection error");
        }
    })

}
function getHotTag(){
    $.ajax({
        url: "http://localhost:8080/tag/hot",
        type: "GET",
        dataType: "json",
        async: false,
        success: function (result) {
            console.log(result)
            var code = result.code;
            if(code != '0001'){
                alert(result.msg)
                return;
            }
            var content = result.content.content;
            for(var i = 0;i<content.length;i++){
                var html = '<li class="list-group-item"><a href="'+content[i].id+'">'+content[i].tagName+'</a></li>';
                $("#tag-hot").append(html);
            }
        },
        error: function (request) {
            alert("Connection error");
        }
    })

}