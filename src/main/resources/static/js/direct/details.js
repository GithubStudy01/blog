$(function () {

})
layui.use('layer',function(){
    var href = window.location.href.split("/");
    var aid = href[href.length-2];
    getArticle(aid);
    getComment(aid,0)
    $("#save-articleId").attr("aid",aid);
    //查看回复
    $(".showReply").on("click",function(){
        var aid = $(this).attr("aid");
        var tid = $(this).attr("tid");
        showReplay(aid,tid,this);
    })

    bindReply();
})
//为回复按钮绑定事件
function bindReply(){
    $(".replyCommnet").on("click",function(){
        console.log("打開模態框")
        var tid = $(this).attr("tid");
        var commentid = $(this).attr("commentid");
        console.log(tid+","+commentid);
        var name = "";
        if(tid == 0){
            name = $(this).parent().prev().find("a span").text();
        }else{
            name = $(this).siblings("a").find("span").text();
        }
        console.log("name:"+name)
        $("#myModalLabel").text("回复 "+name);
        $('#replyBtn').attr("tid",tid);
        $("#replyBtn").attr("commentid",commentid);
        $('#myModal').modal();
    })
}


//模态框保存按钮
function modalSaveBtn(){
    var content = $("#modal-textarea").val();
    if(content == null || content.trim() == ''){
        layer.msg("请输入回复内容！", {icon: 5, time: 1000,shift : 6})
    }
    var aid = $("#save-articleId").attr("aid");
    var tid = $('#replyBtn').attr("tid");
    var commentid = $("#replyBtn").attr("commentid");
    writeComment(tid,commentid,content,aid);

}



