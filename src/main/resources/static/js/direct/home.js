$(function () {

})
layui.use('layer',function(){
    var href = window.location.href.split("/");
    getUserArticles(href[href.length-1]);
})
function getUserArticles(id){
    $.ajax({
        url: "http://localhost:8080/article/user/"+id,
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
                var overhead = content[i].overhead;
                var html = '';
                if(overhead){
                    html += '<div class="list-group-item overhead">';
                }else{
                    html += '<div class="list-group-item">';
                }
                html += '<h3 class="list-group-item-heading"><a>'+content[i].title+'</a></h3>' +
                    '                        <p class="list-group-item-text">' +content[i].content+'</p>' +
                    '                        <div class="row">' +
                    '                            <div class="col-sm-8 col-md-9">' +
                    '                                <span>'+content[i].createtime+'</span>' +
                    '                            </div>' +
                    '                            <div class="col-sm-8 col-md-3">' +
                    '                                <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>' +content[i].goodTimes+
                    '                                <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>' +content[i].viewTimes+
                    '                                <span class="glyphicon glyphicon-comment" aria-hidden="true"></span>' +content[i].commentTimes+
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                    </div>';

                $("#article-list>div").append(html)
            }
        },
        error: function (request) {
            alert("Connection error");
        }
    })

}