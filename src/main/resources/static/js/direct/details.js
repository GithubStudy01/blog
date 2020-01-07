$(function () {
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
                alert(result.msg)
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
            $("#article-content").append(content.content);

        },
        error: function (request) {
            alert("Connection error");
        }
    })
}