function getArticle(id){
    $.ajax({
        url: "http://localhost:8080/article/articles/"+id,
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
            var html = '<h2>'+content.title +'</h2>' +
                '            <span>'+content.createtime+'</span>' +
                '            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>' +content.viewTimes+
                '                <a href="'+content.id+'"><span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>收藏</a><div>';
            var sort = content.sort;
            var sortHtml = '分类专栏:';
            for(var i=0;i<sort.length;i++) {
                sortHtml += '<a href="' + sort[i].id + '">' + sort[i].sortName + '</a></div>';
            }
            $("#article-info").append(html);
            $("#article-info").append(sortHtml);
            $("#article-content>textarea").text(content.content);
            //markdown语言
            var testEditor = editormd.markdownToHTML("article-content", {//注意：这里是上面DIV的id
                htmlDecode: "style,script,iframe",
                emoji: true,
                taskList: true,
                tocm: true,
                tex: true, // 默认不解析
                flowChart: true, // 默认不解析
                sequenceDiagram: true, // 默认不解析
                codeFold: true
            });
        },
        error: function (request) {
            alert("Connection error");
        }
    })
}
// 一级评论 tid=0
function getComment(articleId,tid){
    $.ajax({
        url: "http://localhost:8080/comment/comments",
        type: "GET",
        dataType: "json",
        data:{
            'articleId': articleId,
            'tid':tid
        },
        async: false,
        success: function (result) {
            console.log(result)
            var code = result.code;
            if(code != '0001'){
                layer.msg(result.msg, {icon: 5, time: 1000,shift : 6})
                return;
            }
            $("#comment-content").empty();
            var resultContent = result.content;
            var content = resultContent.content;
            var html = '';
            for(var i = 0;i<content.length;i++){
                html += '<div class="row">' +
                    '                            <div class="col-xs-1 col-sm-1 col-md-1">' +
                    '                                <a target="_blank" href="/home/'+content[i].user.id+'">' +
                    '                                    <img src="'+headBaseUrl+content[i].user.headurl+'"alt="'+content[i].user.nickname+'" class="img-circle"/>' +
                    '                                </a>\n' +
                    '                            </div>\n' +
                    '                            <div class="col-xs-11 col-sm-11 col-md-11">\n' +
                    '                                <div class="source">\n' +
                    '                                    <div>\n' +
                    '                                        <div>\n' +
                    '                                            <a target="_blank" href="/home/'+content[i].user.id+'">\n' +
                    '                                                <span class="name ">'+content[i].user.nickname+'</span>\n' +
                    '                                            </a>\n' +
                    '                                            <span class="date" title="'+content[i].createtime+'">'+content[i].createtime+'</span>\n' +
                    '                                        </div>\n' +
                    '                                        <div>\n' +
                    '                                            <a href="javascript:void(0)" class="replyCommnet" tid="'+content[i].tid+'" commentid="'+content[i].id+'">回复</a>\n';
                    if(content[i].replyCount > 0){
                        html += '<a href="javascript:void(0);" aId="'+articleId+'" tid="'+content[i].id+'" class="showReply">查看回复('+content[i].replyCount+')</a>';
                    }
                    html += '</div>\n' +
                    '                                    </div>\n' +
                    '                                    <div>\n' +
                    '                                        <span class="new-comment">'+content[i].reply+'</span>\n' +
                    '                                    </div>\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                        <hr>'

            }
            $("#comment-content").append(html);
            //分页
            paging(resultContent);
        },
        error: function (request) {
            alert("Connection error");
        }
    })
}
//查看回复 tid = 一级评论的id
function showReplay(articleId,tid,point){
    $.ajax({
        url: "http://localhost:8080/comment/replys",
        type: "GET",
        dataType: "json",
        data:{
            'articleId': articleId,
            'tid':tid
        },
        async: false,
        success: function (result) {
            console.log(result)
            var code = result.code;
            if(code != '0001'){
                layer.msg(result.msg, {icon: 5, time: 1000,shift : 6})
                return;
            }
            var resultContent = result.content;
            var html = '<div class="reply">';
            for(var i = 0;i<resultContent.length;i++){
                html +='<div class="row">' +
                    '       <div class="col-xs-1 col-sm-1 col-md-1">' +
                    '           <a href="/home/'+resultContent[i].user.id+'">' +
                    '               <img src="'+headBaseUrl+resultContent[i].user.headurl+'"alt="'+resultContent[i].user.nickname+'" class="img-circle" />' +
                    '           </a>' +
                    '       </div>' +
                    '       <div class="col-xs-11 col-sm-11 col-md-11">' +
                    '           <div>' +
                    '               <a target="_blank" href="/home/'+resultContent[i].user.id+'">' +
                    '                   <span class="name ">'+resultContent[i].user.nickname+'</span>' +
                    '               </a> 回复 '+resultContent[i].nickname+
                    '               <span class="date" title="'+resultContent[i].createtime+'" >'+resultContent[i].createtime+'</span>' +
                    '               <a href="javascript:void(0)" class="replyCommnet" tid="'+resultContent[i].tid+'" commentid="'+resultContent[i].id+'">回复</a>' +
                    '           </div>' +
                        '       <div>' +
                        '       <span>'+resultContent[i].reply+'</span>' +
                        '       </div>' +
                    '</div></div>';
            }
            html += '</div>';
            $(point).parents(".source").after(html);
            $(point).remove();
            bindReply();
        },
        error: function (request) {
            alert("Connection error");
        }
    })



}

//回复评论
function replyCommnet(){

}


//发表评论
function publishComment(){
    var content = $("#reply-content>textarea").val();
    var aid = $("#save-articleId").attr("aid");
    if(content == null||content.trim() == ''||aid == null){
        return;
    }
    writeComment(0,0,content,aid);
}


function writeComment(tid,cid,reply,articleId){
    $.ajax({
        url: "http://localhost:8080/comment/reply",
        type: "POST",
        dataType: "json",
        data:{
            'article.id': articleId,
            'tid':tid,
            'cid':cid,
            'reply':reply
        },
        async: false,
        success: function (result) {
            console.log(result)
            var code = result.code;
            if(code != '0001'){
                layer.msg(result.msg, {icon: 5, time: 1000,shift : 6})
                return;
            }

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
