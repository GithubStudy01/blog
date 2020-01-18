$(function () {

})
layui.use('layer',function(){
    var href = window.location.href.split("/");
    getArticle(href[href.length-2]);
})
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
